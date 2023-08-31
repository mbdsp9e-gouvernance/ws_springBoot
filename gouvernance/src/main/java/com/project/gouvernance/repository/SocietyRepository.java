package com.project.gouvernance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.gouvernance.model.Society;

@Repository
public interface SocietyRepository extends MongoRepository<Society, String> {

    @Query("{'name': ?0}")
    Optional<Society> findByName(String name);
}
