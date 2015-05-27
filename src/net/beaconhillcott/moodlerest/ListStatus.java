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
public class ListStatus implements Serializable {
  private Integer status=null;
  private ArrayList<MoodleWarning> warnings=null;

  public ListStatus() {
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public ArrayList<MoodleWarning> getWarnings() {
    return warnings;
  }

  public void setWarnings(ArrayList<MoodleWarning> warnings) {
    this.warnings = warnings;
  }
  
  public void addWarnings(MoodleWarning warning) {
    if (warnings==null) warnings=new ArrayList<MoodleWarning>();
    warnings.add(warning);
  }
}
