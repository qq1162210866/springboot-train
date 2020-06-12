package com.psq.train.mysql;

/**
 * TestUser.java
 * Description: MySQL中user表的实体类
 *
 * @author Peng Shiquan
 * @date 2020/5/30
 */
public class TestUser {
    private int id;
    private String name;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
