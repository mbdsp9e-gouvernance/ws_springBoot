package com.project.gouvernance.service.society;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gouvernance.exception.SocietyCollectionException;
import com.project.gouvernance.model.Society;
import com.project.gouvernance.repository.SocietyRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private SocietyRepository societyRepo;

    @Override
    public void createSociety(Society society) throws ConstraintViolationException, SocietyCollectionException {
        Optional<Society> optionalSociety = societyRepo.findByName(society.getName());

        if (optionalSociety.isPresent())
            throw new SocietyCollectionException(SocietyCollectionException.SocietyAlreadyExists());
        else {
            societyRepo.save(society);
        }
    }

    @Override
    public List<Society> getAllSociety() {
        List<Society> societyList = societyRepo.findAll();
        if (societyList.size() > 0)
            return societyList;
        else
            return new ArrayList<Society>();
    }

    @Override
    public Society getSociety(String id) throws SocietyCollectionException {
        Optional<Society> optionalSociety = societyRepo.findById(id);
        if (!optionalSociety.isPresent())
            throw new SocietyCollectionException(SocietyCollectionException.NotFoundException(id));
        else
            return optionalSociety.get();
    }

    @Override
    public void updateSociety(String id, Society society) throws SocietyCollectionException {
        Optional<Society> optionalSociety = societyRepo.findById(id);
        Optional<Society> optionalSocietyRef = societyRepo.findByName(society.getName());
        if (optionalSociety.isPresent()) {
            if (optionalSocietyRef.isPresent() && !optionalSocietyRef.get().getId().equals(id))
                throw new SocietyCollectionException(SocietyCollectionException.SocietyAlreadyExists());

            Society societyUpdate = optionalSociety.get();
            societyUpdate
                    .setName(
                            society.getName() != null ? society.getName() : societyUpdate.getName());
            societyUpdate.setNif(society.getNif() != null ? society.getNif() : societyUpdate.getNif());
            societyUpdate.setStat(
                    society.getStat() != null ? society.getStat() : societyUpdate.getStat());
            societyUpdate
                    .setPassword(society.getPassword() != null ? society.getPassword() : societyUpdate.getPassword());

            societyRepo.save(societyUpdate);
        } else
            throw new SocietyCollectionException(SocietyCollectionException.NotFoundException(id));
    }
}
