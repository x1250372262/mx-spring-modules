package ${dtoPackageName};
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtil;
import ${beanPackageName}.${modelName}<#if (list)>ListBean<#else>Bean</#if>;
<#if (isCreateSwagger)>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.io.Serializable;
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
    private ${field.varType} ${field.varName};
</#list>

    public ${modelName}<#if (list)>ListBean<#else>Bean</#if> toBean() throws MxException {
        return BeanUtil.copy(this, ${modelName}<#if (list)>ListBean<#else>Bean</#if>::new);
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
