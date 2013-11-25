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
public class MoodleModAssignUserFlags implements Serializable {
  private Long assignmentId=null;
  private ArrayList<UserFlags> userFlags=null;

  public MoodleModAssignUserFlags() {
    userFlags=new ArrayList<UserFlags>();
  }
  
  public MoodleModAssignUserFlags(Long assignmentId) {
    this.assignmentId=assignmentId;
    userFlags=new ArrayList<UserFlags>();
  }

  public Long getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(Long assignmentId) {
    this.assignmentId = assignmentId;
  }

  public ArrayList<UserFlags> getUserFlags() {
    return userFlags;
  }

  public void setUserFlags(ArrayList<UserFlags> userFlags) {
    this.userFlags = userFlags;
  }
  
  public UserFlags newUserFlags() {
    UserFlags userFlag=new UserFlags();
    userFlags.add(userFlag);
    return userFlag;
  }
  
  public UserFlags newUserFlags(Long id) {
    UserFlags userFlag=new UserFlags(id);
    userFlags.add(userFlag);
    return userFlag;
  }
  
  public class UserFlags {
    private Long id=null;
    private Long userId=null;
    private Boolean locked=null;
    private Boolean mailed=null;
    private Long extensionDueDate=null;
    private String workflowState=null;
    private Long allocatedMarker=null;

    public UserFlags() {}

    public UserFlags(Long id) {
      this.id=id;
    }
    
    public void setFieldValue(String name, String value) {
      if (value!=null) {
        if (!value.isEmpty()) {
          if (name.equals("id") || name.equals("userid") || name.equals("extensionduedate") || name.equals("allocatedmarker")) {
            if (name.equals("id")) {
              setId(Long.parseLong(value));
            } else {
              if (name.equals("userid")) {
                setUserId(Long.parseLong(value));
              } else {
                if (name.equals("extensionduedate")) {
                  setExtensionDueDate(Long.parseLong(value));
                } else {
                  if (name.equals("allocatedmarker")) {
                    setAllocatedMarker(Long.parseLong(value));
                  }
                }
              }
            }
          } else {
            if (name.equals("locked")) {
              setLocked(Integer.parseInt(value)==0?false:true);
            }else {
              if (name.equals("mailed")) {
                setMailed(Integer.parseInt(value)==0?false:true);
              }
            }
          }
        }
        if (name.equals("workflowstate")) {
            setWorkflowState(value);
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

    public Boolean isLocked() {
      return locked;
    }

    public void setLocked(Boolean locked) {
      this.locked = locked;
    }

    public Long getExtensionDueDate() {
      return extensionDueDate;
    }

    public void setExtensionDueDate(Long extensionDueDate) {
      this.extensionDueDate = extensionDueDate;
    }

    public String getWorkflowState() {
      return workflowState;
    }

    public void setWorkflowState(String workflowState) {
      this.workflowState = workflowState;
    }

    public Long getAllocatedMarker() {
      return allocatedMarker;
    }

    public void setAllocatedMarker(Long allocatedMarker) {
      this.allocatedMarker = allocatedMarker;
    }

    public Boolean getMailed() {
      return mailed;
    }

    public void setMailed(Boolean mailed) {
      this.mailed = mailed;
    }
    
  }
}
