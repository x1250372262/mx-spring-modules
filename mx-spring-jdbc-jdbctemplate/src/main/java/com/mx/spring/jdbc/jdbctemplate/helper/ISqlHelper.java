package com.mx.spring.jdbc.jdbctemplate.helper;

import com.mx.spring.dev.exception.MxException;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2022-05-07 11:33
 * @Description:
 */
public interface ISqlHelper {

    /**
     * 执行SQL查询
     *
     * @param <T>     指定结果集数据类型
     * @param sql     SQL语句对象
     * @param handler 结果集数据处理器
     * @return 返回全部结果数据
     * @throws Exception 可能产生的异常
     */
    <T> List<T> findAll(String sql, RowMapper<T> handler) throws MxException;

//    /**
//     * 执行SQL分页查询（执行总记录数统计）
//     *
//     * @param <T>     指定结果集数据类型
//     * @param sql     SQL语句对象
//     * @param handler 结果集数据处理器
//     * @param page    分页参数对象
//     * @return 返回查询结果数据
//     * @throws Exception 可能产生的异常
//     */
//    <T> IResultSet<T> find(SQL sql, IResultSetHandler<T> handler, Page page) throws Exception;
//
//    /**
//     * 根据实体属性值执行查询
//     *
//     * @param entity 实体对象
//     * @param <T>    指定结果集数据类型
//     * @return 返回全部结果数据
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> IResultSet<T> find(T entity) throws Exception;
//
//
//    /**
//     * 执行实体属性值分页查询（执行总记录数统计）
//     *
//     * @param entity 实体对象
//     * @param page   分页参数对象
//     * @param <T>    指定结果集数据类型
//     * @return 返回查询结果数据
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> IResultSet<T> find(T entity, Page page) throws Exception;


//    /**
//     * 根据实体属性值执行查询
//     *
//     * @param entity 实体对象
//     * @param filter 过滤字段名称集合
//     * @param <T>    指定结果集数据类型
//     * @return 返回查询结果数据
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> IResultSet<T> find(T entity, Fields filter) throws Exception;


//    /**
//     * 根据实体属性值执行查询
//     *
//     * @param entity 实体对象
//     * @param filter 过滤字段名称集合
//     * @param page   分页参数对象
//     * @param <T>    指定结果集数据类型
//     * @return 返回查询结果数据
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> IResultSet<T> find(T entity, Fields filter, Page page) throws Exception;



//
//    /**
//     * 执行SQL查询
//     *
//     * @param <T>     指定结果集数据类型
//     * @param sql     SQL语句对象
//     * @param handler 结果集数据处理器
//     * @return 返回结果集中第一条数据
//     * @throws Exception 可能产生的异常
//     */
//    <T> T findFirst(SQL sql, IResultSetHandler<T> handler) throws Exception;
//


//    /**
//     * 执行SQL更新（如更新、插入和删除
//     *
//     * @param sql SQL语句对象
//     * @return 返回此次更新影响的记录数
//     * @throws Exception 可能产生的异常
//     */
//    int executeForUpdate(SQL sql) throws Exception;
//
//    /**
//     * 执行SQL批量更新（如批更新、插入和删除）
//     *
//     * @param sql 批量SQL更新语句
//     * @return 返回此次更新影响的记录数
//     * @throws Exception 可能产生的异常
//     */
//    int[] executeForUpdate(BatchSQL sql) throws Exception;
//
//    /**
//     * 根据实体执行SQL更新
//     *
//     * @param <T>    指定实体数据类型
//     * @param entity 实体查询对象
//     * @return 返回更新后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T update(T entity) throws Exception;
//
//    /**
//     * 根据实体执行SQL更新
//     *
//     * @param <T>    指定实体数据类型
//     * @param entity 实体查询对象
//     * @param filter 字段过滤集合
//     * @return 返回更新后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T update(T entity, Fields filter) throws Exception;
//
//    /**
//     * 根据实体执行SQL更新
//     *
//     * @param entity       实体查询对象
//     * @param filter       字段过滤集合
//     * @param shardingable 数据分片接口对象
//     * @param <T>          指定实体数据类型
//     * @return 返回更新后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T update(T entity, Fields filter, IShardingable shardingable) throws Exception;
//
//    /**
//     * 根据实体执行SQL批量更新
//     *
//     * @param <T>      指定实体数据类型
//     * @param entities 实体查询对象集合
//     * @param filter   显示字段过滤集合
//     * @return 返回更新后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> update(List<T> entities, Fields filter) throws Exception;
//
//    /**
//     * 根据实体执行SQL批量更新
//     *
//     * @param entities 实体查询对象集合
//     * @param filter   字段过滤集合
//     * @param <T>      指定实体数据类型
//     * @return 返回更新后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> update(ShardingList<T> entities, Fields filter) throws Exception;
//
//    /**
//     * 根据实体执行记录插入
//     *
//     * @param <T>    指定实体数据类型
//     * @param entity 实体对象
//     * @return 返回插入后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T insert(T entity) throws Exception;
//
//    /**
//     * 根据实体执行记录插入
//     *
//     * @param entity       实体对象
//     * @param shardingable 数据分片接口对象
//     * @param <T>          指定实体数据类型
//     * @return 返回插入后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T insert(T entity, IShardingable shardingable) throws Exception;
//
//    /**
//     * 根据实体执行记录插入
//     *
//     * @param entity 实体对象
//     * @param filter 字段过滤集合
//     * @param <T>    指定实体数据类型
//     * @return 返回插入后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T insert(T entity, Fields filter) throws Exception;
//
//    /**
//     * 根据实体执行记录插入
//     *
//     * @param entity       实体对象
//     * @param filter       字段过滤集合
//     * @param shardingable 数据分片接口对象
//     * @param <T>          指定实体数据类型
//     * @return 返回插入后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T insert(T entity, Fields filter, IShardingable shardingable) throws Exception;
//
//    /**
//     * 根据实体执行记录批量插入
//     *
//     * @param <T>      指定实体数据类型
//     * @param entities 实体对象集合
//     * @return 返回插入后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> insert(List<T> entities) throws Exception;
//
//    /**
//     * 根据实体执行记录批量插入
//     *
//     * @param entities 实体对象集合
//     * @param <T>      指定实体数据类型
//     * @return 返回插入后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> insert(ShardingList<T> entities) throws Exception;
//
//    /**
//     * 根据实体执行记录批量插入
//     *
//     * @param entities 实体对象集合
//     * @param filter   字段过滤集合
//     * @param <T>      指定实体数据类型
//     * @return 返回插入后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> insert(List<T> entities, Fields filter) throws Exception;
//
//    /**
//     * 根据实体执行记录批量插入
//     *
//     * @param entities 实体对象集合
//     * @param filter   字段过滤集合
//     * @param <T>      指定实体数据类型
//     * @return 返回插入后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> insert(ShardingList<T> entities, Fields filter) throws Exception;
//
//    /**
//     * 根据实体执行记录删除
//     *
//     * @param <T>    指定实体数据类型
//     * @param entity 实体对象
//     * @return 返回删除后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T delete(T entity) throws Exception;
//
//    /**
//     * 根据实体执行记录删除
//     *
//     * @param entity       实体对象
//     * @param shardingable 数据分片接口对象
//     * @param <T>          指定实体数据类型
//     * @return 返回删除后的实体对象，若影响记录数为0则返回null
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> T delete(T entity, IShardingable shardingable) throws Exception;
//
//    /**
//     * 根据实体类型执行记录删除
//     *
//     * @param entityClass 实体类对象
//     * @param id          记录Id
//     * @param <T>         指定实体数据类型
//     * @return 返回影响记录数
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> int delete(Class<T> entityClass, Serializable id) throws Exception;
//
//    /**
//     * 根据实体类型执行记录删除
//     *
//     * @param entityClass  实体类对象
//     * @param id           记录Id
//     * @param shardingable 数据分片接口对象
//     * @param <T>          指定实体数据类型
//     * @return 返回影响记录数
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> int delete(Class<T> entityClass, Serializable id, IShardingable shardingable) throws Exception;
//
//    /**
//     * 根据实体执行记录批量删除
//     *
//     * @param <T>      指定实体数据类型
//     * @param entities 实体对象集合
//     * @return 返回删除后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> delete(List<T> entities) throws Exception;
//
//    /**
//     * 根据实体执行记录批量删除
//     *
//     * @param entities 实体对象集合
//     * @param <T>      指定实体数据类型
//     * @return 返回删除后的实体对象集合
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> List<T> delete(ShardingList<T> entities) throws Exception;
//
//    /**
//     * 根据实体类型执行记录批量删除
//     *
//     * @param entityClass 实体类对象
//     * @param ids         记录Id集合
//     * @param <T>         指定实体数据类型
//     * @return 返回影响记录数组
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> int[] delete(Class<T> entityClass, Serializable[] ids) throws Exception;
//
//    /**
//     * 根据实体类型执行记录批量删除
//     *
//     * @param entityClass 实体类对象
//     * @param ids         记录Id集合
//     * @param <T>         指定实体数据类型
//     * @return 返回影响记录数组
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> int[] delete(Class<T> entityClass, ShardingList<Serializable> ids) throws Exception;
//
//    /**
//     * 计算查询结果总记录数量
//     *
//     * @param <T>         指定实体类型
//     * @param entityClass 实体类对象
//     * @param where       查询条件对象
//     * @return 返回记录数量
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> long count(Class<T> entityClass, Where where) throws Exception;
//
//    /**
//     * 计算查询结果总记录数量
//     *
//     * @param entityClass 实体类对象
//     * @param <T>         指定实体数据类型
//     * @return 返回记录数量
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> long count(Class<T> entityClass) throws Exception;
//
//    /**
//     * 计算查询结果总记录数量
//     *
//     * @param entityClass  实体类对象
//     * @param where        查询条件对象
//     * @param shardingable 数据分片接口对象
//     * @param <T>          指定实体数据类型
//     * @return 返回记录数量
//     * @throws Exception 可能产生的异常
//     */
//    <T extends IEntity> long count(Class<T> entityClass, Where where, IShardingable shardingable) throws Exception;
//
//    /**
//     * 计算查询结果总记录数量
//     *
//     * @param sql SQL语句对象
//     * @return 返回记录数量
//     * @throws Exception 可能产生的异常
//     */
//    long count(SQL sql) throws Exception;
}
