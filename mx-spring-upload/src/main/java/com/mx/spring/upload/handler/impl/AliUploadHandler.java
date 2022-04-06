package com.mx.spring.upload.handler.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.config.AliUploadConfig;
import com.mx.spring.upload.handler.IUploadHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.mx.spring.upload.code.UploadCode.*;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:41
 * @Description: 基于腾讯云云文件存储
 */
public class AliUploadHandler implements IUploadHandler {

    private final AliUploadConfig aliUploadConfig = SpringUtil.getBean(AliUploadConfig.class);
    OSS ossClient = new OSSClientBuilder().build(aliUploadConfig.getEndpoint(), aliUploadConfig.getAccessKeyId(), aliUploadConfig.getAccessKeySecret());
    @Override
    public M<Upload> handle(MultipartFile file) throws MxException {
        if (StringUtils.isBlank(aliUploadConfig.getUrl())) {
            return M.fail(UPLOAD_ALI_URL_ERROR.getCode(), UPLOAD_ALI_URL_ERROR.getMsg());
        }
        if (StringUtils.isBlank(aliUploadConfig.getAccessKeyId())) {
            return M.fail(UPLOAD_ALI_ACCESS_KEY_ID_ERROR.getCode(), UPLOAD_ALI_ACCESS_KEY_ID_ERROR.getMsg());
        }
        if (StringUtils.isBlank(aliUploadConfig.getAccessKeySecret())) {
            return M.fail(UPLOAD_ALI_ACCESS_KEY_SECRET_ERROR.getCode(), UPLOAD_ALI_ACCESS_KEY_SECRET_ERROR.getMsg());
        }
        if (StringUtils.isBlank(aliUploadConfig.getBucket())) {
            return M.fail(UPLOAD_ALI_BUCKET_ERROR.getCode(), UPLOAD_ALI_BUCKET_ERROR.getMsg());
        }
        if (StringUtils.isBlank(aliUploadConfig.getEndpoint())) {
            return M.fail(UPLOAD_ALI_ENDPOINT_ERROR.getCode(), UPLOAD_ALI_ENDPOINT_ERROR.getMsg());
        }
        Upload upload = uploadFile(file);
        return M.ok(upload);
    }

    private Upload uploadFile(MultipartFile multipartFile) throws MxException {
        Upload upload;
        //获取扩展名
        String extName = FileUtil.extName(multipartFile.getOriginalFilename());
        String hash;
        try {
            hash = DigestUtils.md5DigestAsHex(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new MxException("文件流获取失败", e);
        }
        String type = StringUtils.substringBefore(multipartFile.getContentType(), "/").toUpperCase();
        //获取新文件名
        String newFileName = getFilePath(type, hash, extName);
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(aliUploadConfig.getBucket(), newFileName, new ByteArrayInputStream(multipartFile.getBytes()));
            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            String url = getUrl(aliUploadConfig.getUrl()) + newFileName;
            upload = new Upload(hash, hash + "." + extName, extName, url, multipartFile.getSize(), type, System.currentTimeMillis());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //解析上传成功的结果
        return upload;
    }
}
