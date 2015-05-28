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
public class MoodleRetrievedMessage implements Serializable {
  private Long id;
  private Long userIdFrom;
  private Long userIdTo;
  private String subject;
  private String text;
  private String fullMessage;
  private DescriptionFormat fullMessageFormat;
  private String fullMessageHTML;
  private String smallMessage;
  private Boolean notification;
  private String contextURL;
  private String contextURLName;
  private Long timeCreated;
  private Long timeRead;
  private String userToFullname;
  private String userFromFullname;

  public MoodleRetrievedMessage() {
    this.userFromFullname = null;
    this.userToFullname = null;
    this.timeRead = null;
    this.timeCreated = null;
    this.contextURLName = null;
    this.contextURL = null;
    this.notification = null;
    this.smallMessage = null;
    this.fullMessageHTML = null;
    this.fullMessageFormat = null;
    this.fullMessage = null;
    this.text = null;
    this.subject = null;
    this.userIdTo = null;
    this.userIdFrom = null;
    this.id = null;
  }

  public MoodleRetrievedMessage(Long id) {
    this.userFromFullname = null;
    this.userToFullname = null;
    this.timeRead = null;
    this.timeCreated = null;
    this.contextURLName = null;
    this.contextURL = null;
    this.notification = null;
    this.smallMessage = null;
    this.fullMessageHTML = null;
    this.fullMessageFormat = null;
    this.fullMessage = null;
    this.text = null;
    this.subject = null;
    this.userIdTo = null;
    this.userIdFrom = null;
    this.id=id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserIdFrom() {
    return userIdFrom;
  }

  public void setUserIdFrom(Long userIdFrom) {
    this.userIdFrom = userIdFrom;
  }

  public Long getUserIdTo() {
    return userIdTo;
  }

  public void setUserIdTo(Long userIdTo) {
    this.userIdTo = userIdTo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFullMessage() {
    return fullMessage;
  }

  public void setFullMessage(String fullMessage) {
    this.fullMessage = fullMessage;
  }

  public DescriptionFormat getFullMessageFormat() {
    return fullMessageFormat;
  }

  public void setFullMessageFormat(DescriptionFormat fullMessageFormat) {
    this.fullMessageFormat = fullMessageFormat;
  }

  public String getFullMessageHTML() {
    return fullMessageHTML;
  }

  public void setFullMessageHTML(String fullMessageHTML) {
    this.fullMessageHTML = fullMessageHTML;
  }

  public String getSmallMessage() {
    return smallMessage;
  }

  public void setSmallMessage(String smallMessage) {
    this.smallMessage = smallMessage;
  }

  public Boolean isNotification() {
    return notification;
  }

  public void setNotification(Boolean notification) {
    this.notification = notification;
  }

  public String getContextURL() {
    return contextURL;
  }

  public void setContextURL(String contextURL) {
    this.contextURL = contextURL;
  }

  public String getContextURLName() {
    return contextURLName;
  }

  public void setContextURLName(String contextURLName) {
    this.contextURLName = contextURLName;
  }

  public Long getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(Long timeCreated) {
    this.timeCreated = timeCreated;
  }

  public Long getTimeRead() {
    return timeRead;
  }

  public void setTimeRead(Long timeRead) {
    this.timeRead = timeRead;
  }

  public String getUserToFullname() {
    return userToFullname;
  }

  public void setUserToFullname(String userToFullname) {
    this.userToFullname = userToFullname;
  }

  public String getUserFromFullname() {
    return userFromFullname;
  }

  public void setUserFromFullname(String userFromFullname) {
    this.userFromFullname = userFromFullname;
  }
  
  public void setField(String name, String value) {
    if (name.equals("id")) {id=Long.parseLong(value);}
    if (name.equals("useridfrom")) {userIdFrom=Long.parseLong(value);}
    if (name.equals("useridto")) {userIdTo=Long.parseLong(value);}
    if (name.equals("subject")) {subject=value;}
    if (name.equals("text")) {text=value;}
    if (name.equals("fullmessage")) {fullMessage=value;}
    if (name.equals("fullmessageformat")) {
      for (DescriptionFormat key : DescriptionFormat.values()) {
        if ((""+key.toInt()).equals(value)) {
          setFullMessageFormat(key);
          break;
        }
      }
    }
    if (name.equals("fullmessagehtml")) {fullMessageHTML=value;}
    if (name.equals("smallmessage")) {smallMessage=value;}
    if (name.equals("notification")) {notification=(!value.equals("0"));}
    if (name.equals("contexturl")) {contextURL=value;}
    if (name.equals("contexturlname")) {contextURLName=value;}
    if (name.equals("timecreated")) {timeCreated=Long.parseLong(value);}
    if (name.equals("timeread")) {timeRead=Long.parseLong(value);}
    if (name.equals("usertofullname")) {userToFullname=value;}
    if (name.equals("userfromfullname")) {userFromFullname=value;}
  }
}
