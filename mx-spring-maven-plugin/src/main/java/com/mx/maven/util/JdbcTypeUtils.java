package com.mx.maven.util;

/**
 * @Author: 徐建鹏.
 * @create: 2022-04-21 11:11
 * @Description:
 */
public class JdbcTypeUtils {

    public enum JdbcType{

        /**
         * jdbc类型
         */
        LONGVARCHAR("TEXT"),
        SMALLINT("SMALLINT UNSIGNED"),
        INTEGER("INT");

        private final String value;

        JdbcType(String value) {
            this.value = value;
        }

        public static JdbcType valueTo(String value) {
            switch (value) {
                case "TEXT":
                    return LONGVARCHAR;
                case "SMALLINT UNSIGNED":
                    return SMALLINT;
                case "INT":
                    return INTEGER;
                default:
                    return null;
            }
        }

        public String value() {
            return this.value;
        }

    }

    public static String getJdbcType(String oldType){
        JdbcType jdbcType = JdbcType.valueTo(oldType);
        if(jdbcType == null){
            return oldType;
        }
        return jdbcType.name();
    }

    public static void main(String[] args) {
        System.out.println(getJdbcType("TEXT"));
    }

}
