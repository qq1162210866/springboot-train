package com.psq.train.dao;

import com.psq.train.mysql.TestUser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserMapper.java
 * Description: user表的mapper映射
 *
 * @author Peng Shiquan
 * @date 2020/6/12
 */

public interface UserMapper {

    /**
     * Description: 查询所有用户信息
     *
     * @param
     * @return java.util.List<com.psq.train.mysql.TestUser>
     * @Author: Peng Shiquan
     * @Date: 2020/6/12
     */
    List<TestUser> getAllUser();

    /**
     * Description: 插入一个新的用户
     *
     * @param
     * @return java.lang.Integer
     * @Author: Peng Shiquan
     * @Date: 2020/6/13
     */
    Integer insertUser(TestUser testUser);
}
