package ${servicePackageName}.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.jdbc.mybatis.plus.page.PageHelper;
import com.mx.spring.jdbc.mybatis.plus.util.MpBeanUtils;
import com.mx.spring.security.SaUtil;
import ${beanPackageName}.${modelName}ListBean;
import ${beanPackageName}.${modelName}Bean;
import ${modelPackageName}.${modelName};
import ${voPackageName}.${modelName}ListVO;
import ${voPackageName}.${modelName}VO;
import ${mapperPackageName}.${mapperName};
import ${servicePackageName}.I${modelName}Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: ${modelName}ServiceImpl
*/
@Service
public class ${modelName}ServiceImpl implements I${modelName}Service {

    private final ${modelName} find${modelName} = ${modelName}.init();
    @Autowired
    private I${modelName}Mapper i${modelName}Mapper;
    @Autowired
    private SaUtil saUtil;

    @Override
    public View<Pages${left}${modelName}ListVO${right}> list(${modelName}ListBean ${modelName?uncap_first}ListBean, PageBean<${modelName}> pageBean) throws MxException {
        LambdaQueryWrapper<${modelName}> queryWrapper = Mp.lqw(find${modelName});
        <#list listParamsList as field>
        <#if (field.varType?contains('String'))>
        queryWrapper.eq(StringUtils.isNotBlank(${modelName?uncap_first}ListBean.get${field.varName?cap_first}()),${modelName}::get${field.varName?cap_first},${modelName?uncap_first}ListBean.get${field.varName?cap_first}());
        <#else>
        queryWrapper.eq(Objects.nonNull(${modelName?uncap_first}ListBean.get${field.varName?cap_first}()),${modelName}::get${field.varName?cap_first},${modelName?uncap_first}ListBean.get${field.varName?cap_first}());
        </#if>
        </#list>
        Page<${modelName}> resultData = i${modelName}Mapper.selectPage(PageHelper.in(pageBean),queryWrapper);

        return View.list(MpBeanUtils.copyPage(resultData,${modelName}ListVO::new));
        //如果是单表查询 可以用如下方法
        //return View.list(PageHelper.out(resultData,${modelName}::new));
    }

    @Override
    public Result create(${modelName}Bean ${modelName?uncap_first}Bean) throws MxException {
        <#if (notSameField!='我的相同字段')>
        ${modelName} ${modelName?uncap_first} = i${modelName}Mapper.selectOne(Mp.lqw(find${modelName})
                    .eq(${modelName}::get${notSameField?cap_first}, ${modelName?uncap_first}Bean.get${notSameField?cap_first}()));
        if (${modelName?uncap_first} != null) {
            <#if (notSameField=='name' && notSameText=='名称')>
            return Result.sameName();
            <#else>
            return Result.sameData("${notSameText}");
            </#if>
        }
        ${modelName?uncap_first} = BeanUtil.copy(${modelName?uncap_first}Bean, ${modelName}::new, (s, t) ->
            t.bind()
                .id(IdUtil.fastSimpleUUID())
                .createTime(System.currentTimeMillis())
                .createUser(saUtil.loginId())
                .lastModifyTime(System.currentTimeMillis())
                .lastModifyUser(saUtil.loginId())
        );
        <#else>
        ${modelName} ${modelName?uncap_first} = BeanUtil.copy(${modelName?uncap_first}Bean, ${modelName}::new, (s, t) ->
            t.bind()
                .id(IdUtil.fastSimpleUUID())
                .createTime(System.currentTimeMillis())
                .createUser(saUtil.loginId())
                .lastModifyTime(System.currentTimeMillis())
                .lastModifyUser(saUtil.loginId())
        );
        </#if>
        return Result.result(i${modelName}Mapper.insert(${modelName?uncap_first}));
    }

    @Override
    public Result update(String id, <#if (isCheckVersion)>Long lastModifyTime,</#if> ${modelName}Bean ${modelName?uncap_first}Bean) throws MxException {
        <#if (notSameField!='我的相同字段')>
        ${modelName} ${modelName?uncap_first} = i${modelName}Mapper.selectOne(Mp.lqw(find${modelName})
                    .eq(${modelName}::get${notSameField?cap_first}, ${modelName?uncap_first}Bean.get${notSameField?cap_first}())
                    .ne(${modelName}::getId,id));
        if (${modelName?uncap_first} != null) {
        <#if (notSameField=='name' && notSameText=='名称')>
            return Result.sameName();
        <#else>
            return Result.sameData("${notSameText}");
        </#if>
        }
        ${modelName?uncap_first} = i${modelName}Mapper.selectById(id);
        if (${modelName?uncap_first} == null) {
            return Result.noData();
        }
        <#else>
        ${modelName} ${modelName?uncap_first} = i${modelName}Mapper.selectById(id);
        if (${modelName?uncap_first} == null) {
            return Result.noData();
        }
        </#if>
        if (!Result.checkVersion(${modelName?uncap_first}.getLastModifyTime(), lastModifyTime)) {
            return Result.noVersion();
        }
        ${modelName?uncap_first} = BeanUtil.duplicate(${modelName?uncap_first}Bean, ${modelName?uncap_first}, (s, t) -> {
            t.bind()
                .lastModifyTime(System.currentTimeMillis())
                .lastModifyUser(saUtil.loginId());
        });
        return Result.result(i${modelName}Mapper.updateById(${modelName?uncap_first}));
    }

    @Override
    public View<${modelName}VO> detail(String id) throws MxException {
        return View.ok(BeanUtil.copy(i${modelName}Mapper.selectById(id), ${modelName}VO::new));
    }

    @Override
    public Result delete(String[] ids) throws MxException {
        return Result.result(i${modelName}Mapper.deleteBatchIds(Arrays.asList(ids)));
    }
}
