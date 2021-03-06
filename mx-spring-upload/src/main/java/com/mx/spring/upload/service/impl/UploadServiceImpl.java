package com.mx.spring.upload.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.config.BaseUploadConfig;
import com.mx.spring.upload.handler.IUploadHandler;
import com.mx.spring.upload.handler.impl.LocalUploadHandler;
import com.mx.spring.upload.service.IUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.mx.spring.upload.code.UploadCode.UPLOAD_CONTENT_TYPE_NOT_ALLOW;
import static com.mx.spring.upload.code.UploadCode.UPLOAD_SIZE_ERROR;
import static com.mx.spring.upload.constant.UploadConstants.B;


/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:52
 * @Description:
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private BaseUploadConfig uploadConfig;


    private boolean checkContentType(String contentType) {
        if (StringUtils.isBlank(uploadConfig.getContentType())) {
            return true;
        }
        List<String> contentTypeList = Arrays.asList(uploadConfig.getContentType().toUpperCase(Locale.ROOT).split("\\|"));
        if (CollUtil.isEmpty(contentTypeList)) {
            return true;
        }
        contentType = StringUtils.substringBefore(contentType, "/").toUpperCase(Locale.ROOT);
        return contentTypeList.contains(contentType);
    }


    @Override
    public MxView<Upload> upload(MultipartFile file) throws MxException {

        //检查文件类型是否合适
        if (!checkContentType(file.getContentType())) {
            return MxView.fail(UPLOAD_CONTENT_TYPE_NOT_ALLOW.getCode(), UPLOAD_CONTENT_TYPE_NOT_ALLOW.getMsg());
        }
        //大小不合适
        if (file.getSize() > uploadConfig.getMaxSize() * B) {
            return MxView.fail(UPLOAD_SIZE_ERROR.getCode(), StrUtil.format(UPLOAD_SIZE_ERROR.getMsg(), uploadConfig.getMaxSize()));
        }

        Class<? extends IUploadHandler> handlerClass = uploadConfig.getHandlerClass();
        if (handlerClass == null) {
            handlerClass = LocalUploadHandler.class;
        }
        IUploadHandler uploadHandler;
        try {
            uploadHandler = handlerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return uploadHandler.handle(file);
    }
}
