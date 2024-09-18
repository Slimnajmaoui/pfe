package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Competence;

public interface CompetenceRepository extends MongoRepository<Competence, String> {
  List<Competence> findBynomContaining(String title);
  List<Competence> findByPublished(String email);
}