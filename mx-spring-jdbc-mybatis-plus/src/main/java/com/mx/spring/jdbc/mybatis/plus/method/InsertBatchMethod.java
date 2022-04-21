package com.mx.spring.jdbc.mybatis.plus.method;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 08:56
 * @Description:
 */
public class InsertBatchMethod extends AbstractMethod {

    public InsertBatchMethod() {
        super("insertBatch");
    }

    /**
     * @param name 方法名
     * @since 3.5.0
     */
    public InsertBatchMethod(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "<script>INSERT INTO {} ({}) VALUES {}</script>";
        String fieldSql = createFieldSql(tableInfo);
        String valueSql = createValueSql(tableInfo);
        String sqlResult = StrUtil.format(sql, tableInfo.getTableName(), fieldSql, valueSql);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, "insertBatch", sqlSource, new NoKeyGenerator(), null, null);
    }

    private String createFieldSql(TableInfo tableInfo) {
        StringBuilder fieldSql = new StringBuilder();
        if(StringUtils.isNotBlank(tableInfo.getKeyColumn())){
            fieldSql.append(tableInfo.getKeyColumn()).append(",");
        }
        tableInfo.getFieldList().forEach(t -> fieldSql.append(t.getColumn()).append(","));
        fieldSql.delete(fieldSql.length() - 1, fieldSql.length());
        return fieldSql.toString();
    }

    private String createValueSql(TableInfo tableInfo) {
        final StringBuilder valueSql = new StringBuilder();
        valueSql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">");
        if(StringUtils.isNotBlank(tableInfo.getKeyProperty())){
            valueSql.append("#{item.").append(tableInfo.getKeyProperty()).append("},");
        }
        tableInfo.getFieldList().forEach(x -> valueSql.append("#{item.").append(x.getProperty()).append("},"));
        valueSql.delete(valueSql.length() - 1, valueSql.length());
        valueSql.append("</foreach>");
        return valueSql.toString();
    }
}
