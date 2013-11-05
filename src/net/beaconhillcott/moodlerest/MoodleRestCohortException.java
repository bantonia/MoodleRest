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
class MoodleRestCohortException extends MoodleRestException implements Serializable {
  
  public static final String INCONSISTENT_DATA_PARSE="Returned data not in correct order";

  public MoodleRestCohortException(String string) {
  }
  
}
