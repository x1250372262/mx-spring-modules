//package com.mx.spring.jdbc.jdbctemplate.helper;
//
//import com.mx.spring.jdbc.jdbctemplate.base.BaseEntity;
//import org.apache.commons.lang3.StringUtils;
//
///**
// * @Author: mengxiang.
// * @create: 2022-05-07 16:55
// * @Description:
// */
//public class SqlGeneratorHelper {
//
//
//    public static String createInsertSql(Class<?> entityClass) {
//
//        EntityMeta entityMeta = EntityMeta.load(entityClass);
//        ExpressionUtils exp = ExpressionUtils.bind("INSERT INTO ${table_name} (${fields}) VALUES (${values})")
//                .set("table_name", buildTableName(prefix, entityMeta, shardingable));
//        //
//        Fields newFields = Fields.create();
//        if (fields == null || fields.fields().isEmpty()) {
//            newFields.add(entityMeta.getPropertyNames());
//        } else {
//            newFields.add(fields);
//            doValidProperty(entityMeta, newFields, false);
//        }
//        return exp.set("fields", doGenerateFieldsFormatStr(newFields, null, null)).set("values", StringUtils.repeat("?", ", ", newFields.fields().size())).getResult();
//    }
//}
