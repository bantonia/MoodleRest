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

/**
 * <p>Class for MoodleCourse objects. Used when creating new courses or retrieving course details.</p>
 * <p>When creating a new MoodleCourse object its initial id state is set to default of -1. This will be updated to the id of the course created in Moodle.</p>
 * <p>For Moodle course creation a minimum of attributes shortname, fullname and categoryid have to be set programatically, other required attributes are set to default values found in Moodle, change as appropriate using setter methods before a call for course creation.</p>
 * @see MoodleRestCourse
 * @author Bill Antonia
 *
 */
public class MoodleCourse {

    /**
     *
     */
    public static final int SUMMARY_FORMAT_NO = 0;
    /**
     *
     */
    public static final int SUMMARY_FORMAT_YES = 1;
    /**
     *
     */
    public static final String FORMAT_TOPICS = "topics";
    /**
     *
     */
    public static final String FORMAT_SCORM = "scorm";
    /**
     *
     */
    public static final String FORMAT_WEEKS = "weeks";
    /**
     *
     */
    public static final String FORMAT_SOCIAL = "social";
    /**
     *
     */
    public static final int SHOW_GRADES_NO = 0;
    /**
     *
     */
    public static final int SHOW_GRADES_YES = 1;
    /**
     *
     */
    public static final int SHOW_REPORTS_NO = 0;
    /**
     *
     */
    public static final int SHOW_REPORTS_YES = 1;
    /**
     *
     */
    public static final int HIDDEN_SECTIONS_COLLAPSED = 0;
    /**
     *
     */
    public static final int HIDDEN_SECTIONS_INVISIBLE = 1;
    /**
     *
     */
    public static final int GROUP_MODE_NO_GROUPS = 0;
    /**
     *
     */
    public static final int GROUP_MODE_SEPARATE_GROUPS = 1;
    /**
     *
     */
    public static final int GROUP_MODE_VISIBLE_GROUPS = 2;
    /**
     *
     */
    public static final int GROUP_MODE_FORCE_NO = 0;
    /**
     *
     */
    public static final int GROUP_MODE_FORCE_YES = 1;
    /**
     *
     */
    public static final boolean COURSE_VISIBLE_TO_STUDENTS_NO = false;
    /**
     *
     */
    public static final boolean COURSE_VISIBLE_TO_STUDENTS_YES = true;
    /**
     *
     */
    public static final boolean COMPLETION_DISABLED = false;
    /**
     *
     */
    public static final boolean COMPLETION_ENABLED = true;
    /**
     *
     */
    public static final boolean COMPLETION_NOTIFY_DISABLED = false;
    /**
     *
     */
    public static final boolean COMPLETION_NOTIFY_ENABLED = true;
    /**
     *
     */
    public static final boolean COMPLETION_ON_ENROLL_DISABLED = false;
    /**
     *
     */
    public static final boolean COMPLETION_ON_ENROLL_ENABLED = true;


    private Long id=null;

    // Required for creation, need to be set before call!!!!!!!!
    private String shortname=null;
    private String fullname=null;
    private Long categoryid=null;

    // Required for course creation but will use the default values below unless already changed
    private Integer summaryformat=SUMMARY_FORMAT_NO;
    private String format=FORMAT_TOPICS;
    private Integer showgrades=SHOW_GRADES_YES;
    private Integer newsitems=1;
    private Integer numsections=1;
    private Long maxbytes=0L;
    private Integer showreports=SHOW_REPORTS_NO;
    private Integer hiddensections=HIDDEN_SECTIONS_COLLAPSED;
    private Integer groupmode=GROUP_MODE_NO_GROUPS;
    private Integer groupmodeforce=GROUP_MODE_FORCE_NO;
    private Long defaultgroupingid=0L;

    // Optional but will be set to these default values during creation
    private Boolean enablecompletion=COMPLETION_DISABLED;
    private Boolean completionstartonenrol=COMPLETION_ON_ENROLL_DISABLED;
    private Boolean completionnotify=COMPLETION_NOTIFY_DISABLED;
    private Boolean visible=COURSE_VISIBLE_TO_STUDENTS_YES;

    // Not used during creation
    private Integer categorysortorder=null;
    private Long timecreated=null;
    private Long timemodified=null;
    
