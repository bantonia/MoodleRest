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
class RemoveDevice extends MoodleWarnings implements Serializable {

  private Boolean removed;

  public RemoveDevice() {
    this.removed = null;
  }

  public RemoveDevice(Boolean removed) {
    this.removed = removed;
  }

  public Boolean isRemoved() {
    return removed;
  }

  public void setRemoved(Boolean removed) {
    this.removed = removed;
  }
}
