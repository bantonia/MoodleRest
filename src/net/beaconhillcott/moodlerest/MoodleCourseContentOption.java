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

/**
 * Class to create objects to store an option of a course content.
 *
 * @author Bill Antonia
 */
public class MoodleCourseContentOption implements Serializable {
  private String name=null;
  private String value=null;
  
  /**
   * Constructor for bean compatability
   */
  public MoodleCourseContentOption() {}
  
  /**
   * Method to set a field given its name and value as strings.
   * @param name String
   * @param value String
   */
  public MoodleCourseContentOption(String name, String value) {
    this.name=name;
    this.value=value;
  }
  
  /**
   * Method to read the name of a course option in the object.
   *
   * @return name String
   */
  public String getName() {
    return name;
  }
  
  /**
   * Method to read the value of a course option in the object.
   *
   * @return value String
   */
  public String getValue() {
    return value;
  }
  
  /**
   * Method to set the option name field in the object. Only useful in construction of data from the webservice.
   *
   * @param name String
   */
  public void setName(String name) {
    this.name=name;
  }
  
  /**
   * Method to set the option value field in the object. Only useful in construction of data from the webservice.
   *
   * @param value String
   */
  public void setValue(String value) {
    this.value=value;
  }
  
  /**
   * Method to set the option name and value fields in the object. Only useful in construction of data from the webservice.
   * 
   * @param name String
   * @param value String
   */
  public void setMoodleCourseContentOptionField(String name, String value) {
    this.name=name;
    this.value=value;
  }
  
}
