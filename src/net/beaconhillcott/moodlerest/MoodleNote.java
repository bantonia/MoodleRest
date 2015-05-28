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
 * <p>Class to create objects which contain the details of a Moodle note.</p>
 * 
 * @author Bill Antonia
 */
public class MoodleNote implements Serializable {
  
    /**
     * <p></p>
     */
    public static final String FORMAT_TEXT="text";
    public static final String FORMAT_PLAIN="plain";
    public static final String FORMAT_MOODLE="moodle";
    public static final String FORMAT_MARKDOWN="markdown";
    /**
     * <p></p>
     */
    public static final String FORMAT_HTML="html";
  /**
   * <p></p>
   */
  public static final String PUBLISH_STATE_PERSONAL="personal";
  /**
   * <p></p>
   */
  public static final String PUBLISH_STATE_COURSE="course";
  /**
   * <p></p>
   */
  public static final String PUBLISH_STATE_SITE="site";
  
  private Long userId=null;
  private String publishState=null;
  private Long courseId=null;
  private String text=null;
  private DescriptionFormat format=null;
  //private String format=null;
  private String clientNoteId=null;
  private Long noteId=null;
  private String errorMessage=null;
  private Long created=null;
  private Long lastModified=null;
  private Long userModified=null;
  
  
  /**
   *
   */
  public MoodleNote() {}
  
  /**
   * <p>Constructor for creating a Moodle note error object.<br />Used internally.</p>
   * 
   * @param String clientNoteId String containing a user defined value to identify the note.
   * @param noteId
   * @param errorMessage
   * @throws MoodleRestNotesException
   */
  public MoodleNote(String clientNoteId, Long noteId, String errorMessage) throws MoodleRestNotesException {
    this.clientNoteId=clientNoteId;
    this.noteId=noteId;
    this.errorMessage=errorMessage;
    if (noteId==null) {
      throw new MoodleRestNotesException(this.errorMessage);
    }
  }
  
  /**
   * <p>Constructor to create a note entry.</p>
   * 
   * @param Long userId Id of the user to which the note is about.
   * @param String publishState The state of the note, "site", "personal" or "course".
   * @param Long courseId The ID of the course to which the note is to be attached.
   * @param String text The text of the note to be stored.
   * @param String format "html" or "text".
   * @param String clientNoteId String containing a user defined value to identify the note.
   */
  public MoodleNote(Long userId, String publishState, Long courseId, String text, String format, String clientNoteId) {
    this.userId=userId;
    this.publishState=publishState;
    this.courseId=courseId;
    this.text=text;
    if (format.toLowerCase().equals(FORMAT_TEXT) || format.toLowerCase().equals(FORMAT_PLAIN)) this.format=DescriptionFormat.PLAIN;
    if (format.toLowerCase().equals(FORMAT_HTML)) this.format=DescriptionFormat.HTML;
    if (format.toLowerCase().equals(FORMAT_MOODLE)) this.format=DescriptionFormat.MOODLE;
    if (format.toLowerCase().equals(FORMAT_MARKDOWN)) this.format=DescriptionFormat.MARKDOWN;
    this.clientNoteId=clientNoteId;
  }
  
  public MoodleNote(Long userId, String publishState, Long courseId, String text, DescriptionFormat format, String clientNoteId) {
    this.userId=userId;
    this.publishState=publishState;
    this.courseId=courseId;
    this.text=text;
    this.format=format;
    this.clientNoteId=clientNoteId;
  }

  public void setFormat(DescriptionFormat format) {
    this.format = format;
  }

  public Long getCreated() {
    return created;
  }

  public void setCreated(Long created) {
    this.created = created;
  }

  public Long getLastModified() {
    return lastModified;
  }

  public void setLastModified(Long lastModified) {
    this.lastModified = lastModified;
  }

  public Long getUserModified() {
    return userModified;
  }

  public void setUserModified(Long userModified) {
    this.userModified = userModified;
  }
  
  /**
   * <p>Method to return the userid the note is attached to.</p>
   * 
   * @return long userid
   */
  public Long getUserId() { return userId; }
  
  /**
   * <p>Method to return the publish state of the note.</p>
   * 
   * @return String publishState
   */
  public String getPublishState() { return publishState; }
  
  /**
   * <p>Method to return the course id the note is attached to.</p>
   * 
   * @return Long courseId
   */
  public Long getCourseId() { return courseId; }
  
