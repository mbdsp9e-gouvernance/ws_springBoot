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
import org.springframework.web.bind.annotation.RestController;

import com.project.gouvernance.exception.SocietyCollectionException;
import com.project.gouvernance.model.Society;
import com.project.gouvernance.service.society.SocietyService;

import jakarta.validation.ConstraintViolationException;

@RestController
public class SocietyController {

    @Autowired
    private SocietyService societyService;

    @PostMapping("/society/create")
    public ResponseEntity<?> createSociety(@RequestBody Society society) {
        try {
            societyService.createSociety(society);
            return new ResponseEntity<Society>(society, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (SocietyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/society/all")
    public ResponseEntity<?> getAllSociety() {
        List<Society> society = societyService.getAllSociety();
        return new ResponseEntity<>(society, society.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/society/{id}")
    public ResponseEntity<?> getSociety(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(societyService.getSociety(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/society/update/{id}")
    public ResponseEntity<?> updateSociety(@PathVariable("id") String id, @RequestBody Society society) {
        try {
            societyService.updateSociety(id, society);
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (SocietyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
