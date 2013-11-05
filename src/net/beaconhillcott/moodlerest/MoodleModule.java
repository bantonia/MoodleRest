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

import java.util.ArrayList;
import java.io.Serializable;

/**
 * <p>Class for which to create object in which to store module information that has been attached to a course.</p>
 * 
 * @author Bill Antonia
 * @see MoodleRestCourse
 * @see MoodleModuleContent
 */
public class MoodleModule implements Serializable {
  
    /**
     * <p></p>
     */
    public static final boolean MODULE_VISIBLE_TO_STUDENTS_NO=false;
    /**
     * <p></p>
     */
    public static final boolean  MODULE_VISIBLE_TO_STUDENTS_YES=true;
          
  private Long id=null;
  private String url=null;
  private String name=null;
  private String description=null;
  private Boolean visible=null;
  private String modicon=null;
  private String modname=null;
  private String modplural=null;
  private Long availablefrom=null;
  private Long availableuntil=null;
  private Integer indent=null;
  private ArrayList<MoodleModuleContent> content=null;
  
  /**
   * <p>Constructor for bean purposes</p>
   */
  public MoodleModule() {}
  
  /**
   * <p>Constructor used to set up initial module information which will be completed later from data returned.</p>
   * 
   * @param id long
   */
  public MoodleModule(long id) {
    this.id=id;
    content=new ArrayList();
  }
  
  /**
   * <p>Constructor using complete module information.</p>
   * 
   * @param id long
   * @param name String
   * @param modicon String
   * @param modname String
   * @param modplural String
   * @param indent int
   */
  public MoodleModule(Long id, String name, String modicon, String modname, String modplural, Integer indent) {
    this.id=id;
    this.name=name;
    this.modicon=modicon;
    this.modname=modname;
    this.modplural=modplural;
    this.indent=indent;
    content=new ArrayList();
  }
  
  /**
   * <p>Method to set the id attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param id long
   */
  public void setId(Long id) {
    this.id=id;
  }
  
  /**
   * <p>Method to set the url attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param url String
   */
  public void setUrl(String url) {
    this.url=url;
  }
  
  /**
   * <p>Method to set the name attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param name String
   */
  public void setName(String name) {
    this.name=name;
  }
  
  /**
   * <p>Method to set the description attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param description String
   */
  public void setDescription(String description) {
    this.description=description;
  }
  
  /**
   * <p>Method to set the visible attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param visible boolean
   */
  public void setVisible(Boolean visible) {
    this.visible=visible;
  }
  
  /**
   * <p>Method to set the modicon attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param modicon String
   */
  public void setModIcon(String modicon) {
    this.modicon=modicon;
  }
  
  /**
   * <p>Method to set the modname attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param modname String
   */
  public void setModName(String modname) {
    this.modname=modname;
  }
  
  /**
   * <p>Method to set the modplural attribute of a course module object.<br />
   * This call is used internally.</p>
   * @param modplural String
   */
  public void setModPlural(String modplural) {
    this.modplural=modplural;
  }
  
  /**
   * <p>Method to set the availablefrom attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param availablefrom long
   */
  public void setAvailableFrom(Long availablefrom) {
    this.availablefrom=availablefrom;
  }
  
  /**
   * <p>Method to set the availableuntil attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param availableuntil long
   */
  public void setAvailableUntil(Long availableuntil) {
    this.availableuntil=availableuntil;
  }
  
  /**
   * <p>Method to set the indent attribute of a course module object.<br />
   * This call is used internally.</p>
   * 
   * @param indent int
   */
  public void setIndent(Integer indent) {
    this.indent=indent;
  }
  
  /**
   * <p>Method to get the id attribute of a course module object.</p>
   * 
   * @return id long
   */
  public Long getId() {
    return id;
  }
  
  /**
   * <p>Method to get the url attribute of a course module object.</p>
   * 
   * @return url String
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * <p>Method to get the name attribute of a course module object.</p>
   * 
   * @return name String
   */
  public String getName() {
    return name;
  }
  
  /**
   * <p>Method to get the description attribute of a course module object.</p>
   * 
   * @return description String
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * <p>Method to get the visible attribute of a course module object.</p>
   * 
   * @return visible boolean
   */
  public Boolean getVisible() {
    return visible;
  }
  
  /**
   * <p>Method to get the modicon attribute of a course module object.</p>
   * 
   * @return modicon String
   */
  public String getModIcon() {
    return modicon;
  }
  
  /**
   * <p>Method to get the modname attribute of a course module object.</p>
   * 
   * @return modname String
   */
  public String getModName() {
    return modname;
  }
  
  /**
   * <p>Method to get the modplural attribute of a course module object.</p>
   * 
   * @return modplural String
   */
  public String getModPlural() {
    return modplural;
  }
  
  /**
   * <p>Method to get the availablefrom attribute of a course module object.</p>
   * 
   * @return availablefrom long
   */
  public Long getAvailableFrom() {
    return availablefrom;
  }
  
  /**
   * <p>Method to get the availableuntil attribute of a course module object.</p>
   * 
   * @return availableuntil long
   */
  public Long getAvailableUntil() {
    return availableuntil;
  }
  
  /**
   * <p>Method to get the indent attribute of a course module object.</p>
   * 
   * @return indent int
   */
  public Integer getIndent() {
    return indent;
  }
  
  /**
   * <p>Method to set attribute field of a course module object given the name of the field and value as Strings.</p>
   * 
   * @param name String
   * @param content String
   */
  public void setMoodleModuleField(String name, String content) {
    if (name.equals("id")) setId(Long.parseLong(content.trim()));
    if (name.equals("name")) setName(content);
    if (name.equals("description")) setDescription(content);
    if (name.equals("visible")) setVisible(Integer.parseInt(content)==0?MODULE_VISIBLE_TO_STUDENTS_NO:MODULE_VISIBLE_TO_STUDENTS_YES);
    if (name.equals("modicon")) setModIcon(content);
    if (name.equals("modname")) setModName(content);
    if (name.equals("modplural")) setModPlural(content);
    if (name.equals("availablefrom")) {
      if (content.isEmpty()) {
        content="0" ;
      }
      setAvailableFrom(Long.parseLong(content.trim()));
    }
    if (name.equals("availableuntil")) {
      if (content.isEmpty()) {
        content="0" ;
      }
      setAvailableUntil(Long.parseLong(content.trim()));
    }
    if (name.equals("indent")) {
      if (content.isEmpty()) {
        content="0" ;
      }
      setIndent(Integer.parseInt(content));
    }
  }
  
  /**
   * <p>Method to add a MoodleModuleContent object to the ArrayList.</p>
   * 
   * @param content
   */
  public void addContent(MoodleModuleContent content) {
    this.content.add(content);
  }
  
  /**
   * <p>Method to get an array of MoodleModuleContent objects.</p>
   * 
   * @return MoodleModuleContent[]
   */
  /*public ArrayList<MoodleModuleContent> getContent() {
    if (content.isEmpty())
      return null;
    return content;
  }*/
  
  public MoodleModuleContent[] getContent() {
    if (content.isEmpty())
      return null;
    MoodleModuleContent[] result=new MoodleModuleContent[content.size()];
    for (int i=0; i<content.size(); i++) {
      result[i]=content.get(i);
    }
    return result;
  }
}
