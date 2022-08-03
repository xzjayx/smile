package ${package.Controller};

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${customizefo.qoPackage}.${entity}Qo;
import com.smile.basic.core.base.Result;
import java.util.List;
<#if swagger>
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
*
*  <p>
*    ${table.comment!} 前端控制器
*  </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
<#if swagger>
@Api(tags = "${entity}前端控制器")
</#if>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
<#if swagger>
@Api(tags = "${entity}前端控制器")
</#if>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName}  ${customizefo.capServiceName};

    <#if swagger>
    @ApiOperation(value = "根据主键id查询${entity}")
    @ApiOperationSupport(order = 10)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "String")
    </#if>
    @GetMapping("/get${entity}ById")
    public Result<${entity}> get${entity}ById(String id){
        return Result.success(${customizefo.capServiceName}.getById(id));
    }

    <#if swagger>
    @ApiOperation(value = "对象查询", notes = "通过条件查询对象只返回一条记录")
    @ApiOperationSupport(order = 20,ignoreParameters = {"baseQueryQo"})
    </#if>
    @GetMapping("/findOne")
    public Result<${entity}> findOne(${entity}Qo ${customizefo.capEntity}Qo){
        return Result.success(${customizefo.capServiceName}.findOne(${customizefo.capEntity}Qo));
    }

    <#if swagger>
    @ApiOperation(value = "分页查询", notes = "分页查询不带条件仅仅内置分页对象")
    @ApiOperationSupport(order = 30,includeParameters = {"baseQueryQo.current","baseQueryQo.size","id"} )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "baseQueryQo.current",value = "当前页码数", required = true, dataType = "Long",defaultValue = "1"),
        @ApiImplicitParam(name = "baseQueryQo.size",value = "当前页面显示数量", required = true, dataType = "Long",defaultValue = "10"),
        @ApiImplicitParam(name = "id",value = "主键", dataType = "String")
    })
    </#if>
    @GetMapping("/listPageByWrapper")
    public Result<Page<${entity}>> listPage(${entity}Qo ${customizefo.capEntity}Qo){
        return Result.success(${customizefo.capServiceName}.listPage(${customizefo.capEntity}Qo));
    }

    <#if swagger>
    @ApiOperation(value = "新增", notes = "新增用户")
    @ApiOperationSupport(order = 40)
    </#if>
    @PostMapping
    public Result<${entity}> save(@RequestBody ${entity} ${customizefo.capEntity}){
    return ${customizefo.capServiceName}.save(${customizefo.capEntity}) ? Result.success(${customizefo.capEntity}) : Result.failed();
    }

    <#if swagger>
    @ApiOperation(value = "主键删除", notes = "根据主键id删除")
    @ApiOperationSupport(order = 50)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "String")
    </#if>
    @DeleteMapping("/{id}")
    public Result<?> deleted(@PathVariable String id){
    return ${customizefo.capServiceName}.removeById(id) ? Result.success() : Result.failed();
    }


    <#if swagger>
    @ApiOperation(value = "主键批量删除", notes = "根据主键id主键批量删除")
    @ApiOperationSupport(order = 60)
    </#if>
    @DeleteMapping("/deletedBatch")
    public Result<?> deletedBatch(@RequestParam("ids") List<String> ids){
        return ${customizefo.capServiceName}.removeBatchByIds(ids) ? Result.success() : Result.failed();
    }

    <#if swagger>
    @ApiOperation(value = "对象删除", notes = "根据根据对象删除不一定必须是主键返回操作成功和失败")
    @ApiOperationSupport(order = 70)
    </#if>
    @DeleteMapping("/deletedByT")
    public Result<?> deletedByT(${entity}Qo ${customizefo.capEntity}Qo){
    return ${customizefo.capServiceName}.deleteByT(${customizefo.capEntity}Qo);
    }

    <#if swagger>
    @ApiOperation(value = "更新", notes = "更新用户")
    @ApiOperationSupport(order = 80)
    </#if>
    @PutMapping
    public Result<${entity}> update(@RequestBody ${entity} ${customizefo.capEntity}){
        return ${customizefo.capServiceName}.updateById(${customizefo.capEntity}) ? Result.success(${customizefo.capEntity}) : Result.failed();
    }


}
</#if>
