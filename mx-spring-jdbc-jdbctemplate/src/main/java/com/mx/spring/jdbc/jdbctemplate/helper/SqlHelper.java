//package com.mx.spring.jdbc.jdbctemplate.helper;
//
//import cn.hutool.extra.spring.SpringUtil;
//import com.mx.spring.dev.exception.MxException;
//import org.springframework.context.annotation.Import;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import java.util.List;
//
///**
// * @Author: 徐建鹏.
// * @create: 2022-05-07 11:33
// * @Description:
// */
//@Import(cn.hutool.extra.spring.SpringUtil.class)
//public class SqlHelper {
//
//    private static final NamedParameterJdbcTemplate NAMED_PARAMETER_JDBC_TEMPLATE = SpringUtil.getBean(NamedParameterJdbcTemplate.class);
//
//    public static <T> List<T> findAll(String sql, RowMapper<T> handler) throws MxException {
//        return NAMED_PARAMETER_JDBC_TEMPLATE.query(sql, handler);
//    }
//
//    public static <T> int create(T entity) throws MxException{
//
//    }
//}
