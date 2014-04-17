package com.taskkeeper.events.user;

import com.taskkeeper.events.CreateEvent;

public class CreateUserEvent extends CreateEvent {

	private UserDetails userDetails;

	public CreateUserEvent(UserDetails details) {
		this.userDetails = details;
	}

	public UserDetails getDetails() {
		return userDetails;
	}

}
