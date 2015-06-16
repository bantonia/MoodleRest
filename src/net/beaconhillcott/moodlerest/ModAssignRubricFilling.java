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
public class ModAssignRubricFilling extends ModAssignFilling implements Serializable {

  public ModAssignRubricFilling() {
  }

  public ModAssignRubricFilling(Long criterionId, Long levelId, String remark, DescriptionFormat remarkFormat) {
    super(criterionId, levelId, remark, remarkFormat);
  }
  
}
