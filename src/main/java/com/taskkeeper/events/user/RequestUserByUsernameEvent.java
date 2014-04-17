package com.taskkeeper.events.user;

import com.taskkeeper.events.RequestReadEvent;

public class RequestUserByUsernameEvent extends RequestReadEvent {
	
	private String username;
	
	public RequestUserByUsernameEvent(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
