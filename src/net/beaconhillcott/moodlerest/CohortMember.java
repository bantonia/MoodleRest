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
public class CohortMember implements Serializable {
  
  private CohortTypeId cohortTypeId=null;
  private String cohortIdValue=null;
  private UserTypeId userTypeId=null;
  private String userIdValue=null;

  public CohortMember() {
  }

  public CohortMember(CohortTypeId cohortTypeId, String cohortIdValue, UserTypeId userTypeId, String userIdValue) {
    this.cohortTypeId=cohortTypeId;
    this.cohortIdValue=cohortIdValue;
    this.userTypeId=userTypeId;
    this.userIdValue=userIdValue;
  }
  
  public CohortTypeId getCohortTypeId() {
    return cohortTypeId;
  }

  public void setCohortTypeId(CohortTypeId cohortTypeId) {
    this.cohortTypeId = cohortTypeId;
  }

  public String getCohortIdValue() {
    return cohortIdValue;
  }

  public void setCohortIdValue(String cohortIdValue) {
    this.cohortIdValue = cohortIdValue;
  }

  public UserTypeId getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(UserTypeId userTypeId) {
    this.userTypeId = userTypeId;
  }

  public String getUserIdValue() {
    return userIdValue;
  }

  public void setUserIdValue(String userIdValue) {
    this.userIdValue = userIdValue;
  }
  
}
