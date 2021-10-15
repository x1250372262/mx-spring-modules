package com.mx.maven.bean;

import java.util.List;
import java.util.Map;

/**
 * @program: mx-maven-plugin
 * @description:
 * @author: mengxiang
 * @create: 2021-09-02 14:39
 **/
public class TableMeta {

    private List<String> pkSet;
    private Map<String, ColumnInfo> fieldMap;

    public TableMeta(List<String> pkSet, Map<String, ColumnInfo> fieldMap) {
        this.pkSet = pkSet;
        this.fieldMap = fieldMap;
    }

    public List<String> getPkSet() {
        return pkSet;
    }

    public void setPkSet(List<String> pkSet) {
        this.pkSet = pkSet;
    }

    public Map<String, ColumnInfo> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, ColumnInfo> fieldMap) {
        this.fieldMap = fieldMap;
    }
}
