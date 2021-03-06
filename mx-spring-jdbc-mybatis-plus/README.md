# mx-spring-jdbc-mybatis-plus


整合mybatis-plus，和mx-spring-dev一起使用，提供统一分页的逻辑，简便操作

- PageHelper 用来分页
- MP 便捷使用QueryWrapper
- 增加了批量添加 批量修改的方法
- 其他使用请参考官方文档


## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-jdbc-mybatis-plus</artifactId>
    <version>1.0.0</version>
</dependency>
```


## 配置参数说明

```yaml
mybatis-plus:
  global-config:
    banner: false
  configuration:
    log-impl: org.apache.ibatis.logging.log4j2.Log4j2Impl
  type-aliases-package: com.mx.dao.mapper
  mapper-locations: classpath*:mybatis/mapper/*.xml


```

## 多数据增加依赖
```xml
 <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
</dependency>
```
配置参考mx-spring-jdbc-clickhouse项目


## 拼接条件
### 单独参数
```java
        QueryWrapper<Templates> queryWrapper = Query.create(Templates.init())
                .likeEx(Templates.Fields.NAME, name)
                .warpper();
```

### 类参数
```java
      QueryWrapper<Templates> queryWrapper = Query.create(Templates.init())
        .cond(templateDTO)
        .condWarpper();

```

### 实体类参数
```java
      QueryWrapper<Templates> queryWrapper = Query.create(entity)
        .beanWarpper();

```