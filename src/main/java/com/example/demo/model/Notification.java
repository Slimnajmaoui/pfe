package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notifications")
public class Notification {
  @Id
  private String id;

  private String sujet;
  private String description;
  private String datecreation;

  public Notification() {

  }
  public String getdatecreation() {
	    return datecreation;
	  }

	  public void setdatecreation(String datecreation) {
	    this.datecreation = datecreation;
	  }
  public Notification(String sujet, String description, String datecreation) {
    this.sujet = sujet;
    this.description = description;

  }

  public String getId() {
    return id;
  }

  public String getsujet() {
    return sujet;
  }



  public String getdescription() {
    return description;
  }

  public void setdescription(String description) {
    this.description = description;
  }

  public String issujet() {
    return sujet;
  }

  public void setsujet(String issujet) {
    this.sujet = issujet;
  }

  @Override
  public String toString() {
    return "Notification [id=" + id + ", sujet=" + sujet + ", desc=" + description + ", sujet=" + sujet + "]";
  }
}