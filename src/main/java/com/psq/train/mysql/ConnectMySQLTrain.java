package com.psq.train.mysql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ConnectMySQLTrain.java
 * Description: 使用基础的组件连接MySQL
 *
 * @author Peng Shiquan
 * @date 2020/5/30345
 */
@Component
public class ConnectMySQLTrain {


    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * Description: 测试MySQL数据读取
     *
     * @param
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/5/31
     */
    //@PostConstruct
    public void testMySQL() {
        String sql = "SELECT id,name,password FROM `user`";
        List<TestUser> testUserList = jdbcTemplate.query(sql, new RowMapper<TestUser>() {
            @Override
            public TestUser mapRow(ResultSet resultSet, int i) throws SQLException {
                TestUser testUser = new TestUser();
                testUser.setId(resultSet.getInt("id"));
                testUser.setName(resultSet.getString("name"));
                testUser.setPassword(resultSet.getString("password"));
                return testUser;
            }
        });
        System.err.println("查询成功，查询结果如下：" + testUserList.toString());
    }

}
