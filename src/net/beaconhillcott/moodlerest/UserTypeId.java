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
public enum UserTypeId implements Serializable {
  ID("id"),
  USERNAME("username");
  
  private UserTypeId(String value) {
    this.value=value;
  }

  private String value;

  @Override
  public String toString() {
    return value;
  }
}
