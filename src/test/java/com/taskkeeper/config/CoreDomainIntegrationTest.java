package com.taskkeeper.config;

import com.taskkeeper.config.CoreConfig;
import com.taskkeeper.config.JPAConfiguration;
import com.taskkeeper.config.PersistenceConfig;
import com.taskkeeper.config.StandaloneDataConfig;
import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.AllWorkItemsEvent;
import com.taskkeeper.events.workitem.CreateWorkItemEvent;
import com.taskkeeper.events.workitem.RequestAllWorkItemsEvent;
import com.taskkeeper.events.workitem.WorkItemDetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StandaloneDataConfig.class, JPAConfiguration.class, PersistenceConfig.class, CoreConfig.class})
public class CoreDomainIntegrationTest {
	
  @Autowired
  WorkItemService workItemService;
  
  @Test
  public void addANewOrderToTheSystem() {

    CreateWorkItemEvent ev = new CreateWorkItemEvent(new WorkItemDetails());

    workItemService.createWorkItem(ev);

    AllWorkItemsEvent allWorkItems = workItemService.requestAllWorkItems(new RequestAllWorkItemsEvent());

    assertEquals(1, allWorkItems.getWorkItemDetails().size());
  }

}
