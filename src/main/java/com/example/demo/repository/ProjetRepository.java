package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Projet;

public interface ProjetRepository extends MongoRepository<Projet, String> {
  List<Projet> findBynomContaining(String nom);
  List<Projet> findByPublished(String email);
}