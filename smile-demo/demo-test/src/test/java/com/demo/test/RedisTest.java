package com.demo.test;

import com.demo.test.DemoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author :xiezhi
 * @date : 2023/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test() {
        //金琥SAAS正式环境
        //万能验证码 为1表示开-->不发短信，其余表示关闭发短信
        redisTemplate.opsForValue().set("wanneng_verif_code_switch", "1");

        Object obj = redisTemplate.opsForValue().get("wanneng_verif_code_switch");
        if (null != obj)
        {
            String sw = String.valueOf(obj);
            System.out.println(sw);
        }
    }
}
