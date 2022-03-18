package com.mx.spring.security.satoken.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.enums.OperationType;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.log.mapper.ISecurityLogMapper;
import com.mx.spring.dev.support.log.model.SecurityLog;
import com.mx.spring.dev.support.security.SaUtils;
import com.mx.spring.dev.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ISecurityLogMapper iOperationLogMapper;
    @Autowired
    private SaUtils saUtils;

    private SecurityLog createOperationLog(String title, JSONObject jsonObject, String methodName, String loginId) throws MxException {
        HttpServletRequest request = WebUtils.request();
        String userAgentStr = request.getHeader("user-agent");
        String userName = StringUtils.defaultIfBlank(WebUtils.request().getParameter("userName"),"管理员:id:"+loginId);
        // *========数据库日志=========*//
        return SecurityLog.builder()
                .id(IdUtil.fastSimpleUUID())
                .title(title)
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
                .className("com.mx.spring.security.controller.SecurityLoginController")
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","0");
        jsonObject.put("msg","登录成功");
        try {
            SecurityLog operationLog = createOperationLog("管理员登录",jsonObject, "login",Convert.toStr(loginId));
            iOperationLogMapper.insert(operationLog);
        } catch (MxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","0");
        jsonObject.put("msg","退出成功");
        try {
            SecurityLog operationLog = createOperationLog("管理员退出",jsonObject, "logout", Convert.toStr(loginId));
            iOperationLogMapper.insert(operationLog);
        } catch (MxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doLogoutByLoginId(String loginType, Object loginId, String tokenValue, String device) {

    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue, String device) {

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
