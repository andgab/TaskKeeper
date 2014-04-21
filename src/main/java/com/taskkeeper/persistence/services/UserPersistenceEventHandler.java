package com.taskkeeper.persistence.services;


import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.RequestUserByUsernameEvent;
import com.taskkeeper.events.user.UserCreatedEvent;
import com.taskkeeper.events.user.UserDetailsEvent;
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
	public UserDetailsEvent requestUserByUsername(RequestUserByUsernameEvent requestUserByUsernameEvent)
	{
		User user = usersRepository.findByUsername(requestUserByUsernameEvent.getUsername());
		
    if (user == null) {
      return UserDetailsEvent.notFound(0);
    }

    return new UserDetailsEvent(user.getId(), user.toUserDetails());		
	}
	

}
