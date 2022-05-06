package com.mx.maven.conf;

import cn.hutool.setting.dialect.Props;
import com.mx.maven.util.PropUtils;
import freemarker.template.utility.NullArgumentException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: 实体类配置
 */
public class EntityConfig {

    /**
     * 数据库名称 不能为空
     */
    private String dbName;
    /**
     * 是否采用链式调用模式，默认为true
     */
    private Boolean useChainMode;
    /**
     * 是否直接覆盖实体类 默认true
     */
    private Boolean coverModel;
    /**
     * 数据库表名称前缀
     */
    private String tablePrefix;
    /**
     * 否剔除生成的实体映射表名前缀，默认为false
     */
    private Boolean removeTablePrefix;
    /**
     * 预生成实体的数据表名称列表，多个用'|'分隔，默认为空表示全部生成
     */
    private String tableList;
    /**
     * 排除的数据表名称列表，在此列表内的数据表将不被生成实体，多个用'|'分隔，默认为空
     */
    private String tableExcludeList;
    /**
     * 实体类包名，默认为: 默认主包名.model
     */
    private String packageName;
    /**
     * 生成的代码文件输出路径  跟packageName二选一就可以
     */
    private String outputPath;

    /**
     * 项目根目录
     */
    private String projectPath;


    public static EntityConfig me() {
        return new EntityConfig();
    }

    public EntityConfig init(Props props) {
        this.dbName = props.getStr(PropUtils.DB_NAME);
        if (StringUtils.isBlank(this.dbName)) {
            throw new NullArgumentException(PropUtils.DB_NAME);
        }
        this.useChainMode = props.getBool(PropUtils.USE_CHAIN_MODE, true);
        this.coverModel = props.getBool(PropUtils.COVER_MODEL, true);
        this.tablePrefix = props.getStr(PropUtils.TABLE_PREFIX);
        this.removeTablePrefix = props.getBool(PropUtils.REMOVE_TABLE_PREFIX, false);
        this.tableList = props.getStr(PropUtils.TABLE_LIST);
        this.tableExcludeList = props.getStr(PropUtils.TABLE_EXCLUDE_LIST);
        this.packageName = props.getStr(PropUtils.MODEL_PACKAGE_NAME);
        this.outputPath = props.getStr(PropUtils.OUTPUT_PATH);
        this.projectPath = props.getStr(PropUtils.PROJECT_PATH);
        return this;
    }


    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isUseChainMode() {
        return useChainMode;
    }

    public void setUseChainMode(boolean useChainMode) {
        this.useChainMode = useChainMode;
    }

    public boolean isCoverModel() {
        return coverModel;
    }

    public void setCoverModel(boolean coverModel) {
        this.coverModel = coverModel;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public boolean isRemoveTablePrefix() {
        return removeTablePrefix;
    }

    public void setRemoveTablePrefix(boolean removeTablePrefix) {
        this.removeTablePrefix = removeTablePrefix;
    }

    public String getTableList() {
        return tableList;
    }

    public void setTableList(String tableList) {
        this.tableList = tableList;
    }

    public String getTableExcludeList() {
        return tableExcludeList;
    }

    public void setTableExcludeList(String tableExcludeList) {
        this.tableExcludeList = tableExcludeList;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}
