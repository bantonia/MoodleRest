/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;
/**
 *
 * @author root
 */
public class ModAssignUserFlags implements Serializable {
  
  private Long userId=null;
  private Boolean locked=null;
  private Boolean mailed=null;
  private Long extensionDueDate=null;
  private String workflowState=null;
  private Long allocatedMarker=null;

  public ModAssignUserFlags() {
  }

  public ModAssignUserFlags(Long userId) {
    this.userId=userId;
  }

  public void setFieldValue(String name, String value) {
    if (name.equals("userid") || name.equals("locked") || name.equals("mailed") || name.equals("extensionduedate") || name.equals("workflowstate") || name.equals("allocatedmarker")) {
      if (name.equals("userid")) {
        if (value!=null) {
          setUserId(Long.parseLong(value));
        }
      }
      if (name.equals("locked")) {
        if (value!=null) {
          setLocked(value.equals("0")?false:true);
        }
      }
      if (name.equals("mailed")) {
        if (value!=null) {
          setMailed(value.equals("0")?false:true);
        }
      }
      if (name.equals("extensionduedate")) {
        if (value!=null) {
          setExtensionDueDate(Long.parseLong(value));
        }
      }
      if (name.equals("workflowstate")) {
        setWorkflowState(value);
      }
      if (name.equals("allocatedmarker")) {
        if (value!=null) {
          setAllocatedMarker(Long.parseLong(value));
        }
      }
    }
  }
  
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public Boolean getMailed() {
    return mailed;
  }

  public void setMailed(Boolean mailed) {
    this.mailed = mailed;
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
  
}
