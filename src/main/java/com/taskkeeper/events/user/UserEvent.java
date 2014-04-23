package com.taskkeeper.events.user;


import com.taskkeeper.events.ReadEvent;


public class UserEvent extends ReadEvent {
	private long id;
	private UserDetails userDetails;
	
  private UserEvent(long id) {
    this.id = id;
  }

  public UserEvent(long id, UserDetails userDetails) {
    this.id = id;
    this.userDetails = userDetails;
  }
  
  public long getId() {
    return id;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }
  
  
  public static UserEvent notFound(long id) {
  	UserEvent ev = new UserEvent(id);
    ev.entityFound=false;
    return ev;
  }	
	

}
