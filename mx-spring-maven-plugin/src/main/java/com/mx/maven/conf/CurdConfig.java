package com.mx.maven.conf;

import cn.hutool.setting.dialect.Props;
import com.mx.maven.util.PropUtils;

/**
 * @program: mx-maven-plugin
 * @description: mapper配置
 * @author: mengxiang
 * @create: 2021-09-02 14:39
 **/
public class CurdConfig {


    /**
     * 实体类
     */
    private String entityClass;

    /**
     * 实体类注释
     */
    private String modelComment;

    /**
     * 实体类目录
     */
    private String entityPath;

    /**
     * mapper类
     */
    private String mapperClass;

    /**
     * mapper类位置
     */
    private String mapperPath;

    /**
     * 是否生成Service 默认false
     */
    private boolean createService;

    /**
     * Service包名
     */
    private String serviceName;

    /**
     * Service文件项目目录
     */
    private String serviceProjectPath;

    /**
     * 是否生成Controller 默认false
     */
    private boolean createController;

    /**
     * Controller包名
     */
    private String controllerName;

    /**
     * Controller文件项目目录
     */
    private String controllerProjectPath;

    /**
     * 是否生成Page 默认false
     */
    private boolean createPage;

    /**
     * Page文件项目目录
     */
    private String pageProjectPath;

    /**
     * js文件目录
     */
    private String pageJsPath;

    /**
     * 页面js引用路径
     */
    private String jsUrl;

    /**
     * bean包名
     */
    private String beanName;

    /**
     * bean文件目录
     */
    private String beanProjectPath;

    /**
     * dto包名
     */
    private String dtoName;

    /**
     * dto文件目录
     */
    private String dtoProjectPath;

    /**
     * vo包名
     */
    private String voName;

    /**
     * vo文件目录
     */
    private String voProjectPath;

    /**
     * 是否验证版本号
     */
    private boolean checkVersion;

    /**
     * 不能重复字段
     */
    private String notSameField;

    /**
     * 不重复验证文字
     */
    private String notSameText;

    /**
     * list入参参数对应dto 用|分割
     */
    private String listParams;

    /**
     * list出参参数对应vo 用|分割
     */
    private String listReturns;

    /**
     * 详情出参参数对应vo 用|分割
     */
    private String detailReturns;

    /**
     * 添加修改入参参数 对应dto 用|分割
     */
    private String optionParams;

    /**
     * 是否添加swagger注解
     */
    private boolean createSwagger;


    public static CurdConfig me() {
        return new CurdConfig();
    }

