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
public class ModForumDiscussion implements Serializable {
  
  private Long id=null;
  private Long course=null;
  private Long forum=null;
  private String name=null;
  private Long userId=null;
  private Long groupId=null;
  private Boolean assessed=null;
  private Long timeModified=null;
  private Long userModified=null;
  private Long timeStart=null;
  private Long timeEnd=null;
  private Long firstPost=null;
  private String firstUserFullname=null;
  private String firstUserImageAlt=null;
  private Long firstUserPicture=null;
  private String firstUserEmail=null;
  private String subject=null;
  private String numReplies=null;
  private String numUnread=null;
  private Long lastPost=null;
  private Long lastUserId=null;
  private String lastUserFullname=null;
  private String lastUserImageAlt=null;
  private Long lastUserPicture=null;
  private String lastUserEmail=null;
  
  private final static int ID=0;
  private final static int COURSE=1;
  private final static int FORUM=2;
  private final static int NAME=3;
  private final static int USERID=4;
  private final static int GROUPID=5;
  private final static int ASSESSED=6;
  private final static int TIMEMODIFIED=7;
  private final static int USERMODIFIED=8;
  private final static int TIMESTART=9;
  private final static int TIMEEND=10;
  private final static int FIRSTPOST=11;
  private final static int FIRSTUSERFULLNAME=12;
  private final static int FIRSTUSERIMAGEALT=13;
  private final static int FIRSTUSERPICTURE=14;
  private final static int FIRSTUSEREMAIL=15;
  private final static int SUBJECT=16;
  private final static int NUMREPLIES=17;
  private final static int NUMUNREAD=18;
  private final static int LASTPOST=19;
  private final static int LASTUSERID=20;
  private final static int LASTUSERFULLNAME=21;
  private final static int LASTUSERIMAGEALT=22;
  private final static int LASTUSERPICTURE=23;
  private final static int LASTUSEREMAIL=24;
  private final static String VARIABLES="id.course.forum.name.userid.groupid.assessed.timemodified.usermodified.timestart.timeend.firstpost.firstuserfullname.firstuserimagealt.firstuserpicture.firstuseremail.subject.numreplies.numunread.lastpost.lastuserid.lastuserfullname.lastuserimagealt.lastuserpicture.lastuseremail";

  public ModForumDiscussion() {
  }

  public ModForumDiscussion(Long id) {
    this.id=id;
  }

