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
public class MoodleCompletionStatus implements Serializable {
  private Long cmId=null; // int   //comment ID
  private String modName=null; // string   //activity module name
  private Long instance=null; // int   //instance ID
  private Integer state=null; // int   //completion state value: 0 means incomplete, 1 complete, 2 complete pass, 3 complete fail
  private Long timeCompleted=null; // int   //timestamp for completed activity
  private Long tracking=null; // int   //type of tracking

  public MoodleCompletionStatus() {
  }

  public Long getCmId() {
    return cmId;
  }

  public void setCmId(Long cmId) {
    this.cmId = cmId;
  }

  public String getModName() {
    return modName;
  }

  public void setModName(String modName) {
    this.modName = modName;
  }

  public Long getInstance() {
    return instance;
  }

  public void setInstance(Long instance) {
    this.instance = instance;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public Long getTimeCompleted() {
    return timeCompleted;
  }

  public void setTimeCompleted(Long timeCompleted) {
    this.timeCompleted = timeCompleted;
  }

  public Long getTracking() {
    return tracking;
  }

  public void setTracking(Long tracking) {
    this.tracking = tracking;
  }

  void setField(String nodeName, String content) {
    if (nodeName.equals("cmid")) { setCmId(Long.parseLong(content));}
    if (nodeName.equals("modname")) {setModName(content);}
    if (nodeName.equals("instance")) { setInstance(Long.parseLong(content));}
    if (nodeName.equals("state")) { setState(Integer.parseInt(content));}
    if (nodeName.equals("timecompleted")) { setInstance(Long.parseLong(content));}
    if (nodeName.equals("tracking")) { setTracking(Long.parseLong(content));}
  }
  
}
