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
public class MoodleCohort implements Serializable {
  
  public static final int DESCRIPTION_FORMAT_MOODLE=0;
  public static final int DESCRIPTION_FORMAT_HTML=1;
  public static final int DESCRIPTION_FORMAT_PLAIN=2;
  public static final int DESCRIPTION_FORMAT_MARKDOWN=4;
  
  public static final String CATEGORY_TYPE_COURSE_CATEGORY_ID="id";
  public static final String CATEGORY_TYPE_COURSE_ID="idnumber";
  public static final String CATEGORY_TYPE_SYSTEM="system";
  
  private Long id=null;
  private String name=null;
  private String idnumber=null;
  private String description=null;
  private Integer descriptionformat=DESCRIPTION_FORMAT_HTML;
  private String categorytypetype=null;
  private String categorytypevalue=null;

  public MoodleCohort() {
  }

  public void setMoodleCohortField(String nodeName, String content) {
    if (nodeName.equals("id")) setId(Long.parseLong(content.trim()));
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("idnumber")) setIdNumber(content);
    if (nodeName.equals("description")) setDescription(content);
    if (nodeName.equals("descriptionformat") && !content.isEmpty()) setDescriptionFormat(Integer.parseInt(content.trim()));
    if (nodeName.equals("categorytypetype")) setCategoryTypeType(content);
    if (nodeName.equals("categorytypevalue")) setCategoryTypeValue(content);
  }

  public String getCategoryTypeType() {
    return categorytypetype;
  }

  public void setCategoryTypeType(String categorytypetype) {
    this.categorytypetype = categorytypetype;
  }

  public String getCategoryTypeValue() {
    return categorytypevalue;
  }

  public void setCategoryTypeValue(String categorytypevalue) {
    this.categorytypevalue = categorytypevalue;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getDescriptionFormat() {
    return descriptionformat;
  }

  public void setDescriptionFormat(Integer descriptionformat) {
    this.descriptionformat = descriptionformat;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdNumber() {
    return idnumber;
  }

  public void setIdNumber(String idnumber) {
    this.idnumber = idnumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
