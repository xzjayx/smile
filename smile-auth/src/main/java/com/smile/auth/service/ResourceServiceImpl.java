package com.smile.auth.service;

import cn.hutool.core.collection.CollUtil;
import com.smile.basic.redis.constant.AuthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类，因为用户目前模拟的假的用户，UserServiceImpl初始化之后的 initData
 * 因为是RBAC模型，用户 ---> 角色  ---> 资源
 * 所以这里模拟角色和资源URL进行配对
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello/adminHello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/api/test/testHello", CollUtil.toList( "TEST"));
        redisTemplate.opsForHash().putAll(AuthConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
