package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Groupes")
public class Groupe {
  @Id
  private String id;

  private String nom;
  private String description;
  private String etat;
  private String datecreation;
  private String datemodifcation;



  public Groupe() {

  }
  public String getdatecreation() {
  return datecreation;
}

  public String getetat() {
	    return etat;
	  }
  
  
  
  public String getdatemodification() {
	    return datemodifcation;
	  }

	  public void setdatemodification(String datemodification) {
	    this.datemodifcation = datemodification;
	  }
	  
	  public String isdatecreation() {
		    return datecreation;
		  }

		  public void setdatecreation(String isdatecreation) {
		    this.datecreation = isdatecreation;
		  }
  public Groupe(String nom, String description, String etat) {
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

  public String isetat() {
    return etat;
  }

  public void setetat(String isetat) {
    this.etat = isetat;
  }

  @Override
  public String toString() {
    return "Groupe [id=" + id + ", nom=" + nom + ", desc=" + description + ", etat=" + etat + "]";
  }
}