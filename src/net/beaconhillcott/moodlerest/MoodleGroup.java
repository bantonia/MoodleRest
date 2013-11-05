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

import java.io.Serializable;

/**
 * Class to create and hold the status of a MoodleGroup object. Used in the process of group creation and manipulation for courses.
 * 
 * @author Bill Antonia
 * @see MoodleRestGroup
 */
public class MoodleGroup implements Serializable {

    private Long id=null;
    private Long courseid=null;
    private String name=null;
    private String description=null;
    private String enrolmentkey=null;

    /**
     * Constructor for bean requirements
     */
    public MoodleGroup() {}

    /**
     * <p>Constructor to create a MoodleGroup object.</p>
     * <p>Setter method calls are needed to set other attributes of the object.</p>
     * @param id Long
     */
    public MoodleGroup(Long id) {
        this.id=id;
    }

    /**
     * <p>Constructor to build a basic MoodleGroup object ready to create a Moodle group.</p>
     *
     * @param courseid Long
     * @param name String
     */
    public MoodleGroup(Long courseid, String name) {
        this.courseid=courseid;
        this.name=name;
        this.description="";
        this.enrolmentkey="";
    }

    /**
     * <p>Constructor to build a MoodleGroup object ready to create a Moodle group.</p>
     *
     * @param courseid Long
     * @param name String
     * @param description String
     */
    public MoodleGroup(Long courseid, String name, String description) {
        this.courseid=courseid;
        this.name=name;
        this.description=description;
        this.enrolmentkey="";
    }

    /**
     * <p>Constructor to build a MoodleGroup object ready to create a Moodle group.</p>
     *
     * @param courseid Long
     * @param name String
     * @param description String
     * @param enrolmentkey String
     */
    public MoodleGroup(Long courseid, String name, String description, String enrolmentkey) {
        this.courseid=courseid;
        this.name=name;
        this.description=description;
        this.enrolmentkey=enrolmentkey;
    }

    /**
     * <p>Constructor to build a MoodleGroup object ready to create a Moodle group.<br />
     * Probably will never get used as it needs the id of the Moodle group.</p>
     *
     * @param id Long
     * @param courseid Long
     * @param name String
     * @param description String
     * @param enrolmentkey String
     */
    public MoodleGroup(Long id, Long courseid, String name, String description, String enrolmentkey) {
        this.id=id;
        this.courseid=courseid;
        this.name=name;
        this.description=description;
        this.enrolmentkey=enrolmentkey;
    }

    /**
     * <p>Method to set the id attribute of a MoodleGroup object.</p>
     * <p>Probably will never get used but present due to bean requirements.</p>
     *
     * @param id Long
     */
    public void setId(Long id) {
        this.id=id;
    }

    /**
     * <p>Method to set the courseid attribute of a MoodleGroup object.</
     * p>
     * @param courseid Long
     */
    public void setCourseId(Long courseid) {
        this.courseid=courseid;
    }

    /**
     * <p>Method to set the name attribute of a MoodleGroup object.</p>
     *
     * @param name String
     */
    public void setName(String name) {
        this.name=name;
    }

    /**
     * <p>Method to set the description attribute of a MoodleGroup object.</p>
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description=description;
    }

    /**
     * <p>Method to set the enrolmentkey attribute of a MoodleGroup object.</p>
     *
     * @param enrolmentkey String
     */
    public void setEnrolmentKey(String enrolmentkey) {
        this.enrolmentkey=enrolmentkey;
    }

    /**
     * <p>Method to get the id attribute of a MoodleGroup object.</p>
     *
     * @return id Long
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>Method to get the courseid attribute of a MoodleGroup object.</p>
     *
     * @return courseid Long
     */
    public Long getCourseId() {
        return courseid;
    }

    /**
     * <p>Method to get the name attribute of a MoodleGroup object.</p>
     *
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Method to get the description attribute of a MoodleGroup object.</p>
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>Method to get the enrolmentkey attribute of a MoodleGroup object.</p>
     *
     * @return enrolmentkey String
     */
    public String getEnrolmentKey() {
        return enrolmentkey;
    }

    /**
     * <p>Method to set a MoodleGroup attribute given the name of the attribute and its value, both passed as String. Automatic conversion to the internal type takes place.</p>
     * 
     * @param nodeName String
     * @param content String
     */
    public void setMoodleGroupField(String nodeName, String content) {
        if (nodeName.equals("id")) setId(Long.valueOf(content.trim()));
        if (nodeName.equals("name")) setName(content);
        if (nodeName.equals("courseid")) setCourseId(Long.valueOf(content.trim()));
        if (nodeName.equals("description")) setDescription(content);
        if (nodeName.equals("enrolmentkey")) setEnrolmentKey(content);
    }
}
