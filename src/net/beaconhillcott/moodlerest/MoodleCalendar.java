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
 * @author root
 */
public class MoodleCalendar implements Serializable {
  
  private Integer id=null;
  private String modulename=null;
  private Long userid=null;
  private Integer repeatid=null;
  private Integer instance=null;
  private String uuid=null;
  private Long timemodified=null;
  private Integer subscriptionid=null;
  private String name=null; //event name
  private String description=null; //Description
  private Integer format=null; // int  Default to "1" description format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
  private Integer courseid=null;
  private Integer groupid=null;
  private Integer repeats=null; // Default to "0" number of repeats
  private String eventtype=null; // Default to "user" Event type
  private Long timestart=null; // Default to "1392892303" timestart
  private Integer timeduration=null; // Default to "0" time duration
  private Integer visible=null; // Default to "1" visible
  private Integer sequence=null; // Default to "1" sequence
  
  public static final Integer DESCRIPTION_FORMAT_MOODLE=0;
  public static final Integer DESCRIPTION_FORMAT_HTML=1;
  public static final Integer DESCRIPTION_FORMAT_PLAIN=2;
  public static final Integer DESCRIPTION_FORMAT_MARKDOWN=4;
  
  public static final int GET_OPTION_USEREVENTS_DEFAULT=1;
  public static final int GET_OPTION_SITEEVENTS_DEFAULT=1;
  public static final long GET_OPTION_TIMESTART_DEFAULT=0;
  public static final long GET_OPTION_TIMEEND_DEFAULT=1392892303;
  public static final int GET_OPTION_IGNOREHIDDEN_DEFAULT=1;
  

  public MoodleCalendar() {
  }
  
  public MoodleCalendar(String name) {
    this.name = name;
  }
  
  public void setMoodleCalendarField(String nodeName, String content) {
    if (nodeName.equals("id")) setId(Integer.parseInt(content.trim()));
    if (nodeName.equals("modulename")) setModuleName(content);
    if (nodeName.equals("userid") && !content.isEmpty()) setUserId(Long.parseLong(content.trim()));
    if (nodeName.equals("repeatid") && !content.isEmpty()) setRepeatId(Integer.parseInt(content.trim()));
    if (nodeName.equals("instance") && !content.isEmpty()) setInstance(Integer.parseInt(content.trim()));
    if (nodeName.equals("uuid")) setUUId(content);
    if (nodeName.equals("timemodified") && !content.isEmpty()) setTimeModified(Long.parseLong(content.trim()));
    if (nodeName.equals("subscriptionid") && !content.isEmpty()) setSubscriptionId(Integer.parseInt(content.trim()));
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("description")) setDescription(content);
    if (nodeName.equals("format") && !content.isEmpty()) setFormat(Integer.parseInt(content.trim()));
    if (nodeName.equals("courseid") && !content.isEmpty()) setCourseId(Integer.parseInt(content.trim()));
    if (nodeName.equals("groupid") && !content.isEmpty()) setGroupId(Integer.parseInt(content));
    if (nodeName.equals("repeats") && !content.isEmpty()) setRepeats(Integer.parseInt(content));
    if (nodeName.equals("eventtype")) setEventtype(content);
    if (nodeName.equals("timestart") && !content.isEmpty()) setTimestart(Long.parseLong(content.trim()));
    if (nodeName.equals("timeduration") && !content.isEmpty()) setTimeduration(Integer.parseInt(content));
    if (nodeName.equals("visible") && !content.isEmpty()) setVisible(Integer.parseInt(content.trim()));
    if (nodeName.equals("sequence") && !content.isEmpty()) setSequence(Integer.parseInt(content));
  }

  public Integer getCourseId() {
    return courseid;
  }

  public void setCourseId(Integer courseid) {
    this.courseid = courseid;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEventtype() {
    return eventtype;
  }

  public void setEventtype(String eventtype) {
    this.eventtype = eventtype;
  }

  public Integer getFormat() {
    return format;
  }

  public void setFormat(Integer format) {
    this.format = format;
  }

  public Integer getGroupId() {
    return groupid;
  }

  public void setGroupId(Integer groupid) {
    this.groupid = groupid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRepeats() {
    return repeats;
  }

  public void setRepeats(Integer repeats) {
    this.repeats = repeats;
  }

  public Integer getSequence() {
    return sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public Integer getTimeduration() {
    return timeduration;
  }

  public void setTimeduration(Integer timeduration) {
    this.timeduration = timeduration;
  }

  public Long getTimestart() {
    return timestart;
  }

  public void setTimestart(Long timestart) {
    this.timestart = timestart;
  }

  public Integer getVisible() {
    return visible;
  }

  public void setVisible(Integer visible) {
    this.visible = visible;
  }

  public String getModuleName() {
    return modulename;
  }

  public void setModuleName(String modulename) {
    this.modulename = modulename;
  }

  public Integer getCourseid() {
    return courseid;
  }

  public void setCourseid(Integer courseid) {
    this.courseid = courseid;
  }

  public Integer getGroupid() {
    return groupid;
  }

  public void setGroupid(Integer groupid) {
    this.groupid = groupid;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getInstance() {
    return instance;
  }

  public void setInstance(Integer instance) {
    this.instance = instance;
  }

  public Integer getRepeatId() {
    return repeatid;
  }

  public void setRepeatId(Integer repeatid) {
    this.repeatid = repeatid;
  }

  public Integer getSubscriptionId() {
    return subscriptionid;
  }

  public void setSubscriptionId(Integer subscriptionid) {
    this.subscriptionid = subscriptionid;
  }

  public Long getTimeModified() {
    return timemodified;
  }

  public void setTimeModified(Long timemodified) {
    this.timemodified = timemodified;
  }

  public Long getUserId() {
    return userid;
  }

  public void setUserId(Long userid) {
    this.userid = userid;
  }

  public String getUUId() {
    return uuid;
  }

  public void setUUId(String uuid) {
    this.uuid = uuid;
  }
  
}
