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
public class ModAssignGrades implements Serializable {
  
  private ArrayList<ModAssignGrade> grades=null;

  public ModAssignGrades() {
  }

  public ModAssignGrades(ArrayList<ModAssignGrade> grades) {
    this.grades = grades;
  }

  public ArrayList<ModAssignGrade> getGrades() {
    return grades;
  }

  public void setGrades(ArrayList<ModAssignGrade> grades) {
    this.grades = grades;
  }
  
  public void addGrade(ModAssignGrade grade) {
    if (grades==null) grades=new ArrayList<ModAssignGrade>();
    grades.add(grade);
  }
}
