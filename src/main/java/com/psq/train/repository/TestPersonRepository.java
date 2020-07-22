package com.psq.train.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * TestPersonRepository.java
 * Description:  TestPerson实体类的Repository
 *
 * @author Peng Shiquan
 * @date 2020/7/20
 */
@Component
public interface TestPersonRepository extends ElasticsearchRepository<TestPerson, String> {

}
