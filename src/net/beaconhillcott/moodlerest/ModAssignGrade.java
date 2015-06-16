/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class ModAssignGrade implements Serializable {
  private Long userId=null;
  private Double grade=null;
  private Long attemptNumber=null;
  private Long addAttempt=null;
  private String workflowState=null;
  private ModAssignPluginData pluginData=null;
  private ModAssignAdvancedGradingData advancedGradingData=null;

  public ModAssignGrade() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Double getGrade() {
    return grade;
  }

  public void setGrade(Double grade) {
    this.grade = grade;
  }

  public Long getAttemptNumber() {
    return attemptNumber;
  }

  public void setAttemptNumber(Long attemptNumber) {
    this.attemptNumber = attemptNumber;
  }

  public Long getAddAttempt() {
    return addAttempt;
  }

  public void setAddAttempt(Long addAttempt) {
    this.addAttempt = addAttempt;
  }

  public String getWorkflowState() {
    return workflowState;
  }

  public void setWorkflowState(String workflowState) {
    this.workflowState = workflowState;
  }

  public ModAssignPluginData getPluginData() {
    return pluginData;
  }

  public void setPluginData(ModAssignPluginData pluginData) {
    this.pluginData = pluginData;
  }

  public ModAssignAdvancedGradingData getAdvancedGradingData() {
    return advancedGradingData;
  }

  public void setAdvancedGradingData(ModAssignAdvancedGradingData advancedGradingData) {
    this.advancedGradingData = advancedGradingData;
  }
  
}
