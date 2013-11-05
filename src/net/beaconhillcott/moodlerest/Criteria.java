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
 * @author root
 */
public class Criteria  implements Serializable {
  private String key=null;
  private String value=null;

  /**
   *
   */
  public Criteria() {}

  /**
   *
   * @param name
   * @param value
   */
  public Criteria(String key, String value) {
    this.key=key;
    this.value=value;
  }

  /**
   *
   * @return  String
   */
  public String getKey() {
    return key;
  }

  /**
   *
   * @return String
   */
  public String getValue() {
    return value;
  }

  /**
   *
   * @param name
   */
  public void setKey(String key) {
    this.key=key;
  }

  /**
   *
   * @param value
   */
  public void setValue(String value) {
    this.value=value;
  }
}
