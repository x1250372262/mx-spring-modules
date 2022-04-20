# mx-spring-jdbc-clickhouse


整合clickhouse 基于druid连接池



## Maven依赖

```xml
<dependency>
    <groupId>com.mx.modules</groupId>
    <artifactId>mx-spring-jdbc-clickhouse</artifactId>
    <version>1.0.0</version>
</dependency>
```



## 配置参数说明
```yaml

  datasource:
    dynamic:
      #默认这里选择默认数据
      primary: mysql
      strict: true
      datasource:
        #可以配置多个
        mysql:
          url: jdbc:mysql://localhost:3306/dbname?autoReconnect=true&allowPublicKeyRetrieval=True&characterEncoding=utf-8&useUnicode=true&serverTimezone=Asia/Shanghai
          username: root
          password: root
          driver-class-name: com.mysql.cj.jdbc.Driver
        clickhouse:
          url: jdbc:clickhouse://localhost/dbname
#          username:
#          password:
          driver-class-name: ru.yandex.clickhouse.ClickHouseDriver
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      initial-size: 5 #连接池初始化大小
      min-idle: 10 #最小空闲连接数
      max-active: 20 #最大连接数
      filters: stat,slf4j
      web-stat-filter:
        exclusions: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*" #不统计这些请求数据
      stat-view-servlet: #访问监控网页的登录用户名和密码
        enabled: true
        login-username: druid
        login-password: druid
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
      # 合并多个DruidDataSource的监控数据
      useGlobalDataSourceStat: true
```

## 使用
@ClickHouse使用这个注解即可，配置文件中数据源名称clickhouse才可以使用
如果数据源名称不是clickhouse  请使用@DS("数据源名称")
```java
@RestController
@RequestMapping("/api/v1/data")
@ClickHouse
public class DataController {
}
```