    // Optional for creation
    private String summary=null;
    private String idnumber=null;
    private String lang=null;
    private String forcetheme=null;
    private Long startdate=null;

    // Other stuff!!!
    private Long enrolledusercount=null;
    
    /**
     * <p>MoodleCourse, course object constructor with the minimum attribute settings to create a Moodle course.</p>
     * <p>Note there are a number of required and optional attributes which are set during the construction, change these as appropriate.</p>
     * 
     * @param shortname String
     * @param fullname String
     * @param categoryid long
     */
    public MoodleCourse(String shortname, String fullname, long categoryid) {
        this.shortname=shortname;
        this.fullname=fullname;
        this.categoryid=categoryid;
    }

    /**
     * <p>MoodleCourse object constructor setting the course id.</p>
     * <p>Object used to retrieve the attributes or update a course already in Moodle.</p>
     * @param id long
     */
    public MoodleCourse(long id) {
        this.id=id;
    }

    /**
     * <p>MoodleCourse object constructor for bean requirements.</p>
     */
    public MoodleCourse() {
    }

    /**
     * <p>setMoodleCourseField sets an attribute with type conversion from the name of the attribute and a value, both passed as strings.</p>
     * 
     * @param nodeName String
     * @param content String
     */
    public void setMoodleCourseField(String nodeName, String content) {
        if (nodeName.equals("id")) setId(Long.parseLong(content.trim()));
        if (nodeName.equals("shortname")) setShortname(content);
        if (nodeName.equals("categoryid")) setCategoryId(Long.parseLong(content.trim()));
        if (nodeName.equals("categorysortorder")) setCategorySortOrder(Integer.parseInt(content.trim()));
        if (nodeName.equals("fullname")) setFullname(content);
        if (nodeName.equals("idnumber")) setIdNumber(content);
        if (nodeName.equals("summary")) setSummary(content);
        if (nodeName.equals("summaryformat")) setSummaryFormat(Integer.parseInt(content));
        if (nodeName.equals("format")) setFormat(content);
        if (nodeName.equals("showgrades")) setShowGrades(Integer.parseInt(content));
        if (nodeName.equals("newsitems")) setNewsItems(Integer.parseInt(content));
        if (nodeName.equals("startdate")) setStartDate(Long.parseLong(content.trim()));
        if (nodeName.equals("numsections")) setNumSections(Integer.parseInt(content));
        if (nodeName.equals("maxbytes")) setMaxBytes(Long.parseLong(content.trim()));
        if (nodeName.equals("showreports")) setShowReports(Integer.parseInt(content));
        if (nodeName.equals("visible")) setVisible(Integer.parseInt(content)==0?COURSE_VISIBLE_TO_STUDENTS_NO:COURSE_VISIBLE_TO_STUDENTS_YES);
        if (nodeName.equals("hiddensections") && !content.isEmpty()) setHiddenSections(Integer.parseInt(content));
        if (nodeName.equals("groupmode")) setGroupMode(Integer.parseInt(content));
        if (nodeName.equals("groupmodeforce")) setGroupModeForce(Integer.parseInt(content));
        if (nodeName.equals("defaultgroupingid")) setDefaultGroupingId(Long.parseLong(content.trim()));
        if (nodeName.equals("timecreated")) setTimeCreated(Long.parseLong(content.trim()));
        if (nodeName.equals("timemodified")) setTimeModified(Long.parseLong(content.trim()));
        if (nodeName.equals("enablecompletion")) setEnableCompletion(Integer.parseInt(content)==0?COMPLETION_DISABLED:COMPLETION_ENABLED);
        if (nodeName.equals("completionstartonenrol")) setCompletionStartOnEnrol(Integer.parseInt(content)==0?COMPLETION_ON_ENROLL_DISABLED:COMPLETION_ON_ENROLL_ENABLED);
        if (nodeName.equals("completionnotify")) setCompletionNotify(Integer.parseInt(content)==0?COMPLETION_NOTIFY_DISABLED:COMPLETION_NOTIFY_ENABLED);
        if (nodeName.equals("lang")) setLang(content);
        if (nodeName.equals("forcetheme")) setForceTheme(content);
        if (nodeName.equals("enrolledusercount")) setEnrolledUserCount(Long.parseLong(content.trim())); 
    }

