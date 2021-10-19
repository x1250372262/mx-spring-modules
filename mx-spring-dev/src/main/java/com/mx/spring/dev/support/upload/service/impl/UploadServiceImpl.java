package com.mx.spring.dev.support.upload.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.upload.bean.Upload;
import com.mx.spring.dev.support.upload.config.UploadConfig;
import com.mx.spring.dev.support.upload.service.IUploadService;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.dev.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.mx.spring.dev.code.C.*;


/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:52
 * @Description:
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadConfig uploadConfig;


    private boolean checkContentType(String contentType) {
        if (StringUtils.isBlank(uploadConfig.getContentType())) {
            return true;
        }
        List<String> contentTypeList = Arrays.asList(uploadConfig.getContentType().toUpperCase(Locale.ROOT).split("\\|"));
        if (ListUtils.isEmpty(contentTypeList)) {
            return true;
        }
        contentType = StringUtils.substringBefore(contentType, "/").toUpperCase(Locale.ROOT);
        return contentTypeList.contains(contentType);
    }

    private String getFilePath(String type, String hash, String extension) {
        //路径格式 例如 image/20210/01/01/hash.png
        long time = System.currentTimeMillis();
        String year = TimeHelper.formatTime(time, "yyyy");
        String month = TimeHelper.formatTime(time, "MM");
        String day = TimeHelper.formatTime(time, "dd");
        return StrUtil.format("{}/{}/{}/{}/{}.{}", type, year, month, day, hash, extension);
    }


    @Override
    public M<Upload> upload(MultipartFile file) throws MxException {
        if (!checkContentType(file.getContentType())) {
            return M.fail(UPLOAD_CONTENT_TYPE_NOT_ALLOW.getCode(), UPLOAD_CONTENT_TYPE_NOT_ALLOW.getMsg());
        }
        //大小不合适
        if (file.getSize() > uploadConfig.getMaxSize() * 1024 * 1024) {
            return M.fail(UPLOAD_SIZE_ERROR.getCode(), StrUtil.format(UPLOAD_SIZE_ERROR.getMsg(), uploadConfig.getMaxSize()));
        }
        //上传文件路径没有配置
        if (StringUtils.isBlank(uploadConfig.getFileStoragePath())) {
            return M.fail(UPLOAD_FILE_STORAGE_PATH_ERROR.getCode(), UPLOAD_FILE_STORAGE_PATH_ERROR.getMsg());
        }
        // 请求域名没有配置
        if (StringUtils.isBlank(uploadConfig.getBaseUrl())) {
            return M.fail(UPLOAD_BASE_URL_ERROR.getCode(), UPLOAD_BASE_URL_ERROR.getMsg());
        }
        String hash;
        try {
            hash = DigestUtils.md5DigestAsHex(file.getInputStream());
        } catch (IOException e) {
            throw new MxException("文件流获取失败", e);
        }
        String type = StringUtils.substringBefore(file.getContentType(), "/").toUpperCase();
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf(".") + 1);
        File targetFile = new File(uploadConfig.getFileStoragePath(), getFilePath(type, hash, extension));
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
        String baseUrl = uploadConfig.getBaseUrl();
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        String url = baseUrl + getFilePath(type, hash, extension);
        Upload upload = new Upload(hash, targetFile.getName(), extension, url, file.getSize(), type, System.currentTimeMillis());
        return M.ok(upload);
    }
}
