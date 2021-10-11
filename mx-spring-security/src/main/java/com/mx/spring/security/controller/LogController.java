package com.mx.spring.security.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mx.spring.dev.annotation.FormatRequest;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.dto.PageDTO;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.service.ILogService;
import com.mx.spring.security.vo.LogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * @Author: 徐建鹏.
 * @create: 2021-09-03 15:03
 * @Description:
 */
@RestController
@RequestMapping("/log")
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
    @ApiOperation(value = "人员列表")
    public M<Pages<LogVO>> list(@ApiParam(name = "title", value = "标题") String title,
                                @ApiParam(name = "startTime", value = "开始时间") Long startTime,
                                @ApiParam(name = "endTime", value = "结束时间") Long endTime,
                                PageDTO<LogVO> pageDTO) throws MxException {
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
    public R roleDelete(@ApiParam(name = "ids", value = "ids", required = true)
                        @NotNull(message = "ids不能为空")
                        @RequestParam("ids[]") String[] ids) throws MxException {
        return iLogService.delete(ids);
    }
}
