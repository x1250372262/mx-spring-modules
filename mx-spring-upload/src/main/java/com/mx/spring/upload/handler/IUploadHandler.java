package com.mx.spring.upload.handler;

import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.util.TimeUtil;
import com.mx.spring.upload.bean.Upload;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:40
 * @Description:
 */
public interface IUploadHandler {

    /**
     * 处理上传文件
     *
     * @param file
     * @return
     * @throws MxException
     */
    View<Upload> handle(MultipartFile file) throws MxException;


    /**
     * 获取文件存储路径
     *
     * @param type
     * @param hash
     * @param extension
     * @return
     */
    default String getFilePath(String type, String hash, String extension) {
        //路径格式 例如 image/20210/01/01/hash.png
        long time = System.currentTimeMillis();
        String year = TimeUtil.formatTime(time, "yyyy");
        String month = TimeUtil.formatTime(time, "MM");
        String day = TimeUtil.formatTime(time, "dd");
        return StrUtil.format("{}/{}/{}/{}/{}.{}", type, year, month, day, hash, extension);
    }

    /**
     * 获取文件访问url
     *
     * @param url
     * @return
     */
    default String getUrl(String url) {
        if (!url.endsWith(StrUtil.SLASH)) {
            url = url + StrUtil.SLASH;
        }
        return url;
    }
}
