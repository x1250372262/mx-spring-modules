package com.mx.maven.bean;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: 属性对象
 */
public class Attr {

    String varType;
    String varName;
    String columnName;
    String columnType;
    String comment;
    boolean nullable;

    public Attr(String varType, String varName, String columnName, String columnType, String comment, boolean nullable) {
        this.varName = varName;
        this.varType = varType;
        this.columnName = columnName;
        this.columnType = columnType;
        this.comment = comment;
        this.nullable = nullable;
    }

    public String getVarType() {
        return varType;
    }

    public String getVarName() {
        return varName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getComment() {
        return comment;
    }

    public boolean isNullable() {
        return nullable;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "varType='" + varType + '\'' +
                ", varName='" + varName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", comment='" + comment + '\'' +
                ", nullable='" + nullable + '\'' +
                '}';
    }
}
