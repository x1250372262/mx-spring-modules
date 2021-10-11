package com.mx.spring.dev.support.upload.service;

import com.mx.spring.dev.core.M;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.upload.bean.Upload;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:49
 * @Description:
 */
public interface IUploadService {

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws MxException
     */
    M<Upload> upload(MultipartFile file) throws MxException;
}
