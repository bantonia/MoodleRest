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
public class ModCourseForum implements Serializable {
  
  private Long id=null;
  private String course=null;
  private String type=null;
  private String name=null;
  private String intro=null;
  private DescriptionFormat introFormat=null;
  private Boolean assessed=null;
  private Long assessTimeStart=null;
  private Long assessTimeFinish=null;
  private Integer scale=null;
  private Long maxBytes=null;
  private Integer maxAttachments=null;
  private Boolean forceSubscribe=null;
  private Integer trackingType=null;
  private Integer rssType=null;
  private Integer rssArticles=null;
  private Long timeModified=null;
  private Long warnAfter=null;
  private Long blockAfter=null;
  private Long blockPeriod=null;
  private Integer completionDiscussions=null;
  private Integer completionPosts=null;
  private Long cmId=null;
  
  private static final int ID=0;
  private static final int COURSE=1;
  private static final int TYPE=2;
  private static final int NAME=3;
  private static final int INTRO=4;
  private static final int INTROFORMAT=5;
  private static final int ASSESSED=6;
  private static final int ASSESSTIMESTART=7;
  private static final int ASSESSTIMEFINISH=8;
  private static final int SCALE=9;
  private static final int MAXBYTES=10;
  private static final int MAXATTACHMENTS=11;
  private static final int FORCESUBSCRIBE=12;
  private static final int TRACKINGTYPE=13;
  private static final int RSSTYPE=14;
  private static final int RSSARTICLES=15;
  private static final int TIMEMODIFIED=16;
  private static final int WARNAFTER=17;
  private static final int BLOCKAFTER=18;
  private static final int BLOCKPERIOD=19;
  private static final int COMPLETIONDISCUSSIONS=20;
  private static final int COMPLETIONPOSTS=21;
  private static final int CMID=22;
  
  private final static String VARIABLES="id.course.type.name.intro.introFormat.assessed.assessTimeStart.assessTimeFinish.scale.maxBytes.maxAttachments.forceSubscribe.trackingType.rssType.rssArticles.timeModified.warnAfter.blockAfter.blockPeriod.completionDiscussions.completionPosts.cmid";

  public ModCourseForum() {
  }

