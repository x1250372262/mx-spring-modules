package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mx.spring.dev.annotation.FormatRequest;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.service.IPermissionService;
import com.mx.spring.security.vo.PermissionSelectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/permission")
@Validated
@Api(value = "权限模块", tags = "权限模块")
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;


    /**
     * 权限列表
     *
     * @return
     * @throws MxException
     */
    @GetMapping("/select")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "权限列表")
    public M<List<PermissionSelectVO>> list() throws MxException {
        return iPermissionService.list();
    }

}
