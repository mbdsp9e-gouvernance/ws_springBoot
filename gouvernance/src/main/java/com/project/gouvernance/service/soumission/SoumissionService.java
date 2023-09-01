package com.project.gouvernance.service.soumission;

import java.util.List;

import com.project.gouvernance.exception.SoumissionCollectionException;
import com.project.gouvernance.model.Soumission;

public interface SoumissionService {

    public void createSoumission(Soumission soumission) throws Exception;

    public List<Soumission> getAllSoumission();

    public List<Soumission> getAllSoumissionByFilter(String status, String societyId, String tenderId);

    public Soumission getSoumission(String id) throws SoumissionCollectionException;

    public void updateSoumission(String id, Soumission soumission) throws SoumissionCollectionException;

}
