# mx-spring-redis


整合redis和Redisson  提供便利的api操作。

- IRedisApi 提供简便的redis操作
- 其他使用方法，参考官方文档使用


## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-redis</artifactId>
    <version>1.0.0</version>
</dependency>
```


## 配置参数说明

```yaml
spring:
  redis:
    # Redis数据库索引（默认为0）
    database: 0
    # Redis服务器地址
    host: 127.0.0.1
    # Redis服务器连接端口
    port: 6379
    # Redis服务器连接密码（默认为空）
    password:
    # 连接超时时间（毫秒）
    timeout: 3000ms
    lettuce:
      pool:
        # 连接池最大连接数
        max-active: 200
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: -1ms
        # 连接池中的最大空闲连接
        max-idle: 10
        # 连接池中的最小空闲连接
        min-idle: 0

 
```

