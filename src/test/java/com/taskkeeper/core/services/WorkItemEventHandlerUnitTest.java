package com.taskkeeper.core.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.taskkeeper.core.domain.fixtures.WorkItemsFixtures;
import com.taskkeeper.core.services.WorkItemEventHandler;
import com.taskkeeper.events.workitem.*;
import com.taskkeeper.persistence.services.WorkItemPersistenceService;








import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WorkItemEventHandlerUnitTest {
	
  WorkItemEventHandler uut;
  WorkItemPersistenceService mockWorkItemPersistenceService;
  
  @Before
  public void setupUnitUnderTest() {
    mockWorkItemPersistenceService = mock(WorkItemPersistenceService.class);
    uut = new WorkItemEventHandler(mockWorkItemPersistenceService);
  }
  
  @Test
  public void addANewWorkItemToTheSystem() {

    when(mockWorkItemPersistenceService.createWorkItem(any(CreateWorkItemEvent.class))).thenReturn(new WorkItemCreatedEvent(null, WorkItemsFixtures.standardOrderDetails()));

    CreateWorkItemEvent ev = new CreateWorkItemEvent(new WorkItemDetails());

    uut.createWorkItem(ev);

    verify(mockWorkItemPersistenceService).createWorkItem(any(CreateWorkItemEvent.class));
    verifyNoMoreInteractions(mockWorkItemPersistenceService);
  }
  
  @Test
  public void addTwoNewWorkItemToTheSystem() {

    when(mockWorkItemPersistenceService.createWorkItem(any(CreateWorkItemEvent.class))).thenReturn(new WorkItemCreatedEvent(null, WorkItemsFixtures.standardOrderDetails()));

    CreateWorkItemEvent ev = new CreateWorkItemEvent(new WorkItemDetails());

    uut.createWorkItem(ev);
    uut.createWorkItem(ev);

    verify(mockWorkItemPersistenceService, times(2)).createWorkItem(any(CreateWorkItemEvent.class));
    verifyNoMoreInteractions(mockWorkItemPersistenceService);
  }
  
  @Test
  public void requestAllWorkItemsFromTheSystem() {
  	
  	List<WorkItemDetails> workItems = new ArrayList<WorkItemDetails>();
  	
  	workItems.add(WorkItemsFixtures.standardOrderDetails());
  	
  	when(mockWorkItemPersistenceService.requestAllWorkItems(any(RequestAllWorkItemsEvent.class))).thenReturn(new AllWorkItemsEvent(workItems));
  	
  	RequestAllWorkItemsEvent ev = new RequestAllWorkItemsEvent();
  	
  	AllWorkItemsEvent allWorkItemsEvent = uut.requestAllWorkItems(ev);
  	
  	verify(mockWorkItemPersistenceService).requestAllWorkItems(any(RequestAllWorkItemsEvent.class));
  	assertEquals(1, allWorkItemsEvent.getWorkItemDetails().size());
  }

  @Test
  public void removeAWorkItemFromTheSystemFailsIfNotPresent() {
    
    WorkItemEvent workItemDetailsEvent = new WorkItemEvent(1L, null) {
    	@Override
    	public boolean isEntityFound() {
    		return false;
    	}    	
    };

    when(mockWorkItemPersistenceService.requestWorkItemDetails(any(RequestWorkItemEvent.class))).thenReturn(workItemDetailsEvent);
    
    DeleteWorkItemEvent ev = new DeleteWorkItemEvent(1L);
    
    WorkItemDeletedEvent workItemDeletedEvent = uut.deleteWorkItem(ev);
    
    verify(mockWorkItemPersistenceService, never()).deleteWorkItem(any(DeleteWorkItemEvent.class));
    
    assertFalse(workItemDeletedEvent.isEntityFound());
    assertFalse(workItemDeletedEvent.isDeletionCompleted());
  }
  

  @Test
  public void removeAnWorkItemFromTheSystemWorksIfExists() {

    WorkItemDetails workItemDetails = WorkItemsFixtures.standardOrderDetails();
    
    WorkItemEvent orderDetailsEvent = new WorkItemEvent(1L, workItemDetails);

    when(mockWorkItemPersistenceService.requestWorkItemDetails(any(RequestWorkItemEvent.class))).thenReturn(orderDetailsEvent);

    DeleteWorkItemEvent ev = new DeleteWorkItemEvent(1L);

    WorkItemDeletedEvent workItemDeletedEvent = uut.deleteWorkItem(ev);

    verify(mockWorkItemPersistenceService).deleteWorkItem(any(DeleteWorkItemEvent.class));

    assertTrue(workItemDeletedEvent.isEntityFound());
    assertTrue(workItemDeletedEvent.isDeletionCompleted());
    assertEquals(workItemDetails.getLastUpdate(), workItemDeletedEvent.getDetails().getLastUpdate());
  }

}
