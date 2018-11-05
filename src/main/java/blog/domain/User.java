package blog.domain;

import java.util.Date;

public class User {
private int id;
private String username;
private String password;
private String role;
private Date registrationdate;

public User(int id, String username, String password, String role, Date registrationdate) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.role = role;
	this.registrationdate = registrationdate;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public Date getRegistrationdate() {
	return registrationdate;
}
public void setRegistrationdate(Date registrationdate) {
	this.registrationdate = registrationdate;
}
}
