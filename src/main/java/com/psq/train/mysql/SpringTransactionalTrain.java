package com.psq.train.mysql;

import com.psq.train.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * SpringTransactionalTrain.java
 * Description:  使用Spring来管理事务
 *
 * @author Peng Shiquan
 * @date 2020/6/13
 */
@Component
public class SpringTransactionalTrain {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void mybatisTransactionalTrain() {
        TestUser testUser = new TestUser();
        testUser.setId(3);
        testUser.setName("psq");
        testUser.setPassword("9876");
        System.err.println(testUser.toString());
        try {
            Integer saveResult = userMapper.insertUser(testUser);
            System.err.println("插入成功");
            //一定会报异常
            Integer saveResult2 = userMapper.insertUser(testUser);
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("开始回滚");
            throw e;
        }

    }
}
