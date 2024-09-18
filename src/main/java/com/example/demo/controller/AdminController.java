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
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;

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
public class AdminController {

  @Autowired
  AdminRepository AdminRepository;

  @GetMapping("/Admins")
  public ResponseEntity<List<Admin>> getAllAdmins(@RequestParam(required = false) String username) {
    try {
        List<Admin> Admins = new ArrayList<Admin>();

        if (username == null)
          AdminRepository.findAll().forEach(Admins::add);
        else
          AdminRepository.findByusernameContaining(username).forEach(Admins::add);

        if (Admins.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Admins, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Admins/{id}")
  public ResponseEntity<Admin> getAdminById(@PathVariable("id") String id) {
    Optional<Admin> AdminData = AdminRepository.findById(id);

    if (AdminData.isPresent()) {
      return new ResponseEntity<>(AdminData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Admins")
  public ResponseEntity<Admin> createAdmin(@RequestBody Admin Admin) {
    try {
        Admin _Admin = AdminRepository.save(new Admin(Admin.getusername(), Admin.getmotdepasse(), Admin.getemail()));
        return new ResponseEntity<>(_Admin, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Admins/{id}")
  public ResponseEntity<Admin> updateAdmin(@PathVariable("id") String id, @RequestBody Admin Admin) {
    Optional<Admin> AdminData = AdminRepository.findById(id);

    if (AdminData.isPresent()) {
      Admin _Admin = AdminData.get();
      _Admin.setusername(Admin.getusername());
      _Admin.setmotdepasse(Admin.getmotdepasse());
      _Admin.setemail(Admin.getemail());
      return new ResponseEntity<>(AdminRepository.save(_Admin), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Admins/{id}")
  public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") String id) {
    try {
        AdminRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Admins")
  public ResponseEntity<HttpStatus> deleteAllAdmins() {
    try {
        AdminRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}