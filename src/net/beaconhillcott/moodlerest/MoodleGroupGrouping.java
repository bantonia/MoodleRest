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
public class MoodleGroupGrouping implements Serializable {
  private Long groupingid=null;
  private Long groupid=null;

  public MoodleGroupGrouping() {
  }

  public MoodleGroupGrouping(Long groupingid, Long groupid) {
    this.groupingid=groupingid;
    this.groupid=groupid;
  }

  public Long getGroupid() {
    return groupid;
  }

  public void setGroupid(Long groupid) {
    this.groupid = groupid;
  }

  public Long getGroupingid() {
    return groupingid;
  }

  public void setGroupingid(Long groupingid) {
    this.groupingid = groupingid;
  }
  
}
