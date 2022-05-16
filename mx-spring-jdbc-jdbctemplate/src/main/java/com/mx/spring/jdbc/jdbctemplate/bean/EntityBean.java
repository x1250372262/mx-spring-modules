package com.mx.spring.jdbc.jdbctemplate.bean;

import java.util.List;

/**
 * @Author: 徐建鹏.
 * @create: 2022-05-09 14:40
 * @Description:
 */
public class EntityBean {

    private String tableName;

    private List<String> fields;

    public EntityBean(String tableName, List<String> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
