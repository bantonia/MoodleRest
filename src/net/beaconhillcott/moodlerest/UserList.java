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
public class UserList implements Serializable {
  
  private Long userId=null;
  private Long courseId=null;
  private Integer roleId=null;
  private Boolean configured=null;
  
  /**
   *
   */
  public UserList() {}
  
  /**
   *
   * @param userid
   * @param courseid
   */
  public UserList(Long userId, Long courseId) {
    this.userId=userId;
    this.courseId=courseId;
  }

  public UserList(Long userId, Long courseId, Integer roleId) {
    this.userId=userId;
    this.courseId=courseId;
    this.roleId=roleId;
  }
  
  /**
   *
   * @return
   */
  public Long getUserId() {
    return userId;
  }
  
  /**
   *
   * @return
   */
  public Long getCourseId() {
    return courseId;
  }
  
  /**
   * 
   * @param userid
   */
  public final void setUserId(Long userId) {
    this.userId=userId;
  }
  
  /**
   *
   * @param courseid
   */
  public final void setCourseId(Long courseId) {
    this.courseId=courseId;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public Boolean isConfigured() {
    return configured;
  }

  public void setConfigured(Boolean configured) {
    this.configured = configured;
  }
  
}
