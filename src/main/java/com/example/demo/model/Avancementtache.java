

	package com.example.demo.model;



	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "Avancementprojets")
	public class Avancementtache {
	  @Id
	  private String id;

	  private String nom;
	  private String description;
	  private String datedebut;
	  private String datefin;
	  private String idprojet;
	  private String etat;
	  private String avancement;
	  private String datecreation;

	  private String datemodification;
	  public Avancementtache(String getnom, String getdescription, String getetat) {
		// TODO Auto-generated constructor stub
	}
	public String getdatecreation() {
		    return datecreation;
		  }
	  public String getidprojet() {
		    return idprojet;
		  }

		  public void setidprojet(String idprojet) {
		    this.idprojet = idprojet;
		  }
		  public void setdatecreation(String datecreation) {
		    this.datecreation = datecreation;
		  }
		  public String getdatemodification() {
			    return datemodification;
			  }

			  public void setdatemodification(String datemodification) {
			    this.datemodification = datemodification;
			  }
			  
			  
	  public String getetat() {
		    return etat;
		  }

		  public void setetat(String etat) {
		    this.etat = etat;
		  }
	  public void Avancementprojet() {

	  }
	  public String getdatefin() {
		    return datefin;
		  }

		  public void setdatefin(String datefin) {
		    this.datefin = datefin;
		  }
	  public String getdatedebut() {
		    return datedebut;
		  }

		  public void setdatedebut(String datedebut) {
		    this.datedebut = datedebut;
		  }
	  public void Avancementprojet(String nom, String description, String datedebut) {
	    this.nom = nom;
	    this.description = description;
	    this.datedebut = datedebut;

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

	  public String getavancement() {
	    return avancement;
	  }
	  public void setavancement(String avancement) {
	    this.avancement = avancement;
	  }

	  @Override
	  public String toString() {
	    return "Avancementprojet [id=" + id + ", nom=" + nom + ", desc=" + description + "]";
	  }
	}