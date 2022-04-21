package com.mx.maven.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: mx-maven-plugin
 * @description:
 * @author: mengxiang
 * @create: 2021-09-02 14:39
 **/
public class ModelUtils {

    /**
     * 处理字段名称，使其符合JavaBean属性串格式<br>
     * 例如：属性名称为"user_name"，其处理结果为"UserName"<br>
     *
     * @param propertyName 属性名称
     * @return 符合JavaBean属性格式串
     */
    public static String propertyNameToFieldName(String propertyName) {
        if (StringUtils.contains(propertyName, StrUtil.UNDERLINE)) {
            String[] wordArray = StringUtils.split(propertyName, StrUtil.UNDERLINE);
            if (wordArray != null) {
                if (wordArray.length > 1) {
                    StringBuilder returnBuilder = new StringBuilder();
                    for (String word : wordArray) {
                        returnBuilder.append(StringUtils.capitalize(word.toLowerCase()));
                    }
                    return returnBuilder.toString();
                }
                return StringUtils.capitalize(wordArray[0].toLowerCase());
            }
        }
        return propertyName;
    }

}
