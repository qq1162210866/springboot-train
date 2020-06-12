package com.psq.train.postgresql;

import com.psq.train.dao.UserMapper;
import com.psq.train.mysql.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * PostgreSQLTest.javaa
 * Description:  PostgreSQL的测试demo
 *
 * @author Peng Shiquan
 * @date 2020/6/12
 */
@Component
public class PostgreSQLTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * Description: PostgreSQL的测试方法
     *
     * @param
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/6/12
     */
    @PostConstruct
    public void selectAllUser() {
        List<TestUser> testUserList = userMapper.getAllUser();
        for (TestUser testUser : testUserList) {
            System.err.println(testUser.toString());
        }
    }

}
