package com.project.gouvernance.service.society;

import java.util.List;

import com.project.gouvernance.exception.SocietyCollectionException;
import com.project.gouvernance.model.Society;

import jakarta.validation.ConstraintViolationException;

public interface SocietyService {

    public void createSociety(Society society) throws ConstraintViolationException, SocietyCollectionException;

    public List<Society> getAllSociety();

    public Society getSociety(String id) throws SocietyCollectionException;

    public void updateSociety(String id, Society society) throws SocietyCollectionException;

}
