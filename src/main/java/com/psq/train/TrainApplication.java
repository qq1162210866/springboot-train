package com.psq.train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: SpringBoot启动类
 *
 * @param null
 * @return
 * @Author: Peng Shiquan
 * @Date: 2020/5/28
 */
@SpringBootApplication
@MapperScan("com.psq.train.dao")
public class TrainApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }
}
