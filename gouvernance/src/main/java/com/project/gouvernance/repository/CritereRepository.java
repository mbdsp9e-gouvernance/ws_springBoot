package com.project.gouvernance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.gouvernance.model.Critere;

@Repository
public interface CritereRepository extends MongoRepository<Critere, String> {

    @Query("{'entitle': ?0}")
    Optional<Critere> findByEntitle(String critere);
}
