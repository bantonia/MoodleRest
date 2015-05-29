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
public class MoodleDatabases extends MoodleWarnings implements Serializable {
  ArrayList<MoodleDatabase> databases=null;

  public MoodleDatabases() {
  }

  public ArrayList<MoodleDatabase> getDatabases() {
    return databases;
  }

  public void setDatabases(ArrayList<MoodleDatabase> databases) {
    this.databases = databases;
  }
  
  public void addDatabase(MoodleDatabase database) {
    if (databases==null) {
      databases=new ArrayList<MoodleDatabase>();
    }
    databases.add(database);
  }
}
