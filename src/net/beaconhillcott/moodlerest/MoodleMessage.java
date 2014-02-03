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
 * <p>Class to create an object which will hold the data required to send an instant message between users.</p>
 *
 * @author Bill Antonia
 * @see MoodleRestMessage
 */
public class MoodleMessage implements Serializable {
  
  private Long touserid=null;
  private String text=null;
  private String clientmsgid=null;
  private Long msgid=null;
  private String errormessage=null;

  /**
   * <p>Constructor for bean purposes.</p>
   */
  public MoodleMessage() {}

  /**
   * <p>Constructor to create a MoodleMessage object.</p>
   * <p>This is all which is required to send a message between users. The sender is the account which is used to access the webservice.</p>
   *
   * @param touserid long
   * @param text String
   */
  public MoodleMessage(Long touserid, String text) {
      this.touserid=touserid;
      this.text=text;
  }

  /**
   * <p>Constructor to create a MoodleMessage object with an optional client message id.</p>
   *
   * @param touserid long
   * @param text String
   * @param clientmsgid String
   */
  public MoodleMessage(Long touserid, String text, String clientmsgid) {
      this.touserid=touserid;
      this.text=text;
      this.clientmsgid=clientmsgid;
  }

  /**
   * <p>Method to set the user id to which the message is to be sent.</p>
   *
   * @param touserid long
   */
  public void setToUserId(Long touserid) {
      this.touserid=touserid;
  }

  /**
   * <p>Method to set the message string.</p>
   *
   * @param text String
   */
  public void setText(String text) {
      this.text=text;
  }

  /**
   * <p>Method to set the clientmsgid attribute.</p>
   *
   * @param clientmsgid String
   */
  public void setClientMsgId(String clientmsgid) {
      this.clientmsgid=clientmsgid;
  }

  /**
   * <p>Method used to set the msgid attribute.</p>
   * <p>Used internally after a call to send a message.</p>
   *
   * @param msgid long
   */
  public void setMsgId(Long msgid) {
      this.msgid=msgid;
  }

  /**
   * <p>Method to set the errormessage attribute.</p>
   * <p>Used internally, if a message cannot be sent to a user the reason will be stored using this method.</p>
   *
   * @param errormessage String
   */
  public void setErrorMessage(String errormessage) {
      this.errormessage=errormessage;
  }

  /**
   * <p>Method to get the userid of the user in the object.</p>
   *
   * @return long
   */
  public Long getToUserId() {
      return touserid;
  }

  /**
   * <p>Method to return the message stored in the MoodleMessage object.</p>
   *
   * @return String
   */
  public String getText() {
      return text;
  }

  /**
   *<p>Method to return the clientmsgid attribute or null if not set.</p>
   * 
   * @return String
   */
  public String getClientMsgId() {
      return clientmsgid;
  }

  /**
   * <p>Method to return the msgid attribute. This is set after the message has been sent.</p>
   * 
   * @return long
   */
  public Long getMsgId() {
      return msgid;
  }

  /**
   * <p>Method to return the errormessage atribute after a message has been attempted to be sent. If no error this will be set to null.</p>
   * 
   * @return String
   */
  public String getErrorMessage() {
      return errormessage;
  }

  /**
   * <p>Method to check if an error occured in sending a message. No error false, error true.</p>
   * 
   * @return boolean
   */
  public Boolean isError() {
      return errormessage!=null;
  }

  /**
   * <p>Method to set a MoodleMessage attribute given the field name and value as strings.</p>
   * 
   * @param nodeName String
   * @param value String
   */
  public void setMoodleMesageField(String nodeName, String value) {
      if (nodeName.equals("msgid")) setMsgId(Long.valueOf(value));
      if (nodeName.equals("clientmsgid")) setClientMsgId(value);
      if (nodeName.equals("errormessage")) setErrorMessage(value);
  }
}
