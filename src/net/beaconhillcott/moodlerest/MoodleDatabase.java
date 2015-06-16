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
public class MoodleDatabase implements Serializable {
  private Long id; //int   //Database id
  private Long courseModule; //int   //Course module id
  private String course; //string   //Course id
  private String name; //string   //Database name
  private String intro; //string   //The Database intro
  DescriptionFormat introFormat; //int   //intro format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
  private Long comments; //int  Optional //comments enabled
  private Long timeAvailableFrom; //int  Optional //timeavailablefrom field
  private Long timeAvailableTo; //int  Optional //timeavailableto field
  private Long timeViewFrom; //int  Optional //timeviewfrom field
  private Long timeViewTo; //int  Optional //timeviewto field
  private Long requiredEntries; //int  Optional //requiredentries field
  private Long requiredEntriesToView; //int  Optional //requiredentriestoview field
  private Long maxEntries; //int  Optional //maxentries field
  private Long rssArticles; //int  Optional //rssarticles field
  private String singleTemplate; //string  Optional //singletemplate field
  private String listTemplate; //string  Optional //listtemplate field
  private String listTemplateHeader; //string  Optional //listtemplateheader field
  private String listTemplateFooter; //string  Optional //listtemplatefooter field
  private String addTemplate; //string  Optional //addtemplate field
  private String rssTemplate; //string  Optional //rsstemplate field
  private String rssTitleTemplate; //string  Optional //rsstitletemplate field
  private String cssTemplate; //string  Optional //csstemplate field
  private String jsTemplate; //string  Optional //jstemplate field
  private String aSearchTemplate; //string  Optional //asearchtemplate field
  private Integer approval; //int  Optional //approval field
  private Long scale; //int  Optional //scale field
  private Integer assessed; //int  Optional //assessed field
  private Long assessTimeStart; //int  Optional //assesstimestart field
  private Long assessTimeFinish; //int  Optional //assesstimefinish field
  private Integer defaultSort; //int  Optional //defaultsort field
  private Integer defaultSortDir; //int  Optional //defaultsortdir field
  private Integer editAny; //int  Optional //editany field
  private Integer notification; //int  Optional //notification field

  public MoodleDatabase() {
  }

  public MoodleDatabase(Long id) {
    this.id = id;
  }

