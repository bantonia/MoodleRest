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
 * @author Bill Antonia
 */
public class MoodleComments implements Serializable {

  private ArrayList<MoodleComment> comments=null;
  private ArrayList<MoodleWarning> warnings=null;
  
  public MoodleComments() {
  }

  public ArrayList<MoodleComment> getComments() {
    return comments;
  }

  public void setComments(ArrayList<MoodleComment> comments) {
    this.comments = comments;
  }

  public ArrayList<MoodleWarning> getWarnings() {
    return warnings;
  }

  public void setWarnings(ArrayList<MoodleWarning> warnings) {
    this.warnings = warnings;
  }
  
  public void addComment(MoodleComment comment) {
    if (comments==null) {
      comments=new ArrayList<MoodleComment>();
    }
    comments.add(comment);
  }
  
  public void addWarning(MoodleWarning warning) {
    if (warnings==null) {
      warnings=new ArrayList<MoodleWarning>();
    }
    warnings.add(warning);
  }
}
