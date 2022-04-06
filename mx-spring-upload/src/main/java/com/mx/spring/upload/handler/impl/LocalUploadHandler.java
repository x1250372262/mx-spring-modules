package com.mx.spring.upload.handler.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.config.LocalUploadConfig;
import com.mx.spring.upload.handler.IUploadHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.mx.spring.upload.code.UploadCode.UPLOAD_LOCAL_FILE_STORAGE_PATH_ERROR;
import static com.mx.spring.upload.code.UploadCode.UPLOAD_LOCAL_URL_ERROR;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:41
 * @Description: 基于本地文件存储
 */
public class LocalUploadHandler implements IUploadHandler {


    private final LocalUploadConfig localUploadConfig = SpringUtil.getBean(LocalUploadConfig.class);

    @Override
    public M<Upload> handle(MultipartFile file) throws MxException {
        if (StringUtils.isBlank(localUploadConfig.getFileStoragePath())) {
            return M.fail(UPLOAD_LOCAL_FILE_STORAGE_PATH_ERROR.getCode(), UPLOAD_LOCAL_FILE_STORAGE_PATH_ERROR.getMsg());
        }
        if (StringUtils.isBlank(localUploadConfig.getUrl())) {
            return M.fail(UPLOAD_LOCAL_URL_ERROR.getCode(), UPLOAD_LOCAL_URL_ERROR.getMsg());
        }

        String hash;
        try {
            hash = DigestUtils.md5DigestAsHex(file.getInputStream());
        } catch (IOException e) {
            throw new MxException("文件流获取失败", e);
        }
        String type = StringUtils.substringBefore(file.getContentType(), "/").toUpperCase();
        String extension = FileUtil.extName(file.getOriginalFilename());
        File targetFile = new File(localUploadConfig.getFileStoragePath(), getFilePath(type, hash, extension));
        if (!targetFile.exists()) {
            if (targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            try {
                FileUtil.writeBytes(file.getBytes(), targetFile);
            } catch (IOException e) {
                throw new MxException("文件写入失败", e);
            }
        }
        String url = getUrl(localUploadConfig.getUrl()) + getFilePath(type, hash, extension);
        Upload upload = new Upload(hash, targetFile.getName(), extension, url, file.getSize(), type, System.currentTimeMillis());
        return M.ok(upload);
    }
}
