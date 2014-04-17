package com.taskkeeper.persistence.services;

import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.RequestUserByUsernameEvent;
import com.taskkeeper.events.user.UserCreatedEvent;
import com.taskkeeper.events.user.UserDetailsEvent;

public interface UserPersistenceService {
	
	public UserCreatedEvent createUser(CreateUserEvent createUserEvent);
	
	public UserDetailsEvent requestUserByUsername(RequestUserByUsernameEvent requestUserByUsernameEvent);

}
