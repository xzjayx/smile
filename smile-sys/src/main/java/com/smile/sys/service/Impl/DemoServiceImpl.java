package com.smile.sys.service.Impl;

import com.smile.sys.entity.Demo;
import com.smile.sys.mapper.DemoMapper;
import com.smile.sys.service.DemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smile.basic.core.qo.sys.DemoQo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smile.basic.core.base.Result;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author smile
 * @since 2022-12-27 17:11:41
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

     @Override
     public Demo findOne(DemoQo demoQo) {
     QueryWrapper<Demo> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", demoQo.getId());
        queryWrapper.allEq(map);
        return getOne(queryWrapper);
     }

     @Override
     public Page<Demo> listPage(DemoQo demoQo) {
        QueryWrapper<Demo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(demoQo.getId()), "id", demoQo.getId());
        Page<Demo> page = new Page<>(demoQo.getBaseQueryQo().getCurrent(),
        demoQo.getBaseQueryQo().getSize());
        return page(page,queryWrapper);
     }

     @Override
     public Result<?> deleteByT(DemoQo demoQo) {
        LambdaQueryWrapper<Demo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(demoQo.getId()),Demo::getId,demoQo.getId());
        return remove(queryWrapper) ? Result.success() : Result.failed();
     }


}