  /**
   * <p>Method to return the text of the note.</p>
   * 
   * @return String text
   */
  public String getText() { return text; }
  
  /**
   * <p>Method to return the format of the note.</p>
   * 
   * @return String format
   */
  public String getFormat() { return format.toString(); }
  public DescriptionFormat getDescriptionFormat() { return format; }
  /**
   * <p>Method to return the user defined client id of the note.</p>
   * 
   * @return String clientNoteId
   */
  public String getClientNoteId() { return clientNoteId; }
  
  /**
   * <p>Method to return the note id. Populated after the note has been sent to Moodle if no errors occur.</p>
   * 
   * @return Long noteId
   */
  public Long getNoteId() { return noteId; }

  public String getErrorMessage() { return errorMessage; }
  
  /**
   * <p>Method to set the user id the note is to be attached to.</p>
   * 
   * @param Long userId
   */
  public void setUserId(Long userId) { this.userId=userId; }
  
  /**
   * <p>Method to set the publish state of the note.</p>
   * 
   * @param String publishState
   */
  public void setPublishState(String publishState) { this.publishState=publishState; }
  
  /**
   * <p>Method to set the course id to which the note is to be attached.</p>
   * 
   * @param Long courseId
   */
  public void setCourseId(Long courseId) { this.courseId=courseId; }
  
  /**
   * <p>Method to set the contents of the note.</p>
   * 
   * @param String text
   */
  public void setText(String text) { this.text=text; }
  
  /**
   * <p>Method to set the format of the note.</p>
   * 
   * @param String format
   */
  public void setFormat(String format) {
    if (format.toLowerCase().equals(FORMAT_TEXT) || format.toLowerCase().equals(FORMAT_PLAIN)) this.format=DescriptionFormat.PLAIN;
    if (format.toLowerCase().equals(FORMAT_HTML)) this.format=DescriptionFormat.HTML;
    if (format.toLowerCase().equals(FORMAT_MOODLE)) this.format=DescriptionFormat.MOODLE;
    if (format.toLowerCase().equals(FORMAT_MARKDOWN)) this.format=DescriptionFormat.MARKDOWN;
  }
  
  public void setDescriptionFormat(DescriptionFormat format) {
    this.format=format;
  }
  
  /**
   * <p>Method to set the user defined client note id.</p>
   * 
   * @param String clientNoteId
   */
  public void setClientNoteId(String clientNoteId) { this.clientNoteId=clientNoteId; }
  
  /**
   * <p>Method to set the note id.<br />
   * Used internally after a call to send the note to Moodle, the noteid attribute is then updated</p>
   * 
   * @param Long noteId
   */
  public void setNoteId(Long noteId) { this.noteId=noteId; }
  
  /**
   * <p>Method to set the errormessage attribute<br />
   * Used internally after a call to send the note when an error occurs.</p>
   * 
   * @param String errorMessage
   */
  public void setErrorMessage(String errorMessage) { this.errorMessage=errorMessage; }
  
  /**
   * <p>Method to set a MoodleNote objects attribute given its name and value as strings.</p>
   * 
   * @param String nodeName
   * @param String content
   */
  public void setMoodleNoteField(String nodeName,String content) {
    if (nodeName.equals("clientnoteid"))
      if (!content.isEmpty() && content!=null)
        setClientNoteId(content);
    if (nodeName.equals("noteid") || nodeName.equals("id")) setNoteId(Long.valueOf(content));
    if (nodeName.equals("courseid")) {courseId=Long.parseLong(content);}
    if (nodeName.equals("userid")) {userId=Long.parseLong(content);}
    if (nodeName.equals("format")) {}
    if (nodeName.equals("format")) {
      for (DescriptionFormat key : DescriptionFormat.values()) {
        if ((""+key.toInt()).equals(content)) {
          setFormat(key);
          break;
        }
      }
    }
    if (nodeName.equals("created")) {created=Long.parseLong(content);}
    if (nodeName.equals("content")) {this.text=content;}
    if (nodeName.equals("lastmodified")) {lastModified=Long.parseLong(content);}
    if (nodeName.equals("usermodified")) {userModified=Long.parseLong(content);}
    if (nodeName.equals("publishedstate")) {publishState=content;}
    if (getNoteId()==null)
      if (nodeName.equals("errorMessage")) setErrorMessage(content);
  }
}
