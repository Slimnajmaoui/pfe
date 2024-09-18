package com.example.demo.repository;




import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
  List<Message> findByidenvoieContaining(String idenvoie);
  List<Message> findByPublished(String etat);
}