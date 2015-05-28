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
public class MoodleNotes extends MoodleWarnings implements Serializable {
  ArrayList<MoodleNote> siteNotes=null;
  ArrayList<MoodleNote> courseNotes=null;
  ArrayList<MoodleNote> personalNotes=null;

  public MoodleNotes() {
  }

  public ArrayList<MoodleNote> getSiteNotes() {
    return siteNotes;
  }

  public void setSiteNotes(ArrayList<MoodleNote> siteNotes) {
    this.siteNotes = siteNotes;
  }

  public ArrayList<MoodleNote> getCourseNotes() {
    return courseNotes;
  }

  public void setCourseNotes(ArrayList<MoodleNote> courseNotes) {
    this.courseNotes = courseNotes;
  }

  public ArrayList<MoodleNote> getPersonalNotes() {
    return personalNotes;
  }

  public void setPersonalNotes(ArrayList<MoodleNote> personalNotes) {
    this.personalNotes = personalNotes;
  }
  
  public void addSiteNote(MoodleNote siteNote) {
    if (siteNotes==null) siteNotes=new ArrayList<MoodleNote>();
    siteNotes.add(siteNote);
  }
  
  public void addCourseNote(MoodleNote courseNote) {
    if (courseNotes==null) courseNotes=new ArrayList<MoodleNote>();
    courseNotes.add(courseNote);
  }
  
  public void addPersonalNote(MoodleNote personalNote) {
    if (personalNotes==null) personalNotes=new ArrayList<MoodleNote>();
    personalNotes.add(personalNote);
  }
  
  
}
