package com.taskkeeper.events.user;


import com.taskkeeper.events.CreatedEvent;

public class UserCreatedEvent extends CreatedEvent {
	private final long newId;
	private final UserDetails details;

	public UserCreatedEvent(final long newId, final UserDetails details) {
		this.newId = newId;
		this.details = details;
	}

	public long getNewId() {
		return newId;
	}

	public UserDetails getDetails() {
		return details;
	}

}
