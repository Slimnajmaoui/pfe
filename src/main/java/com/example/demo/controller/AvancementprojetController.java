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
import com.example.demo.model.Avancementprojet;
import com.example.demo.repository.AvancementprojetRepository;

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
public class AvancementprojetController {

  @Autowired
  AvancementprojetRepository AvancementprojetRepository;

  @GetMapping("/Avancementprojets")
  public ResponseEntity<List<Avancementprojet>> getAllAvancementprojets(@RequestParam(required = false) String nom) {
    try {
        List<Avancementprojet> Avancementprojets = new ArrayList<Avancementprojet>();

        if (nom == null)
          AvancementprojetRepository.findAll().forEach(Avancementprojets::add);
        else
          AvancementprojetRepository.findBynomContaining(nom).forEach(Avancementprojets::add);

        if (Avancementprojets.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Avancementprojets, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Avancementprojets/{id}")
  public ResponseEntity<Avancementprojet> getAvancementprojetById(@PathVariable("id") String id) {
    Optional<Avancementprojet> AvancementprojetData = AvancementprojetRepository.findById(id);

    if (AvancementprojetData.isPresent()) {
      return new ResponseEntity<>(AvancementprojetData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Avancementprojets")
  public ResponseEntity<Avancementprojet> createAvancementprojet(@RequestBody Avancementprojet Avancementprojet) {
    try {
        Avancementprojet _Avancementprojet = AvancementprojetRepository.save(new Avancementprojet(Avancementprojet.getnom(), Avancementprojet.getdescription(), Avancementprojet.getdescription()));
        return new ResponseEntity<>(_Avancementprojet, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Avancementprojets/{id}")
  public ResponseEntity<Avancementprojet> updateAvancementprojet(@PathVariable("id") String id, @RequestBody Avancementprojet Avancementprojet) {
    Optional<Avancementprojet> AvancementprojetData = AvancementprojetRepository.findById(id);

    if (AvancementprojetData.isPresent()) {
      Avancementprojet _Avancementprojet = AvancementprojetData.get();
      _Avancementprojet.setnom(Avancementprojet.getnom());
      _Avancementprojet.setdescription(Avancementprojet.getdescription());
      _Avancementprojet.setdatedebut(Avancementprojet.getdatedebut());
      _Avancementprojet.setdatefin(Avancementprojet.getdatefin());
      _Avancementprojet.setetat(Avancementprojet.getetat());
      _Avancementprojet.setavancement(Avancementprojet.getavancement());
      _Avancementprojet.setdatecreation(Avancementprojet.getdatecreation());
      return new ResponseEntity<>(AvancementprojetRepository.save(_Avancementprojet), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Avancementprojets/{id}")
  public ResponseEntity<HttpStatus> deleteAvancementprojet(@PathVariable("id") String id) {
    try {
        AvancementprojetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Avancementprojets")
  public ResponseEntity<HttpStatus> deleteAllAvancementprojets() {
    try {
        AvancementprojetRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}