package ${beanPackageName};
import java.io.Serializable;

/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: ${modelName}<#if (list)>ListBean<#else>Bean</#if>
*/
public class ${modelName}<#if (list)>ListBean<#else>Bean</#if> implements Serializable {

<#list fieldsList as field>
    /**
    * ${field.comment}
    */
    private ${field.varType} ${field.varName};
</#list>

<#list fieldsList as field>

    public ${field.varType} get${field.varName?cap_first}() {
        return ${field.varName};
    }

    public void set${field.varName?cap_first}(${field.varType} ${field.varName}) {
        this.${field.varName} = ${field.varName};
    }
</#list>
}
