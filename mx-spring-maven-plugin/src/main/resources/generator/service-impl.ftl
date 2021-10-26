package ${servicePackageName}.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.security.SaUtils;
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
    private SaUtils saUtils;

    @Override
    public M<Pages${left}${modelName}ListVO${right}> list(${modelName}ListBean ${modelName?uncap_first}ListBean, PageBean<${modelName}> pageBean) throws MxException {
        LambdaQueryWrapper<${modelName}> queryWrapper = MMP.lqw(find${modelName});
        <#list listParamsList as field>
        <#if (field.varType?contains('String'))>
        queryWrapper.eq(StringUtils.isNotBlank(${modelName?uncap_first}ListBean.get${field.varName?cap_first}()),${modelName}::get${field.varName?cap_first},${modelName?uncap_first}ListBean.get${field.varName?cap_first}());
        <#else>
        queryWrapper.eq(Objects.nonNull(${modelName?uncap_first}ListBean.get${field.varName?cap_first}()),${modelName}::get${field.varName?cap_first},${modelName?uncap_first}ListBean.get${field.varName?cap_first}());
        </#if>
        </#list>
        Page<${modelName}> resultData = i${modelName}Mapper.selectPage(pageBean.toPage(),queryWrapper);
        return M.list(BeanUtils.copyPage(resultData,${modelName}ListVO::new));
    }

    @Override
    public R create(${modelName}Bean ${modelName?uncap_first}Bean) throws MxException {
        <#if (notSameField!='我的相同字段')>
        ${modelName} ${modelName?uncap_first} = i${modelName}Mapper.selectOne(MMP.lqw(find${modelName})
                    .eq(${modelName}::get${notSameField?cap_first}, ${modelName?uncap_first}Bean.get${notSameField?cap_first}()));
        if (${modelName?uncap_first} != null) {
            <#if (notSameField=='name' && notSameText=='名称')>
            return R.sameName();
            <#else>
            return R.sameData("${notSameText}");
            </#if>
        }
        ${modelName?uncap_first} = BeanUtils.copy(${modelName?uncap_first}Bean, ${modelName}::new, (s, t) ->
            t.bind()
                .id(IdUtil.fastSimpleUUID())
                .createTime(System.currentTimeMillis())
                .createUser(saUtils.loginId())
                .lastModifyTime(System.currentTimeMillis())
                .lastModifyUser(saUtils.loginId())
        );
        <#else>
        ${modelName} ${modelName?uncap_first} = BeanUtils.copy(${modelName?uncap_first}Bean, ${modelName}::new, (s, t) ->
            t.bind()
                .id(IdUtil.fastSimpleUUID())
                .createTime(System.currentTimeMillis())
                .createUser(saUtils.loginId())
                .lastModifyTime(System.currentTimeMillis())
                .lastModifyUser(saUtils.loginId())
        );
        </#if>
        return R.result(i${modelName}Mapper.insert(${modelName?uncap_first}));
    }

    @Override
    public R update(String id, <#if (isCheckVersion)>Long lastModifyTime,</#if> ${modelName}Bean ${modelName?uncap_first}Bean) throws MxException {
        <#if (notSameField!='我的相同字段')>
        ${modelName} ${modelName?uncap_first} = i${modelName}Mapper.selectOne(MMP.lqw(find${modelName})
                    .eq(${modelName}::get${notSameField?cap_first}, ${modelName?uncap_first}Bean.get${notSameField?cap_first}())
                    .ne(${modelName}::getId,id));
        if (${modelName?uncap_first} != null) {
        <#if (notSameField=='name' && notSameText=='名称')>
            return R.sameName();
        <#else>
            return R.sameData("${notSameText}");
        </#if>
        }
        ${modelName?uncap_first} = i${modelName}Mapper.selectById(id);
        if (${modelName?uncap_first} == null) {
            return R.noData();
        }
        <#else>
        ${modelName} ${modelName?uncap_first} = i${modelName}Mapper.selectById(id);
        if (${modelName?uncap_first} == null) {
            return R.noData();
        }
        </#if>
        if (!R.checkVersion(${modelName?uncap_first}.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        ${modelName?uncap_first} = BeanUtils.duplicate(${modelName?uncap_first}Bean, ${modelName?uncap_first}, (s, t) -> {
            t.bind()
                .lastModifyTime(System.currentTimeMillis())
                .lastModifyUser(saUtils.loginId());
        });
        return R.result(i${modelName}Mapper.updateById(${modelName?uncap_first}));
    }

    @Override
    public M<${modelName}VO> detail(String id) throws MxException {
        return M.ok(BeanUtils.copy(i${modelName}Mapper.selectById(id), ${modelName}VO::new));
    }

    @Override
    public R delete(String[] ids) throws MxException {
        return R.result(i${modelName}Mapper.deleteBatchIds(Arrays.asList(ids)));
    }
}
