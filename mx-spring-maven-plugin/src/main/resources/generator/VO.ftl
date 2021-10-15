package ${voPackageName};

<#if (isCreateSwagger)>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.io.Serializable;

/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: ${modelName}<#if (list)>ListVO<#else>VO</#if>
*/
<#if (isCreateSwagger)>@ApiModel(value = "${modelComment}", description = "${modelComment}")</#if>
public class ${modelName}<#if (list)>ListVO<#else>VO</#if> implements Serializable {

<#list fieldsList as field>
    /**
    * ${field.comment}
    */
    <#if (isCreateSwagger)>
    @ApiModelProperty(value = "${field.comment}")
    </#if>
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
