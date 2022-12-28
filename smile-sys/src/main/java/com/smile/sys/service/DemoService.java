package com.smile.sys.service;

import com.smile.sys.entity.Demo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smile.basic.core.qo.sys.DemoQo;
import com.smile.basic.core.base.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author smile
 * @since 2022-12-27 17:11:41
 */
public interface DemoService extends IService<Demo> {

    /**
    * 根据qoEntity查询唯一对象
    * */
    Demo findOne(DemoQo demoQo);

    /**
    * 根据qoEntity查询对象分页
    * */
    Page<Demo> listPage(DemoQo demoQo);

    /**
    * 根据qoEntity删除符合对象
    * */
    Result<?> deleteByT(DemoQo demoQo);
}
