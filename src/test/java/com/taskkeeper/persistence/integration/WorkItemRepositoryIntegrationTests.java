package com.taskkeeper.persistence.integration;


import com.taskkeeper.config.JPAConfiguration;
import com.taskkeeper.config.StandaloneDataConfig;
import com.taskkeeper.persistence.domain.WorkItem;
import com.taskkeeper.persistence.repository.WorkItemRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StandaloneDataConfig.class, JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WorkItemRepositoryIntegrationTests {

  @Autowired
  WorkItemRepository workItemRepository;

  @Test
  public void thatItemIsInsertedIntoRepoWorks() throws Exception {

    WorkItem workItem = new WorkItem();
    workItem.setCreateDate(new Date());

    workItem = workItemRepository.save(workItem);

    WorkItem retrievedOrder = workItemRepository.findById(workItem.getId());

    assertNotNull(retrievedOrder);
    assertEquals(workItem.getId(), retrievedOrder.getId());
  }

}