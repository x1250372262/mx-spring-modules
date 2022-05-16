package com.mx.spring.security.satoken.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.WebUtil;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.model.SecurityOperationLog;
import com.mx.spring.security.controller.SecurityLoginController;
import com.mx.spring.security.event.OperationLogEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: mengxiang.
 * @create: 2021-10-22 16:52
 * @Description:
 */
@Component
public class MxSaTokenListener implements SaTokenListener {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MxSecurityConfig config;

    private SecurityOperationLog createOperationLog(String title, JSONObject jsonObject, String methodName, String loginId) throws MxException {
        HttpServletRequest request = WebUtil.request();
        String userAgentStr = request.getHeader("user-agent");
        String userName = StringUtils.defaultIfBlank(WebUtil.request().getParameter("userName"), "管理员:id:" + loginId);
        // *========数据库日志=========*//
        return SecurityOperationLog.builder()
                .id(IdUtil.fastSimpleUUID())
                .title(title)
                .client(config.getClient())
                .type(OperationType.LOGIN.name())
                .typeName(OperationType.LOGIN.value())
                .userId(loginId)
                .userName(userName)
                .createTime(System.currentTimeMillis())
                .requestUrl(request.getRequestURI())
                .requestParam(JSONObject.toJSONString(ServletUtil.getParams(request)))
                .returnCode(jsonObject.getString("code"))
                .returnMessage(jsonObject.getString("msg"))
                .returnResult(jsonObject.toJSONString())
                .className(SecurityLoginController.class.getName())
                .methodName(methodName)
                .ip(ServletUtil.getClientIP(request))
                //位置之后再说把
                .location("")
                .os(UserAgentUtil.parse(userAgentStr).getOs().toString())
                .browser(UserAgentUtil.parse(userAgentStr).getBrowser().toString())
                .build();
    }


    @Override
    public void doLogin(String loginType, Object loginId, SaLoginModel loginModel) {
        if(config.isOpenLog()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "0");
            jsonObject.put("msg", "登录成功");
            try {
                SecurityOperationLog securityOperationLog = createOperationLog("管理员登录", jsonObject, "login", Convert.toStr(loginId));
                // 保存数据库
                applicationContext.publishEvent(new OperationLogEvent(securityOperationLog));
            } catch (MxException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        if(config.isOpenLog()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "0");
            jsonObject.put("msg", "退出成功");
            try {
                SecurityOperationLog securityOperationLog = createOperationLog("管理员退出", jsonObject, "logout", Convert.toStr(loginId));
                // 保存数据库
                applicationContext.publishEvent(new OperationLogEvent(securityOperationLog));
            } catch (MxException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doKickout(String s, Object o, String s1) {

    }

    @Override
    public void doReplaced(String s, Object o, String s1) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }
}
