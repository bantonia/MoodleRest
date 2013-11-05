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
  
  ROLE_MANAGER(1),
  ROLE_COURSE_CREATOR(2),
  ROLE_TEACHER(3),
  ROLE_NON_EDITING_TEACHER(4),
  ROLE_STUDENT(5),
  ROLE_GUEST(6),
  ROLE_AUTHENTICATED_USER(7),
  ROLE_AUTHENTICATED_USER_ON_FRONT_PAGE(8);
  
  private final int value;
  
  public int getInt() {
    return this.value;
  }
  private Role(int value) {
    this.value=value;
  }
  
}
