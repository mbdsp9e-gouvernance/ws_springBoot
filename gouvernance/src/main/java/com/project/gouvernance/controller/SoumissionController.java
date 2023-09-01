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

import com.project.gouvernance.exception.SoumissionCollectionException;
import com.project.gouvernance.model.Soumission;
import com.project.gouvernance.service.soumission.SoumissionService;

import jakarta.validation.ConstraintViolationException;

@RestController
public class SoumissionController {

    @Autowired
    private SoumissionService soumissionService;

    @PostMapping("/soumission/create")
    public ResponseEntity<?> createSoumission(@RequestBody Soumission soumission) throws Exception {
        try {
            soumissionService.createSoumission(soumission);
            return new ResponseEntity<Soumission>(soumission, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/soumission/all")
    public ResponseEntity<?> getAllSoumission() {
        List<Soumission> soumission = soumissionService.getAllSoumission();
        return new ResponseEntity<>(soumission, soumission.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/soumission/filter")
    public ResponseEntity<?> getAllSoumissionFilter(
            @RequestParam(name = "society", required = false) String societyId,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "tender", required = false) String tenderId) {
        List<Soumission> soumission = soumissionService.getAllSoumissionByFilter(status, societyId, tenderId);
        return new ResponseEntity<>(soumission, soumission.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/soumission/{id}")
    public ResponseEntity<?> getSoumission(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(soumissionService.getSoumission(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/soumission/updateValidation")
    public ResponseEntity<?> updateSoumission(@RequestParam(name = "id") String id,
            @RequestBody Soumission soumission) {
        try {
            soumissionService.updateSoumission(id, soumission);
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (SoumissionCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
