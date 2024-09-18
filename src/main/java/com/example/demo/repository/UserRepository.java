package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  List<User> findBynomContaining(String nom);
  List<User> findByPublished(String email);
}