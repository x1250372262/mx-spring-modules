package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.support.format.FormatRequest;
import com.mx.spring.security.annotation.OperationLog;
import com.mx.spring.security.base.bean.LoginResult;
import com.mx.spring.security.base.dto.SecurityLoginInfoDTO;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.vo.SecurityLoginVO;
import com.mx.spring.security.service.ISecurityLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;


/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/mx/security/login")
@Validated
@Api(value = "登录模块", tags = "登录模块")
public class SecurityLoginController {

    @Autowired
    private ISecurityLoginService iSecurityLoginService;

    /**
     * 管理员登录
     *
     * @param userName
     * @param password
     * @return
     * @throws MxException
     */
    @PostMapping("/login")
    @FormatRequest
    @ApiOperation(value = "管理员登录")
    public MxView<LoginResult> login(@ApiParam(name = "userName", value = "用户名", required = true)
                                   @NotBlank(message = "用户名不能为空") String userName,
                                     @ApiParam(name = "password", value = "密码(前端需要自行MD5加密一次)", required = true)
                                   @NotBlank(message = "密码不能为空") String password) throws MxException {
        return iSecurityLoginService.login(userName, password);
    }

    /**
     * 管理员退出
     *
     * @return
     * @throws MxException
     */
    @PostMapping("/logout")
    @FormatRequest
    @ApiOperation(value = "管理员退出")
    public MxResult logout() throws MxException {
        return iSecurityLoginService.logout();
    }

    /**
     * 管理员信息
     *
     * @return
     * @throws MxException
     */
    @GetMapping("/info")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "管理员信息")
    public MxView<SecurityLoginVO> info() throws MxException {
        return MxView.ok(iSecurityLoginService.info());
    }

    @SaCheckLogin
    @FormatRequest
    @PostMapping("/update")
    @ApiOperation(value = "修改管理员信息")
    @OperationLog(operationType = OperationType.UPDATE, title = "修改管理员信息")
    public MxResult update(@Validated SecurityLoginInfoDTO securityLoginInfoDTO) throws Exception {
        return iSecurityLoginService.update(securityLoginInfoDTO.toBean());
    }


    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param rePassword
     * @return
     * @throws Exception
     */
    @SaCheckLogin
    @FormatRequest
    @PostMapping("/password")
    @ApiOperation(value = "修改管理员密码")
    @OperationLog(operationType = OperationType.UPDATE, title = "修改管理员密码")
    public MxResult password(@ApiParam(name = "oldPassword", value = "旧密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "旧密码不能为空") String oldPassword, @ApiParam(name = "newPassword", value = "新密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "新密码不能为空") String newPassword, @ApiParam(name = "rePassword", value = "确认密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "确认密码不能为空") String rePassword) throws Exception {
        return iSecurityLoginService.password(oldPassword, newPassword, rePassword);
    }


}
