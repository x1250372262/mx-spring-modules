package com.mx.spring.dev.resturl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.log.MxLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: 徐建鹏.
 * @create: 2022-03-25 20:54
 * @Description:
 */
@Component
public class RestUrlUtils {

    private volatile static List<RestUrl> REST_URL_LIST;

    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void printList() {
        if (CollUtil.isEmpty(REST_URL_LIST)) {
            list();
        }
        if (CollUtil.isEmpty(REST_URL_LIST)) {
            return;
        }
        String str = "请求url:{}====》请求方法:{}====》java方法信息:{}====》返回类型:{}";
        MxLog.info("============开始输出接口信息============");
        for (RestUrl restUrl : REST_URL_LIST) {
            MxLog.info(StrUtil.format(str, restUrl.getRequestUrl(), restUrl.getRequestMethod(), restUrl.getJavaMethodInfo(), restUrl.getReturnType()));
        }
        MxLog.info("============结束输出接口信息============");
    }


    public void list() {
        System.out.println("11111");
        if (REST_URL_LIST == null) {
            synchronized (RestUrlUtils.class) {
                if (REST_URL_LIST == null) {
                    REST_URL_LIST = new ArrayList<>();
                    RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
                    Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                    for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {

                        RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
                        RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
                        PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                        HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

                        // Spring通过BasicErrorController进行统一的异常处理,不计入这些API
                        if ("class org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController".equals(mappingInfoValue.getBeanType().toString())) {
                            continue;
                        }

                        // 请求路径
                        String requestUrl = Objects.requireNonNull(patternsCondition).getPatterns().toString();
                        // 请求类型
                        String requestMethod = methodCondition.getMethods().toString();

                        String javaMethodInfo = mappingInfoValue.toString();

                        String returnType = mappingInfoValue.getMethod().getReturnType().toString();

                        RestUrl restUrl = new RestUrl(requestUrl, requestMethod, javaMethodInfo, returnType);
                        REST_URL_LIST.add(restUrl);
                    }
                }

            }
        }
    }
}
