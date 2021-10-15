package ${mapperPackageName};

import ${modelPackageName}.${modelName};
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: ${createTime?string("yyyy/MM/dd HH:mm:ss")} 生成 ${modelName?cap_first}Mapper
*/
@Mapper
public interface I${modelName?cap_first}Mapper extends MxBaseMapper<${modelName}> {

}