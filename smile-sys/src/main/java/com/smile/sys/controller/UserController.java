package com.smile.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.smile.sys.entity.User;
import com.smile.sys.service.UserService;
import com.smile.basic.core.base.Result;
import com.smile.basic.core.dto.sys.UserDTO;
import com.smile.basic.core.qo.sys.UserQo;
import com.smile.basic.core.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
*  <p>
*    用户信息表 前端控制器
*  </p>
*
* @author smile
* @since 2022-05-24 14:42:29
*/
@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * paramType = "path" ==> @PathVariable URL占位符参数  x-www-form-urlencoded
     * */
    @ApiOperation(value = "主键查询", notes = "根据主键id查询User")
    @ApiOperationSupport(order = 10)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "String")
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable String id){
        return Result.success(userService.getById(id));
    }


    @ApiOperation(value = "对象查询", notes = "通过条件查询对象只返回一条记录")
    @ApiOperationSupport(order = 20,ignoreParameters = {"baseQueryQo"})
    @GetMapping("/findOne")
    public Result<User> findOne(UserQo userQo){
        return Result.success(userService.findOne(userQo));
    }



    /**过滤参数 includeParameters是knife4j 和 @ApiImplicitParams这个是swagger注解可以,可以结合使用这样就可以排除很多别的不需要得属性
     * @ApiOperationSupport 相对于@ApiImplicitParams优先级高一些，主要是对ApiOperationSupport做一些补充包含得元素做一些补充说明
     * 因为如果表单请求是直接接受一个实例类来说，那么该实体类所有得参数都会被文档显示到
     *
     * */
    @ApiOperation(value = "分页查询", notes = "分页查询不带条件仅仅内置分页对象",httpMethod = "GET")
    @ApiOperationSupport(order = 30,includeParameters = {"baseQueryQo.current","baseQueryQo.size","id"} )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "baseQueryQo.current",value = "当前页码数", required = true, dataType = "Long",defaultValue = "1"),
        @ApiImplicitParam(name = "baseQueryQo.size",value = "当前页面显示数量", required = true, dataType = "Long",defaultValue = "10"),
        @ApiImplicitParam(name = "id",value = "主键", dataType = "String")
     })
    @GetMapping("/listPageByWrapper")
    public Result<Page<User>> listPage(UserQo userQo){
        return Result.success(userService.listPage(userQo));
    }



    @ApiOperation(value = "新增", notes = "新增用户", httpMethod = "POST")
    @ApiOperationSupport(order = 40)
    @PostMapping
    public Result<User> save(@RequestBody User user){
      return userService.save(user) ? Result.success(user) : Result.failed();
    }

    @ApiOperation(value = "主键删除", notes = "根据主键id删除")
    @ApiOperationSupport(order = 50)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "String")
    @DeleteMapping("/{id}")
    public Result<?> deleted(@PathVariable String id){
        return userService.removeById(id) ? Result.success() : Result.failed();
    }



    @ApiOperation(value = "主键批量删除", notes = "根据主键id主键批量删除")
    @ApiOperationSupport(order = 60)
    @DeleteMapping("/deletedBatch")
    public Result<?> deletedBatch(@RequestParam("ids") List<String> ids){
        return userService.removeBatchByIds(ids) ? Result.success() : Result.failed();
    }


    @ApiOperation(value = "对象删除", notes = "根据根据对象删除不一定必须是主键返回操作成功和失败")
    @ApiOperationSupport(order = 70)
    @DeleteMapping("/deletedByT")
    public Result<?> deletedByT(UserQo userQo){
        return userService.deleteByT(userQo);
    }


    @ApiOperation(value = "更新", notes = "更新用户")
    @ApiOperationSupport(order = 80)
    @PutMapping
    public Result<User> update(@RequestBody User user){
        return userService.updateById(user) ? Result.success(user) : Result.failed();
    }




    @GetMapping("/loadUserByUsername/{username}")
    public Result<UserDTO> loadUserByUsername(@PathVariable String username){
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(User::getUsername,username);
        User one = userService.getOne(lambdaQueryWrapper);
        System.out.println(one.toString());
        //pojo he dto 转换
        UserDTO userDTO = BeanUtil.copy(one, UserDTO.class);
        return Result.success(userDTO);
    }



}
