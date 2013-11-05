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
 *
 * @author Bill Antonia
 */
public class UserEnrolledCourse implements Serializable {
  private Long id=null;
  private String fullname=null;
  private String shortname=null;
  
  public UserEnrolledCourse() {}
  
  public UserEnrolledCourse(Long id) {
    this.id=id;
  }
  
  public UserEnrolledCourse(Long id, String fullname, String shortname) {
    this.id=id;
    this.fullname=fullname;
    this.shortname=shortname;
  }
  
  public Long getId() {
    return id;
  }
  
  public String getFullName() {
    return fullname;
  }
  
  public String getShortName() {
    return shortname;
  }
  
  public void setId(Long id) {
    this.id=id;
  }
  
  public void setFullName(String fullname) {
    this.fullname=fullname;
  }
  
  public void setShortName(String shortname) {
    this.shortname=shortname;
  }
  
  public void setUserEnrolledCourseField(String field, String value) {
    if (field.equals("id")) setId(Long.valueOf(value));
    if (field.equals("fullname") && value!=null) setFullName(value);
    if (field.equals("shortname") && value!=null) setShortName(value);
  }
}
