/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author Bill Antonia
 */
public class MoodleComment implements Serializable {
  
  private Long id;
  private String content;
  private Integer format;
  private Long timecreated;
  private String strftimeformat;
  private String profileurl;
  private String fullname;
  private String time;
  private String avatar;
  private Long userid;
  private Boolean delete;

  public MoodleComment() {
  }

  public MoodleComment(Long id, String content, Integer format, Long timecreated, String strftimeformat, String profileurl, String fullname, String time, String avatar, Long userid, Boolean delete) {
    this.id = id;
    this.content = content;
    this.format = format;
    this.timecreated = timecreated;
    this.strftimeformat = strftimeformat;
    this.profileurl = profileurl;
    this.fullname = fullname;
    this.time = time;
    this.avatar = avatar;
    this.userid = userid;
    this.delete = delete;
  }
  
  public void setField(String field, String value) {
    if (field!=null && !field.isEmpty()) {
      if (field.equals("id")) setId(Long.parseLong(value));
      if (field.equals("content")) setContent(value);
      if (field.equals("format")) setFormat(Integer.parseInt(value));
      if (field.equals("timecreated")) setTimecreated(Long.parseLong(value));
      if (field.equals("strftimeformat")) setStrftimeformat(value);
      if (field.equals("profileurl")) setProfileurl(value);
      if (field.equals("fullname")) setFullname(value);
      if (field.equals("time")) setTime(value);
      if (field.equals("avatar")) setAvatar(value);
      if (field.equals("userid")) setUserid(Long.parseLong(value));
      if (field.equals("delete")) setDelete((Integer.parseInt(value)!=0));
    }
  }

  public String getFieldAsString(String field) {
    if (field!=null && !field.isEmpty()) {
      if (field.equals("id")) return Long.toString(getId());
      if (field.equals("content")) return getContent();
      if (field.equals("format")) return Long.toString(getFormat());
      if (field.equals("timecreated")) return Long.toString(getTimecreated());
      if (field.equals("strftimeformat")) return getStrftimeformat();
      if (field.equals("profileurl")) return getProfileurl();
      if (field.equals("fullname")) return getFullname();
      if (field.equals("time")) return getTime();
      if (field.equals("avatar")) return getAvatar();
      if (field.equals("userid")) return Long.toString(getUserid());
      if (field.equals("delete")) return Boolean.toString(getDelete());
    }
    return null;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getFormat() {
    return format;
  }

  public void setFormat(Integer format) {
    this.format = format;
  }

  public Long getTimecreated() {
    return timecreated;
  }

  public void setTimecreated(Long timecreated) {
    this.timecreated = timecreated;
  }

  public String getStrftimeformat() {
    return strftimeformat;
  }

  public void setStrftimeformat(String strftimeformat) {
    this.strftimeformat = strftimeformat;
  }

  public String getProfileurl() {
    return profileurl;
  }

  public void setProfileurl(String profileurl) {
    this.profileurl = profileurl;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public Boolean getDelete() {
    return delete;
  }

  public void setDelete(Boolean delete) {
    this.delete = delete;
  }
  
}
