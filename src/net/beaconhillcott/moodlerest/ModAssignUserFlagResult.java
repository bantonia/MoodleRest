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
public class ModAssignUserFlagResult implements Serializable {
  
  private Long id=null;
  private Long userId=null;
  private String errorMessage=null;

  public ModAssignUserFlagResult() {
  }

  public ModAssignUserFlagResult( Long id) {
    this.id=id;
  }

  public void setFieldValue(String name, String value) {
    if (name.equals("id") || name.equals("userid") || name.equals("errormessage")) {
      if (name.equals("id")) {
        if (value!=null) {
          if (!value.isEmpty()) {
            setId(Long.parseLong(value));
          }
        }
      }
      if (name.equals("userid")) {
        if (value!=null) {
          if (!value.isEmpty()) {
            setUserId(Long.parseLong(value));
          }
        }
      }
      if (name.equals("errormessage")) {
        setErrorMessage(value);
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

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  
  
}
