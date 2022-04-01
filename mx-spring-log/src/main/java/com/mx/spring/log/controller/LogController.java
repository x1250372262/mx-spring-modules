package com.mx.spring.log.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mx.spring.dev.support.formatRequest.annotation.FormatRequest;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.result.R;
import com.mx.spring.dev.support.page.PageDTO;
import com.mx.spring.dev.enums.OperationType;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.log.LPConfig;
import com.mx.spring.dev.support.log.annotation.Log;
import com.mx.spring.dev.support.log.model.SecurityLog;
import com.mx.spring.dev.support.log.service.ILogService;
import com.mx.spring.dev.support.log.vo.LogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

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
public class LogController {

    @Autowired
    private ILogService iLogService;


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
    @SaCheckPermission(value = LPConfig.LOG_LIST)
    public M<Pages<LogVO>> list(@ApiParam(name = "title", value = "标题") String title,
                                @ApiParam(name = "startTime", value = "开始时间") Long startTime,
                                @ApiParam(name = "endTime", value = "结束时间") Long endTime,
                                PageDTO<SecurityLog> pageDTO) throws MxException {
        return iLogService.list(title, startTime, endTime, pageDTO.toBean());
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
    @SaCheckPermission(value = LPConfig.LOG_DELETE)
    @Log(operationType = OperationType.DELETE, title = "删除日志")
    public R delete(@ApiParam(name = "ids", value = "ids", required = true)
                        @NotNull(message = "ids不能为空")
                        @RequestParam("ids[]") String[] ids) throws MxException {
        return iLogService.delete(ids);
    }
}
