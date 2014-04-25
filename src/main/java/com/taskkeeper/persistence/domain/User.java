package com.taskkeeper.persistence.domain;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import com.taskkeeper.events.user.UserDetails;

@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String username;
	private String password;
	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserDetails toUserDetails() {
		UserDetails details = new UserDetails();

		details.setId(this.id);
		details.setFirstname(this.firstname);
		details.setLastname(lastname);
		details.setUsername(username);
		details.setPassword(password);

		return details;
	}

	public static User fromUserDetails(UserDetails details) {
		User user = null;
		
		if (details != null) {
			user = new User();
			BeanUtils.copyProperties(details, user);
		}
		return user;
	}

}
