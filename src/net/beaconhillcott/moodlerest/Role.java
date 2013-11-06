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
public class Role implements Serializable {
  
  public static final int MANAGER=1;
  public static final int COURSE_CREATOR=2;
  public static final int TEACHER=3;
  public static final int NON_EDITING_TEACHER=4;
  public static final int STUDENT=5;
  public static final int GUEST=6;
  public static final int AUTHENTICATED_USER=7;
  public static final int AUTHENTICATED_USER_ON_FRONT_PAGE=8;
  
}
