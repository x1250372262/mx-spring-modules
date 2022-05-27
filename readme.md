# mx-spring-modules

主要用于快速开发springboot项目，具体说明如下

- [mx-spring-dev](mx-spring-dev)  核心模块，其他模块都依赖它。提供一些常用工具类，通用返回结果等等；
- [mx-spring-jdbc-clickhouse](mx-spring-jdbc-clickhouse) 整合clickhouse 大数据存储 统计。
- [mx-spring-jdbc-jdbctemplate](mx-spring-jdbc-jdbctemplate) 整合jdbctemplate，和mx-spring-dev一起使用，提供统一分页的逻辑，简便操作，使用java代码操作数据库。
- [mx-spring-jdbc-mybatis-plus](mx-spring-jdbc-mybatis-plus) 整合mybatis-plus，和mx-spring-dev一起使用，提供统一分页的逻辑，简便操作，简化查询方式。
- [mx-spring-maven-plugin](mx-spring-maven-plugin) 代码生成器maven插件 用来生成从mapper到controller的代码，可指定模板。
- [mx-spring-netty](mx-spring-netty) netty模块 内置心跳检测，可同时当服务端和客户端
- [mx-spring-package](mx-spring-package) 打包文件，内置shell脚本，提供linux环境快速启动停止
- [mx-spring-redis](mx-spring-redis) 整合redis  提供便利的api操作。
- [mx-spring-redisson](mx-spring-redisson) 整合Redisson 
- [mx-spring-security](mx-spring-security) 基于sa-token 权限验证 需要数据库的支持，支持扩展自己的用户体系。
- [mx-spring-sms](mx-spring-sms) 发送短信模块，支持腾讯、阿里。也可以自己扩展。使用方法和上传文件基本一样
- [mx-spring-upload](mx-spring-upload) 上传文件模块，支持本地、七牛、minio、腾讯、阿里。也可以自己扩展。






## 注
以上模块由于未发布到maven中央仓库，所以需要自己本地安装一下。
