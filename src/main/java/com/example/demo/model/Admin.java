package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Admins")
public class Admin {
  @Id
  private String id;

  private String username;
  private String motdepasse;
  private String email;
  private String role;
  private String permissions;

  public Admin() {

  }

  public Admin(String username, String motdepasse, String email) {
    this.username = username;
    this.motdepasse = motdepasse;
    this.email = email;
  }
  public String getpermissions() {
	    return permissions;
	  }

	  public void setpermissions(String permissions) {
	    this.permissions = permissions;
	  }
  public String getrole() {
	    return role;
	  }

	  public void setrole(String role) {
	    this.role = role;
	  }
  public String getId() {
    return id;
  }

  public String getusername() {
    return username;
  }

  public void setusername(String username) {
    this.username = username;
  }

  public String getmotdepasse() {
    return motdepasse;
  }

  public void setmotdepasse(String motdepasse) {
    this.motdepasse = motdepasse;
  }

  public String getemail() {
    return email;
  }

  public void setemail(String isemail) {
    this.email = isemail;
  }

  @Override
  public String toString() {
    return "Admin [id=" + id + ", username=" + username + ", desc=" + motdepasse + ", email=" + email + "]";
  }
}