package com.smile.sys.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smile.basic.core.base.Result;
import com.smile.basic.core.qo.sys.UserQo;
import com.smile.sys.entity.User;
import com.smile.sys.mapper.UserMapper;
import com.smile.sys.service.UserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author smile
 * @since 2022-05-24 14:42:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User findOne(UserQo userQo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", userQo.getId());
        queryWrapper.allEq(map);
        return getOne(queryWrapper);
    }

    @Override
    public Page<User> listPage(UserQo userQo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(userQo.getId()), "id", userQo.getId());
        Page<User> page = new Page<>(userQo.getBaseQueryQo().getCurrent(),
                userQo.getBaseQueryQo().getSize());
        return page(page,queryWrapper);
    }

    @Override
    public Result<?> deleteByT(UserQo userQo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(userQo.getId()),User::getId,userQo.getId());
        return remove(queryWrapper) ? Result.success() : Result.failed();
    }
}
