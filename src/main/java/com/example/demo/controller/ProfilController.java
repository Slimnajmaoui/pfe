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
import com.example.demo.model.Profil;
import com.example.demo.repository.ProfilRepository;

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
public class ProfilController {

  @Autowired
  ProfilRepository ProfilRepository;

  @GetMapping("/Profils")
  public ResponseEntity<List<Profil>> getAllProfils(@RequestParam(required = false) String nom) {
    try {
        List<Profil> Profils = new ArrayList<Profil>();

        if (nom == null)
          ProfilRepository.findAll().forEach(Profils::add);
        else
          ProfilRepository.findBynomContaining(nom).forEach(Profils::add);

        if (Profils.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Profils, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Profils/{id}")
  public ResponseEntity<Profil> getProfilById(@PathVariable("id") String id) {
    Optional<Profil> ProfilData = ProfilRepository.findById(id);

    if (ProfilData.isPresent()) {
      return new ResponseEntity<>(ProfilData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Profils")
  public ResponseEntity<Profil> createProfil(@RequestBody Profil Profil) {
    try {
        Profil _Profil = ProfilRepository.save(new Profil(Profil.getnom(), Profil.getprenom()));
        return new ResponseEntity<>(_Profil, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Profils/{id}")
  public ResponseEntity<Profil> updateProfil(@PathVariable("id") String id, @RequestBody Profil Profil) {
    Optional<Profil> ProfilData = ProfilRepository.findById(id);

    if (ProfilData.isPresent()) {
      Profil _Profil = ProfilData.get();
      _Profil.setnom(Profil.getnom());
      _Profil.setprenom(Profil.getprenom());
      _Profil.setdiplome(Profil.getdiplome());
      return new ResponseEntity<>(ProfilRepository.save(_Profil), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Profils/{id}")
  public ResponseEntity<HttpStatus> deleteProfil(@PathVariable("id") String id) {
    try {
        ProfilRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Profils")
  public ResponseEntity<HttpStatus> deleteAllProfils() {
    try {
        ProfilRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}