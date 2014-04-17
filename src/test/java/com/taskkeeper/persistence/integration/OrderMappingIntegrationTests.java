package com.taskkeeper.persistence.integration;

import com.taskkeeper.config.JPAConfiguration;
import com.taskkeeper.config.StandaloneDataConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.taskkeeper.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static com.taskkeeper.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StandaloneDataConfig.class, JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrderMappingIntegrationTests {

	@PersistenceContext
  EntityManager manager;

  @Test
  public void thatItemCustomMappingWorks() throws Exception {

    assertTableExists(manager, "NOODLE_ORDERS");

    assertTableExists(manager, "ORDER_ORDER_ITEMS");

    assertTableHasColumn(manager, "NOODLE_ORDERS", "ORDER_ID");
    assertTableHasColumn(manager, "NOODLE_ORDERS", "SUBMISSION_DATETIME");
  }

}