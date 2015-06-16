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
public class MoodleRestCompletionException extends MoodleRestException implements Serializable {
  
  public static final String NO_LEGACY_CALL="No legacy call";
    
  MoodleRestCompletionException() {}

  MoodleRestCompletionException(String msg) {
    super(msg);
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }
    
}
