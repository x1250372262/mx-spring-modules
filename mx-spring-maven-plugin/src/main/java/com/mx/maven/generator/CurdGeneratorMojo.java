package com.mx.maven.generator;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import com.mx.maven.bean.Attr;
import com.mx.maven.conf.CurdConfig;
import com.mx.maven.util.PropUtils;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.utility.NullArgumentException;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import static org.fusesource.jansi.Ansi.Color.YELLOW;

/**
 * @program: mx-maven-plugin
 * @description:
 * @author: mengxiang
 * @create: 2021-09-02 11:04
 **/
@Mojo(name = "curd")
public class CurdGeneratorMojo extends BaseGeneratorMojo {

    private final Log log = LogFactory.get(CurdGeneratorMojo.class);

    private final Configuration freemarkerConfig;
    private final CurdConfig curdConfig;

    public CurdGeneratorMojo() {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfig.setClassForTemplateLoading(CurdGeneratorMojo.class, "/");
        freemarkerConfig.setDefaultEncoding("UTF-8");
        Props props = PropUtils.getProps();
        curdConfig = CurdConfig.me().init(props);
    }

    private ClassLoader getClassLoader() {
        try {
            // 所有的类路径环境，也可以直接用 compilePath
            List<String> classpathElements = new ArrayList<>();
            classpathElements.add(curdConfig.getEntityPath());
            classpathElements.add(curdConfig.getMapperPath());
            // 转为 URL 数组
            URL[] urls = new URL[classpathElements.size()];
            for (int i = 0; i < classpathElements.size(); ++i) {
                urls[i] = new File(classpathElements.get(i)).toURI().toURL();
            }
            // 自定义类加载器
            return new URLClassLoader(urls, this.getClass().getClassLoader());
        } catch (Exception e) {
            getLog().debug("Couldn't get the classloader.");
            return this.getClass().getClassLoader();
        }
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (StringUtils.isBlank(curdConfig.getEntityClass())) {
            throw new NullArgumentException(PropUtils.CURD_ENTITY_CLASS);
        }
        if (StringUtils.isBlank(curdConfig.getMapperClass())) {
            throw new NullArgumentException(PropUtils.CURD_MAPPER_CLASS);
        }
        Class<?> entityClass = null;
        Class<?> mapperClass = null;
        try {
            entityClass = getClassLoader().loadClass(curdConfig.getEntityClass());
            mapperClass = getClassLoader().loadClass(curdConfig.getMapperClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(entityClass == null || mapperClass == null){
            throw new RuntimeException("实体类或mapper加载失败");
        }
        Map<String, Object> propMap = buildPropMap();
        //实体类包名
        propMap.put("modelPackageName", entityClass.getPackage().getName());
        //mapper包名
        propMap.put("mapperPackageName", mapperClass.getPackage().getName());
        //实体类名称
        propMap.put("modelName", entityClass.getSimpleName());
        //mapper名称
        propMap.put("mapperName", mapperClass.getSimpleName());
        Map<String, Attr> attrMap = new HashMap<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            String comment = "";
            boolean nullable = true;
            if (fieldInfo != null) {
                comment = fieldInfo.comment();
                nullable = fieldInfo.nullable();
            }
            attrMap.put(field.getName(), new Attr(field.getType().getSimpleName(), field.getName(), null, null, comment, nullable));
        }
        //输出service
        //输出serviceImpl
        if (curdConfig.isCreateService()) {
            outService(propMap, attrMap, entityClass.getSimpleName());
        }
        //输出controller
        if (curdConfig.isCreateController()) {
            outController(propMap, entityClass.getSimpleName());
        }
        //输出dto bean
        outDTO(propMap, attrMap, entityClass.getSimpleName());
        outListDTO(propMap, attrMap, entityClass.getSimpleName());
        //输出vo
        outVO(propMap, attrMap, entityClass.getSimpleName());
        outListVO(propMap, attrMap, entityClass.getSimpleName());
        //输出page
        if (curdConfig.isCreatePage()) {
            outPage(propMap, attrMap, entityClass.getSimpleName());
        }
    }

    //输出vo
    private void outVO(Map<String, Object> propMap, Map<String, Attr> attrMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getVoProjectPath()) || StringUtils.isBlank(curdConfig.getVoName()) || StringUtils.isBlank(curdConfig.getDetailReturns())) {
            throw new RuntimeException("请检查vo设置");
        }
        String[] voFields = curdConfig.getDetailReturns().split("\\|");
        List<Attr> fieldsList = new ArrayList<>();
        for (String field : voFields) {
            fieldsList.add(attrMap.get(field));
        }
        propMap.put("fieldsList", fieldsList);
        propMap.put("list", false);
        outFile(curdConfig.getVoProjectPath(), modelName + "VO.java", "/generator/VO.ftl", propMap, curdConfig.getVoName());
    }

    //输出listvo
    private void outListVO(Map<String, Object> propMap, Map<String, Attr> attrMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getVoProjectPath()) || StringUtils.isBlank(curdConfig.getVoName()) || StringUtils.isBlank(curdConfig.getListReturns())) {
            throw new RuntimeException("请检查vo设置");
        }
        String[] voFields = curdConfig.getListReturns().split("\\|");
        List<Attr> fieldsList = new ArrayList<>();
        for (String field : voFields) {
            fieldsList.add(attrMap.get(field));
        }
        propMap.put("fieldsList", fieldsList);
        propMap.put("list", true);
        outFile(curdConfig.getVoProjectPath(), modelName + "ListVO.java", "/generator/VO.ftl", propMap, curdConfig.getVoName());
    }

    //输出dto和bean
    private void outDTO(Map<String, Object> propMap, Map<String, Attr> attrMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getDtoProjectPath()) || StringUtils.isBlank(curdConfig.getDtoName()) || StringUtils.isBlank(curdConfig.getBeanProjectPath()) || StringUtils.isBlank(curdConfig.getBeanName()) || StringUtils.isBlank(curdConfig.getOptionParams())) {
            throw new RuntimeException("请检查dto和bean设置");
        }
        String[] dtoFields = curdConfig.getOptionParams().split("\\|");
        List<Attr> fieldsList = new ArrayList<>();
        for (String field : dtoFields) {
            fieldsList.add(attrMap.get(field));
        }
        propMap.put("fieldsList", fieldsList);
        propMap.put("list", false);
        outFile(curdConfig.getDtoProjectPath(), modelName + "DTO.java", "/generator/DTO.ftl", propMap, curdConfig.getDtoName());
        outFile(curdConfig.getDtoProjectPath(), modelName + "Bean.java", "/generator/Bean.ftl", propMap, curdConfig.getBeanName());
    }

    //输出listDto和bean
    private void outListDTO(Map<String, Object> propMap, Map<String, Attr> attrMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getDtoProjectPath()) || StringUtils.isBlank(curdConfig.getDtoName()) || StringUtils.isBlank(curdConfig.getBeanProjectPath()) || StringUtils.isBlank(curdConfig.getBeanName()) || StringUtils.isBlank(curdConfig.getListParams())) {
            throw new RuntimeException("请检查dto和bean设置");
        }
        String[] dtoFields = curdConfig.getListParams().split("\\|");
        List<Attr> fieldsList = new ArrayList<>();
        for (String field : dtoFields) {
            fieldsList.add(attrMap.get(field));
        }
        propMap.put("fieldsList", fieldsList);
        propMap.put("list", true);
        outFile(curdConfig.getDtoProjectPath(), modelName + "ListDTO.java", "/generator/DTOL.ftl", propMap, curdConfig.getDtoName());
        outFile(curdConfig.getBeanProjectPath(), modelName + "ListBean.java", "/generator/Bean.ftl", propMap, curdConfig.getBeanName());
    }

    //输出service和impl
    private void outService(Map<String, Object> propMap, Map<String, Attr> attrMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getServiceProjectPath()) || StringUtils.isBlank(curdConfig.getServiceName())) {
            throw new RuntimeException("请检查service设置");
        }
        String[] listParams = curdConfig.getListParams().split("\\|");
        List<Attr> listParamsList = new ArrayList<>();
        for (String field : listParams) {
            listParamsList.add(attrMap.get(field));
        }
        propMap.put("listParamsList", listParamsList);
        outFile(curdConfig.getServiceProjectPath(), "I" + modelName + "Service.java", "/generator/service.ftl", propMap, curdConfig.getServiceName());
        outFile(curdConfig.getServiceProjectPath(), "impl" + File.separator + modelName + "ServiceImpl.java", "/generator/service-impl.ftl", propMap, curdConfig.getServiceName());
    }

    //输出controller
    private void outController(Map<String, Object> propMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getControllerName()) || StringUtils.isBlank(curdConfig.getControllerProjectPath())) {
            throw new RuntimeException("请检查controller设置");
        }
        outFile(curdConfig.getControllerProjectPath(), modelName + "Controller.java", "/generator/controller.ftl", propMap, curdConfig.getControllerName());
    }

    //输出页面
    private void outPage(Map<String, Object> propMap, Map<String, Attr> attrMap, String modelName) {
        if (StringUtils.isBlank(curdConfig.getPageProjectPath())) {
            throw new RuntimeException("请检查page设置");
        }
        String[] listReturns = curdConfig.getListReturns().split("\\|");
        String[] listParams = curdConfig.getListParams().split("\\|");
        String[] operationParams = curdConfig.getOptionParams().split("\\|");
        List<Attr> listReturnList = new ArrayList<>();
        List<Attr> listParamList = new ArrayList<>();
        List<Attr> operationParamList = new ArrayList<>();
        for (String field : listReturns) {
            listReturnList.add(attrMap.get(field));
        }
        for (String field : listParams) {
            listParamList.add(attrMap.get(field));
        }
        for (String field : operationParams) {
            operationParamList.add(attrMap.get(field));
        }
        modelName = modelName.substring(0, 1).toLowerCase() + modelName.substring(1);
        propMap.put("jsUrl", curdConfig.getJsUrl().concat("/").concat(modelName + ".js"));
        propMap.put("listReturnList", listReturnList);
        propMap.put("listParamList", listParamList);
        propMap.put("operationParamList", operationParamList);
        propMap.put("listUrl", modelName.concat("/").concat("list"));
        propMap.put("detailUrl", modelName.concat("/").concat("detail"));
        propMap.put("createUrl", modelName.concat("/").concat("create"));
        propMap.put("updateUrl", modelName.concat("/").concat("update"));
        propMap.put("deleteUrl", modelName.concat("/").concat("delete"));
        propMap.put("listUrlKey", modelName.toUpperCase(Locale.ROOT).concat("_").concat("LIST"));
        propMap.put("detailUrlKey", modelName.toUpperCase(Locale.ROOT).concat("_").concat("DETAIL"));
        propMap.put("createUrlKey", modelName.toUpperCase(Locale.ROOT).concat("_").concat("CREATE"));
        propMap.put("updateUrlKey", modelName.toUpperCase(Locale.ROOT).concat("_").concat("UPDATE"));
        propMap.put("deleteUrlKey", modelName.toUpperCase(Locale.ROOT).concat("_").concat("DELETE"));
        outFile(curdConfig.getPageProjectPath(), modelName + ".html", "/generator/page.ftl", propMap, null);
        outFile(curdConfig.getPageJsPath(), modelName + ".js", "/generator/js.ftl", propMap, null);
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
            String outPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java";
            File outputFile;
            if (targetFileName.endsWith(".java")) {
                outputFile = new File(outPath, new File(packageName.replace('.', '/'), targetFileName).getPath());
            } else {
                outputFile = new File(projectPath, targetFileName);
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
        //bean包名
        propMap.put("beanPackageName", curdConfig.getBeanName());
        //dto包名
        propMap.put("dtoPackageName", curdConfig.getDtoName());
        //vo包名
        propMap.put("voPackageName", curdConfig.getVoName());
        //Service包名
        propMap.put("servicePackageName", curdConfig.getServiceName());
        //控制器包名
        propMap.put("controllerPackageName", curdConfig.getControllerName());
        //实体类注释
        propMap.put("modelComment", curdConfig.getmodelComment());
        //是否检查版本
        propMap.put("isCheckVersion", curdConfig.isCheckVersion());
        //是否生成swagger
        propMap.put("isCreateSwagger", curdConfig.isCreateSwagger());
        //不能重复字段
        propMap.put("notSameField", curdConfig.getNotSameField());
        //不重复验证文字
        propMap.put("notSameText", curdConfig.getNotSameText());
        //#
        propMap.put("jing", "#");
        //<
        propMap.put("left", "<");
        //>
        propMap.put("right", ">");
        return propMap;
    }
}
