package com.psq.train;

import com.psq.train.mysql.TransactionalTrain;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TrainApplication.java
 * Description: SpringBoot启动类
 *
 * @author Peng Shiquan
 * @date 2020/6/13
 */
@SpringBootApplication
@MapperScan("com.psq.train.dao")
public class TrainApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }
}
