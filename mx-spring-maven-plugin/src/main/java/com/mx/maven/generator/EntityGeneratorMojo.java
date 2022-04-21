package com.mx.maven.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
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
import com.mx.spring.dev.support.log.MxLog;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.*;
import java.sql.*;
import java.util.*;

import static com.mx.maven.constant.FileNameConstants.XML_FILE_SUFFIX;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * @program: mx-maven-plugin
 * @description:
 * @author: mengxiang
 * @create: 2021-09-02 11:04
 **/
@Mojo(name = "entity")
public class EntityGeneratorMojo extends BaseGeneratorMojo {

    private final Log log = LogFactory.get(EntityGeneratorMojo.class);

    private final Configuration freemarkerConfig;
    private final MxConfig mxConfig;
    private final EntityConfig entityConfig;
    private final MapperConfig mapperConfig;

    public EntityGeneratorMojo() {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfig.setClassForTemplateLoading(EntityGeneratorMojo.class, "/");
        freemarkerConfig.setDefaultEncoding("UTF-8");
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
            out(YELLOW, "数据库表明:" + tableName);
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
            MxLog.error("代码生成异常",e);
        } finally {
            close(statement, resultSet, connection);
        }
        return meta;
    }

    /**
     * 关闭数据库信息
     */
    private void close(Statement statement, ResultSet resultSet, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.warn("", e);
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.warn("", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.warn("", e);
            }
        }
    }


    /**
     * 检查是否可以生成表
     *
     * @param tableExcludeList
     * @param tableName
     * @return
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
                if (flag) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 创建实体
     *
     * @param tableMeta
     * @param tableName
     * @param prefixs
     * @param isRemovePrefix
     */
    private void buildModel(TableMeta tableMeta, String tableName, String[] prefixs, boolean isRemovePrefix) {

        if (tableMeta != null) {
            //生成实体的参数
            Map<String, Object> propMap = buildPropMap();
            propMap.put("tableName", tableName);
            String modelName = null;
            for (String prefix : prefixs) {
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
            propMap.put("modelName", modelName);
            // 存放所有字段
            List<Attr> fieldList = new ArrayList<>();
            // 用于生成字段名称常量
            List<Attr> fieldConstantList = new ArrayList<>();
            if (tableMeta.getPkSet().size() > 1) {
                throw new RuntimeException("暂时不支持多主键");
            } else {
                propMap.put("isPrimaryKeyImport", 1);
                propMap.put("primaryKeyImport", "");
                propMap.put("primaryKeyType", tableMeta.getFieldMap().get(tableMeta.getPkSet().get(0)).getColumnType());
                propMap.put("primaryKeyName", StringUtils.uncapitalize(ModelUtils.propertyNameToFieldName(tableMeta.getPkSet().get(0))));
                for (String key : tableMeta.getFieldMap().keySet()) {
                    ColumnInfo ci = tableMeta.getFieldMap().get(key);
                    Attr attr = ci.toAttr();
                    fieldList.add(attr);
                    fieldConstantList.add(new Attr("String", ci.getColumnName().toUpperCase(), ci.getColumnName(), ci.getJdbcType(), ci.getComment(), ci.isNullable()));
                }
            }
            propMap.put("fieldList", fieldList);
            // 为必免构造方法重复，构造参数数量相同则清空
            propMap.put("fieldConstantList", fieldConstantList);
            //
            outFile(entityConfig.getProjectPath(), modelName + ".java", "/generator/Entity.ftl", propMap, entityConfig.getPackageName());
            if (mapperConfig.isCreateMapper()) {
                outFile(mapperConfig.getMapperProjectPath(), "I" + StringUtils.capitalize(modelName) + "Mapper.java", "/generator/mapper.ftl", propMap, mapperConfig.getPackageName());
                outFile(StringUtils.defaultIfBlank(mapperConfig.getXmlProjectPath(), mapperConfig.getMapperProjectPath()), modelName + "Mapper.xml", "/generator/mapperXml.ftl", propMap, mapperConfig.getXmlPath());
            }

        }
    }

    /**
     * 输出文件
     *
     * @param targetFileName
     * @param tmplFile
     * @param propMap
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
            File outputFile = new File(outPath, new File(packageName.replace('.', '/'), targetFileName).getPath());
            if (targetFileName.endsWith(XML_FILE_SUFFIX)) {
                String xmlProjectPath = projectPath;
                if (StringUtils.isBlank(xmlProjectPath)) {
                    xmlProjectPath = System.getProperty("user.dir");
                }
                outputFile = new File(xmlProjectPath, "src" + File.separator + "main" + File.separator + "resources");
                outputFile = new File(outputFile, new File(packageName.replace('.', '/'), targetFileName).getPath());
            }
            File path = outputFile.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            Template template = freemarkerConfig.getTemplate(tmplFile);
            outWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StringUtils.defaultIfEmpty(freemarkerConfig.getOutputEncoding(), freemarkerConfig.getDefaultEncoding())));
            template.process(propMap, outWriter);
            out(YELLOW, "输出路径" + outputFile);
        } catch (Exception e) {
            log.warn("", e);
        } finally {
            if (outWriter != null) {
                try {
                    outWriter.flush();
                    outWriter.close();
                } catch (IOException e) {
                    log.warn("", e);
                }
            }
        }
    }


    /**
     * 创建ftl参数集合
     *
     * @return
     */
    private Map<String, Object> buildPropMap() {
        Map<String, Object> propMap = baseProp();
        //实体类包名
        propMap.put("modelPackageName", entityConfig.getPackageName());
        propMap.put("mapperPackageName", mapperConfig.getPackageName());
        propMap.put("isUseChainMode", entityConfig.isUseChainMode());
        //#
        propMap.put("jing", "#");
        //{
        propMap.put("left", "{");
        //}
        propMap.put("right", "}");
        return propMap;
    }
}
