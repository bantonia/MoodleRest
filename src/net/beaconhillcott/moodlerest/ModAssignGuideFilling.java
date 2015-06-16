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
public class ModAssignGuideFilling extends ModAssignFilling implements Serializable {
  private Double score=null;

  public ModAssignGuideFilling() {
  }

  public ModAssignGuideFilling(Long criterionId, Long levelId, String remark, DescriptionFormat remarkFormat, Double score) {
    super(criterionId, levelId, remark, remarkFormat);
    this.score = score;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }
  
}
