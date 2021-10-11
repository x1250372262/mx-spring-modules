package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mx.spring.dev.annotation.FormatRequest;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.annotation.Log;
import com.mx.spring.security.annotation.UniqueSubmit;
import com.mx.spring.security.bean.Token;
import com.mx.spring.security.dto.AdminDTO;
import com.mx.spring.security.enums.OperationType;
import com.mx.spring.security.service.IAdminService;
import com.mx.spring.security.vo.AdminVO;
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
 * @Author: 徐建鹏.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/admin")
@Validated
@Api(value = "管理员模块", tags = "管理员模块")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

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
    @Log(operationType = OperationType.LOGIN, title = "管理员登录")
    public M<Token> login(@ApiParam(name = "userName", value = "用户名", required = true) @NotBlank(message = "用户名不能为空") String userName, @ApiParam(name = "password", value = "密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "密码不能为空") String password) throws MxException {
        return iAdminService.login(userName, password);
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
    @Log(operationType = OperationType.LOGIN, title = "管理员退出")
    public R logout() throws MxException {
        String adminId = SaUtils.loginId();
        StpUtil.logout();
        return R.ok().attr("adminId", adminId);
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
//    @SaCheckRole(value = {"super-admin", "测试角色"}, mode = SaMode.OR)
    @ApiOperation(value = "管理员信息")
//    @SaCheckPermission(value = PermissionConfig.ADMIN_INFO)
    public M<AdminVO> info() throws MxException {
        return M.ok(iAdminService.info());
    }

    @SaCheckLogin
    @FormatRequest
    @PostMapping("/update")
    @Log(operationType = OperationType.UPDATE, title = "修改管理员信息")
    @UniqueSubmit(retType = UniqueSubmit.RetType.R)
    public R update(@Validated AdminDTO adminDTO) throws Exception {
        return iAdminService.update(adminDTO.toBean());
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
    @Log(operationType = OperationType.UPDATE, title = "修改管理员密码")
    @UniqueSubmit(retType = UniqueSubmit.RetType.R)
    public R password(@ApiParam(name = "oldPassword", value = "旧密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "旧密码不能为空") String oldPassword, @ApiParam(name = "newPassword", value = "新密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "新密码不能为空") String newPassword, @ApiParam(name = "rePassword", value = "确认密码(前端需要自行MD5加密一次)", required = true) @NotBlank(message = "确认密码不能为空") String rePassword) throws Exception {
        return iAdminService.password(oldPassword, newPassword, rePassword);
    }


}
