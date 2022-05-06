package com.mx.maven.bean;

import java.util.List;
import java.util.Map;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: 表元数据
 */
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
