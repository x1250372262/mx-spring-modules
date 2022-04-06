package com.mx.spring.upload.handler.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.config.MinIOUploadConfig;
import com.mx.spring.upload.handler.IUploadHandler;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.mx.spring.upload.code.UploadCode.*;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:41
 * @Description: 基于MinIO文件存储
 */
public class MinIOUploadHandler implements IUploadHandler {

    private final MinIOUploadConfig minIOUploadConfig = SpringUtil.getBean(MinIOUploadConfig.class);


    @Override
    public M<Upload> handle(MultipartFile file) throws MxException {
        if (StringUtils.isBlank(minIOUploadConfig.getUrl())) {
            return M.fail(UPLOAD_MINIO_URL_ERROR.getCode(), UPLOAD_MINIO_URL_ERROR.getMsg());
        }
        if (StringUtils.isBlank(minIOUploadConfig.getAccessKey())) {
            return M.fail(UPLOAD_MINIO_ACCESS_KEY_ERROR.getCode(), UPLOAD_MINIO_ACCESS_KEY_ERROR.getMsg());
        }
        if (StringUtils.isBlank(minIOUploadConfig.getSecretKey())) {
            return M.fail(UPLOAD_MINIO_SECRET_KEY_ERROR.getCode(), UPLOAD_MINIO_SECRET_KEY_ERROR.getMsg());
        }
        if (StringUtils.isBlank(minIOUploadConfig.getBucket())) {
            return M.fail(UPLOAD_MINIO_BUCKET_ERROR.getCode(), UPLOAD_MINIO_BUCKET_ERROR.getMsg());
        }
        Upload upload = uploadFile(file);
        return M.ok(upload);
    }

    private Upload uploadFile(MultipartFile multipartFile) throws MxException {
        Upload upload;
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = MinioClient.builder().endpoint(minIOUploadConfig.getUrl()).credentials(minIOUploadConfig.getAccessKey(), minIOUploadConfig.getSecretKey()).build();
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

            // 重新生成一个文件名
            InputStream inputStram = multipartFile.getInputStream();
            minioClient.putObject(PutObjectArgs.builder().bucket(minIOUploadConfig.getBucket()).object(newFileName).stream(inputStram, multipartFile.getSize(), -1).contentType(multipartFile.getContentType()).build());
            String url = getUrl(minIOUploadConfig.getUrl()) + minIOUploadConfig.getBucket() + "/" + newFileName;
            upload = new Upload(hash, hash + "." + extName, extName, url, multipartFile.getSize(), type, System.currentTimeMillis());
        } catch (Exception e) {
            throw new MxException("文件上传失败", e);
        }
        return upload;
    }
}