  public ModCourseForum(Long id) {
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
          case ASSESSED:  
          case ASSESSTIMESTART:
          case ASSESSTIMEFINISH:
          case MAXBYTES:
          case FORCESUBSCRIBE:
          case TIMEMODIFIED:
          case WARNAFTER:
          case BLOCKAFTER:
          case BLOCKPERIOD:
          case CMID:
            if (!value.isEmpty()) {
              Long converted=Long.parseLong(value);
              switch (varIndex) {
                case ID: setId(converted); break;
                case ASSESSED: setAssessed((converted.equals(0L)?false:true)); break;
                case ASSESSTIMESTART: setAssessTimeStart(converted); break;
                case ASSESSTIMEFINISH: setAssessTimeFinish(converted); break;
                case MAXBYTES: setMaxBytes(converted); break;
                case FORCESUBSCRIBE: setForceSubscribe((converted.equals(0L)?false:true)); break;
                case TIMEMODIFIED: setTimeModified(converted); break;
                case WARNAFTER: setWarnAfter(converted); break;
                case BLOCKAFTER: setBlockAfter(converted); break;
                case BLOCKPERIOD: setBlockPeriod(converted); break;
                case CMID: setCmId(converted); break;
              }
            }
            break;
          case COURSE: setCourse(value); break;
          case TYPE: setType(value); break;
          case NAME: setName(value); break;
          case INTRO: setIntro(value); break;
          case INTROFORMAT:
          case SCALE:
          case MAXATTACHMENTS:
          case TRACKINGTYPE:
          case RSSTYPE:
          case RSSARTICLES:
          case COMPLETIONDISCUSSIONS:
          case COMPLETIONPOSTS:
            if (!value.isEmpty()) {
              Integer converted=Integer.parseInt(value);
              switch (varIndex) {
                case INTROFORMAT:
                  switch (converted) {
                    case 0: setIntroFormat(DescriptionFormat.MOODLE); break;
                    case 1: setIntroFormat(DescriptionFormat.HTML); break;
                    case 2: setIntroFormat(DescriptionFormat.PLAIN); break;
                    case 3: setIntroFormat(DescriptionFormat.MARKDOWN); break;
                  }
                  break;
                case SCALE: setScale(converted); break;
                case MAXATTACHMENTS: setMaxAttachments(converted); break;
                case TRACKINGTYPE: setTrackingType(converted); break;
                case RSSTYPE: setRssType(converted); break;
                case RSSARTICLES: setRssArticles(converted); break;
                case COMPLETIONDISCUSSIONS: setCompletionDiscussions(converted); break;
                case COMPLETIONPOSTS: setCompletionPosts(converted); break;
              }
            }
          break;
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

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public DescriptionFormat getIntroFormat() {
    return introFormat;
  }

  public void setIntroFormat(DescriptionFormat introFormat) {
    this.introFormat = introFormat;
  }

  public Boolean getAssessed() {
    return assessed;
  }

  public void setAssessed(Boolean assessed) {
    this.assessed = assessed;
  }

  public Long getAssessTimeStart() {
    return assessTimeStart;
  }

  public void setAssessTimeStart(Long assessTimeStart) {
    this.assessTimeStart = assessTimeStart;
  }

  public Long getAssessTimeFinish() {
    return assessTimeFinish;
  }

  public void setAssessTimeFinish(Long assessTimeFinish) {
    this.assessTimeFinish = assessTimeFinish;
  }

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public Long getMaxBytes() {
    return maxBytes;
  }

  public void setMaxBytes(Long maxBytes) {
    this.maxBytes = maxBytes;
  }

  public Integer getMaxAttachments() {
    return maxAttachments;
  }

  public void setMaxAttachments(Integer maxAttachments) {
    this.maxAttachments = maxAttachments;
  }

  public Boolean getForceSubscribe() {
    return forceSubscribe;
  }

  public void setForceSubscribe(Boolean forceSubscribe) {
    this.forceSubscribe = forceSubscribe;
  }

  public Integer getTrackingType() {
    return trackingType;
  }

  public void setTrackingType(Integer trackingType) {
    this.trackingType = trackingType;
  }

  public Integer getRssType() {
    return rssType;
  }

  public void setRssType(Integer rssType) {
    this.rssType = rssType;
  }

  public Integer getRssArticles() {
    return rssArticles;
  }

  public void setRssArticles(Integer rssArticles) {
    this.rssArticles = rssArticles;
  }

  public Long getTimeModified() {
    return timeModified;
  }

  public void setTimeModified(Long timeModified) {
    this.timeModified = timeModified;
  }

  public Long getWarnAfter() {
    return warnAfter;
  }

  public void setWarnAfter(Long warnAfter) {
    this.warnAfter = warnAfter;
  }

  public Long getBlockAfter() {
    return blockAfter;
  }

  public void setBlockAfter(Long blockAfter) {
    this.blockAfter = blockAfter;
  }

  public Long getBlockPeriod() {
    return blockPeriod;
  }

  public void setBlockPeriod(Long blockPeriod) {
    this.blockPeriod = blockPeriod;
  }

  public Integer getCompletionDiscussions() {
    return completionDiscussions;
  }

  public void setCompletionDiscussions(Integer completionDiscussions) {
    this.completionDiscussions = completionDiscussions;
  }

  public Integer getCompletionPosts() {
    return completionPosts;
  }

  public void setCompletionPosts(Integer completionPosts) {
    this.completionPosts = completionPosts;
  }

  public Long getCmId() {
    return cmId;
  }

  public void setCmId(Long cmId) {
    this.cmId = cmId;
  }
  
}
