/*
 *  Copyright (C) 2013 Bill Antonia
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
public class MoodleWarning implements Serializable {

  private String item=null;
  private Integer itemid=null;
  private String warningcode=null;
  private String message=null;

  public MoodleWarning() {
  }
  
  public MoodleWarning(String warningcode) {
    this.warningcode=warningcode;
  }

  public MoodleWarning(String warningcode, String message) {
    this.warningcode=warningcode;
    this.message=message;
  }
  
  public void setMoodleWarningField(String nodeName, String content) {
    if (nodeName.equals("item")) {
      setItem(content);
    }
    if (nodeName.equals("itemid")) {
      setItemId(Integer.parseInt(content.trim()));
    }
    if (nodeName.equals("warningcode")) {
      setWarningCode(content);
    }
    if (nodeName.equals("message")) {
      setMessage(content);
    }
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public Integer getItemId() {
    return itemid;
  }

  public void setItemId(Integer itemid) {
    this.itemid = itemid;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getWarningCode() {
    return warningcode;
  }

  public void setWarningCode(String warningcode) {
    this.warningcode = warningcode;
  }

}
