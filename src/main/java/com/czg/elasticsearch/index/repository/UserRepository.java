package com.czg.elasticsearch.index.repository;

import com.czg.elasticsearch.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author czg
 * @date 2023/10/30 11:29
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

}
