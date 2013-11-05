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
public class UserGroup implements Serializable {

  private Long id=null;
  private String name=null;
  private String decription=null;
  
  /**
   *
   */
  public UserGroup() {}
  
  /**
   * 
   * @param id
   * @param name
   * @param description
   */
  public UserGroup(Long id, String name, String description) {
    this.id=id;
    this.name=name;
    this.decription=description;
  }
  
  /**
   *
   * @return
   */
  public Long getId() {
    return id;
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
  public String getDescription() {
    return decription;
  }
  
  /**
   *
   * @param id
   */
  public void setId(Long id) {
    this.id=id;
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
   * @param description
   */
  public void setDescription(String description) {
    this.decription=description;
  }
  
  /**
   *
   * @param nodeName
   * @param content
   */
  public void setUserGroupField(String nodeName,String content) {
    if (nodeName.equals("id")) setId(Long.valueOf(content));
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("description")) setDescription(content);
  }
}
