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
import com.mx.spring.security.dto.MenuDTO;
import com.mx.spring.security.service.IMenuService;
import com.mx.spring.security.vo.MenuListVO;
import com.mx.spring.security.vo.MenuNavVO;
import com.mx.spring.security.vo.MenuRoleVO;
import com.mx.spring.security.vo.MenuVO;
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
public class MenuController {

    @Autowired
    private IMenuService iMenuService;


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
    public M<List<MenuNavVO>> nav() throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_LIST)
    public M<List<MenuListVO>> list() throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_CREATE)
    public R create(@Validated MenuDTO menuDTO) throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_UPDATE)
    public R update(@ApiParam(name = "id", value = "ID", required = true)
                    @NotBlank(message = "ID不能为空") String id,
                    @Validated MenuDTO menuDTO) throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_DETAIL)
    public M<MenuVO> detail(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_DELETE)
    public R delete(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_ROLE_LIST)
    public M<Pages<MenuRoleVO>> roleList(@ApiParam(name = "menuId", value = "菜单ID", required = true)
                                         @NotBlank(message = "菜单ID不能为空") String menuId,
                                         @ApiParam(name = "name", value = "角色名称") String name,
                                         PageDTO<MenuRoleVO> pageDTO) throws MxException {
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_ROLE_CREATE)
    public R roleCreate(@ApiParam(name = "menuId", value = "菜单ID", required = true)
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
    @SaCheckRole(value = {SPConfig.SECURITY_ROLE_NAME})
    @SaCheckPermission(value = SPConfig.SECURITY_MENU_ROLE_DELETE)
    public R roleDelete(@ApiParam(name = "ids", value = "ids", required = true)
                        @NotNull(message = "ids不能为空")
                        @RequestParam("ids[]") String[] ids) throws MxException {
        return iMenuService.roleDelete(ids);
    }
}