  public void setField(String name, String value) {
    if (name.equals("id")) {id=Long.parseLong(value);} // int   //Database id
    if (name.equals("coursemodule")) {courseModule=Long.parseLong(value);} //int   //Course module id
    if (name.equals("course")) {course=value;} //string   //Course id
    if (name.equals("name")) {name=value;} //string   //Database name
    if (name.equals("intro")) {intro=value;} //string   //The Database intro
    if (name.equals("introformat")) { //int   //intro format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
      for (DescriptionFormat key : DescriptionFormat.values()) {
        if ((""+key.toInt()).equals(value)) {
          setIntroFormat(key);
          break;
        }
      }
    }
    if (name.equals("comments")) {comments=Long.parseLong(value);} // int  Optional //comments enabled
    if (name.equals("timeavailablefrom")) {timeAvailableFrom=Long.parseLong(value);} // int  Optional //timeavailablefrom field
    if (name.equals("timeavailableto")) {timeAvailableTo=Long.parseLong(value);} // int  Optional //timeavailableto field
    if (name.equals("timeviewfrom")) {timeViewFrom=Long.parseLong(value);} // int  Optional //timeviewfrom field
    if (name.equals("timeviewto")) {timeViewTo=Long.parseLong(value);} // int  Optional //timeviewto field
    if (name.equals("requiredentries")) {requiredEntries=Long.parseLong(value);} // int  Optional //requiredentries field
    if (name.equals("requiredentriestoview")) {requiredEntriesToView=Long.parseLong(value);} // int  Optional //requiredentriestoview field
    if (name.equals("maxentries")) {maxEntries=Long.parseLong(value);} // int  Optional //maxentries field
    if (name.equals("rssarticles")) {rssArticles=Long.parseLong(value);} // int  Optional //rssarticles field
    if (name.equals("singletemplate")) {singleTemplate=value;} // string  Optional //singletemplate field
    if (name.equals("listtemplate")) {listTemplate=value;} // string  Optional //listtemplate field
    if (name.equals("listtemplateheader")) {listTemplateHeader=value;} // string  Optional //listtemplateheader field
    if (name.equals("listtemplatefooter")) {listTemplateFooter=value;} // string  Optional //listtemplatefooter field
    if (name.equals("addtemplate")) {addTemplate=value;} // string  Optional //addtemplate field
    if (name.equals("rsstemplate")) {rssTemplate=value;} // string  Optional //rsstemplate field
    if (name.equals("rsstitletemplate")) {rssTitleTemplate=value;} // string  Optional //rsstitletemplate field
    if (name.equals("csstemplate")) {cssTemplate=value;} // string  Optional //csstemplate field
    if (name.equals("jstemplate")) {jsTemplate=value;} // string  Optional //jstemplate field
    if (name.equals("asearchtemplate")) {aSearchTemplate=value;} // string  Optional //asearchtemplate field
    if (name.equals("approval")) {approval=Integer.parseInt(value);} // int  Optional //approval field
    if (name.equals("scale")) {scale=Long.parseLong(value);} // int  Optional //scale field
    if (name.equals("assessed")) {assessed=Integer.parseInt(value);} // int  Optional //assessed field
    if (name.equals("assesstimestart")) {assessTimeStart=Long.parseLong(value);} // int  Optional //assesstimestart field
    if (name.equals("assesstimefinish")) {assessTimeFinish=Long.parseLong(value);} // int  Optional //assesstimefinish field
    if (name.equals("defaultsort")) {defaultSort=Integer.parseInt(value);} // int  Optional //defaultsort field
    if (name.equals("defaultsortdir")) {defaultSortDir=Integer.parseInt(value);} // int  Optional //defaultsortdir field
    if (name.equals("editany")) {editAny=Integer.parseInt(value);} // int  Optional //editany field
    if (name.equals("notification")) {notification=Integer.parseInt(value);} // int  Optional //notification field
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCourseModule() {
    return courseModule;
  }

  public void setCourseModule(Long courseModule) {
    this.courseModule = courseModule;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
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

  public Long getComments() {
    return comments;
  }

  public void setComments(Long comments) {
    this.comments = comments;
  }

  public Long getTimeAvailableFrom() {
    return timeAvailableFrom;
  }

  public void setTimeAvailableFrom(Long timeAvailableFrom) {
    this.timeAvailableFrom = timeAvailableFrom;
  }

  public Long getTimeAvailableTo() {
    return timeAvailableTo;
  }

  public void setTimeAvailableTo(Long timeAvailableTo) {
    this.timeAvailableTo = timeAvailableTo;
  }

  public Long getTimeViewFrom() {
    return timeViewFrom;
  }

  public void setTimeViewFrom(Long timeViewFrom) {
    this.timeViewFrom = timeViewFrom;
  }

  public Long getTimeViewTo() {
    return timeViewTo;
  }

  public void setTimeViewTo(Long timeViewTo) {
    this.timeViewTo = timeViewTo;
  }

  public Long getRequiredEntries() {
    return requiredEntries;
  }

  public void setRequiredEntries(Long requiredEntries) {
    this.requiredEntries = requiredEntries;
  }

  public Long getRequiredEntriesToView() {
    return requiredEntriesToView;
  }

  public void setRequiredEntriesToView(Long requiredEntriesToView) {
    this.requiredEntriesToView = requiredEntriesToView;
  }

  public Long getMaxEntries() {
    return maxEntries;
  }

  public void setMaxEntries(Long maxEntries) {
    this.maxEntries = maxEntries;
  }

  public Long getRssArticles() {
    return rssArticles;
  }

  public void setRssArticles(Long rssArticles) {
    this.rssArticles = rssArticles;
  }

  public String getSingleTemplate() {
    return singleTemplate;
  }

  public void setSingleTemplate(String singleTemplate) {
    this.singleTemplate = singleTemplate;
  }

  public String getListTemplate() {
    return listTemplate;
  }

  public void setListTemplate(String listTemplate) {
    this.listTemplate = listTemplate;
  }

  public String getListTemplateHeader() {
    return listTemplateHeader;
  }

  public void setListTemplateHeader(String listTemplateHeader) {
    this.listTemplateHeader = listTemplateHeader;
  }

  public String getListTemplateFooter() {
    return listTemplateFooter;
  }

  public void setListTemplateFooter(String listTemplateFooter) {
    this.listTemplateFooter = listTemplateFooter;
  }

  public String getAddTemplate() {
    return addTemplate;
  }

  public void setAddTemplate(String addTemplate) {
    this.addTemplate = addTemplate;
  }

  public String getRssTemplate() {
    return rssTemplate;
  }

  public void setRssTemplate(String rssTemplate) {
    this.rssTemplate = rssTemplate;
  }

  public String getRssTitleTemplate() {
    return rssTitleTemplate;
  }

  public void setRssTitleTemplate(String rssTitleTemplate) {
    this.rssTitleTemplate = rssTitleTemplate;
  }

  public String getCssTemplate() {
    return cssTemplate;
  }

  public void setCssTemplate(String cssTemplate) {
    this.cssTemplate = cssTemplate;
  }

  public String getJsTemplate() {
    return jsTemplate;
  }

  public void setJsTemplate(String jsTemplate) {
    this.jsTemplate = jsTemplate;
  }

  public String getaSearchTemplate() {
    return aSearchTemplate;
  }

  public void setaSearchTemplate(String aSearchTemplate) {
    this.aSearchTemplate = aSearchTemplate;
  }

  public Integer getApproval() {
    return approval;
  }

  public void setApproval(Integer approval) {
    this.approval = approval;
  }

  public Long getScale() {
    return scale;
  }

  public void setScale(Long scale) {
    this.scale = scale;
  }

  public Integer getAssessed() {
    return assessed;
  }

  public void setAssessed(Integer assessed) {
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

  public Integer getDefaultSort() {
    return defaultSort;
  }

  public void setDefaultSort(Integer defaultSort) {
    this.defaultSort = defaultSort;
  }

  public Integer getDefaultSortDir() {
    return defaultSortDir;
  }

  public void setDefaultSortDir(Integer defaultSortDir) {
    this.defaultSortDir = defaultSortDir;
  }

  public Integer getEditAny() {
    return editAny;
  }

  public void setEditAny(Integer editAny) {
    this.editAny = editAny;
  }

  public Integer getNotification() {
    return notification;
  }

  public void setNotification(Integer notification) {
    this.notification = notification;
  }
  
}
