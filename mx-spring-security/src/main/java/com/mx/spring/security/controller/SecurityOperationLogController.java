package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.result.R;
import com.mx.spring.dev.support.format.FormatRequest;
import com.mx.spring.dev.support.page.PageDTO;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.annotation.OperationLog;
import com.mx.spring.security.base.config.SPConfig;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.model.SecurityOperationLog;
import com.mx.spring.security.base.vo.SecurityOperationLogListVO;
import com.mx.spring.security.base.vo.SecurityOperationLogVO;
import com.mx.spring.security.service.ISecurityOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/mx/security/log")
@Validated
@Api(value = "日志模块", tags = "日志模块")
public class SecurityOperationLogController {

    @Autowired
    private ISecurityOperationLogService iLogService;


    /**
     * 日志列表
     *
     * @param title
     * @param startTime
     * @param endTime
     * @param pageDTO
     * @return
     * @throws MxException
     */
    @GetMapping("/list")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "日志列表")
    @SaCheckPermission(value = SPConfig.SECURITY_LOG_LIST)
    public M<Pages<SecurityOperationLogListVO>> list(@ApiParam(name = "title", value = "标题") String title, @ApiParam(name = "startTime", value = "开始时间") Long startTime, @ApiParam(name = "endTime", value = "结束时间") Long endTime, PageDTO<SecurityOperationLog> pageDTO) throws MxException {
        return iLogService.list(title, startTime, endTime, pageDTO.toBean());
    }


    /**
     * 日志详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    @GetMapping("/detail/{id}")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "日志详情")
    @SaCheckPermission(value = SPConfig.SECURITY_LOG_DETAIL)
    public M<SecurityOperationLogVO> detail(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String id) throws MxException {
        return iLogService.detail(id);
    }

    /**
     * 删除日志
     *
     * @param ids
     * @return
     * @throws MxException
     */
    @PostMapping("/delete")
    @FormatRequest
    @SaCheckLogin
    @ApiOperation(value = "删除日志")
    @SaCheckPermission(value = SPConfig.SECURITY_LOG_DELETE)
    @OperationLog(operationType = OperationType.DELETE, title = "删除日志")
    public R delete(@ApiParam(name = "ids", value = "ids", required = true) @NotNull(message = "ids不能为空") @RequestParam("ids[]") String[] ids) throws MxException {
        return iLogService.delete(ids);
    }
}