    /**
     * <p>Method to set the course id in a MoodleCourse object.</p>
     * 
     * @param id long
     */
    public void setId(long id) {
        this.id=id;
    }

    /**
     * <p>Method to set the shortname of a course in a MoodleCourse object.</p>
     * <p>Required to be set on course creation. Default null, error on course creation if null.</p>
     * 
     * @param shortname String
     */
    public void setShortname(String shortname) {
        this.shortname=shortname;
    }

    /**
     * Method to set the category id of a course in a MoodleCourse object. Required to be set on course creation. Default -1, error on course creation.
     * @param categoryid long
     */
    public void setCategoryId(Long categoryid) {
        this.categoryid=categoryid;
    }

    /**
     * Method to set the category sort order for a course. Default -1, do not set, use Moodle default.
     * @param categorysortorder long
     */
    public void setCategorySortOrder(Integer categorysortorder) {
        this.categorysortorder=categorysortorder;
    }

    /**
     * Method to set the fullname of a course in a MoodleCourse object. Required to be set on course creation. Default null, error on course creation.
     * @param fullname String
     */
    public void setFullname(String fullname) {
        this.fullname=fullname;
    }

    /**
     * Method to set the idnumber attribute of the MoodleCourse object. Note idnumber does not have to be a number, it can be a string. Default null.
     * @param idnumber String
     */
    public void setIdNumber(String idnumber) {
        this.idnumber=idnumber;
    }

    /**
     * Method to set the Moodle course summary field of the MoodleCourse object. A summary of the course. Default null, no summary set.
     * @param summary String
     */
    public void setSummary(String summary) {
        this.summary=summary;
    }

    /**
     * Method to set the summaryformat attribute of a MoodleCourse object. Not sure what this field does as it seems to have no equivalent in the course creation form within Moodle. Default 0 or off.
     * @param summaryformat int
     */
    public void setSummaryFormat(Integer summaryformat) {
        this.summaryformat=summaryformat;
    }

    /**
     * Method to set the format of the MoodleCouse object "topics", "weekly", "social" or "scorm". There is also a "site" format but there should be only one course within the database, this is "Site home". Default "topics".
     * @param format String
     */
    public void setFormat(String format) {
        this.format=format;
    }

    /**
     * Method to set the showgrades attribute of a MoodleCourse object. Although this could be a boolean, the Moodle database stores this in a field which has more than on bit. Default 1 or "Yes".
     * @param showgrades int
     */
    public void setShowGrades(Integer showgrades) {
        this.showgrades=showgrades;
    }

    /**
     * Method to set the MoodleCourse object newsitems attribute. The number of news items to show in a Moodle course. Default 1.
     * @param newsitems int
     */
    public void setNewsItems(Integer newsitems) {
        this.newsitems=newsitems;
    }

    /**
     * Method to set the start date of a MoodleCourse object as a Unix timestamp. Default -1, no start date set.
     * @param startdate long
     */
    public void setStartDate(Long startdate) {
        this.startdate=startdate;
    }

    /**
     * Method to set the numsections attribute of a MoodleCourse object. Sets the number of weeks/topics of a Moodle course. Default 1.
     * @param numsections int
     */
    public void setNumSections(Integer numsections) {
        this.numsections=numsections;
    }

    /**
     * Method to set the maxbytes attribute of a MoodleCourse object. Sets the size in bytes the maximum allowed transfer size for files within the course.
     * @param maxbytes long
     */
    public void setMaxBytes(Long maxbytes) {
        this.maxbytes=maxbytes;
    }

    /**
     * Method to set the showreports attribute of a MoodleCourse object. Flag to be able to set to enable reports to be visible to students. Although this could be a boolean, the Moodle database stores this in a field which has more than on bit. Default 0 or off.
     * @param showreports int
     */
    public void setShowReports(Integer showreports) {
        this.showreports=showreports;
    }

    /**
     * Method to set the visible attribute of a MoodleCourse object. Sets the visibility of a Moodle course for students, "This course is not available to students" false, "This course is available to students" true. Default true or visible to students.
     * @param visible boolean
     */
    public void setVisible(Boolean visible) {
        this.visible=visible;
    }

