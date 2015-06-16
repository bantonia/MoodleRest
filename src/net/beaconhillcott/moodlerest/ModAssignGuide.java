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
public class ModAssignGuide implements Serializable {
  private ArrayList<ModAssignGuideCriteria> criteria=null;

  public ModAssignGuide() {
  }

  public ModAssignGuide(ArrayList<ModAssignGuideCriteria> criteria) {
    this.criteria = criteria;
  }

  public ArrayList<ModAssignGuideCriteria> getCriteria() {
    return criteria;
  }

  public void setCriteria(ArrayList<ModAssignGuideCriteria> criteria) {
    this.criteria = criteria;
  }
  
  public void addGuideCriteria(ModAssignGuideCriteria guideCriteria) {
    if (criteria==null) criteria=new ArrayList<ModAssignGuideCriteria>();
    criteria.add(guideCriteria);
  }
}
