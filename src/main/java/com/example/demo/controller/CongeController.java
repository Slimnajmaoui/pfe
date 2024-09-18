package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Conge;
import com.example.demo.repository.CongeRepository;

import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CongeController {

  @Autowired
  CongeRepository CongeRepository;

  @GetMapping("/Conges")
  public ResponseEntity<List<Conge>> getAllConges(@RequestParam(required = false) String titre) {
    try {
        List<Conge> Conges = new ArrayList<Conge>();

        if (titre == null)
          CongeRepository.findAll().forEach(Conges::add);
        else
          CongeRepository.findBytitreContaining(titre).forEach(Conges::add);

        if (Conges.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Conges, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Conges/{id}")
  public ResponseEntity<Conge> getCongeById(@PathVariable("id") String id) {
    Optional<Conge> CongeData = CongeRepository.findById(id);

    if (CongeData.isPresent()) {
      return new ResponseEntity<>(CongeData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Conges")
  public ResponseEntity<Conge> createConge(@RequestBody Conge Conge) {
    try {
        Conge _Conge = CongeRepository.save(new Conge(Conge.gettitre(), Conge.getdescription(), Conge.getdescription()));
        return new ResponseEntity<>(_Conge, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Conges/{id}")
  public ResponseEntity<Conge> updateConge(@PathVariable("id") String id, @RequestBody Conge Conge) {
    Optional<Conge> CongeData = CongeRepository.findById(id);

    if (CongeData.isPresent()) {
      Conge _Conge = CongeData.get();
      _Conge.settitre(Conge.gettitre());
      _Conge.setdescription(Conge.getdescription());
      _Conge.setetat(Conge.getetat());
      _Conge.setdatedebut(Conge.getdatedebut());
      _Conge.setdatefin(Conge.getdatefin());
      
      _Conge.setdatecreation(Conge.getdatecreation());
      _Conge.setdatemodification(Conge.getdatemodification());
      return new ResponseEntity<>(CongeRepository.save(_Conge), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Conges/{id}")
  public ResponseEntity<HttpStatus> deleteConge(@PathVariable("id") String id) {
    try {
        CongeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Conges")
  public ResponseEntity<HttpStatus> deleteAllConges() {
    try {
        CongeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}