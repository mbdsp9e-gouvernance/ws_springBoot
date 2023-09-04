package com.project.gouvernance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.gouvernance.exception.TenderCollectionException;
import com.project.gouvernance.model.Soumission;
import com.project.gouvernance.model.Tender;
import com.project.gouvernance.service.tender.TenderService;

import jakarta.validation.ConstraintViolationException;

@RestController
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @PostMapping("/tender/create")
    public ResponseEntity<?> createTender(@RequestBody Tender tender) {
        try {
            tenderService.createTender(tender);
            return new ResponseEntity<Tender>(tender, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TenderCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/tender/all")
    public ResponseEntity<?> getAllTender() {
        List<Tender> tenders = tenderService.getAllTender();
        return new ResponseEntity<>(tenders, tenders.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tender/filter")
    public ResponseEntity<?> getAllTenderFilter(@RequestParam(name = "date1", required = false) String date1,
            @RequestParam(name = "date2", required = false) String date2,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "ascending", required = false) String ascending) {
        List<Tender> tender = tenderService.getAllTenderFilter(date1, date2, status, ascending);
        return new ResponseEntity<>(tender, tender.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tender/{id}")
    public ResponseEntity<?> getTender(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(tenderService.getTender(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/tender/update")
    public ResponseEntity<?> updateTender(@RequestParam(name = "id") String id, @RequestBody Tender tender) {
        try {
            tenderService.updateTender(id, tender);
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TenderCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

}
