/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

/**
 *
 * @author root
 */
public class MoodleModAssignAssignmentConfig {
  private Long id=null;
  private Long assignment=null;
  private String plugin=null;
  private String subtype=null;
  private String name=null;
  private String value=null;

  public MoodleModAssignAssignmentConfig() {
  }

  public MoodleModAssignAssignmentConfig(Long id, Long assignment, String plugin, String subtype, String name, String value) {
    this.id=id;
    this.assignment=assignment;
    this.plugin=plugin;
    this.subtype=subtype;
    this.name=name;
    this.value=value;
  }

  public Long getAssignment() {
    return assignment;
  }

  public void setAssignment(Long assignment) {
    this.assignment = assignment;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlugin() {
    return plugin;
  }

  public void setPlugin(String plugin) {
    this.plugin = plugin;
  }

  public String getSubtype() {
    return subtype;
  }

  public void setSubtype(String subtype) {
    this.subtype = subtype;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  public void setMoodleModAssignAssignmentConfigField(String nodeName,String content) {
    if (nodeName.equals("id")) setId(Long.valueOf(content));
    if (nodeName.equals("assignment")) setAssignment(Long.valueOf(content));
    if (nodeName.equals("plugin")) setPlugin(content);
    if (nodeName.equals("subtype")) setSubtype(content);
    if (nodeName.equals("name")) setName(content);
    if (nodeName.equals("value")) setValue(content);
  }

}
