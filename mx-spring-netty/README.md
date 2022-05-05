# mx-spring-netty


netty封装，内置心跳检测，同时支持服务端和客户端



## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-netty</artifactId>
    <version>1.0.0</version>
</dependency>
```



## 配置参数说明

```yaml
mx:
  netty:
    # 启动的客户端 all全部 server服务端  client 客户端  默认all
    server-client: all
    # 服务端配置
    server:
      #服务端端口 优先级最高
      port:
      #服务端开始端口 指定port了 以port为准
      start-port:
      # 服务端结束端口 指定port了 以port为准
      end-port:
      #排除端口 用,号分割 只针对startPort endPort有效
      exclude-port:
      #心跳维护时间 默认不维护 单位s
      heart-beat-time:
      #处理器名称 可以指定多个用,号分割 按顺序添加
      handler-class-name:
      #编解码名称 只能指定一个
      decoder-class-name:

    # 客户端配置
    client:
      #远程连接地址 ip:port  多个用逗号分割
      remote-address:
      #心跳维护时间 默认不维护 单位s
      heart-beat-time:
      #处理器名称 可以指定多个用,号分割 按顺序添加
      handler-class-name:
      #编解码名称 只能指定一个
      decoder-class-name:
 
```
