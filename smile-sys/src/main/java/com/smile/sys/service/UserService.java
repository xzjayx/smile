package com.smile.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smile.basic.core.base.Result;
import com.smile.basic.core.qo.sys.UserQo;
import com.smile.sys.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author smile
 * @since 2022-05-24 14:42:29
 */
public interface UserService extends IService<User> {

    /**
     * 根据qoEntity查询唯一对象
     * */
    User findOne(UserQo userQo);

    /**
     * 根据qoEntity查询对象分页
     * */
    Page<User> listPage(UserQo userQo);

    /**
     * 根据qoEntity删除符合对象
     * */
    Result<?> deleteByT(UserQo userQo);
}
