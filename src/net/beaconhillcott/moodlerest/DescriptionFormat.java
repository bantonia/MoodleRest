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
public enum DescriptionFormat implements Serializable {
  
  MOODLE(0),
  HTML(1),
  PLAIN(2),
  MARKDOWN(4);
  
  private final int value;
  
  public int getInt() {
    return this.value;
  }
  private DescriptionFormat(int value) {
    this.value=value;
  }
  
}
