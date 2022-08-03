package com.smile.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smile.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author smile
 * @since 2022-05-24 14:42:29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
