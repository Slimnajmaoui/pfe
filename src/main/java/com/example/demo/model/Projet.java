package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projets")
public class Projet {
  @Id
  private String id;

  private String nom;
  private String description;
  private String datedebut;
  private String datefin;
  private String etat;
  private String datecreation;
  private String datemodification;

  public String getdatemodification() {
	    return datemodification;
	  }

	  public void setdatemodification(String datemodification) {
	    this.datemodification = datemodification;
	  }
  public String getdatecreation() {
	    return datecreation;
	  }

	  public void setdatecreation(String datecreation) {
	    this.datecreation = datecreation;
	  }
	  
	  
	  
  public String getetat() {
	    return etat;
	  }

	  public void setetat(String etat) {
	    this.etat = etat;
	  }
  public String getdatedebut() {
	    return datedebut;
	  }

	  public void setdatedebut(String datedebut) {
	    this.datedebut = datedebut;
	  }
	  
	  
	  
  public Projet() {

  }

  public Projet(String nom, String description, String datedebut) {
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

  public String isdatefin() {
    return datefin;
  }

  public void setdatefin(String isdatefin) {
    this.datefin = isdatefin;
  }

  @Override
  public String toString() {
    return "Projet [id=" + id + ", nom=" + nom + ", desc=" + description + ", datefin=" + datefin + "]";
  }
}