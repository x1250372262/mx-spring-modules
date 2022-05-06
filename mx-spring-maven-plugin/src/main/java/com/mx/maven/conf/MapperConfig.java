package com.mx.maven.conf;

import cn.hutool.setting.dialect.Props;
import com.mx.maven.util.PropUtils;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: mapper配置
 */
public class MapperConfig {

    /**
     * 是否生成mapper 默认false
     */
    private Boolean createMapper;

    /**
     * mapper接口包名
     */
    private String packageName;

    /**
     * mapper.xml路径
     */
    private String xmlPath;

    /**
     * mapper文件项目目录
     */
    private String mapperProjectPath;

    /**
     * mapper.xml文件项目目录 默认mapperProjectPath
     */
    private String xmlProjectPath;


    public static MapperConfig me() {
        return new MapperConfig();
    }

    public MapperConfig init(Props props) {
        this.createMapper = props.getBool(PropUtils.MAPPER_CREATE, false);
        this.packageName = props.getStr(PropUtils.MAPPER_PACK_NAME);
        this.xmlPath = props.getStr(PropUtils.MAPPER_XML_PATH);
        this.mapperProjectPath = props.getStr(PropUtils.MAPPER_PROJECT_PATH);
        this.xmlProjectPath = props.getStr(PropUtils.MAPPER_XML_PROJECT_PATH);
        return this;
    }

    public Boolean isCreateMapper() {
        return createMapper;
    }

    public void setCreateMapper(Boolean createMapper) {
        this.createMapper = createMapper;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getMapperProjectPath() {
        return mapperProjectPath;
    }

    public void setMapperProjectPath(String mapperProjectPath) {
        this.mapperProjectPath = mapperProjectPath;
    }

    public String getXmlProjectPath() {
        return xmlProjectPath;
    }

    public void setXmlProjectPath(String xmlProjectPath) {
        this.xmlProjectPath = xmlProjectPath;
    }
}
