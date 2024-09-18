package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Messages")
public class Message {
  @Id
  private String id;

  private String idenvoie;
  private String idreception;
  private String sujet;
  private String texte;
  private String etat;
  private String datecreationmessage;

  public Message() {

  }

  public Message(String idenvoie, String idreception, String sujet) {
    this.idenvoie = idenvoie;
    this.idreception = idreception;

  }
  public String getdatecreationmessage() {
	    return datecreationmessage;
	  }

	  public void setdatecreationmessage(String datecreationmessage) {
	    this.datecreationmessage = datecreationmessage;
	  }
  public String getsujet() {
	    return sujet;
	  }
  public String gettexte() {
	    return texte;
	  }

	  public void settexte(String texte) {
	    this.texte = texte;
	  }
	  public void setsujet(String sujet) {
	    this.sujet = sujet;
	  }
	  
	  
	  
  public String getId() {
    return id;
  }

  public String getidenvoie() {
    return idenvoie;
  }

  public void setidenvoie(String idenvoie) {
    this.idenvoie = idenvoie;
  }

  public String getidreception() {
    return idreception;
  }

  public void setidreception(String idreception) {
    this.idreception = idreception;
  }

  public String isetat() {
    return etat;
  }

  public void setetat(String isetat) {
    this.etat = isetat;
  }

  @Override
  public String toString() {
    return "Message [id=" + id + ", idenvoie=" + idenvoie + ", desc=" + idreception + ", etat=" + etat + "]";
  }
}