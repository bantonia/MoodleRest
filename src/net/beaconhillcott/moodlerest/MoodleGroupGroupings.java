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
public class MoodleGroupGroupings implements Serializable {
  
  private Long id=null;
  private Long courseid=null;
  private String name=null;
  private String description=null;
  private DescriptionFormat descriptionformat=DescriptionFormat.HTML;

  public MoodleGroupGroupings() {
  }

  public MoodleGroupGroupings(Long courseid, String name, String description) {
    this.courseid=courseid;
    this.name=name;
    this.description=description;
  }
  
  public MoodleGroupGroupings(Long courseid, String name, String description, DescriptionFormat descriptionformat) {
    this.courseid=courseid;
    this.name=name;
    this.description=description;
    this.descriptionformat=descriptionformat;
  }
  
  public MoodleGroupGroupings(Long id, Long courseid, String name, String description, DescriptionFormat descriptionformat) {
    this.id=id;
    this.courseid=courseid;
    this.name=name;
    this.description=description;
    this.descriptionformat=descriptionformat;
  }

  public MoodleGroupGroupings(Long id) {
    this.id=id;
  }
  
  public void setMoodleGroupGroupingsField(String nodeName,String content) throws MoodleGroupGroupingsException {
    if (nodeName.equals("id")) setId(Long.valueOf(content));
    if (nodeName.equals("courseid")) setCourseid(Long.valueOf(content));
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("description")) setDescription(content);
    if (nodeName.equals("descriptionformat")) {
      setDescriptionformat(Integer.parseInt(content));
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCourseid() {
    return courseid;
  }

  public void setCourseid(Long courseid) {
    this.courseid = courseid;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DescriptionFormat getDescriptionformat() {
    return descriptionformat;
  }

  public void setDescriptionformat(DescriptionFormat descriptionformat) {
    this.descriptionformat = descriptionformat;
  }
  
  public void setDescriptionformat(Integer descriptionformat) throws MoodleGroupGroupingsException {
    boolean flag=false;
    for(DescriptionFormat d : DescriptionFormat.values()) {
      if (d.toInt()==descriptionformat) {
        this.descriptionformat=d;
        flag=true;
      }
    }
    if (!flag) {
      throw new MoodleGroupGroupingsException();
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
