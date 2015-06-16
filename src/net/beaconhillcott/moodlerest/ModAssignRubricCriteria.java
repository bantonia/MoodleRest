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
public class ModAssignRubricCriteria implements Serializable {
  private Long criterionId=null;
  private ArrayList<ModAssignRubricFilling> fillings=null;

  public ModAssignRubricCriteria() {
  }

  public ModAssignRubricCriteria(Long criterionId, ArrayList<ModAssignRubricFilling> fillings) {
    this.criterionId = criterionId;
    this.fillings = fillings;
  }

  public Long getCriterionId() {
    return criterionId;
  }

  public void setCriterionId(Long criterionId) {
    this.criterionId = criterionId;
  }

  public ArrayList<ModAssignRubricFilling> getFillings() {
    return fillings;
  }

  public void setFillings(ArrayList<ModAssignRubricFilling> fillings) {
    this.fillings = fillings;
  }
  
  public void addFilling(ModAssignRubricFilling filling) {
    if (fillings==null) fillings=new ArrayList<ModAssignRubricFilling>();
    fillings.add(filling);
  }
  
}
