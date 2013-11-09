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

  
  MANAGER(1L),
  COURSE_CREATOR(2L),
  TEACHER(3L),
  NON_EDITING_TEACHER(4L),
  STUDENT(5L),
  GUEST(6L),
  AUTHENTICATED_USER(7L),
  AUTHENTICATED_USER_ON_FRONT_PAGE(8L);
  
  private Role(Long value) {
    this.value=value;
  }

  private Long value;

  public Long toLongValue() {
    return value.longValue();
  }
  
}