    /**
     * Method to set the attribute hiddensections in a MoodleCourse object. The hidden sections make sections, i.e. topics or weeks in a course either collapsed 0 or completely invisible 1. Default is 0 or "Hidden sections are shown in collapsed form".
     * @param hiddensections int
     */
    public void setHiddenSections(Integer hiddensections) {
        this.hiddensections=hiddensections;
    }

    /**
     * Method to set the attribute groupmode in a MoodleCourse object. Group mode in a Moodle course can be set to "No groups" 0, "Separate groups" 1 or "Visible groups" 2. Default 0 or "No groups".
     * @param groupmode int
     */
    public void setGroupMode(Integer groupmode) {
        this.groupmode=groupmode;
    }

    /**
     * Method to set the attribute groupmodeforce in a MoodleCourse object. Sets groups to be used within every activity of a course. Default 0 or "No".
     * @param groupmodeforce int
     */
    public void setGroupModeForce(Integer groupmodeforce) {
        this.groupmodeforce=groupmodeforce;
    }

    /**
     * Method to set the attribute defaultgroupingid of a MoodleCourse object. Not sure what it does at the moment. Default 0.
     * @param defaultgroupingid long
     */
    public void setDefaultGroupingId(Long defaultgroupingid) {
        this.defaultgroupingid=defaultgroupingid;
    }

    /**
     * Method to set the attribute timecreated of a MoodleCourse object. UNIX timestamp of the date the course is created. Default -1 or do not use during course creation.
     * @param timecreated long
     */
    public void setTimeCreated(Long timecreated) {
        this.timecreated=timecreated;
    }

    /**
     * Method to set the attribute timemodified of a MoodleCourse object. UNIX timestamp of the date the course was modified. Default -1 or do not use during course creation.
     * @param timemodified long
     */
    public void setTimeModified(Long timemodified) {
        this.timemodified=timemodified;
    }

    /**
     * Method to set the attribute enablecompletion of a MoodleCourse object. 
     * @param enablecompletion boolean
     */
    public void setEnableCompletion(Boolean enablecompletion) {
        this.enablecompletion=enablecompletion;
    }

    /**
     * Method to set the attribute completionstartonenrol of a MoodleCourse object. Note completion has to be enabled first in Settings->site administration->Advanced settings. Default disabled.
     * @param completionstartonenrol boolean
     */
    public void setCompletionStartOnEnrol(Boolean completionstartonenrol) {
        this.completionstartonenrol=completionstartonenrol;
    }

    /**
     * Method to set the attribute completionnotify of a MoodleCourse object. Note completion has to be enabled first in Settings->site administration->Advanced settings. Default disabled.
     * @param completionnotify boolean
     */
    public void setCompletionNotify(Boolean completionnotify) {
        this.completionnotify=completionnotify;
    }

    /**
     * Method to set the language of the Moodle course. Default is null, use the installed default language.
     * @param lang String
     */
    public void setLang(String lang) {
        this.lang=lang;
    }

    /**
     * Method to set the forcetheme attribute of a MoodleCourse object. Used to set the theme of a course.
     * @param forcetheme String
     */
    public void setForceTheme(String forcetheme) {
        this.forcetheme=forcetheme;
    }
    
    /**
     * Method to set the enrolledusercount attribute of a MoodleCourse object.
     * @param enrolledusercount long
     */
    private void setEnrolledUserCount(Long enrolledusercount) {
      this.enrolledusercount=enrolledusercount;
    }

    /**
     * Method to get the id of a Moodle course from a MoodleCourse object.
     * @return id long
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to get the shortname of a Moodle course from a MoodleCourse object.
     * @return shortname String
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * Method to get the categoryid of a Moodle course from a MoodleCourse object.
     * @return categoryid long
     */
    public Long getCategoryId() {
        return categoryid;
    }

    /**
     * Method to return the categorysortorder attribute of a MoodleCourse object.
     * @return categorysortorder
     */
    public Integer getCategorySortOrder() {
        return categorysortorder;
    }

    /**
     * Method to get the fullname attribute of a MoodleCourse object.
     * @return fullname String
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Method to get the idnumber attribute of a MoodleCourse object.
     * @return idnumber String
     */
    public String getIdNumber() {
        return idnumber;
    }

