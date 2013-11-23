/*
 *  Copyright (C) 2012 Bill Antonia
 * 
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Bill Antonia
 */
public class MoodleModAssignGrades implements Serializable {

  private Long assignmentId=null;
  private ArrayList<Grade> grades=null;
  
  public MoodleModAssignGrades() { grades=new ArrayList<Grade>();}
  public MoodleModAssignGrades(Long assignmentId) { this.assignmentId=assignmentId; grades=new ArrayList<Grade>();}
  
  public Grade newGrade() {
    if (grades==null) {
      grades=new ArrayList<Grade>();
    }
    Grade grade=new Grade();
    grades.add(grade);
    return grade;
  }

  public Long getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(Long assignmentId) {
    this.assignmentId = assignmentId;
  }

  public ArrayList<Grade> getGrades() {
    return grades;
  }

  public void setGrades(ArrayList<Grade> grades) {
    this.grades = grades;
  }
  
  public class Grade {
    private Long id=null;
    private Long userId=null;
    private Long timeCreated=null;
    private Long timeModified=null;
    private Long grader=null;
    private String grade=null;
    
    public void setId(Long id) { this.id=id; }
    public void setUserId(Long userId) { this.userId=userId; }
    public void setTimeCreated(Long timeCreated) { this.timeCreated=timeCreated; }
    public void setTimeModified(Long timeModified) { this.timeModified=timeModified; }
    public void setGrader(Long grader) { this.grader=grader; }
    public void setGrade(String grade) { this.grade=grade; }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getTimeCreated() { return timeCreated; }
    public Long getTimeModified() { return timeModified; }
    public Long getGrader() { return grader; }
    public String getGrade() { return grade; }

    public void setFieldValue(String field, String value) {
      if (field!=null && !field.isEmpty()) {
        if (field.equals("id")) { setId(Long.parseLong(value)); }
        if (field.equals("userid")) { setUserId(Long.parseLong(value)); }
        if (field.equals("timecreated")) { setTimeCreated(Long.parseLong(value)); }
        if (field.equals("timemodified")) { setTimeModified(Long.parseLong(value)); }
        if (field.equals("grader")) { setGrader(Long.parseLong(value)); }
        if (field.equals("grade")) { setGrade(value); }
      }
    }
  }
}
