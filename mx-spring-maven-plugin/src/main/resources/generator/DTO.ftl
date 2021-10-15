package ${dtoPackageName};
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtils;
import ${beanPackageName}.${modelName}<#if (list)>ListBean<#else>Bean</#if>;
<#if (isCreateSwagger)>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: ${modelName}<#if (list)>ListDTO<#else>DTO</#if>
*/
<#if (isCreateSwagger)>@ApiModel(value = "${modelComment}", description = "${modelComment}")</#if>
public class ${modelName}<#if (list)>ListDTO<#else>DTO</#if> implements Serializable {

<#list fieldsList as field>
    /**
    * ${field.comment}
    */
    <#if (isCreateSwagger)>
    @ApiModelProperty(value = "${field.comment}"<#if (!field.nullable)>, required = true</#if>)
    </#if>
    <#if (!field.nullable)>
    <#if (field.varType?contains('String'))>
    @NotBlank(message = "不能为空")
    <#else>
    @NotNull(message = "不能为空")
    </#if>
    </#if>
    private ${field.varType} ${field.varName};
</#list>

    public ${modelName}<#if (list)>ListBean<#else>Bean</#if> toBean() throws MxException {
        return BeanUtils.copy(this, ${modelName}<#if (list)>ListBean<#else>Bean</#if>::new);
    }

<#list fieldsList as field>
    public ${field.varType} get${field.varName?cap_first}() {
        return ${field.varName};
    }

    public void set${field.varName?cap_first}(${field.varType} ${field.varName}) {
        this.${field.varName} = ${field.varName};
    }
</#list>
}
