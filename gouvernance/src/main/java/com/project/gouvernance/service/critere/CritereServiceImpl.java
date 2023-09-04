package com.project.gouvernance.service.critere;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gouvernance.exception.CritereCollectionException;
import com.project.gouvernance.model.Critere;
import com.project.gouvernance.repository.CritereRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class CritereServiceImpl implements CritereService {

    @Autowired
    private CritereRepository critereRepo;

    @Override
    public void createCritere(Critere critere) throws ConstraintViolationException, CritereCollectionException {
        Optional<Critere> optionalCritere = critereRepo.findByEntitle(critere.getEntitle());

        if (optionalCritere.isPresent())
            throw new CritereCollectionException(CritereCollectionException.CritereAlreadyExists());
        else {
            critereRepo.save(critere);
        }
    }

    @Override
    public List<Critere> getAllCritere() {
        List<Critere> criteres = critereRepo.findAll();
        if (criteres.size() > 0)
            return criteres;
        else
            return new ArrayList<Critere>();
    }

    @Override
    public Critere getCritere(String id) throws CritereCollectionException {
        Optional<Critere> optionalCritere = critereRepo.findById(id);
        if (!optionalCritere.isPresent())
            throw new CritereCollectionException(CritereCollectionException.NotFoundException(id));
        else
            return optionalCritere.get();
    }

    @Override
    public void updateCritere(String id, Critere critere) throws CritereCollectionException {
        Optional<Critere> optionalCritere = critereRepo.findById(id);
        Optional<Critere> optionalCritereRef = critereRepo.findByEntitle(critere.getEntitle());
        if (optionalCritere.isPresent()) {
            if (optionalCritereRef.isPresent() && !optionalCritereRef.get().getId().equals(id))
                throw new CritereCollectionException(CritereCollectionException.CritereAlreadyExists());

            Critere critereUpdate = optionalCritere.get();
            critereUpdate
                    .setEntitle(
                            critere.getEntitle() != null ? critere.getEntitle() : critereUpdate.getEntitle());
            critereUpdate.setDescription(
                    critere.getDescription() != null ? critere.getDescription() : critereUpdate.getDescription());

            critereRepo.save(critereUpdate);
        } else
            throw new CritereCollectionException(CritereCollectionException.NotFoundException(id));

    }
}
