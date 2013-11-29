/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

/**
 *
 * @author root
 */
public class MoodleDuplicatedCourse {
  private Long id=null;
  private String shortName=null;

  public MoodleDuplicatedCourse() {
  }

  public MoodleDuplicatedCourse(Long id) {
    this.id=id;
  }

  public void setFieldValue(String name, String value) {
    if (value!=null) {
      if (name.equals("id")) {
        if (!value.isEmpty()) {
          if (name.equals("id")) setId(Long.parseLong(value));
        }
      } else {
        if (name.equals("shortname")) {
          if (name.equals("shortname")) setShortName(value);
        }
      }
    }
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
  
}
