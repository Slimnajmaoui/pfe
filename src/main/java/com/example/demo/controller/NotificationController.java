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
import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;

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
public class NotificationController {

  @Autowired
  NotificationRepository NotificationRepository;

  @GetMapping("/Notifications")
  public ResponseEntity<List<Notification>> getAllNotifications(@RequestParam(required = false) String sujet) {
    try {
        List<Notification> Notifications = new ArrayList<Notification>();

        if (sujet == null)
          NotificationRepository.findAll().forEach(Notifications::add);
        else
          NotificationRepository.findBysujetContaining(sujet).forEach(Notifications::add);

        if (Notifications.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Notifications, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Notifications/{id}")
  public ResponseEntity<Notification> getNotificationById(@PathVariable("id") String id) {
    Optional<Notification> NotificationData = NotificationRepository.findById(id);

    if (NotificationData.isPresent()) {
      return new ResponseEntity<>(NotificationData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Notifications")
  public ResponseEntity<Notification> createNotification(@RequestBody Notification Notification) {
    try {
        Notification _Notification = NotificationRepository.save(new Notification(Notification.getsujet(), Notification.getdescription(), Notification.getsujet()));
        return new ResponseEntity<>(_Notification, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Notifications/{id}")
  public ResponseEntity<Notification> updateNotification(@PathVariable("id") String id, @RequestBody Notification Notification) {
    Optional<Notification> NotificationData = NotificationRepository.findById(id);

    if (NotificationData.isPresent()) {
      Notification _Notification = NotificationData.get();
      _Notification.setsujet(Notification.getsujet());
      _Notification.setdescription(Notification.getdescription());
      _Notification.setsujet(Notification.issujet());
      return new ResponseEntity<>(NotificationRepository.save(_Notification), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Notifications/{id}")
  public ResponseEntity<HttpStatus> deleteNotification(@PathVariable("id") String id) {
    try {
        NotificationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Notifications")
  public ResponseEntity<HttpStatus> deleteAllNotifications() {
    try {
        NotificationRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}