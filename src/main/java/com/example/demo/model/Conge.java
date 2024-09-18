package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Conges")
public class Conge {
  @Id
  private String id;

  private String titre;
  private String description;
  private String etat;
  private String datedebut;
  private String datefin;
  private String datecreation;
  private String datemodification;

  public Conge() {

  }
  public String getdatefin() {
	    return datefin;
	  }
  public String getdatedebut() {
  return datedebut;
}
  public String getdatecreation() {
  return datecreation;
}
  
	  public void setdatefin(String datefin) {
	    this.datefin = datefin;
	  }
  public String getdatemodification() {
	    return datemodification;
	  }

	  public void setdatemodification(String datemodification) {
	    this.datemodification = datemodification;
	  }
  public String isdatecreation() {
	    return datecreation;
	  }

	  public void setdatecreation(String isdatecreation) {
	    this.datecreation = isdatecreation;
	  }
  public Conge(String titre, String description, String etat) {
    this.titre = titre;
    this.description = description;

  }
  public String getetat() {
	    return etat;
	  }

	  public void setetat(String etat) {
	    this.etat = etat;
	  }
	  
  public String getId() {
    return id;
  }

  public String gettitre() {
    return titre;
  }

  public void settitre(String titre) {
    this.titre = titre;
  }

  public String getdescription() {
    return description;
  }

  public void setdescription(String description) {
    this.description = description;
  }

  public String isdatedebut() {
    return datedebut;
  }

  public void setdatedebut(String isdatedebut) {
    this.datedebut = isdatedebut;
  }

  @Override
  public String toString() {
    return "Conge [id=" + id + ", titre=" + titre + ", desc=" + description + ", datedebut=" + datedebut + "]";
  }
}