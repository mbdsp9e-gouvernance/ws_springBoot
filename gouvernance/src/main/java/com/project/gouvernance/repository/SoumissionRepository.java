package com.project.gouvernance.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.gouvernance.model.Soumission;

@Repository
public interface SoumissionRepository extends MongoRepository<Soumission, String> {

    @Query("{'society.id': ?0}")
    List<Soumission> findAllBySocietyId(String societyId);

    @Query("{'status': ?0}")
    List<Soumission> findAllByStatus(Integer status);

    @Query("{'tender.id': ?0}")
    List<Soumission> findAllByTenderId(String id);

    @Query("{'status': ?0,'society.id': ?1}")
    List<Soumission> findAllByStatusAndSocietyId(Integer status, String societyId);
}
