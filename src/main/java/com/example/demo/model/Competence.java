package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Competences")
public class Competence {
  @Id
  private String id;

  private String nom;
  private String description;
  private String datecreation;
  private String datemodification;

  public Competence() {

  }
  public String getdatemodification() {
	    return datemodification;
	  }

  public String getdatecreation() {
	    return datecreation;
	  }

  
  
  
	  public void setdatemodification(String datemodification) {
	    this.datemodification = datemodification;
	  }
  public Competence(String nom, String description, String datecreation) {
    this.nom = nom;
    this.description = description;
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

  public String getdescription() {
    return description;
  }

  public void setdescription(String description) {
    this.description = description;
  }

  public String isdatecreation() {
    return datecreation;
  }

  public void setdatecreation(String isdatecreation) {
    this.datecreation = isdatecreation;
  }

  @Override
  public String toString() {
    return "Competence [id=" + id + ", nom=" + nom + ", desc=" + description + ", datecreation=" + datecreation + "]";
  }
}