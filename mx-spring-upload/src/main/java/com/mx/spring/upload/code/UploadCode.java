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
    UPLOAD_CONTENT_TYPE_NOT_ALLOW("MUB00", "不允许的contentType"),
    UPLOAD_SIZE_ERROR("MUB01", "文件大小不能超过{}M"),
    UPLOAD_LOCAL_FILE_STORAGE_PATH_ERROR("MUL00", "文件保存路径未配置"),
    UPLOAD_LOCAL_URL_ERROR("MUL01", "请求域名未配置"),

    UPLOAD_MINIO_URL_ERROR("MUM01", "Minio请求域名未配置"),
    UPLOAD_MINIO_ACCESS_KEY_ERROR("MUM02", "Minio公钥未配置"),
    UPLOAD_MINIO_SECRET_KEY_ERROR("MUM03", "Minio私钥未配置"),
    UPLOAD_MINIO_BUCKET_ERROR("MUM04", "Minio桶未配置"),

    UPLOAD_QINIU_URL_ERROR("MUQ00", "七牛外链地址未配置"),
    UPLOAD_QINIU_ACCESS_KEY_ERROR("MUQ01", "七牛公钥ak未配置"),
    UPLOAD_QINIU_SECRET_KEY_ERROR("MUQ02", "七牛私钥sk未配置"),
    UPLOAD_QINIU_BUCKET_ERROR("MUQ03", "七牛空间名称未配置"),

    UPLOAD_TX_URL_ERROR("MUT00", "腾讯地址未配置"),
    UPLOAD_TX_ACCESS_KEY_ERROR("MUT01", "腾讯secretId未配置"),
    UPLOAD_TX_SECRET_KEY_ERROR("MUT02", "腾讯secretKey未配置"),
    UPLOAD_TX_BUCKET_ERROR("MUT03", "腾讯存储桶未配置"),
    UPLOAD_TX_REGION_ERROR("MUT04", "腾讯地域未配置"),

    UPLOAD_ALI_URL_ERROR("MUA00", "阿里地址未配置"),
    UPLOAD_ALI_ACCESS_KEY_ID_ERROR("MUA01", "阿里accessKeyId未配置"),
    UPLOAD_ALI_ACCESS_KEY_SECRET_ERROR("MUA02", "阿里accessKeySecret未配置"),
    UPLOAD_ALI_BUCKET_ERROR("MUA03", "阿里存储桶未配置"),
    UPLOAD_ALI_ENDPOINT_ERROR("MUA04", "阿里endpoint未配置");

    private final String code;
    private final String msg;

    UploadCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
