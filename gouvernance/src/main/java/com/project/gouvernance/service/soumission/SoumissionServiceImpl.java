package com.project.gouvernance.service.soumission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gouvernance.exception.SoumissionCollectionException;
import com.project.gouvernance.model.Soumission;
import com.project.gouvernance.repository.SoumissionRepository;

@Service
public class SoumissionServiceImpl implements SoumissionService {

    @Autowired
    private SoumissionRepository soumissionRepo;

    @Override
    public void createSoumission(Soumission soumission) throws Exception {
        try {
            soumission.setDateSoumission(new Date(System.currentTimeMillis()));
            soumissionRepo.save(soumission);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Soumission> getAllSoumission() {
        List<Soumission> soumissions = soumissionRepo.findAll();
        if (soumissions.size() > 0)
            return soumissions;
        else
            return new ArrayList<Soumission>();
    }

    @Override
    public List<Soumission> getAllSoumissionByFilter(String status, String societyId, String tenderId) {
        List<Soumission> soumission = new ArrayList<>();

        if (societyId != null && status == null) {
            soumission = soumissionRepo.findAllBySocietyId(societyId);
        } else if (status != null && societyId == null) {
            soumission = soumissionRepo.findAllByStatus(Integer.parseInt(status));
        } else if (societyId != null && status != null) {
            soumission = soumissionRepo.findAllByStatusAndSocietyId(Integer.parseInt(status), societyId);
        } else if (tenderId != null) {
            soumission = soumissionRepo.findAllByTenderId(tenderId);
        } else {
            soumission = soumissionRepo.findAll();
        }

        return soumission;
    }

    @Override
    public Soumission getSoumission(String id) throws SoumissionCollectionException {
        Optional<Soumission> optionalSoumission = soumissionRepo.findById(id);
        if (!optionalSoumission.isPresent())
            throw new SoumissionCollectionException(SoumissionCollectionException.NotFoundException(id));
        else
            return optionalSoumission.get();
    }

    @Override
    public void updateSoumission(String id, Soumission soumission) throws SoumissionCollectionException {
        Optional<Soumission> optionalSoumission = soumissionRepo.findById(id);
        if (optionalSoumission.isPresent()) {
            Soumission soumissionUpdate = optionalSoumission.get();
            soumissionUpdate.setSociety(soumissionUpdate.getSociety());
            soumissionUpdate.setDateSoumission(soumissionUpdate.getDateSoumission());
            soumissionUpdate.setStatus(soumission.getStatus());
            soumissionRepo.save(soumissionUpdate);
        } else
            throw new SoumissionCollectionException(SoumissionCollectionException.NotFoundException(id));

    }

}
