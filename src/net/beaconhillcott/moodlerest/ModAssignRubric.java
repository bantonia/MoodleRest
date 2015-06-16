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
public class ModAssignRubric implements Serializable {
  private ArrayList<ModAssignRubricCriteria> criteria=null;

  public ModAssignRubric() {
  }

  public ModAssignRubric(ArrayList<ModAssignRubricCriteria> criteria) {
    this.criteria = criteria;
  }

  public ArrayList<ModAssignRubricCriteria> getCriteria() {
    return criteria;
  }

  public void setCriteria(ArrayList<ModAssignRubricCriteria> criteria) {
    this.criteria = criteria;
  }
  
  public void addRubricCriteria(ModAssignRubricCriteria rubricCriteria) {
    if (criteria==null) criteria=new ArrayList<ModAssignRubricCriteria>();
    criteria.add(rubricCriteria);
  }
}
