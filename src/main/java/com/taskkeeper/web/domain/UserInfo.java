package com.taskkeeper.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.taskkeeper.events.user.UserDetails;


public class UserInfo implements Serializable {

  @NotNull
  @NotEmpty
	private String firstName;

  @NotNull
  @NotEmpty
	private String lastName;

  @NotNull
  @NotEmpty
	private String username;

  @NotNull
  @NotEmpty
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	
	public UserDetails toUserDetails() {
		UserDetails details = new UserDetails();

    details.setId(0);
    details.setFirstName(this.firstName);
    details.setLastName(this.lastName);
    details.setUsername(this.username);
    details.setPassword(this.password);
    
	  return details;
  }

}
