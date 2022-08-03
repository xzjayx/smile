package com.smile.basic.core.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.smile.basic.core.dto.sys.UserDTO;
/**
 * 关于用户的而一些常用判断util
 * */
public class TokenUtil {


    public static  boolean isUserExist(UserDTO userDTO){
        return ObjectUtil.isNotEmpty(userDTO) && StrUtil.isNotEmpty(userDTO.getId());
    }
}
