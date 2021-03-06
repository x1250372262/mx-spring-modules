package com.mx.spring.upload.handler.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.upload.bean.Upload;
import com.mx.spring.upload.config.TxUploadConfig;
import com.mx.spring.upload.handler.IUploadHandler;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.mx.spring.upload.code.UploadCode.*;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:41
 * @Description: 基于腾讯云云文件存储
 */
public class TxUploadHandler implements IUploadHandler {

    private final TxUploadConfig txUploadConfig = SpringUtil.getBean(TxUploadConfig.class);
    COSCredentials cred = new BasicCOSCredentials(txUploadConfig.getSecretId(), txUploadConfig.getSecretKey());
    ClientConfig clientConfig = new ClientConfig(new Region(txUploadConfig.getRegion()));
    COSClient cosclient = new COSClient(cred, clientConfig);


    @Override
    public MxView<Upload> handle(MultipartFile file) throws MxException {
        if (StringUtils.isBlank(txUploadConfig.getUrl())) {
            return MxView.fail(UPLOAD_TX_URL_ERROR.getCode(), UPLOAD_TX_URL_ERROR.getMsg());
        }
        if (StringUtils.isBlank(txUploadConfig.getSecretId())) {
            return MxView.fail(UPLOAD_TX_ACCESS_KEY_ERROR.getCode(), UPLOAD_TX_ACCESS_KEY_ERROR.getMsg());
        }
        if (StringUtils.isBlank(txUploadConfig.getSecretKey())) {
            return MxView.fail(UPLOAD_TX_SECRET_KEY_ERROR.getCode(), UPLOAD_TX_SECRET_KEY_ERROR.getMsg());
        }
        if (StringUtils.isBlank(txUploadConfig.getBucket())) {
            return MxView.fail(UPLOAD_TX_BUCKET_ERROR.getCode(), UPLOAD_TX_BUCKET_ERROR.getMsg());
        }
        if (StringUtils.isBlank(txUploadConfig.getRegion())) {
            return MxView.fail(UPLOAD_TX_REGION_ERROR.getCode(), UPLOAD_TX_REGION_ERROR.getMsg());
        }
        Upload upload = uploadFile(file);
        return MxView.ok(upload);
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
        File tempFile = null;
        try {
            tempFile = createTempFile(multipartFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(txUploadConfig.getBucket(), newFileName, tempFile);
            cosclient.putObject(putObjectRequest);
            String url = getUrl(txUploadConfig.getUrl()) + newFileName;
            upload = new Upload(hash, hash + "." + extName, extName, url, multipartFile.getSize(), type, System.currentTimeMillis());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (tempFile != null && tempFile.getParentFile().exists()) {
                tempFile.delete();
            }
        }
        //解析上传成功的结果
        return upload;
    }

    private File createTempFile(MultipartFile multipartFile) throws IOException {
        // 创建一个临时目录文件
        File dest = new File(FileUtil.getTmpDir(), multipartFile.getName());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(dest.toPath()));
        out.write(multipartFile.getBytes());
        out.flush();
        out.close();
        return dest;
    }


}
