package com.mx.maven.bean;

import com.mx.maven.util.ModelUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: mx-maven-plugin
 * @description:
 * @author: mengxiang
 * @create: 2021-09-02 14:39
 **/
public class ColumnInfo {

    private String columnName;
    private String columnType;
    private String jdbcType;
    private String comment;
    private boolean nullable;

    public ColumnInfo(String columnName, String columnType,String jdbcType,String comment,boolean nullable) {
        this.columnName = columnName;
        this.columnType = columnType;
        if("BIT".equals(jdbcType)){
            this.jdbcType = "BOOLEAN";
        }else if("TEXT".equals(jdbcType)){
            this.jdbcType = "LONGVARCHAR";
        }else if("SMALLINT UNSIGNED".equals(jdbcType)){
            this.jdbcType = "SMALLINT";
        }else if("INT".equals(jdbcType)){
            this.jdbcType = "INTEGER";
        }else{
            this.jdbcType = jdbcType;
        }
        this.comment = comment;
        this.nullable = nullable;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Attr toAttr() {
        return new Attr(getColumnType(), StringUtils.uncapitalize(ModelUtils.propertyNameToFieldName(getColumnName().toLowerCase())), getColumnName(),getJdbcType(),getComment(),isNullable());
    }
}
