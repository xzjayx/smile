package com.smile.basic.core.util;

import cn.hutool.core.util.ObjectUtil;
import com.smile.basic.core.config.exception.CommonRuntimeException;
import com.smile.basic.core.enums.ResultEnum;

/**
 * @author xiezhi
 */
public class AssertUtil {

    public static void assertNotNull(Object obj){
        if(ObjectUtil.isNull(obj)){
            throw new CommonRuntimeException(ResultEnum.OBJECT_NOT_NULL);
        }
    }


}
