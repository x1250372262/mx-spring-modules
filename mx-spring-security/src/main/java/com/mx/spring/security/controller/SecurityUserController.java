package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.support.format.FormatRequest;
import com.mx.spring.dev.support.page.PageDTO;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.annotation.OperationLog;
import com.mx.spring.security.base.config.SecurityPermissionConfig;
import com.mx.spring.security.base.dto.SecurityUserDTO;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.vo.SecurityUserListVO;
import com.mx.spring.security.base.vo.SecurityUserRoleVO;
import com.mx.spring.security.base.vo.SecurityUserVO;
import com.mx.spring.security.service.ISecurityUserService;
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
@RequestMapping("/mx/security/user")
@Validated
@Api(value = "人员管理模块", tags = "人员管理模块")
public class SecurityUserController {

    @Autowired
    private ISecurityUserService iUserService;


    /**
     * 人员列表
     *
     * @param userName
     * @param realName
     * @param disableStatus
     * @param pageDTO
     * @return
     * @throws MxException
     */
    @GetMapping("/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "人员列表")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_LIST)
    public View<Pages<SecurityUserListVO>> list(@ApiParam(name = "userName", value = "用户名") String userName,
                                                @ApiParam(name = "realName", value = "真实姓名") String realName,
                                                @ApiParam(name = "disableStatus", value = "状态") Integer disableStatus,
                                                PageDTO<SecurityUserListVO> pageDTO) throws MxException {
        return iUserService.list(userName, realName, disableStatus, pageDTO.toBean());
    }

    /**
     * 添加人员
     *
     * @param password
     * @param securityUserDTO
     * @return
     * @throws MxException
     */
    @PostMapping("/create")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "添加人员")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_CREATE)
    @OperationLog(operationType = OperationType.CREATE, title = "添加人员")
    public Result create(@ApiParam(name = "password", value = "密码(前端需自行md5加密一次)", required = true)
                         @NotBlank(message = "密码不能为空") String password,
                         @Validated SecurityUserDTO securityUserDTO) throws MxException {
        return iUserService.create(password, securityUserDTO.toBean());
    }

    /**
     * 修改状态
     *
     * @param id
     * @param lastModifyTime
     * @param status
     * @return
     * @throws MxException
     */
    @PostMapping("/status/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "修改状态")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_UPDATE_STATUS)
    @OperationLog(operationType = OperationType.OTHER, title = "修改状态")
    public Result status(@ApiParam(name = "id", value = "ID", required = true)
                         @PathVariable String id,
                         @ApiParam(name = "lastModifyTime", value = "最后修改时间(乐观锁)", required = true)
                         @NotNull(message = "最后修改时间(乐观锁)不能为空") Long lastModifyTime,
                         @ApiParam(name = "status", value = "状态", required = true)
                         @NotNull(message = "状态不能为空") Integer status) throws MxException {
        return iUserService.status(id, lastModifyTime, status);
    }

    /**
     * 解锁
     *
     * @param id
     * @param lastModifyTime
     * @return
     * @throws MxException
     */
    @PostMapping("/unlock/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "解锁")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_UNLOCK)
    @OperationLog(operationType = OperationType.OTHER, title = "解锁")
    public Result unlock(@ApiParam(name = "id", value = "ID", required = true)
                         @PathVariable String id,
                         @ApiParam(name = "lastModifyTime", value = "最后修改时间(乐观锁)", required = true)
                         @NotNull(message = "最后修改时间(乐观锁)不能为空") Long lastModifyTime) throws MxException {
        return iUserService.unlock(id, lastModifyTime);
    }

    /**
     * 重置密码
     *
     * @param id
     * @param lastModifyTime
     * @return
     * @throws MxException
     */
    @PostMapping("/resetPassword/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "重置密码")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_RESET_PASSWORD)
    @OperationLog(operationType = OperationType.OTHER, title = "重置密码")
    public Result resetPassword(@ApiParam(name = "id", value = "ID", required = true)
                                @PathVariable String id,
                                @ApiParam(name = "lastModifyTime", value = "最后修改时间(乐观锁)", required = true)
                                @NotNull(message = "最后修改时间(乐观锁)不能为空") Long lastModifyTime) throws MxException {
        return iUserService.resetPassword(id, lastModifyTime);
    }

    /**
     * 人员详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    @GetMapping("/detail/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "人员详情")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_DETAIL)
    public View<SecurityUserVO> detail(@ApiParam(name = "id", value = "ID", required = true)
                                       @PathVariable String id) throws MxException {
        return iUserService.detail(id);
    }


    /**
     * 人员角色列表
     *
     * @param userId
     * @param pageDTO
     * @return
     * @throws MxException
     */
    @GetMapping("/role/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "人员角色列表")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_ROLE_LIST)
    public View<Pages<SecurityUserRoleVO>> roleList(@ApiParam(name = "userId", value = "人员ID")
                                                    @NotNull(message = "人员ID不能为空") String userId,
                                                    PageDTO<SecurityUserRoleVO> pageDTO) throws MxException {
        return iUserService.roleList(userId, pageDTO.toBean());
    }

    /**
     * 添加人员角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws MxException
     */
    @PostMapping("/role/create")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "添加人员角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_ROLE_CREATE)
    @OperationLog(operationType = OperationType.CREATE, title = "添加人员角色")
    public Result roleCreate(@ApiParam(name = "userId", value = "人员ID")
                             @NotNull(message = "人员ID不能为空") String userId,
                             @ApiParam(name = "roleId", value = "角色ID")
                             @NotNull(message = "角色ID不能为空") String roleId) throws MxException {
        return iUserService.roleCreate(userId, roleId);
    }

    /**
     * 删除人员角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    @PostMapping("/role/delete")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "删除人员角色")
    @SaCheckPermission(value = SecurityPermissionConfig.SECURITY_USER_ROLE_DELETE)
    @OperationLog(operationType = OperationType.DELETE, title = "删除人员角色")
    public Result roleDelete(@ApiParam(name = "ids", value = "ids", required = true)
                             @NotNull(message = "ids不能为空")
                             @RequestParam("ids[]") String[] ids) throws MxException {
        return iUserService.roleDelete(ids);
    }
}
