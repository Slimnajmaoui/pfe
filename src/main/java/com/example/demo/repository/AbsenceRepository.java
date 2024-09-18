package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Absence;


public interface AbsenceRepository extends MongoRepository<Absence, String> {
  List<Absence> findBytitreContaining(String title);
  List<Absence> findBytitre(String titre);
}