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
import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;

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
public class MessageController {

  @Autowired
  MessageRepository MessageRepository;

  @GetMapping("/Messages")
  public ResponseEntity<List<Message>> getAllMessages(@RequestParam(required = false) String idenvoie) {
    try {
        List<Message> Messages = new ArrayList<Message>();

        if (idenvoie == null)
          MessageRepository.findAll().forEach(Messages::add);
        else
          MessageRepository.findByidenvoieContaining(idenvoie).forEach(Messages::add);

        if (Messages.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Messages, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Messages/{id}")
  public ResponseEntity<Message> getMessageById(@PathVariable("id") String id) {
    Optional<Message> MessageData = MessageRepository.findById(id);

    if (MessageData.isPresent()) {
      return new ResponseEntity<>(MessageData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Messages")
  public ResponseEntity<Message> createMessage(@RequestBody Message Message) {
    try {
        Message _Message = MessageRepository.save(new Message(Message.getidenvoie(), Message.getidreception(), Message.getidenvoie()));
        return new ResponseEntity<>(_Message, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Messages/{id}")
  public ResponseEntity<Message> updateMessage(@PathVariable("id") String id, @RequestBody Message Message) {
    Optional<Message> MessageData = MessageRepository.findById(id);

    if (MessageData.isPresent()) {
      Message _Message = MessageData.get();
      _Message.setidenvoie(Message.getidenvoie());
      _Message.setidreception(Message.getidreception());
      _Message.setetat(Message.isetat());
      return new ResponseEntity<>(MessageRepository.save(_Message), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Messages/{id}")
  public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("id") String id) {
    try {
        MessageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Messages")
  public ResponseEntity<HttpStatus> deleteAllMessages() {
    try {
        MessageRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}