package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.support.format.FormatRequest;
import com.mx.spring.dev.support.page.PageDTO;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.annotation.OperationLog;
import com.mx.spring.security.base.config.SecurityPermissionConfig;
import com.mx.spring.security.base.dto.SecurityMenuDTO;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.vo.SecurityMenuListVO;
import com.mx.spring.security.base.vo.SecurityMenuNavVO;
import com.mx.spring.security.base.vo.SecurityMenuRoleVO;
import com.mx.spring.security.base.vo.SecurityMenuVO;
import com.mx.spring.security.service.ISecurityMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/mx/security/menu")
@Validated
@Api(value = "菜单模块", tags = "菜单模块")
public class SecurityMenuController {

    @Autowired
    private ISecurityMenuService iMenuService;


    /**
     * 左侧导航栏
     *
     * @return
     * @throws MxException
     */
    @GetMapping("/nav")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "菜单导航栏")
    public MxView<List<SecurityMenuNavVO>> nav() throws MxException {
        return iMenuService.nav();
    }


    /**
     * 菜单列表
     *
     * @return
     * @throws MxException
     */
    @GetMapping("/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "菜单列表")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_LIST)
    public MxView<List<SecurityMenuListVO>> list() throws MxException {
        return iMenuService.list();
    }

    /**
     * 添加菜单
     *
     * @param menuDTO
     * @return
     * @throws MxException
     */
    @PostMapping("/create")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "添加菜单")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_CREATE)
    @OperationLog(operationType = OperationType.CREATE, title = "添加菜单")
    public MxResult create(@Validated SecurityMenuDTO menuDTO) throws MxException {
        return iMenuService.create(menuDTO.toBean());
    }

    /**
     * 修改菜单
     *
     * @param id
     * @param menuDTO
     * @return
     * @throws MxException
     */
    @PostMapping("/update")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "修改菜单")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_UPDATE)
    @OperationLog(operationType = OperationType.UPDATE, title = "修改菜单")
    public MxResult update(@ApiParam(name = "id", value = "ID", required = true)
                         @NotBlank(message = "ID不能为空") String id,
                           @Validated SecurityMenuDTO menuDTO) throws MxException {
        return iMenuService.update(id, menuDTO.toBean());
    }

    /**
     * 菜单详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    @GetMapping("/detail")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "菜单详情")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_DETAIL)
    public MxView<SecurityMenuVO> detail(@ApiParam(name = "id", value = "ID", required = true)
                                       @NotBlank(message = "ID不能为空") String id) throws MxException {
        return iMenuService.detail(id);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     * @throws MxException
     */
    @PostMapping("/delete")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "删除菜单")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_DELETE)
    @OperationLog(operationType = OperationType.DELETE, title = "删除菜单")
    public MxResult delete(@ApiParam(name = "id", value = "ID", required = true)
                         @NotBlank(message = "ID不能为空") String id) throws MxException {
        return iMenuService.delete(id);
    }

    /**
     * 菜单角色列表
     *
     * @param menuId
     * @param name
     * @param pageDTO
     * @return
     * @throws MxException
     */
    @GetMapping("/role/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "菜单角色列表")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_ROLE_LIST)
    public MxView<Pages<SecurityMenuRoleVO>> roleList(@ApiParam(name = "menuId", value = "菜单ID", required = true)
                                                    @NotBlank(message = "菜单ID不能为空") String menuId,
                                                      @ApiParam(name = "name", value = "角色名称") String name,
                                                      PageDTO<SecurityMenuRoleVO> pageDTO) throws MxException {
        return iMenuService.roleList(menuId, name, pageDTO.toBean());
    }

    /**
     * 添加菜单角色
     *
     * @param menuId
     * @param menuId
     * @return
     * @throws MxException
     */
    @PostMapping("/role/create")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "添加菜单角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_ROLE_CREATE)
    @OperationLog(operationType = OperationType.CREATE, title = "添加菜单角色")
    public MxResult roleCreate(@ApiParam(name = "menuId", value = "菜单ID", required = true)
                             @NotBlank(message = "菜单ID不能为空") String menuId,
                               @ApiParam(name = "roleId", value = "角色ID", required = true)
                             @NotBlank(message = "角色ID不能为空") String roleId) throws MxException {
        return iMenuService.roleCreate(menuId, roleId);
    }

    /**
     * 删除菜单角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    @PostMapping("/role/delete")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "删除菜单角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_MENU_ROLE_DELETE)
    @OperationLog(operationType = OperationType.DELETE, title = "删除菜单角色")
    public MxResult roleDelete(@ApiParam(name = "ids", value = "ids", required = true)
                             @NotNull(message = "ids不能为空")
                             @RequestParam("ids[]") String[] ids) throws MxException {
        return iMenuService.roleDelete(ids);
    }
}
