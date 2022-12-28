package com.smile.sys.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smile.sys.entity.Demo;
import com.smile.sys.service.DemoService;
import com.smile.basic.core.qo.sys.DemoQo;
import com.smile.basic.core.base.Result;
import java.util.List;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
*
*  <p>
*     前端控制器
*  </p>
*
* @author smile
* @since 2022-12-27 17:11:41
*/
@Api(tags = "Demo前端控制器")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService  demoService;

    @ApiOperation(value = "根据主键id查询Demo")
    @ApiOperationSupport(order = 10)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "String")
    @GetMapping("/getDemoById")
    public Result<Demo> getDemoById(String id){
        return Result.success(demoService.getById(id));
    }

    @ApiOperation(value = "对象查询", notes = "通过条件查询对象只返回一条记录")
    @ApiOperationSupport(order = 20,ignoreParameters = {"baseQueryQo"})
    @GetMapping("/findOne")
    public Result<Demo> findOne(DemoQo demoQo){
        return Result.success(demoService.findOne(demoQo));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询不带条件仅仅内置分页对象")
    @ApiOperationSupport(order = 30,includeParameters = {"baseQueryQo.current","baseQueryQo.size","id"} )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "baseQueryQo.current",value = "当前页码数", required = true, dataType = "Long",defaultValue = "1"),
        @ApiImplicitParam(name = "baseQueryQo.size",value = "当前页面显示数量", required = true, dataType = "Long",defaultValue = "10"),
        @ApiImplicitParam(name = "id",value = "主键", dataType = "String")
    })
    @GetMapping("/listPageByWrapper")
    public Result<Page<Demo>> listPage(DemoQo demoQo){
        return Result.success(demoService.listPage(demoQo));
    }

    @ApiOperation(value = "新增", notes = "新增用户")
    @ApiOperationSupport(order = 40)
    @PostMapping
    public Result<Demo> save(@RequestBody Demo demo){
    return demoService.save(demo) ? Result.success(demo) : Result.failed();
    }

    @ApiOperation(value = "主键删除", notes = "根据主键id删除")
    @ApiOperationSupport(order = 50)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "String")
    @DeleteMapping("/{id}")
    public Result<?> deleted(@PathVariable String id){
    return demoService.removeById(id) ? Result.success() : Result.failed();
    }


    @ApiOperation(value = "主键批量删除", notes = "根据主键id主键批量删除")
    @ApiOperationSupport(order = 60)
    @DeleteMapping("/deletedBatch")
    public Result<?> deletedBatch(@RequestParam("ids") List<String> ids){
        return demoService.removeBatchByIds(ids) ? Result.success() : Result.failed();
    }

    @ApiOperation(value = "对象删除", notes = "根据根据对象删除不一定必须是主键返回操作成功和失败")
    @ApiOperationSupport(order = 70)
    @DeleteMapping("/deletedByT")
    public Result<?> deletedByT(DemoQo demoQo){
    return demoService.deleteByT(demoQo);
    }

    @ApiOperation(value = "更新", notes = "更新用户")
    @ApiOperationSupport(order = 80)
    @PutMapping
    public Result<Demo> update(@RequestBody Demo demo){
        return demoService.updateById(demo) ? Result.success(demo) : Result.failed();
    }


}
