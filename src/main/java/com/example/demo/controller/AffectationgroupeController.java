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
import com.example.demo.model.Affectationgroupe;
import com.example.demo.repository.AffectationgroupeRepository;

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
public class AffectationgroupeController {

  @Autowired
  AffectationgroupeRepository AffectationgroupeRepository;

  @GetMapping("/Affectationgroupes")
  public ResponseEntity<List<Affectationgroupe>> getAllAffectationgroupes(@RequestParam(required = false) String nom) {
    try {
        List<Affectationgroupe> Affectationgroupes = new ArrayList<Affectationgroupe>();

        if (nom == null)
          AffectationgroupeRepository.findAll().forEach(Affectationgroupes::add);
        else
          AffectationgroupeRepository.findBynomContaining(nom).forEach(Affectationgroupes::add);

        if (Affectationgroupes.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Affectationgroupes, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Affectationgroupes/{id}")
  public ResponseEntity<Affectationgroupe> getAffectationgroupeById(@PathVariable("id") String id) {
    Optional<Affectationgroupe> AffectationgroupeData = AffectationgroupeRepository.findById(id);

    if (AffectationgroupeData.isPresent()) {
      return new ResponseEntity<>(AffectationgroupeData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Affectationgroupes")
  public ResponseEntity<Affectationgroupe> createAffectationgroupe(@RequestBody Affectationgroupe Affectationgroupe) {
    try {
        Affectationgroupe _Affectationgroupe = AffectationgroupeRepository.save(new Affectationgroupe(Affectationgroupe.getnom(), Affectationgroupe.getdescription(), Affectationgroupe.getetat()));
        return new ResponseEntity<>(_Affectationgroupe, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Affectationgroupes/{id}")
  public ResponseEntity<Affectationgroupe> updateAffectationgroupe(@PathVariable("id") String id, @RequestBody Affectationgroupe Affectationgroupe) {
    Optional<Affectationgroupe> AffectationgroupeData = AffectationgroupeRepository.findById(id);

    if (AffectationgroupeData.isPresent()) {
      Affectationgroupe _Affectationgroupe = AffectationgroupeData.get();
      _Affectationgroupe.setnom(Affectationgroupe.getnom());
      _Affectationgroupe.setdescription(Affectationgroupe.getdescription());
      _Affectationgroupe.setetat(Affectationgroupe.getetat());
      _Affectationgroupe.setiduser(Affectationgroupe.getiduser());
      _Affectationgroupe.setdatecreation(Affectationgroupe.getdatecreation());
      _Affectationgroupe.setdatemodification(Affectationgroupe.getdatemodification());
      return new ResponseEntity<>(AffectationgroupeRepository.save(_Affectationgroupe), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Affectationgroupes/{id}")
  public ResponseEntity<HttpStatus> deleteAffectationgroupe(@PathVariable("id") String id) {
    try {
        AffectationgroupeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Affectationgroupes")
  public ResponseEntity<HttpStatus> deleteAllAffectationgroupes() {
    try {
        AffectationgroupeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}