/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class ComponentString implements Serializable {
  private String stringId=null;
  private String string=null;
  private String component=null;
  private String lang=null;
  private OptionParameter[] stringParams=null;

  public ComponentString() {
  }

  public ComponentString(String stringId) {
    this.stringId=stringId;
  }

  public void setFieldValue(String name, String value) {
    if (value!=null) {
      if (name.equals("stringid")) setStringId(value);
      if (name.equals("string")) setString(value);
      if (name.equals("component")) setComponent(value);
      if (name.equals("lang")) setLang(value);
    }
  }

  public OptionParameter[] getStringParams() {
    return stringParams;
  }

  public void setStringParams(OptionParameter[] stringParams) {
    this.stringParams = stringParams;
  }
  
  public String getStringId() {
    return stringId;
  }

  public void setStringId(String stringId) {
    this.stringId = stringId;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }
  
}
