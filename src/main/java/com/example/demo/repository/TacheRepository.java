package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Tache;

public interface TacheRepository extends MongoRepository<Tache, String> {
  List<Tache> findBynomContaining(String idprojet);
  List<Tache> findByPublished(String email);
}