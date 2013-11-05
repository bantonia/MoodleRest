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
public class UserCustomField implements Serializable {
  
  private String type=null;
  private String value=null;
  private String name=null;
  private String shortname=null;
  
  /**
   *
   */
  public UserCustomField() {}
  
  /**
   *
   * @param type
   * @param value
   * @param name
   * @param shortname
   */
  public UserCustomField(String type, String value, String name, String shortname) {
    this.type=type;
    this.value=value;
    this.name=name;
    this.shortname=shortname;
  }
  
  /**
   *
   * @return
   */
  public String getType() {
    return type;
  }
  
  /**
   *
   * @return
   */
  public String getValue() {
    return value;
  }
  
  /**
   *
   * @return
   */
  public String getName() {
    return name;
  }
  
  /**
   *
   * @return
   */
  public String getShortname() {
    return shortname;
  }
  
  /**
   * 
   * @param type
   */
  public void setType(String type) {
    this.type=type;
  }
  
  /**
   *
   * @param value
   */
  public void setValue(String value) {
    this.value=value;
  }
  
  /**
   *
   * @param name
   */
  public void setName(String name) {
    this.name=name;
  }
  
  /**
   *
   * @param shortname
   */
  public void setShortname(String shortname) {
    this.shortname=shortname;
  }
  
  /**
   *
   * @param nodeName
   * @param content
   */
  public void setCustomFieldField(String nodeName, String content) {
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("type")) setType(content);
    if (nodeName.equals("value")) setValue(content);
    if (nodeName.equals("shortname")) setShortname(content);
  }
}
