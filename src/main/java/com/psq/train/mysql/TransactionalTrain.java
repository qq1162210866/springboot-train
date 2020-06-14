package com.psq.train.mysql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TransactionalTrain.java
 * Description: SpringBoot数据库事务练习
 *
 * @author Peng Shiquan
 * @date 2020/6/13
 */
@Service
public class TransactionalTrain extends SqlSessionDaoSupport {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    //@PostConstruct
    public void mybatisTransactionalTrain() {
        //开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        TestUser testUser = new TestUser();
        testUser.setId(3);
        testUser.setName("qsp");
        testUser.setPassword("87654321");
        try {
            Integer saveResult = sqlSession.insert("com.psq.train.dao.UserMapper.insertUser", testUser);
            //重复插入，id相同一定回产生异常
            Integer saveResult2 = sqlSession.insert("com.psq.train.dao.UserMapper.insertUser", testUser);
        } catch (Exception e) {
            System.err.println("事务开始回滚");
            sqlSession.rollback();
            System.err.println("事务回滚成功");
            throw e;
        } finally {
            sqlSession.close();
        }
    }
}
