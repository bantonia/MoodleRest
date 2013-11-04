/*
 *  Copyright (C) 2012 Bill Antonia
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

import java.util.ArrayList;

/**
 *
 * @author root
 */
public class MoodleModAssignAssignment {
  
    private Long id=null;
    private Long course=null;
    private String name=null;
    private Integer nosubmissions=null;
    private Integer submissiondrafts=null;
    private Integer sendnotifications=null;
    private Integer sendlatenotifications=null;
    private Integer duedate=null;
    private Integer allowsubmissionsfromdate=null;
    private Integer grade=null;
    private Integer timemodified=null;
    private Integer completionsubmit=null;
    private Integer cutoffdate=null;
    private Integer teamsubmission=null;
    private Integer requireallteammemberssubmit=null;
    private Integer teamsubmissiongroupingid=null;
    private Integer blindmarking=null;
    private Integer revealidenties=null;
    private Integer requiresubmissionstatement=null;
    private ArrayList<MoodleModAssignAssignmentConfig> configs=null;

    public MoodleModAssignAssignment() {}

    public MoodleModAssignAssignment(Long id) { this.id=id; }

  public ArrayList<MoodleModAssignAssignmentConfig> getConfigs() {
    return configs;
  }


  public void setId(Long id) { this.id=id; }
  public Long getId() { return id; }

  public Integer getAllowsubmissionsfromdate() {
    return allowsubmissionsfromdate;
  }

  public void setAllowsubmissionsfromdate(Integer allowsubmissionsfromdate) {
    this.allowsubmissionsfromdate = allowsubmissionsfromdate;
  }

  public Integer getBlindmarking() {
    return blindmarking;
  }

  public void setBlindmarking(Integer blindmarking) {
    this.blindmarking = blindmarking;
  }

  public Integer getCompletionsubmit() {
    return completionsubmit;
  }

  public void setCompletionsubmit(Integer completionsubmit) {
    this.completionsubmit = completionsubmit;
  }

  public Long getCourse() {
    return course;
  }

  public void setCourse(Long course) {
    this.course = course;
  }

  public Integer getCutoffdate() {
    return cutoffdate;
  }

  public void setCutoffdate(Integer cutoffdate) {
    this.cutoffdate = cutoffdate;
  }

  public Integer getDuedate() {
    return duedate;
  }

  public void setDuedate(Integer duedate) {
    this.duedate = duedate;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNosubmissions() {
    return nosubmissions;
  }

  public void setNosubmissions(Integer nosubmissions) {
    this.nosubmissions = nosubmissions;
  }

  public Integer getRequireallteammemberssubmit() {
    return requireallteammemberssubmit;
  }

  public void setRequireallteammemberssubmit(Integer requireallteammemberssubmit) {
    this.requireallteammemberssubmit = requireallteammemberssubmit;
  }

  public Integer getRequiresubmissionstatement() {
    return requiresubmissionstatement;
  }

  public void setRequiresubmissionstatement(Integer requiresubmissionstatement) {
    this.requiresubmissionstatement = requiresubmissionstatement;
  }

  public Integer getRevealidenties() {
    return revealidenties;
  }

  public void setRevealidenties(Integer revealidenties) {
    this.revealidenties = revealidenties;
  }

  public Integer getSendlatenotifications() {
    return sendlatenotifications;
  }

  public void setSendlatenotifications(Integer sendlatenotifications) {
    this.sendlatenotifications = sendlatenotifications;
  }

  public Integer getSendnotifications() {
    return sendnotifications;
  }

  public void setSendnotifications(Integer sendnotifications) {
    this.sendnotifications = sendnotifications;
  }

  public Integer getSubmissiondrafts() {
    return submissiondrafts;
  }

  public void setSubmissiondrafts(Integer submissiondrafts) {
    this.submissiondrafts = submissiondrafts;
  }

  public Integer getTeamsubmission() {
    return teamsubmission;
  }

  public void setTeamsubmission(Integer teamsubmission) {
    this.teamsubmission = teamsubmission;
  }

  public Integer getTeamsubmissiongroupingid() {
    return teamsubmissiongroupingid;
  }

  public void setTeamsubmissiongroupingid(Integer teamsubmissiongroupingid) {
    this.teamsubmissiongroupingid = teamsubmissiongroupingid;
  }

  public Integer getTimemodified() {
    return timemodified;
  }

  public void setTimemodified(Integer timemodified) {
    this.timemodified = timemodified;
  }
  
  public void addConfig(MoodleModAssignAssignmentConfig config) {
    if (configs==null)
      configs=new ArrayList<MoodleModAssignAssignmentConfig>();
    configs.add(config);
  }
    
}
