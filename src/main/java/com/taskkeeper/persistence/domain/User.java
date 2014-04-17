package com.taskkeeper.persistence.domain;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import com.taskkeeper.events.user.UserDetails;

@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserDetails toUserDetails() {
		UserDetails details = new UserDetails();

		details.setId(this.id);
		details.setFirstName(this.firstName);
		details.setLastName(lastName);
		details.setUsername(username);
		details.setPassword(password);

		return details;
	}

	public static User fromUserDetails(UserDetails details) {
		User user = new User();
		BeanUtils.copyProperties(details, user);

		return user;
	}

}