    /**
     * Method to get the summary attribute of a MoodleCourse object.
     * @return summary String
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Method to get the summaryformat attribute of a MoodleCourse object.
     * @return summaryformat int
     */
    public Integer getSummaryFormat() {
        return summaryformat;
    }

    /**
     * Method to get the course format attribute of a MoodleCourse object
     * @return format String
     */
    public String getFormat() {
        return format;
    }

    /**
     * Method to get the status of the showgrades attribute of a MoodleCourse object.
     * @return showgrades int
     */
    public Integer getShowGrades() {
        return showgrades;
    }

    /**
     * Method to get the number of news items from the newsitems attribute in a MoodleCourse object
     * @return newsitems int
     */
    public Integer getNewsItems() {
        return newsitems;
    }

    /**
     * Method to get the startdate attribute of a MoodleCourse object. Unix timestamp.
     * @return startdate long
     */
    public Long getStartDate() {
        return startdate;
    }

    /**
     * Method to get the numsections attribute of a MoodleCourse object. Gets the number of sections in a Moodle course.
     * @return numsections int
     */
    public Integer getNumSections() {
        return numsections;
    }

    /**
     * Method to get the maxbytes attribute in a MoodleCourse object. This is the maximum number of bytes allowed to be transferred during an upload to the course.
     * @return maxbytes long
     */
    public Long getMaxBytes() {
        return maxbytes;
    }

    /**
     * Method to get the showreports attribute in a MoodleCourse object. Returns an integer representing the status of students being allowed to view reports.
     * @return showreports int
     */
    public Integer getShowReports() {
        return showreports;
    }

    /**
     * Is the course visible to students?
     * @return visible boolean
     */
    public Boolean isVisibleToStudents() {
        return visible;
    }

    /**
     * Method to get the visible attribute of a MoodleCourse object.
     * @return visible boolean
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Method to get the hiddensections attribute of a MoodleCourse object. Value returned, 0 hidden sections are in a collapsed state, 1 hidden sections are invisible.
     * @return hiddensections int
     */
    public Integer getHiddenSections() {
        return hiddensections;
    }

    /**
     * Method to get the groupmode attribute of a MoodleCourse object. Value returned, 0 no groups, 1 separate groups, 2 visible groups.
     * @return group mode int
     */
    public Integer getGroupMode() {
        return groupmode;
    }

    /**
     * Method to get the groupmodeforce attribute of a MoodleCourse object.
     * @return groupmodeforce int
     */
    public Integer getGroupModeForce() {
        return groupmodeforce;
    }

    /**
     * Method to get the defaultgroupingid attribute of a MoodleCourse object.
     * @return defaultgroupingid long
     */
    public Long getDefaultGroupingId() {
        return defaultgroupingid;
    }

    /**
     * Method to get the timecreated attribute of a MoodleCourse object.
     * @return timecreated long
     */
    public Long getTimeCreated() {
        return timecreated;
    }

    /**
     * Method to get the timemodified attribute of a MoodleCourse object.
     * @return timemodified long
     */
    public Long getTimeModified() {
        return timemodified;
    }

    /**
     * Method to get the enablecompletion attribute of a MoodleCourse object.
     * @return enablecompletion boolean
     */
    public Boolean getEnableCompletion() {
        return enablecompletion;
    }

    /**
     * Method to get the completionstartonenrol attribute of a MoodleCourse object.
     * @return completionstartonenrol boolean
     */
    public Boolean getCompletionStartOnEnrol() {
        return completionstartonenrol;
    }

    /**
     * Method to get the completionnotify attribute of a MoodleCourse object.
     * @return completionnotify boolean
     */
    public Boolean getCompletionNotify() {
        return completionnotify;
    }

    /**
     * Method to get the lang attribute of a MoodleCourse object.
     * @return lang String
     */
    public String getLang() {
        return lang;
    }

    /**
     * Method to get the forcetheme attribute of a MoodleCourse object.
     * @return forcetheme String
     */
    public String getForceTheme() {
        return forcetheme;
    }
    
    /**
     * Method to get the enrolledusercount attribute of a MoodleCourse object.
     * @return enrolledusercount long
     */
    public Long getEnrolledUserCount() {
      return enrolledusercount;
    }
}
