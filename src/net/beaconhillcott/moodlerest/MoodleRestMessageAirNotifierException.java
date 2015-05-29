/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author Bill Antonia
 */
public class MoodleRestMessageAirNotifierException extends MoodleRestException implements Serializable {

  public MoodleRestMessageAirNotifierException() {
  }

  public MoodleRestMessageAirNotifierException(String msg) {
    super(msg);
  }
  
}
