/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author Bill Antonia
 */
public class ModAssignFilling implements Serializable {
  private Long criterionId=null;
  private Long levelId=null;
  private String remark=null;
  private DescriptionFormat remarkFormat=null;

  public ModAssignFilling() {
  }

  public ModAssignFilling(Long criterionId, Long levelId, String remark, DescriptionFormat remarkFormat) {
    this.criterionId = criterionId;
    this.levelId = levelId;
    this.remark = remark;
    this.remarkFormat = remarkFormat;
  }

  public void setField(String name, String value) {
    if (name.equals("criterionid")) {criterionId=Long.parseLong(value);}
    if (name.equals("levelid")) {levelId=Long.parseLong(value);}
    if (name.equals("remark")) {remark=value;}
    if (name.equals("remarkformat")) { //int   //intro format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
      for (DescriptionFormat key : DescriptionFormat.values()) {
        if ((""+key.toInt()).equals(value)) {
          setRemarkFormat(key);
          break;
        }
      }
    }
  }
  
  public Long getCriterionId() {
    return criterionId;
  }

  public void setCriterionId(Long criterionId) {
    this.criterionId = criterionId;
  }

  public Long getLevelId() {
    return levelId;
  }

  public void setLevelId(Long levelId) {
    this.levelId = levelId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public DescriptionFormat getRemarkFormat() {
    return remarkFormat;
  }

  public void setRemarkFormat(DescriptionFormat remarkFormat) {
    this.remarkFormat = remarkFormat;
  }
  
}
