package com.mx.maven.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.mx.maven.bean.Attr;
import com.mx.maven.bean.ColumnInfo;
import com.mx.maven.bean.TableMeta;
import com.mx.maven.conf.EntityConfig;
import com.mx.maven.conf.MapperConfig;
import com.mx.maven.conf.MxConfig;
import com.mx.maven.util.DbUtils;
import com.mx.maven.util.ModelUtils;
import com.mx.maven.util.PropUtils;
import com.mx.spring.dev.constants.Constants;
import com.mx.spring.dev.support.log.MxLog;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.*;

import static com.mx.maven.constants.FileNameConstant.XML_FILE_SUFFIX;
import static com.mx.maven.constants.FtlTemplatesConstant.*;
import static com.mx.maven.constants.MapConstant.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: 实体类生成
 */
@Mojo(name = "entity")
public class EntityGeneratorMojo extends BaseGeneratorMojo {


    private final Configuration freemarkerConfig;
    private final MxConfig mxConfig;
    private final EntityConfig entityConfig;
    private final MapperConfig mapperConfig;

    public EntityGeneratorMojo() {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfig.setClassForTemplateLoading(EntityGeneratorMojo.class, "/");
        freemarkerConfig.setDefaultEncoding(Constants.DEFAULT_CHARSET);
        Props props = PropUtils.getProps();
        mxConfig = MxConfig.me().init(props);
        entityConfig = EntityConfig.me().init(props);
        mapperConfig = MapperConfig.me().init(props);
    }


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //表前缀集合
        String[] prefixs = StringUtils.split(StringUtils.defaultIfBlank(entityConfig.getTablePrefix(), ""), '|');
        //不生成的表
        List<String> tableExcludeList = Arrays.asList(StringUtils.split(StringUtils.defaultIfBlank(entityConfig.getTableExcludeList(), "").toLowerCase(), "|"));
        //生成的表
        List<String> tableList = Arrays.asList(StringUtils.split(StringUtils.defaultIfBlank(entityConfig.getTableList(), ""), "|"));
        if (CollUtil.isEmpty(tableList)) {
            try {
                tableList = DbUtils.getTableNames();
            } catch (Exception e) {
                throw new RuntimeException("check database", e);
            }
        }
        if (tableList == null) {
            return;
        }
        for (String tableName : tableList) {
            if (isTableExcludeList(tableExcludeList, tableName)) {
                buildModel(getTableMeta(tableName), tableName, prefixs, entityConfig.isRemoveTablePrefix());
            }
        }
    }

    /**
     * 获取数据表元数据描述对象
     *
     * @param tableName 表名称
     * @return 获取数据表元数据描述对象
     */
    private TableMeta getTableMeta(String tableName) {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, ColumnInfo> tableFields = new LinkedHashMap<>();
        List<String> pkFields = new LinkedList<>();
        TableMeta meta = new TableMeta(pkFields, tableFields);
        try {
            connection = DbUtils.connection();
            DatabaseMetaData dbMetaData = connection.getMetaData();
            out(YELLOW, "数据库名称:" + entityConfig.getDbName());
            out(YELLOW, "数据库用户名:" + mxConfig.getUserName());
            out(YELLOW, "数据库表名:" + tableName);
            resultSet = dbMetaData.getPrimaryKeys(entityConfig.getDbName(), dbMetaData.getUserName(), tableName);
            if (resultSet == null) {
                out(RED, tableName + "表没有设置主键,本次生成忽略");
                return null;
            } else {
                while (resultSet.next()) {
                    pkFields.add(resultSet.getString(4).toLowerCase());
                }
                if (pkFields.isEmpty()) {
                    out(RED, tableName + "表没有设置主键,本次生成忽略");
                    return null;
                }
            }
            out(GREEN, ">>> " + "列名称 / " + "列类型 / " + "是否是主键 / " + "备注 / " + "是否为空");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * FROM " + tableName + " ");
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            for (int idx = 1; idx <= rsMetaData.getColumnCount(); idx++) {
                // 获取字段元数据对象
                ResultSet column = dbMetaData.getColumns(entityConfig.getDbName(), dbMetaData.getUserName(), tableName, rsMetaData.getColumnName(idx));
                if (column.next()) {
                    boolean nullable = ResultSetMetaData.columnNullable == rsMetaData.isNullable(idx);
                    // 提取字段定义及字段默认值
                    tableFields.put(rsMetaData.getColumnName(idx).toLowerCase(), new ColumnInfo(rsMetaData.getColumnName(idx).toLowerCase(), rsMetaData.getColumnClassName(idx), column.getString("TYPE_NAME"), column.getString("REMARKS"), nullable));
                    out(GREEN, "--> " + rsMetaData.getColumnName(idx).toLowerCase() + "\t" + rsMetaData.getColumnClassName(idx) + "\t" + pkFields.contains(rsMetaData.getColumnName(idx).toLowerCase()) + "\t" + column.getString("REMARKS") + "\t" + nullable);
                }
                column.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MxLog.error("代码生成异常", e);
        } finally {
            close(statement, resultSet, connection);
        }
        return meta;
    }

    /**
     * 关闭数据库
     *
     * @param statement  Statement
     * @param resultSet  ResultSet
     * @param connection Connection
     */
    private void close(Statement statement, ResultSet resultSet, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                MxLog.error("关闭异常", e);
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                MxLog.error("关闭异常", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                MxLog.error("关闭异常", e);
            }
        }
    }


    /**
     * 检查是否可以生成表
     *
     * @param tableExcludeList 排序表集合
     * @param tableName        表名
     * @return 是否可以生成表
     */
    private boolean isTableExcludeList(List<String> tableExcludeList, String tableName) {
        if (!tableExcludeList.isEmpty()) {
            if (tableExcludeList.contains(tableName.toLowerCase())) {
                return false;
            } else {
                boolean flag = false;
                for (String excludedName : tableExcludeList) {
                    if (StringUtils.contains(excludedName, "*") && StringUtils.startsWithIgnoreCase(tableName, StringUtils.substringBefore(excludedName, "*"))) {
                        flag = true;
                        break;
                    }
                }
                return !flag;
            }
        }
        return true;
    }

    /**
     * 生成实体
     *
     * @param tableMeta      表元数据
     * @param tableName      表名
     * @param prefixes       前缀数组
     * @param isRemovePrefix 是否删除前缀
     */
    private void buildModel(TableMeta tableMeta, String tableName, String[] prefixes, boolean isRemovePrefix) {

        if (tableMeta != null) {
            //生成实体的参数
            Map<String, Object> propMap = buildPropMap();
            propMap.put(TABLE_NAME, tableName);
            String modelName = null;
            for (String prefix : prefixes) {
                if (tableName.startsWith(prefix)) {
                    if (isRemovePrefix) {
                        tableName = tableName.substring(prefix.length());
                    }
                    modelName = StringUtils.capitalize(ModelUtils.propertyNameToFieldName(tableName));
                    break;
                }
            }
            if (StringUtils.isBlank(modelName)) {
                modelName = StringUtils.capitalize(ModelUtils.propertyNameToFieldName(tableName));
            }
            propMap.put(MODEL_NAME, modelName);
            // 存放所有字段
            List<Attr> fieldList = new ArrayList<>();
            // 用于生成字段名称常量
            List<Attr> fieldConstantList = new ArrayList<>();
            if (tableMeta.getPkSet().size() > 1) {
                throw new RuntimeException("暂时不支持多主键");
            } else {
                propMap.put(IS_PRIMARY_KEY_IMPORT, 1);
                propMap.put(PRIMARY_KEY_IMPORT, "");
                propMap.put(PRIMARY_KEY_TYPE, tableMeta.getFieldMap().get(tableMeta.getPkSet().get(0)).getColumnType());
                propMap.put(PRIMARY_KEY_NAME, StringUtils.uncapitalize(ModelUtils.propertyNameToFieldName(tableMeta.getPkSet().get(0))));
                for (String key : tableMeta.getFieldMap().keySet()) {
                    ColumnInfo ci = tableMeta.getFieldMap().get(key);
                    Attr attr = ci.toAttr();
                    fieldList.add(attr);
                    fieldConstantList.add(new Attr("String", ci.getColumnName().toUpperCase(), ci.getColumnName(), ci.getJdbcType(), ci.getComment(), ci.isNullable()));
                }
            }
            propMap.put(FIELD_LIST, fieldList);
            // 为必免构造方法重复，构造参数数量相同则清空
            propMap.put(FIELD_CONSTANT_LIST, fieldConstantList);
            outFile(entityConfig.getProjectPath(), createEntityJavaFileName(modelName), ENTITY, propMap, entityConfig.getPackageName());
            if (mapperConfig.isCreateMapper()) {
                outFile(mapperConfig.getMapperProjectPath(), createMapperJavaFileName(modelName), MAPPER_JAVA, propMap, mapperConfig.getPackageName());
                outFile(StringUtils.defaultIfBlank(mapperConfig.getXmlProjectPath(), mapperConfig.getMapperProjectPath()), createMapperXmlFileName(modelName), MAPPER_XML, propMap, mapperConfig.getXmlPath());
            }

        }
    }

    /**
     * 输出文件
     *
     * @param projectPath    项目根目录
     * @param targetFileName 文件名称
     * @param tmplFile       模板名称
     * @param propMap        参数
     * @param packageName    包名
     */
    private void outFile(String projectPath, String targetFileName, String tmplFile, Map<String, Object> propMap, String packageName) {
        Writer outWriter = null;
        try {
            String outPath = "${root}";
            if (entityConfig.isCoverModel()) {
                if (StringUtils.isNotBlank(projectPath)) {
                    outPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java";
                } else {
                    outPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java";
                }
            } else {
                if (StringUtils.isNotBlank(entityConfig.getOutputPath())) {
                    outPath = entityConfig.getOutputPath();
                }

            }
            File outputFile = new File(outPath, new File(packageName.replace(StrUtil.DOT, StrUtil.SLASH), targetFileName).getPath());
            if (targetFileName.endsWith(XML_FILE_SUFFIX)) {
                String xmlProjectPath = projectPath;
                if (StringUtils.isBlank(xmlProjectPath)) {
                    xmlProjectPath = System.getProperty("user.dir");
                }
                outputFile = new File(xmlProjectPath, "src" + File.separator + "main" + File.separator + "resources");
                outputFile = new File(outputFile, new File(packageName.replace(StrUtil.DOT, StrUtil.SLASH), targetFileName).getPath());
            }
            File path = outputFile.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            Template template = freemarkerConfig.getTemplate(tmplFile);
            outWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(outputFile.toPath()), StringUtils.defaultIfEmpty(freemarkerConfig.getOutputEncoding(), freemarkerConfig.getDefaultEncoding())));
            template.process(propMap, outWriter);
            out(YELLOW, "输出路径" + outputFile);
        } catch (Exception e) {
            MxLog.error("输出文件异常", e);
        } finally {
            if (outWriter != null) {
                try {
                    outWriter.flush();
                    outWriter.close();
                } catch (IOException e) {
                    MxLog.error("关闭输出流异常", e);
                }
            }
        }
    }


    /**
     * 通用参数
     *
     * @return 通用参数map
     */
    private Map<String, Object> buildPropMap() {
        Map<String, Object> propMap = baseProp();
        //实体类包名
        propMap.put(MODEL_PACKAGE_NAME, entityConfig.getPackageName());
        propMap.put(MAPPER_PACKAGE_NAME, mapperConfig.getPackageName());
        propMap.put(IS_USE_CHAIN_MODE, entityConfig.isUseChainMode());
        //#
        propMap.put(JING, "#");
        //{
        propMap.put(LEFT, "{");
        //}
        propMap.put(RIGHT, "}");
        return propMap;
    }
}
