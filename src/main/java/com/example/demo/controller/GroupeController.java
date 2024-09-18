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
import com.example.demo.model.Groupe;
import com.example.demo.repository.GroupeRepository;

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
public class GroupeController {

  @Autowired
  GroupeRepository GroupeRepository;

  @GetMapping("/Groupes")
  public ResponseEntity<List<Groupe>> getAllGroupes(@RequestParam(required = false) String nom) {
    try {
        List<Groupe> Groupes = new ArrayList<Groupe>();

        if (nom == null)
          GroupeRepository.findAll().forEach(Groupes::add);
        else
          GroupeRepository.findBynomContaining(nom).forEach(Groupes::add);

        if (Groupes.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Groupes, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Groupes/{id}")
  public ResponseEntity<Groupe> getGroupeById(@PathVariable("id") String id) {
    Optional<Groupe> GroupeData = GroupeRepository.findById(id);

    if (GroupeData.isPresent()) {
      return new ResponseEntity<>(GroupeData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Groupes")
  public ResponseEntity<Groupe> createGroupe(@RequestBody Groupe Groupe) {
    try {
        Groupe _Groupe = GroupeRepository.save(new Groupe(Groupe.getnom(), Groupe.getdescription(), Groupe.getdescription()));
        return new ResponseEntity<>(_Groupe, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Groupes/{id}")
  public ResponseEntity<Groupe> updateGroupe(@PathVariable("id") String id, @RequestBody Groupe Groupe) {
    Optional<Groupe> GroupeData = GroupeRepository.findById(id);

    if (GroupeData.isPresent()) {
      Groupe _Groupe = GroupeData.get();
      _Groupe.setnom(Groupe.getnom());
      _Groupe.setdescription(Groupe.getdescription());
      _Groupe.setetat(Groupe.getetat());
      _Groupe.setdatecreation(Groupe.getdatecreation());
      _Groupe.setdatemodification(Groupe.getdatemodification());
      return new ResponseEntity<>(GroupeRepository.save(_Groupe), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Groupes/{id}")
  public ResponseEntity<HttpStatus> deleteGroupe(@PathVariable("id") String id) {
    try {
        GroupeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Groupes")
  public ResponseEntity<HttpStatus> deleteAllGroupes() {
    try {
        GroupeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}