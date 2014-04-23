package com.taskkeeper.core.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.taskkeeper.events.user.RequestUserByUsernameEvent;
import com.taskkeeper.events.user.UserEvent;
import com.taskkeeper.persistence.services.UserPersistenceService;

public class UserSecurityService implements UserDetailsService {

	private final UserPersistenceService userPersistenceService;

	public UserSecurityService(final UserPersistenceService userPersistenceService) {
		this.userPersistenceService = userPersistenceService;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserEvent userDetailsEvent = userPersistenceService.requestUserByUsername(new RequestUserByUsernameEvent(username));
		
		if(!userDetailsEvent.isEntityFound()) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}
		
    boolean enabled = true;  
    boolean accountNonExpired = true;  
    boolean credentialsNonExpired = true;  
    boolean accountNonLocked = true;  
    
    
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
    grantedAuthorities.add(grantedAuthority);
        
    return  new User(  
    				userDetailsEvent.getUserDetails().getUsername(),   
    				userDetailsEvent.getUserDetails().getPassword(),   
            enabled,   
            accountNonExpired,   
            credentialsNonExpired,   
            accountNonLocked,  
            grantedAuthorities  
    ); 		
	}

}
