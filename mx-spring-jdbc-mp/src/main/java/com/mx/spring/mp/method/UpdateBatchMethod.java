package com.mx.spring.mp.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:01
 * @Description:
 */
public class UpdateBatchMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlTemp = "<script>UPDATE %s %s WHERE %s in %s</script>";

        String whereSql = "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\",\" close=\")\">#{item."+tableInfo.getKeyProperty()+"}</foreach>";

        StringBuilder sb =  new StringBuilder("set ");
        for (TableFieldInfo tableFieldInfo : tableInfo.getFieldList()) {
            sb.append(tableFieldInfo.getColumn()).append(" = case ").append(tableInfo.getKeyColumn());
            sb.append(" <foreach item='item' index='index' collection='list' > ");
            sb.append(" WHEN #{item.").append(tableInfo.getKeyProperty()).append("}");
            sb.append(" <if test='item.").append(tableFieldInfo.getProperty()).append("!=null'>  ");
            sb.append(" THEN #{item.").append(tableFieldInfo.getProperty()).append("} </if> ");
            sb.append(" <if test='item.").append(tableFieldInfo.getProperty()).append("==null'>  ");
            sb.append(" THEN ").append(tableFieldInfo.getColumn()).append("</if> ");
            sb.append(" </foreach> end,");
        }
        // 删除最后一个,逗号
        String setSql = sb.substring(0, sb.toString().length()-1);

        String sql = String.format(sqlTemp, tableInfo.getTableName(), setSql, tableInfo.getKeyColumn(),
                whereSql);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, "updateBatch", sqlSource);
    }
}
