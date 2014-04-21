package com.taskkeeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taskkeeper.persistence.repository.WorkItemRepository;
import com.taskkeeper.persistence.repository.UserRepository;
import com.taskkeeper.persistence.services.WorkItemPersistenceEventHandler;
import com.taskkeeper.persistence.services.WorkItemPersistenceService;
import com.taskkeeper.persistence.services.UserPersistenceEventHandler;
import com.taskkeeper.persistence.services.UserPersistenceService;

@Configuration
public class PersistenceConfig {
	
  @Bean
  public WorkItemPersistenceService orderPersistenceService(WorkItemRepository ordersRepository) {
    return new WorkItemPersistenceEventHandler(ordersRepository);
  }
  
  
  @Bean
  public UserPersistenceService userPersistenceService(UserRepository usersRepository) {
    return new UserPersistenceEventHandler(usersRepository);
  }

}
