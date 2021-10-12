package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.mx.spring.dev.annotation.FormatRequest;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.dto.PageDTO;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.config.SPConfig;
import com.mx.spring.security.dto.RoleDTO;
import com.mx.spring.security.model.Role;
import com.mx.spring.security.service.IRoleService;
import com.mx.spring.security.vo.RoleListVO;
import com.mx.spring.security.vo.RoleVO;
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
public class RoleController {

    @Autowired
    private IRoleService iRoleService;


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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_LIST)
    public M<Pages<RoleListVO>> list(@ApiParam(name = "name", value = "角色名称") String name,
                                     PageDTO<Role> pageDTO) throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_CREATE)
    public R create(@Validated RoleDTO roleDTO) throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_UPDATE)
    public R update(@ApiParam(name = "id", value = "ID", required = true)
                    @PathVariable String id,
                    @ApiParam(name = "lastModifyTime", value = "最后修改时间(乐观锁)", required = true)
                    @NotNull(message = "最后修改时间(乐观锁)不能为空") Long lastModifyTime,
                    @Validated RoleDTO roleDTO) throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_DETAIL)
    public M<RoleVO> detail(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_DELETE)
    public R delete(@ApiParam(name = "ids", value = "ids", required = true)
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_PERMISSION_LIST)
    public R permissionList(@ApiParam(name = "id", value = "id", required = true)
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_ROLE_PERMISSION_BIND)
    public R permissionBind(@ApiParam(name = "id", value = "id", required = true)
                            @NotBlank(message = "id不能为空") String id,
                            @RequestParam(value = "permissions[]") String[] permissions) throws MxException {
        return iRoleService.permissionBind(id, permissions);
    }


}
