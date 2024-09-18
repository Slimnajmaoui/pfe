package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
  @Id
  private String id;

  private String nom;
  private String prenom;
  private String mdp;
  private String datecreation;

private String statut;


  public User() {

  }

  public User(String nom, String prenom, String mdp) {
    this.nom = nom;
    this.prenom = prenom;

  }

  public String getId() {
    return id;
  }

  public String getnom() {
    return nom;
  }

  public void setnom(String nom) {
    this.nom = nom;
  }

  public String getprenom() {
    return prenom;
  }

  public void setprenom(String prenom) {
    this.prenom = prenom;
  }

  public String isdatecreation() {
    return datecreation;
  }

  public void setdatecreation(String isdatecreation) {
    this.datecreation = isdatecreation;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", nom=" + nom + ", desc=" + prenom + ", datecreation=" + datecreation + "]";
  }
}