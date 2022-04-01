package com.mx.spring.upload.code;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public enum UploadCode {

    /**
     * 上传文件错误码
     */
    UPLOAD_CONTENT_TYPE_NOT_ALLOW(51000, "不允许的contentType"),
    UPLOAD_SIZE_ERROR(51001, "文件大小不能超过{}M"),
    UPLOAD_LOCAL_FILE_STORAGE_PATH_ERROR(51100, "文件保存路径未配置"),
    UPLOAD_LOCAL_URL_ERROR(51101, "请求域名未配置"),

    UPLOAD_MINIO_URL_ERROR(51200, "Minio请求域名未配置"),
    UPLOAD_MINIO_ACCESS_KEY_ERROR(51201, "Minio公钥未配置"),
    UPLOAD_MINIO_SECRET_KEY_ERROR(51202, "Minio私钥未配置"),
    UPLOAD_MINIO_BUCKET_ERROR(51203, "Minio桶未配置"),

    UPLOAD_QINIU_URL_ERROR(51300, "七牛外链地址未配置"),
    UPLOAD_QINIU_ACCESS_KEY_ERROR(51301, "七牛公钥ak未配置"),
    UPLOAD_QINIU_SECRET_KEY_ERROR(51302, "七牛私钥sk未配置"),
    UPLOAD_QINIU_BUCKET_ERROR(51303, "七牛空间名称未配置"),

    UPLOAD_TX_URL_ERROR(51300, "腾讯地址未配置"),
    UPLOAD_TX_ACCESS_KEY_ERROR(51301, "腾讯secretId未配置"),
    UPLOAD_TX_SECRET_KEY_ERROR(51302, "腾讯secretKey未配置"),
    UPLOAD_TX_BUCKET_ERROR(51303, "腾讯存储桶未配置"),
    UPLOAD_TX_REGION_ERROR(51304, "腾讯地域未配置"),

    UPLOAD_ALI_URL_ERROR(51400, "阿里地址未配置"),
    UPLOAD_ALI_ACCESS_KEY_ID_ERROR(51401, "阿里accessKeyId未配置"),
    UPLOAD_ALI_ACCESS_KEY_SECRET_ERROR(51402, "阿里accessKeySecret未配置"),
    UPLOAD_ALI_BUCKET_ERROR(51403, "阿里存储桶未配置"),
    UPLOAD_ALI_ENDPOINT_ERROR(51403, "阿里endpoint未配置");

    private final int code;
    private final String msg;

    UploadCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
