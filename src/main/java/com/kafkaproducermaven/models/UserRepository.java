package com.kafkaproducermaven.models;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository 

public interface UserRepository extends CassandraRepository<User, Integer> {

    @Query("SELECT *FROM user WHERE age<=?0 ALLOW FILTERING")
    List<User> findByAgeLessThanEqual(int age);
}
