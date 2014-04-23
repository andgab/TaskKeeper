package com.taskkeeper.events.user;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.taskkeeper.events.ReadEvent;

public class AllUsersEvent extends ReadEvent {
	
	private final List<UserDetails> userDetails;
	
  public AllUsersEvent(List<UserDetails> userDetails) {
    this.userDetails = Collections.unmodifiableList(userDetails);
  }

  public Collection<UserDetails> getUserDetails() {
    return this.userDetails;
  }

}
