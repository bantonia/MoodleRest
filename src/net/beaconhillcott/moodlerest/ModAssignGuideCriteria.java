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
public class ModAssignGuideCriteria implements Serializable {
  private Long criterionId=null;
  private ArrayList<ModAssignGuideFilling> fillings=null;

  public ModAssignGuideCriteria() {
  }

  public ModAssignGuideCriteria(Long criterionId, ArrayList<ModAssignGuideFilling> fillings) {
    this.criterionId = criterionId;
    this.fillings = fillings;
  }

  public Long getCriterionId() {
    return criterionId;
  }

  public void setCriterionId(Long criterionId) {
    this.criterionId = criterionId;
  }

  public ArrayList<ModAssignGuideFilling> getFillings() {
    return fillings;
  }

  public void setFillings(ArrayList<ModAssignGuideFilling> fillings) {
    this.fillings = fillings;
  }
  
  public void addFilling(ModAssignGuideFilling filling) {
    if (fillings==null) fillings=new ArrayList<ModAssignGuideFilling>();
    fillings.add(filling);
  }
  
}
