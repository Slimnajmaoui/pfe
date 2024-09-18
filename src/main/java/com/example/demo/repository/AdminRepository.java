package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
  List<Admin> findByusernameContaining(String title);
  List<Admin> findByPublished(String email);
}