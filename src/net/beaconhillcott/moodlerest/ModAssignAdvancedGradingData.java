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
public class ModAssignAdvancedGradingData implements Serializable {
  
  private ModAssignGuide guide=null;
  private ModAssignRubric rubric=null;

  public ModAssignAdvancedGradingData() {
  }

  public ModAssignGuide getGuide() {
    return guide;
  }

  public void setGuide(ModAssignGuide guide) {
    this.guide = guide;
  }

  public ModAssignRubric getRubric() {
    return rubric;
  }

  public void setRubric(ModAssignRubric rubric) {
    this.rubric = rubric;
  }
  
}
