package com.taskkeeper.persistence.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.taskkeeper.events.workitem.*;
import com.taskkeeper.persistence.domain.WorkItem;
import com.taskkeeper.persistence.domain.fixture.PersistenceFixture;
import com.taskkeeper.persistence.repository.WorkItemRepository;
import com.taskkeeper.persistence.services.WorkItemPersistenceEventHandler;



public class WorkItemPersistenceEventHandlerUnitTest {
	
	WorkItemPersistenceEventHandler uut;
	WorkItemRepository mockWorkItemRepository;
	
	
  @Before
  public void setupUnitUnderTest() {
  	mockWorkItemRepository = mock(WorkItemRepository.class);
    uut = new WorkItemPersistenceEventHandler(mockWorkItemRepository);
  }
  
  
  @Test
  public void addANewWorkItemToTheSystem() {

    when(mockWorkItemRepository.save(any(WorkItem.class))).thenReturn(PersistenceFixture.standardWorkItem());

    CreateWorkItemEvent ev = new CreateWorkItemEvent(new WorkItemDetails());

    uut.createWorkItem(ev);

    verify(mockWorkItemRepository).save(any(WorkItem.class));
    verifyNoMoreInteractions(mockWorkItemRepository);
  }
  
 	

}
