package com.mx.maven.util;

import cn.hutool.setting.dialect.Props;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mx-maven-plugin
 * @description: 数据库工具类
 * @author: mengxiang
 * @create: 2021-09-02 14:25
 **/
public class DbUtils {

    private static final Props PROPS = PropUtils.getProps();

//    /**
//     * 获取数据源
//     *
//     * @return
//     */
//    public static DataSource dataSource() {
//        DbConfig dbConfig = new DbConfig();
//        dbConfig.setDriver(PROPS.getStr(PropUtils.DRIVER_CLASS_NAME));
//        dbConfig.setUrl(PROPS.getStr(PropUtils.URL));
//        dbConfig.setUser(PROPS.getStr(PropUtils.USER_NAME));
//        dbConfig.setPass(PROPS.getStr(PropUtils.PASSWORD));
//        return new PooledDataSource(dbConfig);
//    }


    public static Connection connection() throws Exception {
        System.out.println(PROPS.getStr(PropUtils.DRIVER_CLASS_NAME));
        Class.forName(PROPS.getStr(PropUtils.DRIVER_CLASS_NAME));
        return DriverManager.getConnection(PROPS.getStr(PropUtils.URL), PROPS.getStr(PropUtils.USER_NAME), PROPS.getStr(PropUtils.PASSWORD));
    }

    public static List<String> getTableNames() {
        Connection connection = null;
        try {
            connection = connection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(connection.getCatalog(), "root", null, new String[]{"TABLE"});
            List<String> tableNames = new ArrayList<>();
            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }
            return tableNames;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
