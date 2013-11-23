/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class MoodleModAssignAssignments implements Serializable {
  private Long id=null;
  private String fullName=null;
  private String shortName=null;
  private Long timeModified=null;
  private ArrayList<Assignment> assignments=null;
  
  public MoodleModAssignAssignments() {
    assignments=new ArrayList<Assignment>();
  }
  
  public Assignment newAssignment() {
    if (assignments==null) {
      assignments=new ArrayList<Assignment>();
    }
    Assignment assignment=new Assignment();
    assignments.add(assignment);
    return assignment;
  }
  
  public void setFieldValue(String name, String value) {
    if (name.equals("id") || name.equals("timemodified")) {
      if (value!=null && !value.isEmpty()) {
        if (name.equals("id")) { setId(Long.parseLong(value));}
        if (name.equals("timemodified")) { setTimeModified(Long.parseLong(value));}
      }
    }
    if (name.equals("fullname") || name.equals("shortname")) {
      if (value!=null) {
        if (name.equals("fullname")) { setFullName(value);}
        if (name.equals("shortname")) { setShortName(value);}
      }
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullname) {
    this.fullName = fullname;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortname) {
    this.shortName = shortname;
  }

  public Long getTimeModified() {
    return timeModified;
  }

  public void setTimeModified(Long timemodified) {
    this.timeModified = timemodified;
  }

  public ArrayList<Assignment> getAssignments() {
    return assignments;
  }

  public void setAssignments(ArrayList<Assignment> assignments) {
    this.assignments = assignments;
  }
  
  public class Assignment {
    private Long id=null;
    private Long course=null;
    private String name=null;
    private Integer noSubmissions=null;
    private Integer submissionDrafts=null;
    private Integer sendNotifications=null;
    private Integer sendLateNotifications=null;
    private Long dueDate=null;
    private Long allowSubmissionsFromDate=null;
    private Integer grade=null;
    private Long timeModified=null;
    private Integer completionSubmit=null;
    private Long cutOffDate=null;
    private Integer teamSubmission=null;
    private Integer requireAllTeamMembersSubmit=null;
    private Long teamSubmissionGroupingId=null;
    private Integer blindMarking=null;
    private Integer revealIdentities=null;
    private Integer requireSubmissionStatement=null;
    private ArrayList<Config> configs=null;
    
    public Assignment() {
      configs=new ArrayList<Config>();
    }
    
    public void setFieldValue(String name, String value) {
      if (name.equals("id") || name.equals("course") || name.equals("nosubmissions") || name.equals("submissiondrafts")
           || name.equals("sendnotifications") || name.equals("sendlatenotifications") || name.equals("duedate")
           || name.equals("allowsubmissionsfromdate") || name.equals("grade") || name.equals("timemodified")
           || name.equals("completionsubmit") || name.equals("cutoffdate") || name.equals("teamsubmission")
           || name.equals("requireallteammemberssubmit") || name.equals("teamsubmissiongroupingid")
           || name.equals("blindmarking") || name.equals("revealidentities") || name.equals("requiresubmissionstatement")) {
          if (value!=null && !value.isEmpty()) {
            if (name.equals("id")) { setId(Long.parseLong(value));}
            if (name.equals("course")) { setCourse(Long.parseLong(value));}
            if (name.equals("nosubmissions")) { setNoSubmissions(Integer.parseInt(value));}
            if (name.equals("submissiondrafts")) { setSubmissionDrafts(Integer.parseInt(value));}
            if (name.equals("sendnotifications")) { setSendNotifications(Integer.parseInt(value));}
            if (name.equals("sendlatenotifications")) { setSendLateNotifications(Integer.parseInt(value));}
            if (name.equals("duedate")) { setDueDate(Long.parseLong(value));}
            if (name.equals("allowsubmissionsfromdate")) { setAllowSubmissionsFromDate(Long.parseLong(value));}
            if (name.equals("grade")) { setGrade(Integer.parseInt(value));}
            if (name.equals("timemodified")) { setTimeModified(Long.parseLong(value));}
            if (name.equals("completionsubmit")) { setCompletionSubmit(Integer.parseInt(value));}
            if (name.equals("cutoffdate")) { setCutOffDate(Long.parseLong(value));}
            if (name.equals("teamsubmission")) { setTeamSubmission(Integer.parseInt(value));}
            if (name.equals("requireallteammemberssubmit")) { setRequireAllTeamMembersSubmit(Integer.parseInt(value));}
            if (name.equals("teamsubmissiongroupingid")) { setTeamSubmissionGroupingId(Long.parseLong(value));}
            if (name.equals("blindmarking")) { setBlindMarking(Integer.parseInt(value));}
            if (name.equals("revealidentities")) { setRevealIdentities(Integer.parseInt(value));}
            if (name.equals("requiresubmissionstatement")) { setRequireSubmissionStatement(Integer.parseInt(value));}
          }
        }
        if (name.equals("name") || name.equals("value")) {
          if (value!=null) {
            if (name.equals("name")) { setName(value);}
          }
        }
    }
    
    public Config newConfig() {
      if (configs==null) {
        configs=new ArrayList<Config>();
      }
      Config config=new Config();
      configs.add(config);
      return config;
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

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getNoSubmissions() {
      return noSubmissions;
    }

    public void setNoSubmissions(Integer nosubmissions) {
      this.noSubmissions = nosubmissions;
    }

    public Integer getSubmissionDrafts() {
      return submissionDrafts;
    }

    public void setSubmissionDrafts(Integer submissiondrafts) {
      this.submissionDrafts = submissiondrafts;
    }

    public Integer getSendNotifications() {
      return sendNotifications;
    }

    public void setSendNotifications(Integer sendnotifications) {
      this.sendNotifications = sendnotifications;
    }

    public Integer getSendLateNotifications() {
      return sendLateNotifications;
    }

    public void setSendLateNotifications(Integer sendlatenotifications) {
      this.sendLateNotifications = sendlatenotifications;
    }

    public Long getDueDate() {
      return dueDate;
    }

    public void setDueDate(Long duedate) {
      this.dueDate = duedate;
    }

    public Long getAllowSubmissionsFromDate() {
      return allowSubmissionsFromDate;
    }

    public void setAllowSubmissionsFromDate(Long allowsubmissionsfromdate) {
      this.allowSubmissionsFromDate = allowsubmissionsfromdate;
    }

    public Integer getGrade() {
      return grade;
    }

    public void setGrade(Integer grade) {
      this.grade = grade;
    }

    public Long getTimeModified() {
      return timeModified;
    }

    public void setTimeModified(Long timemodified) {
      this.timeModified = timemodified;
    }

    public Integer getCompletionSubmit() {
      return completionSubmit;
    }

    public void setCompletionSubmit(Integer completionsubmit) {
      this.completionSubmit = completionsubmit;
    }

    public Long getCutOffDate() {
      return cutOffDate;
    }

    public void setCutOffDate(Long cutoffdate) {
      this.cutOffDate = cutoffdate;
    }

    public Integer getTeamSubmission() {
      return teamSubmission;
    }

    public void setTeamSubmission(Integer teamsubmission) {
      this.teamSubmission = teamsubmission;
    }

    public Integer getRequireAllTeamMembersSubmit() {
      return requireAllTeamMembersSubmit;
    }

    public void setRequireAllTeamMembersSubmit(Integer requireallteammemberssubmit) {
      this.requireAllTeamMembersSubmit = requireallteammemberssubmit;
    }

    public Long getTeamSubmissionGroupingId() {
      return teamSubmissionGroupingId;
    }

    public void setTeamSubmissionGroupingId(Long teamsubmissiongroupingid) {
      this.teamSubmissionGroupingId = teamsubmissiongroupingid;
    }

    public Integer getBlindMarking() {
      return blindMarking;
    }

    public void setBlindMarking(Integer blindmarking) {
      this.blindMarking = blindmarking;
    }

    public Integer getRevealIdentities() {
      return revealIdentities;
    }

    public void setRevealIdentities(Integer revealIdentities) {
      this.revealIdentities = revealIdentities;
    }

    public Integer getRequireSubmissionStatement() {
      return requireSubmissionStatement;
    }

    public void setRequireSubmissionStatement(Integer requiresubmissionstatement) {
      this.requireSubmissionStatement = requiresubmissionstatement;
    }

    public ArrayList<Config> getConfigs() {
      return configs;
    }

    public void setConfigs(ArrayList<Config> configs) {
      this.configs = configs;
    }
    
    public class Config {
      private Long id=null;
      private Long assignment=null;
      private String plugin=null;
      private String subType=null;
      private String name=null;
      private String value=null;
      
      public Config() {}
      
      public void setFieldValue(String name, String value) {
        if (name.equals("id") || name.equals("assignment")) {
          if (value!=null && !value.isEmpty()) {
            if (name.equals("id")) { setId(Long.parseLong(value));}
            if (name.equals("assignment")) { setAssignment(Long.parseLong(value));}
          }
        }
        if (name.equals("plugin") || name.equals("subtype") || name.equals("name") || name.equals("value")) {
          if (value!=null) {
            if (name.equals("plugin")) { setPlugin(value);}
            if (name.equals("subtype")) { setSubType(value);}
            if (name.equals("name")) { setName(value);}
            if (name.equals("value")) { setValue(value);}
          }
        }
      }

      public Long getId() {
        return id;
      }

      public void setId(Long id) {
        this.id = id;
      }

      public Long getAssignment() {
        return assignment;
      }

      public void setAssignment(Long assignment) {
        this.assignment = assignment;
      }

      public String getPlugin() {
        return plugin;
      }

      public void setPlugin(String plugin) {
        this.plugin = plugin;
      }

      public String getSubType() {
        return subType;
      }

      public void setSubType(String subType) {
        this.subType = subType;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
      
    }
    
  }
  
}
