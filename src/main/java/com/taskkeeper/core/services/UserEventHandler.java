package com.taskkeeper.core.services;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.taskkeeper.core.domain.User;
import com.taskkeeper.events.user.AllUsersEvent;
import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.RequestAllUsersEvent;
import com.taskkeeper.events.user.UserCreatedEvent;
import com.taskkeeper.persistence.services.UserPersistenceService;

public class UserEventHandler implements UserService {
	
	
	private final UserPersistenceService userPersistenceService;
	private final PasswordEncoder passwordEncoder;

	public UserEventHandler(final UserPersistenceService userPersistenceService, final PasswordEncoder passwordEncoder) {
		this.userPersistenceService = userPersistenceService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserCreatedEvent createUser(CreateUserEvent createUserEvent) {
		User user = User.fromUserDetails(createUserEvent.getDetails());
		
		//TODO: add check for user name 
		
		// encode password before the user is stored in the database
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		createUserEvent = new CreateUserEvent(user.toUserDetails());
		UserCreatedEvent event = userPersistenceService.createUser(createUserEvent);
		
		return event;
	}
	
	
	public AllUsersEvent requestAllUsersSortedByFirstname(RequestAllUsersEvent requestAllUsersEvent) {	
		return userPersistenceService.requestAllUserSortedByFirstName(requestAllUsersEvent);
	}
	

}
