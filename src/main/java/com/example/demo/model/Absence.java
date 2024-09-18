package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Absences")
public class Absence {
  @Id
  private String id;

  private String titre;
  private String description;
  private String etat;
  private String datedebut;
  private String datefin;
  private String datecreation;
  private String datemodification;

  public Absence() {

  }
  public String getetat() {
    return etat;
  }
  public Absence(String titre, String description, String etat) {
    this.titre = titre;
    this.description = description;
    this.etat = etat;
  }

  public String getId() {
    return id;
  }
  public String getdatemodification() {
    return datemodification;
  }

  public void setdatemodification(String datemodification) {
    this.datemodification = datemodification;
  }
  public String gettitre() {
    return titre;
  }
  public String getdatedebut() {
    return datedebut;
  }
  public String getdatefin() {
    return datefin;
  }
  public String getdatecreation() {
    return datecreation;
  }

  public void setdatecreation(String datecreation) {
    this.datecreation = datecreation;
  }
  public void setdatefin(String datefin) {
    this.datefin = datefin;
  }
  public void setdatedebut(String datedebut) {
    this.datedebut = datedebut;
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

  public String isetat() {
    return etat;
  }

  public void setetat(String isetat) {
    this.etat = isetat;
  }

  @Override
  public String toString() {
    return "Absence [id=" + id + ", titre=" + titre + ", desc=" + description + ", etat=" + etat + "]";
  }
}