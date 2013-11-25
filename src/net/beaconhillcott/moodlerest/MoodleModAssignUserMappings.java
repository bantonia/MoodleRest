/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class MoodleModAssignUserMappings implements Serializable {
  private Long assignmentId=null;
  private ArrayList<UserMappings> userMappings=null;

  public MoodleModAssignUserMappings() {
    userMappings=new ArrayList<UserMappings>();
  }
  
  public MoodleModAssignUserMappings(Long assignmentId) {
    this.assignmentId=assignmentId;
    userMappings=new ArrayList<UserMappings>();
  }

  public Long getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(Long assignmentId) {
    this.assignmentId = assignmentId;
  }

  public ArrayList<UserMappings> getUserMappings() {
    return userMappings;
  }

  public void setUserMappings(ArrayList<UserMappings> userMappings) {
    this.userMappings = userMappings;
  }
  
  public UserMappings newUserMappings() {
    UserMappings userMapping=new UserMappings();
    userMappings.add(userMapping);
    return userMapping;
  }
  
  public UserMappings newUserMappings(Long id) {
    UserMappings userMapping=new UserMappings(id);
    userMappings.add(userMapping);
    return userMapping;
  }
  
  public class UserMappings {
    private Long id=null;
    private Long userId=null;

    public UserMappings() {}

    public UserMappings(Long id) {
      this.id=id;
    }
    
    public void setFieldValue(String name, String value) {
      if (value!=null) {
        if (!value.isEmpty()) {
          if (name.equals("id") || name.equals("userid")) {
            if (name.equals("id")) {
              setId(Long.parseLong(value));
            } else {
              if (name.equals("userid")) {
                setUserId(Long.parseLong(value));
              }
            }
          }
        }
      }
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getUserId() {
      return userId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }
    
  }
}
