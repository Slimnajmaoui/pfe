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
import com.example.demo.model.Avancementtache;
import com.example.demo.repository.AvancementtacheRepository;

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
public class AvancementtacheController {

  @Autowired
  AvancementtacheRepository AvancementtacheRepository;

  @GetMapping("/Avancementtaches")
  public ResponseEntity<List<Avancementtache>> getAllAvancementtaches(@RequestParam(required = false) String qualite) {
    try {
        List<Avancementtache> Avancementtaches = new ArrayList<Avancementtache>();

        if (qualite == null)
          AvancementtacheRepository.findAll().forEach(Avancementtaches::add);
        else
          AvancementtacheRepository.findBynomContaining(qualite).forEach(Avancementtaches::add);

        if (Avancementtaches.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Avancementtaches, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Avancementtaches/{id}")
  public ResponseEntity<Avancementtache> getAvancementtacheById(@PathVariable("id") String id) {
    Optional<Avancementtache> AvancementtacheData = AvancementtacheRepository.findById(id);

    if (AvancementtacheData.isPresent()) {
      return new ResponseEntity<>(AvancementtacheData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Avancementtaches")
  public ResponseEntity<Avancementtache> createAvancementtache(@RequestBody Avancementtache Avancementtache) {
    try {
        Avancementtache _Avancementtache = AvancementtacheRepository.save(new Avancementtache(Avancementtache.getnom(), Avancementtache.getdescription(), Avancementtache.getetat()));
        return new ResponseEntity<>(_Avancementtache, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Avancementtaches/{id}")
  public ResponseEntity<Avancementtache> updateAvancementtache(@PathVariable("id") String id, @RequestBody Avancementtache Avancementtache) {
    Optional<Avancementtache> AvancementtacheData = AvancementtacheRepository.findById(id);

    if (AvancementtacheData.isPresent()) {
      Avancementtache _Avancementtache = AvancementtacheData.get();
      _Avancementtache.setnom(Avancementtache.getnom());
      _Avancementtache.setdescription(Avancementtache.getdescription());
      _Avancementtache.setdatedebut(Avancementtache.getdatedebut());
      _Avancementtache.setdatefin(Avancementtache.getdatefin());
      _Avancementtache.setidprojet(Avancementtache.getidprojet());
      _Avancementtache.setetat(Avancementtache.getetat());
      _Avancementtache.setavancement(Avancementtache.getavancement());
      _Avancementtache.setdatecreation(Avancementtache.getdatecreation());
      return new ResponseEntity<>(AvancementtacheRepository.save(_Avancementtache), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Avancementtaches/{id}")
  public ResponseEntity<HttpStatus> deleteAvancementtache(@PathVariable("id") String id) {
    try {
        AvancementtacheRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Avancementtaches")
  public ResponseEntity<HttpStatus> deleteAllAvancementtaches() {
    try {
        AvancementtacheRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}