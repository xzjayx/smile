package com.smile.basic.core.util;

/*import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;*/

public class BeanUtil {

    /**
     * 两个类相同属性值复制并返回新对象
     *
     * @param source 复制源
     * @param clazz  目标类
     */
    public static <T> T copy(Object source, Class<T> clazz,String... ignoreProperties) {
        if (source != null) {
            T t;
            try {
                t = clazz.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(source, t,ignoreProperties);
                return t;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

  /*  *
     * 两个类相同属性值复制并返回新对象
     *
     * @param source 复制源
     * @param clazz  目标类
     * @return java.util.Optional<T>

    public static <T> Optional<T> copyOptional(Object source, Class<T> clazz) {
        if (source != null) {
            T t;
            try {
                t = clazz.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(source, t);
                return Optional.of(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    *
     * 两个类相同属性值复制并返回新对象
     *
     * @param sources 复制源
     * @param clazz   目标类

    public static <T> List<T> copyList(List<?> sources, Class<T> clazz) {
        if (sources != null && sources.size() > 0) {
            List<T> items = new ArrayList<>();
            try {
                T t = null;
                for (Object source : sources) {
                    t = clazz.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(source, t);
                    items.add(t);
                }
                return items;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    *
     * JSON 格式字符串转换为对象
     *
     * @param json  json格式字符串
     * @param clazz 对象

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StrUtil.isNotEmpty(json)) {
            return JSON.parseObject(json, clazz);
        }
        return null;
    }

    *
     * JSON 格式字符串转换为对象集合
     *
     * @param jsonStr json格式字符串数组
     * @param clazz   对象

    public static <T> List<T> parseList(String jsonStr, Class<T> clazz) {
        if (!StrUtil.isEmpty(jsonStr)) {
            List<T> result = new ArrayList<>();
            JSONArray array = JSONArray.parseArray(jsonStr);
            for (int i = 0; i < array.size(); i++) {
                result.add(JSON.toJavaObject((JSONObject) array.get(i), clazz));
            }
            return result;
        }
        return null;
    }

    *
     * 获取对象指定属性值
     *
     * @param bean 目标对象
     * @param name 属性名称

    public static String getProperty(Object bean, String name) {
        try {
            return BeanUtils.getProperty(bean, name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    *
     * Object 数组转换为实体对象工具方法
     *
     * @param objs Object 数组
     * @param clz  实体类
     *
    public static <T> T transEntity(Object[] objs, Class<T> clz) {
        T entity = null;
        try {
            // 获取泛型实例
            entity = clz.newInstance();
            // 获取所有属性，按照属性顺序来赋值
            Field[] fields = clz.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                // 实时数据个数大于字段下标时赋值
                if (objs.length > j) {
                    // 解决 No value specified for 'Date'异常
                    ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                    // 解决 No value specified for 'BigDecimal'异常
                    BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                    BeanUtils.setProperty(entity, fields[j].getName(), objs[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    *
     * 将map转换为实体类
     * 注意map的key必须为实体的属性名
     *
     * @param map
     * @param clz
     * @return: T
     * @author: zhaojiaxing
     * @createTime: 2019/10/23 0023 14:39

    public static <T> T convertMap(Map map, Class<T> clz) {
        T entity = null;
        try {
            // 获取泛型实例
            entity = clz.newInstance();
            // 获取所有属性，按照属性顺序来赋值
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                // 获取参数类型名字
                String filedTypeName = field.getType().getName();
                // 解决 No value specified for 'Date'异常
                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                // 解决 No value specified for 'BigDecimal'异常
                BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                Object value = map.get(fieldName);
                // 判断是否为时间类型，使用equalsIgnoreCase比较字符串，不区分大小写
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String dateTimestamp = value.toString();
                    if (!dateTimestamp.equalsIgnoreCase("null")) {
                        value =  DateTimeUtil.strToDate(dateTimestamp,DateTimeUtil.YYYY_MM_DDHHMMSS);
                    }
                }
                BeanUtils.setProperty(entity, fieldName, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }


    *
     * 数组对象转实体集合工具方法
     *
     * @param items 数组对象
     * @param clz   实体类
     *
    public static <T> List<T> transCollects(List<Object[]> items, Class<T> clz) {
        List<T> result = new ArrayList<>();
        try {
            if (null != items && items.size() > 0) {
                // 循环调用数组转对象接口
                items.forEach(item -> result.add(transEntity(item, clz)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
