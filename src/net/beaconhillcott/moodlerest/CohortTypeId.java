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
public enum CohortTypeId implements Serializable {
  ID("id"),
  ID_NUMBER("idnumber"),
  SYSTEM("system");
  
  private CohortTypeId(String value) {
    this.value=value;
  }

  private String value;

  @Override
  public String toString() {
    return value;
  }
}
