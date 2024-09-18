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
import com.example.demo.model.Absence;
import com.example.demo.repository.AbsenceRepository;

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
public class AbsenceController {

  @Autowired
  AbsenceRepository AbsenceRepository;

  @GetMapping("/Absences")
  public ResponseEntity<List<Absence>> getAllAbsences(@RequestParam(required = false) String titre) {
	  try {
		    List<Absence> Absences = new ArrayList<Absence>();

		    if (titre == null)
		      AbsenceRepository.findAll().forEach(Absences::add);
		    else
		      AbsenceRepository.findBytitreContaining(titre).forEach(Absences::add);

		    if (Absences.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(Absences, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }

  @GetMapping("/Absences/{id}")
  public ResponseEntity<Absence> getAbsenceById(@PathVariable("id") String id) {
	  Optional<Absence> AbsenceData = AbsenceRepository.findById(id);

	  if (AbsenceData.isPresent()) {
	    return new ResponseEntity<>(AbsenceData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
  }

  @PostMapping("/Absences")
  public ResponseEntity<Absence> createAbsence(@RequestBody Absence Absence) {
	  try {
		    Absence _Absence = AbsenceRepository.save(new Absence(Absence.gettitre(), Absence.getdescription(), Absence.getdescription()));
		    return new ResponseEntity<>(_Absence, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }

  @PutMapping("/Absences/{id}")
  public ResponseEntity<Absence> uptitre(@PathVariable("id") String id, @RequestBody Absence Absence) {
	  Optional<Absence> AbsenceData = AbsenceRepository.findById(id);

	  if (AbsenceData.isPresent()) {
	    Absence _Absence = AbsenceData.get();
	    _Absence.settitre(Absence.gettitre());
	    _Absence.setdescription(Absence.getdescription());
	    _Absence.setetat(Absence.getetat());
	    _Absence.setdatedebut(Absence.getdatedebut());
	    _Absence.setdatefin(Absence.getdatefin());
	    _Absence.setdatecreation(Absence.getdatecreation());
	    _Absence.setdatemodification(Absence.getdatemodification());
	    return new ResponseEntity<>(AbsenceRepository.save(_Absence), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
  }

  @DeleteMapping("/Absences/{id}")
  public ResponseEntity<HttpStatus> deleteAbsence(@PathVariable("id") String id) {
	  try {
		    AbsenceRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }

  @DeleteMapping("/Absences")
  public ResponseEntity<HttpStatus> deleteAllAbsences() {
	  try {
		    AbsenceRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }



}