package com.taskkeeper.events.user;


import com.taskkeeper.events.ReadEvent;


public class UserDetailsEvent extends ReadEvent {
	private long id;
	private UserDetails userDetails;
	
  private UserDetailsEvent(long id) {
    this.id = id;
  }

  public UserDetailsEvent(long id, UserDetails userDetails) {
    this.id = id;
    this.userDetails = userDetails;
  }
  
  public long getId() {
    return id;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }
  
  
  public static UserDetailsEvent notFound(long id) {
  	UserDetailsEvent ev = new UserDetailsEvent(id);
    ev.entityFound=false;
    return ev;
  }	
	

}
