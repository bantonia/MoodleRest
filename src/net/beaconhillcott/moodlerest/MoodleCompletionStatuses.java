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
class MoodleCompletionStatuses implements Serializable {

  private ArrayList<MoodleCompletionStatus> statuses=null;
  private ArrayList<MoodleWarning> warn=null;
  
  public void addCompletionStatus(MoodleCompletionStatus status) {
    if (statuses==null) {
      statuses=new ArrayList<MoodleCompletionStatus>();
    }
    statuses.add(status);
  }
  
  public void addWarning(MoodleWarning warning) {
    if (warn==null) {
      warn=new ArrayList<MoodleWarning>();
    }
    warn.add(warning);
  }
  
  public void setComments(ArrayList<MoodleCompletionStatus> statuses) {
    this.statuses=statuses;
  }

  public void setWarnings(ArrayList<MoodleWarning> warn) {
    this.warn=warn;
  }
  
}
