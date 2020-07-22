package com.psq.train.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * RestClientConfig.java
 * Description:  ESclient的配置类
 *
 * @author Peng Shiquan
 * @date 2020/7/20
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.psq.train.repository")
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        /**
         * 使用构造器来提供集群地址，设置默认值或者启用SSL
         */
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("111.229.157.173:9200")
                .build();
        /**
         * 创建RestHighLevelClient
         */
        return RestClients.create(clientConfiguration).rest();
    }
}
