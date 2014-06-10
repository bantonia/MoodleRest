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
  
  private Long id=null;
  private String name=null;
  private String idnumber=null;
  private String description=null;
  private DescriptionFormat descriptionformat=DescriptionFormat.HTML;
  private CohortTypeId categorytypetype=CohortTypeId.SYSTEM;
  private String categorytypevalue="";

  public MoodleCohort() {    
  }

  public void setMoodleCohortField(String nodeName, String content) throws MoodleCohortException {
    if (nodeName.equals("id")) setId(Long.parseLong(content.trim()));
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("idnumber")) setIdNumber(content);
    if (nodeName.equals("description")) setDescription(content);
    if (nodeName.equals("descriptionformat") && !content.isEmpty()) setDescriptionFormat(Integer.parseInt(content.trim()));
    if (nodeName.equals("categorytypetype")) setCategoryTypeType(content);
    if (nodeName.equals("categorytypevalue")) setCategoryTypeValue(content);
  }

  public CohortTypeId getCategoryTypeType() {
    return categorytypetype;
  }

  public void setCategoryTypeType(CohortTypeId categorytypetype) {
    this.categorytypetype = categorytypetype;
  }
  
  public void setCategoryTypeType(String categorytypetype) throws MoodleCohortException {
    boolean flag=false;
    for (CohortTypeId c : CohortTypeId.values()) {
      if (c.toString().equals(categorytypetype)) {
        this.categorytypetype = c;
        flag=true;
      }
    }
    if (!flag) {
      throw new MoodleCohortException();
    }
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

  /*public Integer getDescriptionFormat() {
    return descriptionformat.toInt();
  }*/
  
  public DescriptionFormat getDescriptionFormat() {
    return descriptionformat;
  }

  public void setDescriptionFormat(DescriptionFormat descriptionformat) {
    this.descriptionformat = descriptionformat;
  }
  
  public void setDescriptionFormat(Integer descriptionformat) throws MoodleCohortException {
    boolean flag=false;
    for(DescriptionFormat d : DescriptionFormat.values()) {
      if (d.toInt()==descriptionformat) {
        this.descriptionformat=d;
        flag=true;
      }
    }
    if (!flag) {
      throw new MoodleCohortException();
    }
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
