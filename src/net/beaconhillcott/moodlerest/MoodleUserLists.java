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
public class MoodleUserLists extends MoodleWarnings implements Serializable {
  ArrayList<UserList> users=null;

  public MoodleUserLists() {
  }

  public ArrayList<UserList> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<UserList> users) {
    this.users = users;
  }
  
  public void addUserList(UserList user) {
    if (users==null) users=new ArrayList<UserList>();
    users.add(user);
  }
}
