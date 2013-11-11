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
public enum EnrolledUsersOption implements Serializable {
  
  WITH_CAPABILITY("withcapability"),
  GROUP_ID("groupid"),
  ONLY_ACTIVE("onlyactive"),
  USER_FIELDS("userfields"),
  LIMIT_FROM("limitfrom"),
  LIMIT_NUMBER("limitnumber");
  
  private String value;
  
  private EnrolledUsersOption(String value) {
    this.value=value;
  }
  
  @Override
  public String toString() {
    return value;
  }
  
}
