//package com.mx.spring.jdbc.jdbctemplate.helper;
//
//import cn.hutool.core.annotation.AnnotationUtil;
//import cn.hutool.core.util.ClassUtil;
//import com.mx.spring.jdbc.jdbctemplate.annotation.TableName;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Author: 徐建鹏.
// * @create: 2022-05-07 16:58
// * @Description:
// */
//public class EntityHelper {
//
//    private static final Map<Class<? extends IEntity>, EntityMeta> ENTITY_METAS = new ConcurrentHashMap<>();
//
//    private static String tableName;
//
//    private List<String>
//
//
//    public static EntityHelper init(Class<?> entityClass){
//        if (AnnotationUtil.hasAnnotation(entityClass, TableName.class)) {
//            TableName tableNameAnno = AnnotationUtil.getAnnotation(entityClass, TableName.class);
//            tableName = tableNameAnno.value();
//            try {
//                t
//                return ReentrantLockHelper.putIfAbsentAsync(ENTITY_METAS, targetClass, () -> {
//                    // 注册数据实体类
//                    String entityName = StringUtils.defaultIfBlank(targetClass.getAnnotation(Entity.class).value(), ClassUtils.fieldNameToPropertyName(targetClass.getSimpleName(), 0));
//                    EntityMeta entityMeta = new EntityMeta(entityName, ClassUtils.isAnnotationOf(targetClass, Readonly.class), shardingRule != null ? shardingRule.value() : null);
//                    // 判断clazz对象是否声明了@Comment注解
//                    if (ClassUtils.isAnnotationOf(targetClass, Comment.class)) {
//                        entityMeta.comment = targetClass.getAnnotation(Comment.class).value();
//                    }
//                    // 处理字段属性
//                    parseProperties(targetClass, entityMeta);
//                    //
//                    return entityMeta;
//                });
//            } catch (Exception e) {
//                if (LOG.isWarnEnabled()) {
//                    LOG.warn(StringUtils.EMPTY, RuntimeUtils.unwrapThrow(e));
//                }
//            }
//        }
//    }
//}
