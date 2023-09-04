package com.project.gouvernance.service.critere;

import java.util.List;

import com.project.gouvernance.exception.CritereCollectionException;
import com.project.gouvernance.model.Critere;

import jakarta.validation.ConstraintViolationException;

public interface CritereService {

    public void createCritere(Critere critere) throws ConstraintViolationException, CritereCollectionException;

    public List<Critere> getAllCritere();

    public Critere getCritere(String id) throws CritereCollectionException;

    public void updateCritere(String id, Critere critere) throws CritereCollectionException;

}
