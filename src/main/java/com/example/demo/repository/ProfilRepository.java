package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Profil;

public interface ProfilRepository extends MongoRepository<Profil, String> {
  List<Profil> findBynomContaining(String description);
  List<Profil> findByPublished(String email);
}