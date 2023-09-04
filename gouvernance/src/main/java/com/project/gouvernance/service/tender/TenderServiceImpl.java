package com.project.gouvernance.service.tender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.gouvernance.exception.TenderCollectionException;
import com.project.gouvernance.model.Tender;
import com.project.gouvernance.repository.TenderRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepo;

    @Override
    public void createTender(Tender tender) throws ConstraintViolationException, TenderCollectionException {
        Optional<Tender> optionalTender = tenderRepo.findByReference(tender.getReference());

        if (optionalTender.isPresent())
            throw new TenderCollectionException(TenderCollectionException.TenderAlreadyExists());
        else {
            tender.setDateEmission(new Date(System.currentTimeMillis()));
            tenderRepo.save(tender);
        }
    }

    @Override
    public List<Tender> getAllTender() {
        List<Tender> tenders = tenderRepo.findAll();
        if (tenders.size() > 0)
            return tenders;
        else
            return new ArrayList<Tender>();
    }

    @Override
    public List<Tender> getAllTenderFilter(String date1, String date2, String status, String ascendingString) {
        List<Tender> tenders = new ArrayList<Tender>();
        boolean ascending = false;
        if (ascendingString != null && ascendingString.equalsIgnoreCase("true")) {
            ascending = true;
        }

        Sort sort = ascending ? Sort.by(Sort.Direction.ASC, "dateLimit")
                : Sort.by(Sort.Direction.DESC, "dateLimit");

        if (date1 != null && date2 != null && status == null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate1 = dateFormat.parse(date1);
                Date parsedDate2 = dateFormat.parse(date2);
                tenders = tenderRepo.findBetweenTwoDate(parsedDate1, parsedDate2, sort);

            } catch (ParseException e) {
                // Handle parsing exception
            }
        } else if (status != null && date1 == null && date2 == null) {
            tenders = tenderRepo.findBySoumissionStatus(Integer.parseInt(status), sort);
        } else if (date1 != null && date2 != null && status != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate1 = dateFormat.parse(date1);
                Date parsedDate2 = dateFormat.parse(date2);
                tenders = tenderRepo.findBetweenTwoDateAndSoumissionStatus(parsedDate1, parsedDate2,
                        Integer.parseInt(status), sort);
            } catch (ParseException e) {
                // Handle parsing exception
            }
        } else {
            tenders = tenderRepo.findAll(sort);
        }
        return tenders;
    }

    @Override
    public Tender getTender(String id) throws TenderCollectionException {
        Optional<Tender> optionalTender = tenderRepo.findById(id);
        if (!optionalTender.isPresent())
            throw new TenderCollectionException(TenderCollectionException.NotFoundException(id));
        else
            return optionalTender.get();
    }

    @Override
    public void updateTender(String id, Tender tender) throws TenderCollectionException {
        Optional<Tender> optionalTender = tenderRepo.findById(id);
        Optional<Tender> optionalTenderRef = tenderRepo.findByReference(tender.getReference());
        if (optionalTender.isPresent()) {
            if (optionalTenderRef.isPresent() && !optionalTenderRef.get().getId().equals(id))
                throw new TenderCollectionException(TenderCollectionException.TenderAlreadyExists());

            Tender tenderUpdate = optionalTender.get();
            tenderUpdate
                    .setReference(
                            tender.getReference() != null ? tender.getReference() : tenderUpdate.getReference());
            tenderUpdate.setTitle(tender.getTitle() != null ? tender.getTitle() : tenderUpdate.getTitle());
            tenderUpdate.setDescription(
                    tender.getDescription() != null ? tender.getDescription() : tenderUpdate.getDescription());
            tenderUpdate.setCritere(tender.getCritere() != null ? tender.getCritere() : tenderUpdate.getCritere());
            tenderUpdate
                    .setDateLimit(
                            tender.getDateLimit() != null ? tender.getDateLimit() : tenderUpdate.getDateLimit());
            tenderUpdate.setTenderStatus(
                    tender.getTenderStatus() != null ? tender.getTenderStatus() : tenderUpdate.getTenderStatus());

            tenderRepo.save(tenderUpdate);
        } else
            throw new TenderCollectionException(TenderCollectionException.NotFoundException(id));

    }
}
