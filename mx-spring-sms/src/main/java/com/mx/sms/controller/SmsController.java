package com.mx.sms.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.mx.sms.service.ISmsService;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.support.format.FormatRequest;
import com.mx.spring.dev.util.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:39
 * @Description:
 */
@RestController
@RequestMapping("mx/sms")
@Api(value = "发送短信模块", tags = "发送短信模块")
@Validated
public class SmsController {

    @Autowired
    private ISmsService iSmsService;

    /**
     * 发送短信
     *
     * @param mobile
     * @return
     * @throws MxException
     */
    @PostMapping("/send")
    @ApiOperation(value = "发送短信")
    @FormatRequest
    public MxResult send(@ApiParam(value = "手机号", required = true) @NotBlank(message = "手机号不能为空") String mobile) throws MxException {
        return iSmsService.send(mobile, ServletUtil.getParamMap(WebUtil.request()));
    }

    /**
     * 群发短信
     *
     * @param mobileJson
     * @return
     * @throws MxException
     */
    @PostMapping("/send/multiple")
    @ApiOperation(value = "群发")
    @FormatRequest
    public MxResult sendMultiple(@ApiParam(value = "手机号列表用逗号分割", required = true) @NotBlank(message = "手机号列表不能为空") String mobileJson) throws MxException {
        return iSmsService.sendMultiple(mobileJson, ServletUtil.getParamMap(WebUtil.request()));
    }


}
