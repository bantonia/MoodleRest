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
 * Class used to hold user/course memberships. Returned when fetching a list of users enrolled in a course.
 * @see MoodleRestCourse
 * @author Bill Antonia
 */
public class MoodleCourseUser implements Serializable {

    private Long courseid=null;
    private Long userid=null;

    /**
     * Constructor for bean requirements
     */
    public MoodleCourseUser(){}

    /**
     * Constructor setting user to course membership
     * @param courseid Long
     * @param userid Long
     */
    public MoodleCourseUser(Long courseid, Long userid) {
        this.courseid=courseid;
        this.userid=userid;
    }

    /**
     * Method to set the courseid attribute of a MoodleCourseUser object.
     * @param courseid Long
     */
    public void setCourseId(Long courseid) {
        this.courseid=courseid;
    }

    /**
     * Method to set the userid attribute of a MoodleCourseUser object.
     * @param userid Long
     */
    public void setUserId(Long userid) {
        this.userid=userid;
    }

    /**
     * Method to get the courseid attribute of a MoodleCourseUser object.
     * @return courseid long
     */
    public Long getCourseId() {
        return courseid;
    }

    /**
     * Method to get the userid attribute of a MoodleCourseUser object.
     * @return userid long
     */
    public long getUserId() {
        return userid;
    }
}
