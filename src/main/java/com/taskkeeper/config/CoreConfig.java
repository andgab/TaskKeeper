package com.taskkeeper.config;


import com.taskkeeper.core.services.WorkItemEventHandler;
import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.core.services.UserEventHandler;
import com.taskkeeper.core.services.UserSecurityService;
import com.taskkeeper.core.services.UserService;
import com.taskkeeper.persistence.services.WorkItemPersistenceService;
import com.taskkeeper.persistence.services.UserPersistenceService;





import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CoreConfig {
	
  @Bean
  public WorkItemService orderService(WorkItemPersistenceService orderPersistenceService) {
    return new WorkItemEventHandler(orderPersistenceService);
  }
  
  
  @Bean
  public UserDetailsService userSecurityService(UserPersistenceService userPersistenceService) {
    return new UserSecurityService(userPersistenceService);
  }
  
  
  @Bean
  public UserService userService(UserPersistenceService userPersistenceService, PasswordEncoder passwordEncoder) {
    return new UserEventHandler(userPersistenceService, passwordEncoder);
  }
  
}