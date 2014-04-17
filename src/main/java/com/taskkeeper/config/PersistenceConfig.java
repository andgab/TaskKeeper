package com.taskkeeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taskkeeper.persistence.repository.OrdersRepository;
import com.taskkeeper.persistence.repository.UsersRepository;
import com.taskkeeper.persistence.services.OrderPersistenceEventHandler;
import com.taskkeeper.persistence.services.OrderPersistenceService;
import com.taskkeeper.persistence.services.UserPersistenceEventHandler;
import com.taskkeeper.persistence.services.UserPersistenceService;

@Configuration
public class PersistenceConfig {
	
  @Bean
  public OrderPersistenceService orderPersistenceService(OrdersRepository ordersRepository) {
    return new OrderPersistenceEventHandler(ordersRepository);
  }
  
  
  @Bean
  public UserPersistenceService userPersistenceService(UsersRepository usersRepository) {
    return new UserPersistenceEventHandler(usersRepository);
  }

}
