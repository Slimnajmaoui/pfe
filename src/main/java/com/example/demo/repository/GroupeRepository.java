package com.example.demo.repository;




import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Groupe;

public interface GroupeRepository extends MongoRepository<Groupe, String> {
  List<Groupe> findBynomContaining(String nom);
  List<Groupe> findByPublished(String description);
}