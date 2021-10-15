<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackageName}.I${modelName?cap_first}Mapper">

    <resultMap id="BaseResultMap" type="${modelPackageName}.${modelName}">
        <#list fieldList as field>
         <#if primaryKeyName = field.varName>
         <id property="${field.varName}" column="${field.columnName}" jdbcType="${field.columnType}"/>
         </#if>
         <#if primaryKeyName != field.varName>
        <result property="${field.varName}" column="${field.columnName}" jdbcType="${field.columnType}"/>
        </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list fieldList as field>${field.columnName}<#if field_has_next>, </#if></#list>
    </sql>


</mapper>