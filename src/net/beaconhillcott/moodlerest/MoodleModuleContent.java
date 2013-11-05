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
 * <p>Class to create objects to store the information and content of a module of a course after a call to MoodleRestCourse.getCourseContent</p>
 * 
 * @author Bill Antonia
 * @see MoodleCourseContent
 * @see MoodleRestCourse
 */
public class MoodleModuleContent implements Serializable {

  private String type=null;
  private String filename=null;
  private String filepath=null;
  private long filesize=-1;
  private String fileurl=null;
  private String content=null;
  private long timecreated=-1;
  private long timemodified=-1;
  private int sortorder=-1;
  private long userid=-1;
  private String author=null;
  private String license=null;
  
  /**
   * <p>Method used to store returned data into a MoodleModuleContent object given the attribute name and value as strings.</p>
   * 
   * @param nodeName String
   * @param content String
   */
  protected void setMoodleModuleContentField(String nodeName, String content) {
    if (nodeName.equals("type")) setType(content);
    if (nodeName.equals("filename")) setFilename(content);
    if (nodeName.equals("filepath")) setFilePath(content);
    if (nodeName.equals("filesize")) setFileSize(Long.parseLong(content));
    if (nodeName.equals("fileurl")) setFileURL(content);
    if (nodeName.equals("content")) setContent(content);
    if (nodeName.equals("timecreated") && !content.equals("")) setTimeCreated(Long.parseLong(content));
    if (nodeName.equals("timemodified") && !content.equals("")) setTimeModified(Long.parseLong(content));
    if (nodeName.equals("userid") && !content.equals("")) setUserId(Long.parseLong(content));
    if (nodeName.equals("author")) setAuthor(content);
    if (nodeName.equals("license")) setLicense(content);
  }
  
  /**
   * <p>Method to return the type of module in the course.</p>
   * 
   * @return type String
   */
  public String getType() {
    return type;
  }
  
  /**
   * <p>Method to return the filename of the content.</p>
   *
   * @return filename String
   */
  public String getFilename() {
    return filename;
  }
  
  /**
   * <p>Method to return the filepath of the content</p>
   * 
   * @return filepath String
   */
  public String getFilePath() {
    return filepath;
  }
  
  /**
   * <p>Method to return the filesize of the content.</p>
   * 
   * @return filesize String
   */
  public long getFileSize() {
    return filesize;
  }
  
  /**
   * <p>Method to return the content as a Base64 uuencoded string.</p>
   * 
   * @return content String
   */
  public String getContent() {
    return content;
  }
  
  /**
   * <p>Method to return the url of the file.</p>
   * 
   * @return
   */
  public String getFileURL() {
    return fileurl;
  }
  
  /**
   * <p>Method to retur the created time and date in UNIX format.</p>
   * 
   * @return timecreated long
   */
  public long getTimeCreated() {
    return timecreated;
  }
  
  /**
   * <p>Method to retur the last modified time and date in UNIX format</p>
   * 
   * @return timemodified long
   */
  public long getTimeModified() {
    return timemodified;
  }
  
  /**
   * <p>Method to return the sort order.</p>
   * 
   * @return sortorder int
   */
  public int getSortOrder() {
    return sortorder;
  }
  
  /**
   * <p>method to return the user id.</p>
   * 
   * @return userid long
   */
  public long getUserId() {
    return userid;
  }
  
  /**
   * <p>Method to return the author of the module.</p>
   * 
   * @return author String
   */
  public String getAuthor() {
    return author;
  }                    
  
  /**
   * <p>Method to return the license set by the author.</p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            </p>
   * 
   * @return license String
   */
  public String getLicense() {
    return license;
  }
  
  /**
   * <p>Method to set the type of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param content String
   */
  public void setType(String type) {
    this.type=type;
  }
  
  /**
   * <p>Method to set the filename of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param filename String
   */
  public void setFilename(String filename) {
    this.filename=filename;
  }
  
  /**
   * <p>Method to set the filepath of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param filepath String
   */
  public void setFilePath(String filepath) {
    this.filepath=filepath;
  }
  
  /**
   * <p>Method to set the filesize of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param filesize long
   */
  public void setFileSize(long filesize) {
    this.filesize=filesize;
  }
  
  /**
   * <p>Method to set the file url of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param fileurl String
   */
  public void setFileURL(String fileurl) {
    this.fileurl=fileurl;
  }
  
  /**
   * <p>Method to set the content stored in the object.<br />
   * The content is base64 uuencoded as returned by Moodle.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param content String
   */
  public void setContent(String content) {
    this.content=content;
  }
  
  /**
   * <p>Method to set the created time in UNIX format of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param timecreated long
   */
  public void setTimeCreated(long timecreated) {
    this.timecreated=timecreated;
  }
  
  /**
   * <p>Method to set the modified date/time of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param timemodified long
   */
  public void setTimeModified(long timemodified) {
    this.timemodified=timemodified;
  }
  
  /**
   * <p>Method to set the sort order of content stored in the object.</p>
   * 
   * @param sortorder int
   */
  public void setSortOrder(int sortorder) {
    this.sortorder=sortorder;
  }
  
  /**
   * <p>Method to set the userid of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param userid long
   */
  public void setUserId(long userid) {
    this.userid=userid;
  }
  
  /**
   * <p>Method to set the author of content stored in the object.<br />
   * Used internally after returning data from Moodle.</p>
   * 
   * @param author String
   */
  public void setAuthor(String author) {
    this.author=author;
  }
  
  /**
   * <p>Method to the current licence,</p>
   * 
   * @param license String
   */
  public void setLicense(String license) {
    this.license=license;
  }
  
}
