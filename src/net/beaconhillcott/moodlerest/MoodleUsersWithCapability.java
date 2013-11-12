/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author root
 */
public class MoodleUsersWithCapability implements Serializable {
  
  private Long courseId=null;
  private Capability capability=null;
  private ArrayList<MoodleUser> users=null;

  public MoodleUsersWithCapability() {
  }

  public MoodleUsersWithCapability(Long courseId, Capability capability, ArrayList<MoodleUser> users) {
    this.courseId = courseId;
    this.capability = capability;
    this.users = users;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public Capability getCapability() {
    return capability;
  }

  public void setCapability(Capability capability) {
    this.capability = capability;
  }

  public ArrayList<MoodleUser> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<MoodleUser> users) {
    this.users = users;
  }
  
}
