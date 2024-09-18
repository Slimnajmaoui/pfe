package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Avancementtache;

public interface AvancementtacheRepository extends MongoRepository<Avancementtache, String> {
  List<Avancementtache> findBynomContaining(String title);
  List<Avancementtache> findByPublished(String email);
}