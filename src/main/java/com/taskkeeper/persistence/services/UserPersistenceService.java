package com.taskkeeper.persistence.services;

import com.taskkeeper.events.user.AllUsersEvent;
import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.RequestAllUsersEvent;
import com.taskkeeper.events.user.RequestUserByUsernameEvent;
import com.taskkeeper.events.user.UserCreatedEvent;
import com.taskkeeper.events.user.UserEvent;

public interface UserPersistenceService {
	
	public UserCreatedEvent createUser(CreateUserEvent createUserEvent);
	
	public UserEvent requestUserByUsername(RequestUserByUsernameEvent requestUserByUsernameEvent);
	
	public AllUsersEvent requestAllUserSortedByFirstName(RequestAllUsersEvent requestAllUsersEvent);

}
