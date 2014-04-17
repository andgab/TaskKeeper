package com.taskkeeper.persistence.integration;

import javax.persistence.PersistenceContext;

import com.taskkeeper.config.JPAConfiguration;
import com.taskkeeper.config.StandaloneDataConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.taskkeeper.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static com.taskkeeper.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StandaloneDataConfig.class, JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserMappingIntegrationTests {

  @PersistenceContext
  EntityManager manager;

  @Test
  public void thatItemCustomMappingWorks() throws Exception {

    assertTableExists(manager, "user");

    assertTableExists(manager, "userRole");

    assertTableHasColumn(manager, "user", "id");
    assertTableHasColumn(manager, "user", "username");
  }

}
