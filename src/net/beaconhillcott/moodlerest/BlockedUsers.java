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
public class BlockedUsers implements Serializable {
  private ArrayList<MoodleUser> users=null;
  private ArrayList<MoodleWarning> warnings=null;

  public BlockedUsers() {
  }

  public ArrayList<MoodleUser> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<MoodleUser> users) {
    this.users = users;
  }

  public ArrayList<MoodleWarning> getWarnings() {
    return warnings;
  }

  public void setWarnings(ArrayList<MoodleWarning> warnings) {
    this.warnings = warnings;
  }
  
  public void addUser(MoodleUser user) {
    if (users==null) users=new ArrayList<MoodleUser>();
    users.add(user);
  }
  
  public void addWarning(MoodleWarning warning) {
    if (warnings==null) warnings=new ArrayList<MoodleWarning>();
    warnings.add(warning);
  }
}