  public void setFieldValue(String name, String value) {
    if (("."+VARIABLES+".").indexOf("."+name+".")!=-1) {
      if (value!=null) {
        int varIndex;
        String[] a=VARIABLES.split("\\.");
        for (varIndex=0; varIndex<a.length; varIndex++) {
          if (name.equals(a[varIndex])) {
            break;
          }
        }
        switch (varIndex) {
          case ID:
          case COURSE:
          case FORUM:
          case USERID:
          case GROUPID:
          case ASSESSED:
          case TIMEMODIFIED:
          case USERMODIFIED:
          case TIMESTART:
          case TIMEEND:
          case FIRSTPOST:
          case FIRSTUSERPICTURE:
          case LASTPOST:
          case LASTUSERID:
          case LASTUSERPICTURE:
            if (!value.isEmpty()) {
              Long converted=Long.parseLong(value);
              switch (varIndex) {
                case ID:  setId(converted); break;
                case COURSE:  setCourse(converted); break;
                case FORUM:  setForum(converted); break;
                case USERID:  setUserId(converted); break;
                case GROUPID:  setGroupId(converted); break;
                case ASSESSED:  setAssessed((converted.equals(0L)?false:true)); break;
                case TIMEMODIFIED:  setTimeModified(converted); break;
                case USERMODIFIED:  setUserModified(converted); break;
                case TIMESTART:  setTimeStart(converted); break;
                case TIMEEND: setTimeEnd(converted); break;
                case FIRSTPOST: setFirstPost(converted); break;
                case FIRSTUSERPICTURE: setFirstUserPicture(converted); break;
                case LASTPOST: setLastPost(converted); break;
                case LASTUSERID: setLastUserId(converted); break;
                case LASTUSERPICTURE: setLastUserPicture(converted); break;
              }
            }
            break;
          case NAME:  setName(value); break;
          case FIRSTUSERFULLNAME: setFirstUserFullname(value); break;
          case FIRSTUSERIMAGEALT: setFirstUserImageAlt(value); break;
          case FIRSTUSEREMAIL: setFirstUserEmail(value); break;
          case SUBJECT: setSubject(value); break;
          case NUMREPLIES: setNumReplies(value); break;
          case NUMUNREAD: setNumUnread(value); break;
          case LASTUSERFULLNAME: setLastUserFullname(value); break;
          case LASTUSERIMAGEALT: setLastUserImageAlt(value); break;
          case LASTUSEREMAIL: setLastUserEmail(value); break;
        }
      }
    }
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCourse() {
    return course;
  }

  public void setCourse(Long course) {
    this.course = course;
  }

  public Long getForum() {
    return forum;
  }

  public void setForum(Long forum) {
    this.forum = forum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public Boolean getAssessed() {
    return assessed;
  }

  public void setAssessed(Boolean assessed) {
    this.assessed = assessed;
  }

  public Long getTimeModified() {
    return timeModified;
  }

  public void setTimeModified(Long timeModified) {
    this.timeModified = timeModified;
  }

  public Long getUserModified() {
    return userModified;
  }

  public void setUserModified(Long userModified) {
    this.userModified = userModified;
  }

  public Long getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(Long timeStart) {
    this.timeStart = timeStart;
  }

  public Long getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(Long timeEnd) {
    this.timeEnd = timeEnd;
  }

  public Long getFirstPost() {
    return firstPost;
  }

  public void setFirstPost(Long firstPost) {
    this.firstPost = firstPost;
  }

  public String getFirstUserFullname() {
    return firstUserFullname;
  }

  public void setFirstUserFullname(String firstUserFullname) {
    this.firstUserFullname = firstUserFullname;
  }

  public String getFirstUserImageAlt() {
    return firstUserImageAlt;
  }

  public void setFirstUserImageAlt(String firstUserImageAlt) {
    this.firstUserImageAlt = firstUserImageAlt;
  }

  public Long getFirstUserPicture() {
    return firstUserPicture;
  }

  public void setFirstUserPicture(Long firstUserPicture) {
    this.firstUserPicture = firstUserPicture;
  }

  public String getFirstUserEmail() {
    return firstUserEmail;
  }

  public void setFirstUserEmail(String firstUserEmail) {
    this.firstUserEmail = firstUserEmail;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getNumReplies() {
    return numReplies;
  }

  public void setNumReplies(String numReplies) {
    this.numReplies = numReplies;
  }

  public String getNumUnread() {
    return numUnread;
  }

  public void setNumUnread(String numUnread) {
    this.numUnread = numUnread;
  }

  public Long getLastPost() {
    return lastPost;
  }

  public void setLastPost(Long lastPost) {
    this.lastPost = lastPost;
  }

  public Long getLastUserId() {
    return lastUserId;
  }

  public void setLastUserId(Long lastUserId) {
    this.lastUserId = lastUserId;
  }

  public String getLastUserFullname() {
    return lastUserFullname;
  }

  public void setLastUserFullname(String lastUserFullname) {
    this.lastUserFullname = lastUserFullname;
  }

  public String getLastUserImageAlt() {
    return lastUserImageAlt;
  }

  public void setLastUserImageAlt(String lastUserImageAlt) {
    this.lastUserImageAlt = lastUserImageAlt;
  }

  public Long getLastUserPicture() {
    return lastUserPicture;
  }

  public void setLastUserPicture(Long lastUserPicture) {
    this.lastUserPicture = lastUserPicture;
  }

  public String getLastUserEmail() {
    return lastUserEmail;
  }

  public void setLastUserEmail(String lastUserEmail) {
    this.lastUserEmail = lastUserEmail;
  }
  
}
