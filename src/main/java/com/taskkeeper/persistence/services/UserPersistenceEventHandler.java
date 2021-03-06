package com.taskkeeper.persistence.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.taskkeeper.events.user.AllUsersEvent;
import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.RequestAllUsersEvent;
import com.taskkeeper.events.user.RequestUserByUsernameEvent;
import com.taskkeeper.events.user.UserCreatedEvent;
import com.taskkeeper.events.user.UserDetails;
import com.taskkeeper.events.user.UserEvent;
import com.taskkeeper.persistence.domain.User;
import com.taskkeeper.persistence.repository.UserRepository;

public class UserPersistenceEventHandler implements UserPersistenceService{
	
	private final UserRepository usersRepository;

	// private final OrderStatusRepository orderStatusRepository;

	public UserPersistenceEventHandler(final UserRepository usersRepository /*,
	    final OrderStatusRepository orderStatusRepository*/) {
		
		this.usersRepository = usersRepository;
		//this.orderStatusRepository = orderStatusRepository;
	}
	
	
	@Override
	public UserCreatedEvent createUser(CreateUserEvent createUserEvent) {
		
		User user = User.fromUserDetails(createUserEvent.getDetails());	
		
		user.setId(null);
		user = usersRepository.save(user);

		return new UserCreatedEvent(user.getId(), user.toUserDetails());
	}	
	
	
	@Override
	public UserEvent requestUserByUsername(RequestUserByUsernameEvent requestUserByUsernameEvent) {
		
		User user = usersRepository.findByUsername(requestUserByUsernameEvent.getUsername());
		
    if (user == null) {
      return UserEvent.notFound(0);
    }

    return new UserEvent(user.getId(), user.toUserDetails());		
	}
	
	@Override
	public AllUsersEvent requestAllUserSortedByFirstName(RequestAllUsersEvent requestAllUsersEvent) {
		List<UserDetails> generatedDetails = new ArrayList<UserDetails>();
		
    for (User user : usersRepository.findAll(new Sort(Sort.Direction.ASC, "firstname"))) {
      generatedDetails.add(user.toUserDetails());
    }
		return new AllUsersEvent(generatedDetails);
	}
	

}
