package ${servicePackageName};

import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import ${beanPackageName}.${modelName}ListBean;
import ${beanPackageName}.${modelName}Bean;
import ${modelPackageName}.${modelName};
import ${voPackageName}.${modelName}ListVO;
import ${voPackageName}.${modelName}VO;

/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: I${modelName}Service
*/
public interface I${modelName}Service {


    /**
    * ${modelComment}列表
    *
    * @param ${modelName?uncap_first}ListBean
    * @param pageBean
    * @return
    * @throws MxException
    */
    M<Pages${left}${modelName}ListVO${right}> list(${modelName}ListBean ${modelName?uncap_first}ListBean, PageBean<${modelName}> pageBean) throws MxException;

    /**
    * 添加${modelComment}
    *
    * @param ${modelName?uncap_first}Bean
    * @return
    * @throws MxException
    */
    R create(${modelName}Bean ${modelName?uncap_first}Bean) throws MxException;

    /**
    * 修改${modelComment}
    *
    * @param id
    <#if (isCheckVersion)>
    * @param lastModifyTime
    </#if>
    * @param ${modelName?uncap_first}Bean
    * @return
    * @throws MxException
    */
    R update(String id, <#if (isCheckVersion)>Long lastModifyTime,</#if> ${modelName}Bean ${modelName?uncap_first}Bean) throws MxException;

    /**
    * ${modelComment}详情
    *
    * @param id
    * @return
    * @throws MxException
    */
    M<${modelName}VO> detail(String id) throws MxException;

    /**
    * 删除${modelComment}
    *
    * @param ids
    * @return
    * @throws MxException
    */
    R delete(String[] ids) throws MxException;

}