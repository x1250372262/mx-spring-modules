# mx-spring-dev


所有模块的核心模块，提供一些常用工具类，通用返回结果等等

- 提供通用结果类 M和R  M用户查询  R用于增删改。两者基于swagger都能正常显示
- 格式化请求参数，更直观的看到请求的接口 ip 参数 返回参数 耗时等情况
- 封装log 不需要每个类都定义，直接MxLog.log() 就可以使用，给不愿意使用lombok的人提供简单的操作
- 提供统一分页方法，分页的入参 出参。jdbc模块针对这个转换一下即可
- 输出所有的接口列表。
- 提供WebUtil BeanUtil TimeUtil等工具类
- 定义全局异常输出，定义全局错误码


## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-dev</artifactId>
    <version>1.0.0</version>
</dependency>
```


## 配置参数说明

```yaml
mx:
  config:
    #是否格式化请求
    format-request: false
    #是否输入resturl列表
    print-rest-url: false
 
```

