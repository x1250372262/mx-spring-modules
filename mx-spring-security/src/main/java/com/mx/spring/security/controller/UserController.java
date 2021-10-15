package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mx.spring.dev.annotation.FormatRequest;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.dto.PageDTO;
import com.mx.spring.dev.enums.OperationType;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.annotation.Log;
import com.mx.spring.security.config.SPConfig;
import com.mx.spring.security.dto.UserDTO;
import com.mx.spring.security.service.IUserService;
import com.mx.spring.security.vo.AdminRoleVO;
import com.mx.spring.security.vo.UserListVO;
import com.mx.spring.security.vo.UserVO;
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
public class UserController {

    @Autowired
    private IUserService iUserService;


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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_LIST)
    public M<Pages<UserListVO>> list(@ApiParam(name = "userName", value = "用户名") String userName,
                                     @ApiParam(name = "realName", value = "真实姓名") String realName,
                                     @ApiParam(name = "disableStatus", value = "状态") Integer disableStatus,
                                     PageDTO<UserListVO> pageDTO) throws MxException {
        return iUserService.list(userName, realName, disableStatus, pageDTO.toBean());
    }

    /**
     * 添加人员
     *
     * @param password
     * @param userDTO
     * @return
     * @throws MxException
     */
    @PostMapping("/create")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "添加人员")
    @SaCheckPermission(value = SPConfig.SECURITY_USER_CREATE)
    @Log(operationType = OperationType.CREATE, title = "添加人员")
    public R create(@ApiParam(name = "password", value = "密码(前端需自行md5加密一次)", required = true)
                    @NotBlank(message = "密码不能为空") String password,
                    @Validated UserDTO userDTO) throws MxException {
        return iUserService.create(password, userDTO.toBean());
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_UPDATE_STATUS)
    @Log(operationType = OperationType.OTHER, title = "修改状态")
    public R status(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_UNLOCK)
    @Log(operationType = OperationType.OTHER, title = "解锁")
    public R unlock(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_RESET_PASSWORD)
    @Log(operationType = OperationType.OTHER, title = "重置密码")
    public R resetPassword(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_DETAIL)
    public M<UserVO> detail(@ApiParam(name = "id", value = "ID", required = true)
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_ROLE_LIST)
    public M<Pages<AdminRoleVO>> roleList(@ApiParam(name = "userId", value = "人员ID")
                                          @NotNull(message = "人员ID不能为空") String userId,
                                          PageDTO<AdminRoleVO> pageDTO) throws MxException {
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_ROLE_CREATE)
    @Log(operationType = OperationType.CREATE, title = "添加人员角色")
    public R roleCreate(@ApiParam(name = "userId", value = "人员ID")
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
    @SaCheckPermission(value = SPConfig.SECURITY_USER_ROLE_DELETE)
    @Log(operationType = OperationType.DELETE, title = "删除人员角色")
    public R roleDelete(@ApiParam(name = "ids", value = "ids", required = true)
                        @NotNull(message = "ids不能为空")
                        @RequestParam("ids[]") String[] ids) throws MxException {
        return iUserService.roleDelete(ids);
    }
}
