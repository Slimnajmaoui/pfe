package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Affectationgroupe;

public interface AffectationgroupeRepository extends MongoRepository<Affectationgroupe, String> {
  List<Affectationgroupe> findBynomContaining(String title);
  List<Affectationgroupe> findByPublished(String email);
}