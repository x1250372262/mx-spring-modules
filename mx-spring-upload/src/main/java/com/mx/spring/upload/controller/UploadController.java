package com.mx.spring.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.support.formatRequest.annotation.FormatRequest;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.service.IUploadService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:39
 * @Description:
 */
@RestController
@RequestMapping("upload")
@Api(value = "文件上传模块", tags = "文件上传模块")
public class UploadController {

    @Autowired
    private IUploadService iUploadService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws MxException
     */
    @PostMapping("/")
    @ApiOperation(value = "文件上传")
    @FormatRequest
    @ApiImplicitParam(name = "file", dataType = "__File", value = "文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "MultipartFile", dataTypeClass = MultipartFile.class, paramType = "query")})
    public M<Upload> upload(MultipartFile file) throws MxException {
        return iUploadService.upload(file);
    }

    /**
     * 上传文件富文本编辑器
     *
     * @param file
     * @param type
     * @return
     * @throws MxException
     */
    @PostMapping("/fwb")
    @ApiOperation(value = "文件上传")
    @FormatRequest
    public JSONObject uploadBD(@ApiParam(value = "文件", required = true) MultipartFile file, String type) throws MxException {
        Upload upload = iUploadService.upload(file).getData();
        JSONObject jsonObject = new JSONObject();
        if ("wang".equals(type)) {
            jsonObject.put("state", "SUCCESS");
            jsonObject.put("title", upload.getName());
            jsonObject.put("size", upload.getSize());
            jsonObject.put("url", upload.getUrl());
        }
        return jsonObject;
    }


}