    public CurdConfig init(Props props) {
        this.modelComment = props.getStr(PropUtils.CURD_ENTITY_COMMENT);
        this.entityClass = props.getStr(PropUtils.CURD_ENTITY_CLASS);
        this.entityPath = props.getStr(PropUtils.CURD_ENTITY_PATH);
        this.mapperClass = props.getStr(PropUtils.CURD_MAPPER_CLASS);
        this.mapperPath = props.getStr(PropUtils.CURD_MAPPER_PATH);
        this.createService = props.getBool(PropUtils.CURD_SERVICE_CREATE, false);
        this.serviceName = props.getStr(PropUtils.CURD_SERVICE_PACK_NAME);
        this.serviceProjectPath = props.getStr(PropUtils.CURD_SERVICE_PROJECT_PATH);
        this.createController = props.getBool(PropUtils.CURD_CONTROLLER_CREATE, false);
        this.controllerName = props.getStr(PropUtils.CURD_CONTROLLER_PACK_NAME);
        this.controllerProjectPath = props.getStr(PropUtils.CURD_CONTROLLER_PROJECT_PATH);
        this.createPage = props.getBool(PropUtils.CURD_PAGE_CREATE, false);
        this.pageProjectPath = props.getStr(PropUtils.CURD_PAGE_PROJECT_PATH);
        this.pageJsPath = props.getStr(PropUtils.CURD_PAGE_JS_PATH);
        this.jsUrl = props.getStr(PropUtils.CURD_JS_URL);
        this.beanName = props.getStr(PropUtils.CURD_BEAN_PACK_NAME);
        this.beanProjectPath = props.getStr(PropUtils.CURD_BEAN_PROJECT_PATH);
        this.dtoName = props.getStr(PropUtils.CURD_DTO_PACK_NAME);
        this.dtoProjectPath = props.getStr(PropUtils.CURD_DTO_PROJECT_PATH);
        this.voName = props.getStr(PropUtils.CURD_VO_PACK_NAME);
        this.voProjectPath = props.getStr(PropUtils.CURD_VO_PROJECT_PATH);
        this.checkVersion = props.getBool(PropUtils.CURD_CHECK_VERSION, false);
        this.notSameField = props.getStr(PropUtils.CURD_NOT_SAME_FIELD,"我的相同字段");
        this.notSameText = props.getStr(PropUtils.CURD_NOT_SAME_TEXT,"我是相同字段描述");
        this.listParams = props.getStr(PropUtils.CURD_LIST_PARAMS);
        this.listReturns = props.getStr(PropUtils.CURD_LIST_RETURNS);
        this.detailReturns = props.getStr(PropUtils.CURD_DETAIL_RETURNS);
        this.optionParams = props.getStr(PropUtils.CURD_OPTION_PARAMS);
        this.createSwagger = props.getBool(PropUtils.CURD_CREATE_SWAGGER, false);
        return this;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getmodelComment() {
        return modelComment;
    }

    public void setmodelComment(String modelComment) {
        this.modelComment = modelComment;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public boolean isCreateService() {
        return createService;
    }

    public void setCreateService(boolean createService) {
        this.createService = createService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceProjectPath() {
        return serviceProjectPath;
    }

    public void setServiceProjectPath(String serviceProjectPath) {
        this.serviceProjectPath = serviceProjectPath;
    }

    public boolean isCreateController() {
        return createController;
    }

    public void setCreateController(boolean createController) {
        this.createController = createController;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getControllerProjectPath() {
        return controllerProjectPath;
    }

    public void setControllerProjectPath(String controllerProjectPath) {
        this.controllerProjectPath = controllerProjectPath;
    }

    public boolean isCreatePage() {
        return createPage;
    }

    public void setCreatePage(boolean createPage) {
        this.createPage = createPage;
    }

    public String getPageProjectPath() {
        return pageProjectPath;
    }

    public void setPageProjectPath(String pageProjectPath) {
        this.pageProjectPath = pageProjectPath;
    }

    public String getPageJsPath() {
        return pageJsPath;
    }

    public void setPageJsPath(String pageJsPath) {
        this.pageJsPath = pageJsPath;
    }

    public String getJsUrl() {
        return jsUrl;
    }

    public void setJsUrl(String jsUrl) {
        this.jsUrl = jsUrl;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanProjectPath() {
        return beanProjectPath;
    }

    public void setBeanProjectPath(String beanProjectPath) {
        this.beanProjectPath = beanProjectPath;
    }

    public String getDtoName() {
        return dtoName;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public String getDtoProjectPath() {
        return dtoProjectPath;
    }

    public void setDtoProjectPath(String dtoProjectPath) {
        this.dtoProjectPath = dtoProjectPath;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public String getVoProjectPath() {
        return voProjectPath;
    }

    public void setVoProjectPath(String voProjectPath) {
        this.voProjectPath = voProjectPath;
    }

    public boolean isCheckVersion() {
        return checkVersion;
    }

    public void setCheckVersion(boolean checkVersion) {
        this.checkVersion = checkVersion;
    }

    public String getNotSameField() {
        return notSameField;
    }

    public void setNotSameField(String notSameField) {
        this.notSameField = notSameField;
    }

    public String getNotSameText() {
        return notSameText;
    }

    public void setNotSameText(String notSameText) {
        this.notSameText = notSameText;
    }

    public String getListParams() {
        return listParams;
    }

    public void setListParams(String listParams) {
        this.listParams = listParams;
    }

    public String getListReturns() {
        return listReturns;
    }

    public void setListReturns(String listReturns) {
        this.listReturns = listReturns;
    }

    public String getDetailReturns() {
        return detailReturns;
    }

    public void setDetailReturns(String detailReturns) {
        this.detailReturns = detailReturns;
    }

    public String getOptionParams() {
        return optionParams;
    }

    public void setOptionParams(String optionParams) {
        this.optionParams = optionParams;
    }

    public boolean isCreateSwagger() {
        return createSwagger;
    }

    public void setCreateSwagger(boolean createSwagger) {
        this.createSwagger = createSwagger;
    }
}

