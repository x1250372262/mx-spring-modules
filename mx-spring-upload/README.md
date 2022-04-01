# mx-spring-upload


基于 springboot 框架实现的文件上传模块，特性如下：
- 支持本地、minio、七牛云、阿里云、腾讯云
- 可以自己扩展存储方式，只需要实现IUploadHandler接口 配置handler-class即可



## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-upload</artifactId>
    <version>1.0.0</version>
</dependency>
```



## 配置参数说明

```yaml
mx:
  #文件上传配置
  upload:
    #上传文件大小最大值(M) 默认10M
    max-size: 1
    #允许上传的contentType列表  用|分割 默认不限制
    content-type: image|video
    #文件上传处理类
    #默认com.mx.spring.upload.handler.impl.LocalUploadHandler基于本地
    #QiniuUploadHandler基于七牛云
    #MinIOUploadHandler基于Minio
    #TxUploadHandler基于腾讯云
    #AliUploadHandler基于阿里云
    handler-class:
    #本地文件配置
    local:
      # 文件存储位置 文件上传处理类=LocalUploadHandler时不能为空
      file-storage-path: 
      #请求域名 文件上传处理类=LocalUploadHandler时不能为空
      url: 
    #Minio文件配置
    minio:
      #地址 文件上传处理类=MinIOUploadHandler时不能为空
      url: 
      #公钥 文件上传处理类=MinIOUploadHandler时不能为空
      access-key: 
      #私钥 文件上传处理类=MinIOUploadHandler时不能为空
      secret-key: 
      #桶 文件上传处理类=MinIOUploadHandler时不能为空
      bucket: 
    #七牛文件配置
    qiniu:
      #外链地址 文件上传处理类=QiniuUploadHandler时不能为空
      url: 
      #公钥ak 文件上传处理类=QiniuUploadHandler时不能为空
      access-key: 
      #私钥sk 文件上传处理类=QiniuUploadHandler时不能为空
      secret-key: 
      #空间名称 文件上传处理类=QiniuUploadHandler时不能为空
      bucket: 
      #地域 不配置会自动判断区域，使用相应域名处理。
      #如果可以明确 区域 的话，最好指定固定区域，这样可以少一步网络请求，少一步出错的可能。
      #https://developer.qiniu.com/kodo/1671/region-endpoint-fq  具体配置参考这个网址  新增的配置可联系作者添加
      region:
    #腾讯文件配置
    tx:
      #地址 文件上传处理类=TxUploadHandler时不能为空
      url: 
      #secretId 文件上传处理类=TxUploadHandler时不能为空
      secret-id: 
      #secretKey 文件上传处理类=TxUploadHandler时不能为空
      secret-key: 
      #存储桶 文件上传处理类=TxUploadHandler时不能为空
      bucket: 
      #地域 文件上传处理类=TxUploadHandler时不能为空
      region: 
    #阿里文件配置
    ali:
      #文件预览地址 https://bucket.endpoint 文件上传处理类=TxUploadHandler时不能为空
      url: 
      #地址 文件上传处理类=AliUploadHandler时不能为空
      endpoint: 
      #accessKeyId 文件上传处理类=AliUploadHandler时不能为空
      access-key-id: 
      #accessKeySecret 文件上传处理类=AliUploadHandler时不能为空
      access-key-secret: 
      #存储桶 文件上传处理类=AliUploadHandler时不能为空
      bucket: 
```

## 返回参数：
```json
{
  "code": "错误码|int", 
  "data": {
    "createTime": "文件创建时间|long",
    "extension": "扩展名称|String",
    "hash": "文件哈希值|String",
    "name": "文件名称|String",
    "size": "文件大小（字节）|int",
    "type": "文件资源类型|String",
    "url": "文件静态引用URL地址|String"
  },
  "msg": "错误信息|String"
}
```


## 返回示例：
```json
{
  "code": 0,
  "msg": "操作成功",
  "data": {
    "hash": "27b3226150f44ef546434c250edfb221",
    "name": "27b3226150f44ef546434c250edfb221.jpg",
    "extension": "jpg",
    "url": "https://xxx.com/IMAGE/2022/04/01/27b3226150f44ef546434c250edfb221.jpg",
    "size": 135092,
    "type": "IMAGE",
    "createTime": 1648798182598
  }
}
```