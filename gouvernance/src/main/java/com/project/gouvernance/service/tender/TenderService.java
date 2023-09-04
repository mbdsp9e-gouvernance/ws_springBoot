package com.project.gouvernance.service.tender;

import java.util.List;

import com.project.gouvernance.exception.TenderCollectionException;
import com.project.gouvernance.model.Tender;

import jakarta.validation.ConstraintViolationException;

public interface TenderService {

    public void createTender(Tender tender) throws ConstraintViolationException, TenderCollectionException;

    public List<Tender> getAllTender();

    public List<Tender> getAllTenderFilter(String date1, String date2, String status, String ascending);

    public Tender getTender(String id) throws TenderCollectionException;

    public void updateTender(String id, Tender tender) throws TenderCollectionException;

}
