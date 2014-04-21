package com.taskkeeper.events.workitem;

import com.taskkeeper.events.DeleteEvent;

public class DeleteWorkItemEvent extends DeleteEvent {
  private final Long id;

  public DeleteWorkItemEvent(final Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
