package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserRepository UserRepository;

  @GetMapping("/Users")
  public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String nom) {
    try {
        List<User> Users = new ArrayList<User>();

        if (nom == null)
          UserRepository.findAll().forEach(Users::add);
        else
          UserRepository.findBynomContaining(nom).forEach(Users::add);

        if (Users.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Users, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
    Optional<User> UserData = UserRepository.findById(id);

    if (UserData.isPresent()) {
      return new ResponseEntity<>(UserData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Users")
  public ResponseEntity<User> createUser(@RequestBody User User) {
    try {
        User _User = UserRepository.save(new User(User.getnom(), User.getprenom(), User.getprenom()));
        return new ResponseEntity<>(_User, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User User) {
    Optional<User> UserData = UserRepository.findById(id);

    if (UserData.isPresent()) {
      User _User = UserData.get();
      _User.setnom(User.getnom());
      _User.setprenom(User.getprenom());
      _User.setdatecreation(User.isdatecreation());
      return new ResponseEntity<>(UserRepository.save(_User), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    try {
        UserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Users")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    try {
        UserRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}