package com.taskkeeper.persistence.domain.fixture;


import com.taskkeeper.persistence.domain.WorkItem;

import java.util.*;

public class PersistenceFixture {

  private static Long id = 1L;

  public static WorkItem standardWorkItem() {
    WorkItem workItem = new WorkItem();

    workItem.setId(id++);
    workItem.setTitle("Test WorkItem");
    workItem.setDescription("Test text");
    workItem.setCreateDate(new Date());
    workItem.setDoDate(new Date());
    workItem.setDoneDate(new Date());
    workItem.setLastUpdate(new Date());
    workItem.setStatus(1);

    return workItem;
  }

}
