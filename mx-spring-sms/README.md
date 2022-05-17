# mx-spring-upload


基于 springboot 框架实现的短信模块，特性如下：
- 暂时只支持腾讯云



## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-sms</artifactId>
    <version>1.0.0</version>
</dependency>
```



## 配置参数说明

```yaml
mx:
  #文件上传配置
  sms:
    #SecretID
    secret-id:
    #SecretKey
    secret-key:
    #地域
    region:
    #短信应用ID
    app-id:
    #模板id
    template-id:
    #短信签名内容
    sign-text:
    #短信发送处理类
    handler-class:
    #短信发送提供者
    provider-class:

```
