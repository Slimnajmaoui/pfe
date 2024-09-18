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
import com.example.demo.model.Projet;
import com.example.demo.repository.ProjetRepository;

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
public class ProjetController {

  @Autowired
  ProjetRepository ProjetRepository;

  @GetMapping("/Projets")
  public ResponseEntity<List<Projet>> getAllProjets(@RequestParam(required = false) String nom) {
    try {
        List<Projet> Projets = new ArrayList<Projet>();

        if (nom == null)
          ProjetRepository.findAll().forEach(Projets::add);
        else
          ProjetRepository.findBynomContaining(nom).forEach(Projets::add);

        if (Projets.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Projets, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Projets/{id}")
  public ResponseEntity<Projet> getProjetById(@PathVariable("id") String id) {
    Optional<Projet> ProjetData = ProjetRepository.findById(id);

    if (ProjetData.isPresent()) {
      return new ResponseEntity<>(ProjetData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Projets")
  public ResponseEntity<Projet> createProjet(@RequestBody Projet Projet) {
    try {
        Projet _Projet = ProjetRepository.save(new Projet(Projet.getnom(), Projet.getdescription(), Projet.getdescription()));
        return new ResponseEntity<>(_Projet, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Projets/{id}")
  public ResponseEntity<Projet> updateProjet(@PathVariable("id") String id, @RequestBody Projet Projet) {
    Optional<Projet> ProjetData = ProjetRepository.findById(id);

    if (ProjetData.isPresent()) {
      Projet _Projet = ProjetData.get();
      _Projet.setnom(Projet.getnom());
      _Projet.setdescription(Projet.getdescription());
      _Projet.setdatedebut(Projet.getdatedebut());
      return new ResponseEntity<>(ProjetRepository.save(_Projet), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Projets/{id}")
  public ResponseEntity<HttpStatus> deleteProjet(@PathVariable("id") String id) {
    try {
        ProjetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Projets")
  public ResponseEntity<HttpStatus> deleteAllProjets() {
    try {
        ProjetRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}