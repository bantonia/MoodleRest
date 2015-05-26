/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class MoodleGroups implements Serializable {
  private ArrayList<MoodleGroup> groups=null;
  private ArrayList<MoodleWarning> warnings=null;

  public MoodleGroups() {
  }

  public ArrayList<MoodleGroup> getGroups() {
    return groups;
  }

  public void setGroups(ArrayList<MoodleGroup> groups) {
    this.groups = groups;
  }

  public ArrayList<MoodleWarning> getWarnings() {
    return warnings;
  }

  public void setWarnings(ArrayList<MoodleWarning> warnings) {
    this.warnings = warnings;
  }
  
  
}
