package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Avancementprojet;

public interface AvancementprojetRepository extends MongoRepository<Avancementprojet, String> {
  List<Avancementprojet> findBynomContaining(String title);
  List<Avancementprojet> findByPublished(String email);
}