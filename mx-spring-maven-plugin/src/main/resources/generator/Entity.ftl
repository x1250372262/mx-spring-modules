package ${modelPackageName};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: ${createTime?string("yyyy/MM/dd")}.
 * @Time: ${createTime?string("HH:mm:ss")}.
 * @Description: ${createTime?string("yyyy/MM/dd HH:mm:ss")} 生成 ${modelName?cap_first}
 */
@TableName(${modelName?cap_first}.TABLE_NAME)
public class ${modelName?cap_first} implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "${tableName}";

	<#list fieldList as field>
	/**
	* ${field.comment}
	*/
	<#if field.varName == primaryKeyName>
	@TableId(value = FIELDS.${field.columnName?upper_case}, type = IdType.INPUT)
	</#if>
	<#if field.varName != primaryKeyName>
	@TableField(value = FIELDS.${field.columnName?upper_case})
	</#if>
	@FieldInfo(comment = "${field.comment}"<#if (!field.nullable)>, nullable = false</#if>)
	private ${field.varType} ${field.varName};

	</#list>

	/**
	* 初始化
	*/
	public static ${modelName?cap_first} init() {
		return new ${modelName?cap_first}();
	}

	/**
	 * 构造器
	 */
	public ${modelName?cap_first}() {
	}

	/**
	 * 构造器
	<#list fieldList as field>
	 *	@param ${field.varName}
	</#list>
	 */
	public ${modelName?cap_first}(<#list fieldList as field>${field.varType} ${field.varName}<#if field_has_next>, </#if></#list>) {
		<#list fieldList as field>
		this.${field.varName} = ${field.varName};
		</#list>
	}

	public ${primaryKeyType} getId() {
		return ${primaryKeyName};
	}

	public void setId(${primaryKeyType} id) {
		this.${primaryKeyName} = id;
	}

	<#list fieldList as field>
	<#if field.varName != 'id'>
	public ${field.varType} get${field.varName?cap_first}() {
		return ${field.varName};
	}

	public void set${field.varName?cap_first}(${field.varType} ${field.varName}) {
		this.${field.varName} = ${field.varName};
	}
	<#elseif field.varName != primaryKeyName>
    public ${field.varType} get_${field.varName?cap_first}() {
    	return ${field.varName};
    }

    public void set_${field.varName?cap_first}(${field.varType} ${field.varName}) {
    	this.${field.varName} = ${field.varName};
    }
	</#if>

	</#list>


	<#if (isUseChainMode)>

	public static ${modelName?cap_first}Builder builder() {
		return new ${modelName?cap_first}Builder();
	}

	public ${modelName?cap_first}Builder bind() {
    	return new ${modelName?cap_first}Builder(this);
    }

	public static class ${modelName?cap_first}Builder {

		private final ${modelName?cap_first} modelTarget;

		public ${modelName?cap_first}Builder() {
			modelTarget = new ${modelName?cap_first}();
		}

		public ${modelName?cap_first}Builder(${modelName?cap_first} model) {
			modelTarget = model;
		}

		public ${modelName?cap_first} build() {
			return modelTarget;
		}

	<#list fieldList as field>
		public ${field.varType} ${field.varName}() {
			return modelTarget.get${field.varName?cap_first}();
		}

		public ${modelName?cap_first}Builder ${field.varName}(${field.varType} ${field.varName}) {
			modelTarget.set${field.varName?cap_first}(${field.varName});
			return this;
		}

	</#list>
	}
	</#if>

	/**
	 * ${modelName?cap_first} 字段常量表
	 */
	public static class FIELDS {
	<#list fieldConstantList as field>
		public static final ${field.varType} ${field.varName} = "${field.columnName}";
	</#list>
	}


}
