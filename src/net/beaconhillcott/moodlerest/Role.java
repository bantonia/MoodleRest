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
public enum Role implements Serializable {
  
  MANAGER(1),
  COURSE_CREATOR(2),
  TEACHER(3),
  NON_EDITING_TEACHER(4),
  STUDENT(5),
  GUEST(6),
  AUTHENTICATED_USER(7),
  AUTHENTICATED_USER_ON_FRONT_PAGE(8);
  
  private final int value;
  
  public int getInt() {
    return this.value;
  }
  private Role(int value) {
    this.value=value;
  }
  
}
