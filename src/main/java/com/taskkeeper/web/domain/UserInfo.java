package com.taskkeeper.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.taskkeeper.events.user.UserDetails;

public class UserInfo implements Serializable {

	private Long id;

	@NotNull
	@NotEmpty
	private String firstname;

	@NotNull
	@NotEmpty
	private String lastname;

	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String password;

	public UserInfo() {
		id = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastName) {
		this.lastname = lastName;
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

		details.setId(id);
		details.setFirstname(this.firstname);
		details.setLastname(this.lastname);
		details.setUsername(this.username);
		details.setPassword(this.password);

		return details;
	}

	public static UserInfo fromUserDetails(UserDetails details) {
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(details, userInfo);

		return userInfo;
	}

}
