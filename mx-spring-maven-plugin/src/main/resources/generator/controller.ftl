package ${controllerPackageName};

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mx.spring.dev.annotation.FormatRequest;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.dto.PageDTO;
import com.mx.spring.dev.exception.MxException;

import ${dtoPackageName}.${modelName}ListDTO;
import ${dtoPackageName}.${modelName}DTO;
import ${modelPackageName}.${modelName};
import ${servicePackageName}.I${modelName}Service;
import ${voPackageName}.${modelName}ListVO;
import ${voPackageName}.${modelName}VO;
<#if (isCreateSwagger)>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
* @Author: mx-maven-plugin.
* @Date: ${createTime?string("yyyy/MM/dd")}.
* @Time: ${createTime?string("HH:mm:ss")}.
* @Description: ${modelName}Controller
*/
@RestController
@RequestMapping("${modelName?uncap_first}")
@Validated
<#if (isCreateSwagger)>
@Api(value = "${modelComment}", tags = "${modelComment}")
</#if>
public class ${modelName}Controller {

    @Autowired
    private I${modelName}Service i${modelName}Service;


    /**
    * ${modelComment}列表
    *
    * @param ${modelName?uncap_first}ListDTO
    * @param pageDTO
    * @return
    * @throws MxException
    */
    @GetMapping("/list")
    @FormatRequest
    @SaCheckLogin
    <#if (isCreateSwagger)>
    @ApiOperation(value = "${modelComment}列表")
    </#if>
    //@SaCheckRole(value = {角色名})
    //@SaCheckPermission(value = 权限名)
    public M<Pages${left}${modelName}ListVO${right}> list(${modelName}ListDTO ${modelName?uncap_first}ListDTO,PageDTO<${modelName}> pageDTO) throws MxException {
        return i${modelName}Service.list(${modelName?uncap_first}ListDTO.toBean(), pageDTO.toBean());
    }

    /**
    * 添加${modelComment}
    *
    * @param ${modelName?uncap_first}DTO
    * @return
    * @throws MxException
    */
    @PostMapping("/create")
    @FormatRequest
    @SaCheckLogin
    <#if (isCreateSwagger)>
    @ApiOperation(value = "添加${modelComment}")
    </#if>
    //@SaCheckRole(value = {角色名})
    //@SaCheckPermission(value = 权限名)
    public R create(@Validated ${modelName}DTO ${modelName?uncap_first}DTO) throws MxException {
        return i${modelName}Service.create(${modelName?uncap_first}DTO.toBean());
    }

    /**
    * 修改${modelComment}
    *
    * @param id
    <#if (isCheckVersion)>
        * @param lastModifyTime
    </#if>
    * @param ${modelName?uncap_first}DTO
    * @return
    * @throws MxException
    */
    @PostMapping("/update/{id}")
    @FormatRequest
    @SaCheckLogin
    <#if (isCreateSwagger)>
    @ApiOperation(value = "修改${modelComment}")
    </#if>
    //@SaCheckRole(value = {角色名})
    //@SaCheckPermission(value = 权限名)
    public R update(<#if (isCreateSwagger)>@ApiParam(name = "id", value = "ID", required = true)</#if> @PathVariable String id,
    <#if (isCreateSwagger)>@ApiParam(name = "lastModifyTime", value = "最后修改时间(乐观锁)", required = true)</#if> @NotNull(message = "最后修改时间(乐观锁)不能为空") Long lastModifyTime,
    @Validated ${modelName}DTO ${modelName?uncap_first}DTO) throws MxException {
        return i${modelName}Service.update(id, lastModifyTime, ${modelName?uncap_first}DTO.toBean());
    }

    /**
    * ${modelComment}详情
    *
    * @param id
    * @return
    * @throws MxException
    */
    @GetMapping("/detail/{id}")
    @FormatRequest
    @SaCheckLogin
    <#if (isCreateSwagger)>
    @ApiOperation(value = "${modelComment}详情")
    </#if>
    //@SaCheckRole(value = {角色名})
    //@SaCheckPermission(value = 权限名)
    public M<${modelName}VO> detail(<#if (isCreateSwagger)>@ApiParam(name = "id", value = "ID", required = true)</#if> @PathVariable String id) throws MxException {
        return i${modelName}Service.detail(id);
    }

    /**
    * 删除${modelComment}
    *
    * @param ids
    * @return
    * @throws MxException
    */
    @PostMapping("/delete")
    @FormatRequest
    @SaCheckLogin
    <#if (isCreateSwagger)>
    @ApiOperation(value = "删除${modelComment}")
    </#if>
    //@SaCheckRole(value = {角色名})
    //@SaCheckPermission(value = 权限名)
    public R delete(<#if (isCreateSwagger)>@ApiParam(name = "ids", value = "ids", required = true)</#if> @NotNull(message = "ids不能为空") @RequestParam("ids[]") String[] ids) throws MxException {
        return i${modelName}Service.delete(ids);
    }

}
