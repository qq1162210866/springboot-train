package com.psq.train.redis;

import com.psq.train.mysql.TestUser;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * RedisTrain.java
 * Description: Redis练习
 *
 * @author Peng Shiquan
 * @date 2020/5/31
 */
@Component
public class RedisTrain {

    /**
     * 使用StringRedisTemplate，类似于RedisTemplate<String,String>
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Description: Redis练习的主方法
     *
     * @param
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/5/31
     */
    //@PostConstruct
    public void redisTrainmain() {
        TestUser testUser = new TestUser();
        testUser.setId(3);
        testUser.setName("张三");
        testUser.setPassword("1234567");
        System.err.println("下面开始Redis插入");
        redisTrainInsert(testUser);
        System.err.println("下面开始查询");
        redisTrainSelect(testUser.getName());
    }

    /**
     * Description: redis插入方法
     *
     * @param testUser
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/6/1
     */
    public void redisTrainInsert(TestUser testUser) {
        String key = testUser.getName();
        String value = testUser.toString();
        stringRedisTemplate.opsForValue().set(key, value);
        System.err.println("存储成功，存储对象为:key:" + key + ",value:" + value);
    }

    /**
     * Description: redis查询方法
     *
     * @param name
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/6/1
     */
    public void redisTrainSelect(String name) {
        System.err.println("需要查询的name为:" + name);
        System.err.println(name);
        String value = stringRedisTemplate.opsForValue().get(name);
        System.err.println("查询到到结果为:" + value);
    }
}
