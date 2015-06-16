/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class ModAssignFeedbackCommentsEditor implements Serializable {
  private String text=null;
  private DescriptionFormat format=null;

  public ModAssignFeedbackCommentsEditor() {
  }

  public ModAssignFeedbackCommentsEditor(String text, DescriptionFormat format) {
    this.text = text;
    this.format = format;
  }
  
  public void setField(String name, String value) {
    if (name.equals("text")) {text=value;}
    if (name.equals("format")) { //int   //intro format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
      for (DescriptionFormat key : DescriptionFormat.values()) {
        if ((""+key.toInt()).equals(value)) {
          setFormat(key);
          break;
        }
      }
    }
  }
  
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public DescriptionFormat getFormat() {
    return format;
  }

  public void setFormat(DescriptionFormat format) {
    this.format = format;
  }
  
}
