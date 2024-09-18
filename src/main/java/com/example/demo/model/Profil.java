package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Profils")
public class Profil {
  @Id
  private String id;

  private String nom;
  private String prenom;
  private String diplome;
  private String experience;
  private String niveau;
  private String etat;
  private String datecreation;
  private String datemodification;
  
  
  public String getniveau() {
	    return niveau;
	  }

	  public void setniveau(String niveau) {
	    this.niveau = niveau;
	  }
	  
	  public String getdiplome() {
		    return diplome;
		  }

		  public void setdiplome(String diplome) {
		    this.diplome = diplome;
		  }
  
  public String getdatemodification() {
	    return datemodification;
	  }
  public String getetat() {
	    return etat;
	  }
  public String getexperience() {
	    return experience;
	  }

	  public void setexperience(String experience) {
	    this.experience = experience;
	  }
	  
	  
	  public void setetat(String etat) {
	    this.etat = etat;
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

  public Profil() {

  }

  public Profil(String nom, String prenom) {
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



  @Override
  public String toString() {
    return "Profil [id=" + id + ", nom=" + nom + ", desc=" + prenom + ", prenom=" + prenom + "]";
  }

public String isprenom() {
	// TODO Auto-generated method stub
	return null;
}
}