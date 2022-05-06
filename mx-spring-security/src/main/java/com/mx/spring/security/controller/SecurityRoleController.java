package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.support.format.FormatRequest;
import com.mx.spring.dev.support.page.PageDTO;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.annotation.OperationLog;
import com.mx.spring.security.base.config.SecurityPermissionConfig;
import com.mx.spring.security.base.dto.SecurityRoleDTO;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.model.SecurityRole;
import com.mx.spring.security.base.vo.SecurityRoleListVO;
import com.mx.spring.security.base.vo.SecurityRoleVO;
import com.mx.spring.security.service.ISecurityRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/mx/security/role")
@Validated
@Api(value = "角色模块", tags = "角色模块")
public class SecurityRoleController {

    @Autowired
    private ISecurityRoleService iRoleService;


    /**
     * 角色列表
     *
     * @param pageDTO
     * @return
     * @throws MxException
     */
    @GetMapping("/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "角色列表")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_LIST)
    public View<Pages<SecurityRoleListVO>> list(@ApiParam(name = "name", value = "角色名称") String name,
                                                PageDTO<SecurityRole> pageDTO) throws MxException {
        return iRoleService.list(name, pageDTO.toBean());
    }

    /**
     * 添加角色
     *
     * @param roleDTO
     * @return
     * @throws MxException
     */
    @PostMapping("/create")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "添加角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_CREATE)
    @OperationLog(operationType = OperationType.CREATE, title = "添加角色")
    public Result create(@Validated SecurityRoleDTO roleDTO) throws MxException {
        return iRoleService.create(roleDTO.toBean());
    }

    /**
     * 修改角色
     *
     * @param id
     * @param roleDTO
     * @return
     * @throws MxException
     */
    @PostMapping("/update/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "修改角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_UPDATE)
    @OperationLog(operationType = OperationType.UPDATE, title = "修改角色")
    public Result update(@ApiParam(name = "id", value = "ID", required = true)
                    @PathVariable String id,
                         @ApiParam(name = "lastModifyTime", value = "最后修改时间(乐观锁)", required = true)
                    @NotNull(message = "最后修改时间(乐观锁)不能为空") Long lastModifyTime,
                         @Validated SecurityRoleDTO roleDTO) throws MxException {
        return iRoleService.update(id, lastModifyTime, roleDTO.toBean());
    }

    /**
     * 角色详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    @GetMapping("/detail/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "角色详情")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_DETAIL)
    public View<SecurityRoleVO> detail(@ApiParam(name = "id", value = "ID", required = true)
                                    @PathVariable String id) throws MxException {
        return iRoleService.detail(id);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    @PostMapping("/delete")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "删除角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_DELETE)
    @OperationLog(operationType = OperationType.DELETE, title = "删除角色")
    public Result delete(@ApiParam(name = "ids", value = "ids", required = true)
                    @NotNull(message = "ids不能为空")
                    @RequestParam("ids[]") String[] ids) throws MxException {
        return iRoleService.delete(ids);
    }


    /**
     * 角色权限列表
     *
     * @param id
     * @return
     * @throws MxException
     */
    @GetMapping("/permission/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "角色权限列表")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_PERMISSION_LIST)
    public Result permissionList(@ApiParam(name = "id", value = "id", required = true)
                            @NotBlank(message = "id不能为空") String id) throws MxException {
        return iRoleService.permissionList(id);
    }


    /**
     * 角色权限绑定
     *
     * @param id
     * @param permissions
     * @return
     * @throws MxException
     */
    @PostMapping("/permission/bind")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "角色权限绑定")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_ROLE_PERMISSION_BIND)
    @OperationLog(operationType = OperationType.OTHER, title = "角色权限绑定")
    public Result permissionBind(@ApiParam(name = "id", value = "id", required = true)
                            @NotBlank(message = "id不能为空") String id,
                                 @RequestParam(value = "permissions[]") String[] permissions) throws MxException {
        return iRoleService.permissionBind(id, permissions);
    }


}
