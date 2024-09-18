package com.example.demo.repository;






import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Conge;

public interface CongeRepository extends MongoRepository<Conge, String> {
  List<Conge> findBytitreContaining(String title);
  List<Conge> findByPublished(String email);
}