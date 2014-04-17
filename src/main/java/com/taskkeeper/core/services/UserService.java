package com.taskkeeper.core.services;

import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.UserCreatedEvent;

public interface UserService {
	
	public UserCreatedEvent createUser(CreateUserEvent createUserEvent);

}
