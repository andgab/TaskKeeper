package com.taskkeeper.persistence.integration;


import com.taskkeeper.config.JPAConfiguration;
import com.taskkeeper.config.StandaloneDataConfig;
import com.taskkeeper.persistence.domain.WorkItem;
import com.taskkeeper.persistence.domain.fixture.PersistenceFixture;
import com.taskkeeper.persistence.repository.WorkItemRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StandaloneDataConfig.class, JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WorkItemRepositoryFindWorkItemContainingTests {

  @Autowired
  WorkItemRepository workItemRepository;

//  @Test
//  public void thatSearchingForOrdesContainingWorks() throws Exception {
//
//    workItemRepository.save(PersistenceFixture.standardWorkItem());
//    workItemRepository.save(PersistenceFixture.standardWorkItem());
//    workItemRepository.save(PersistenceFixture.standardWorkItem());
//    workItemRepository.save(PersistenceFixture.standardWorkItem());
//
//    WorkItem retrievedWorkItem = workItemRepository.findById(1L);
//
//    assertNotNull(retrievedWorkItem);
//    assertEquals(2, retrievedWorkItem.size());
//    assertEquals(22, (int) retrievedWorkItem.get(0).getOrderItems().get("yummy16"));
//
//    retrievedWorkItem = workItemRepository.findOrdersContaining("yummy3");
//
//    assertNotNull(retrievedWorkItem);
//    assertEquals(2, retrievedWorkItem.size());
//  }

}