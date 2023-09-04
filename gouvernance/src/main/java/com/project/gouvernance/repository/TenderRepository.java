package com.project.gouvernance.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.gouvernance.model.Tender;

@Repository
public interface TenderRepository extends MongoRepository<Tender, String> {

    @Query("{'reference': ?0}")
    Optional<Tender> findByReference(String reference);

    @Query("{'dateLimit': {'$gte': ?0, '$lte': ?1}}")
    List<Tender> findBetweenTwoDate(Date date1, Date date2, Sort sort);

    @Query("{'soumission.status': ?0}")
    List<Tender> findBySoumissionStatus(Integer status, Sort sort);

    @Query("{'dateLimit': {'$gte': ?0, '$lte': ?1},'soumission.status': ?2}")
    List<Tender> findBetweenTwoDateAndSoumissionStatus(Date date1, Date date2, Integer status, Sort sort);

}
