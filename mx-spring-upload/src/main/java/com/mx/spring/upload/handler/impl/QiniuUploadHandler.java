package com.mx.spring.upload.handler.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.config.QiniuUploadConfig;
import com.mx.spring.upload.handler.IUploadHandler;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static com.mx.spring.upload.code.UploadCode.*;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:41
 * @Description: 基于七牛云文件存储
 */
public class QiniuUploadHandler implements IUploadHandler {

    private final QiniuUploadConfig qiniuUploadConfig = SpringUtil.getBean(QiniuUploadConfig.class);
    private final Auth auth = Auth.create(qiniuUploadConfig.getAccessKey(), qiniuUploadConfig.getSecretKey());

    private final Configuration cfg = new Configuration(getRegion());
    private final UploadManager uploadManager = new UploadManager(cfg);


    @Override
    public MxView<Upload> handle(MultipartFile file) throws MxException {
        if (StringUtils.isBlank(qiniuUploadConfig.getUrl())) {
            return MxView.fail(UPLOAD_QINIU_URL_ERROR.getCode(), UPLOAD_QINIU_URL_ERROR.getMsg());
        }
        if (StringUtils.isBlank(qiniuUploadConfig.getAccessKey())) {
            return MxView.fail(UPLOAD_QINIU_ACCESS_KEY_ERROR.getCode(), UPLOAD_QINIU_ACCESS_KEY_ERROR.getMsg());
        }
        if (StringUtils.isBlank(qiniuUploadConfig.getSecretKey())) {
            return MxView.fail(UPLOAD_QINIU_SECRET_KEY_ERROR.getCode(), UPLOAD_QINIU_SECRET_KEY_ERROR.getMsg());
        }
        if (StringUtils.isBlank(qiniuUploadConfig.getBucket())) {
            return MxView.fail(UPLOAD_QINIU_BUCKET_ERROR.getCode(), UPLOAD_QINIU_BUCKET_ERROR.getMsg());
        }
        Upload upload = uploadFile(file);
        return MxView.ok(upload);
    }

    private Upload uploadFile(MultipartFile multipartFile) throws MxException {
        Upload upload;
        // 生成上传凭证，然后准备上传
        String upToken = auth.uploadToken(qiniuUploadConfig.getBucket());
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
        Response response;
        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            response = uploadManager.put(fileInputStream, newFileName, upToken, null, null);
            if (!response.isOK()) {
                throw new MxException("上传七牛出错:" + response);
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = JSONObject.parseObject(response.bodyString(), DefaultPutRet.class);

            String url = getUrl(qiniuUploadConfig.getUrl()) + putRet.key;
            upload = new Upload(hash, hash + "." + extName, extName, url, multipartFile.getSize(), type, System.currentTimeMillis());
        } catch (IOException e) {
            throw new MxException("上传七牛出错", e);
        }
        //解析上传成功的结果
        return upload;
    }


    private Region getRegion() {
        Region region;
        switch (qiniuUploadConfig.getRegion()) {
            case "z0":
                region = Region.region0();
                break;
            case "z1":
                region = Region.region1();
                break;
            case "z2":
                region = Region.region2();
                break;
            case "na0":
                region = Region.regionNa0();
                break;
            case "as0":
                region = Region.regionAs0();
                break;
            case "cnEast2":
                region = Region.region0();
                break;
            default:
                region = Region.autoRegion();
                break;
        }
        return region;
    }

}
