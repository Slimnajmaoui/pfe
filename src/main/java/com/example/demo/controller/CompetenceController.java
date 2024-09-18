package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Competence;
import com.example.demo.repository.CompetenceRepository;

import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CompetenceController {

  @Autowired
  CompetenceRepository CompetenceRepository;

  @GetMapping("/Competences")
  public ResponseEntity<List<Competence>> getAllCompetences(@RequestParam(required = false) String nom) {
    try {
        List<Competence> Competences = new ArrayList<Competence>();

        if (nom == null)
          CompetenceRepository.findAll().forEach(Competences::add);
        else
          CompetenceRepository.findBynomContaining(nom).forEach(Competences::add);

        if (Competences.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Competences, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Competences/{id}")
  public ResponseEntity<Competence> getCompetenceById(@PathVariable("id") String id) {
    Optional<Competence> CompetenceData = CompetenceRepository.findById(id);

    if (CompetenceData.isPresent()) {
      return new ResponseEntity<>(CompetenceData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Competences")
  public ResponseEntity<Competence> createCompetence(@RequestBody Competence Competence) {
    try {
        Competence _Competence = CompetenceRepository.save(new Competence(Competence.getnom(), Competence.getdescription(), Competence.getnom()));
        return new ResponseEntity<>(_Competence, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Competences/{id}")
  public ResponseEntity<Competence> updateCompetence(@PathVariable("id") String id, @RequestBody Competence Competence) {
    Optional<Competence> CompetenceData = CompetenceRepository.findById(id);

    if (CompetenceData.isPresent()) {
      Competence _Competence = CompetenceData.get();
      _Competence.setnom(Competence.getnom());
      _Competence.setdescription(Competence.getdescription());
      _Competence.setdatecreation(Competence.getdatecreation());
      _Competence.setdatemodification(Competence.getdatemodification());
      return new ResponseEntity<>(CompetenceRepository.save(_Competence), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Competences/{id}")
  public ResponseEntity<HttpStatus> deleteCompetence(@PathVariable("id") String id) {
    try {
        CompetenceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Competences")
  public ResponseEntity<HttpStatus> deleteAllCompetences() {
    try {
        CompetenceRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}