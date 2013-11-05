/*
 *  Copyright (C) 2013 Bill Antonia
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
 * 
 * @author Bill Antonia
 */
public class MoodleGroupings implements Serializable {

    public static final Long MOODLE=0L;
    public static final Long HTML=1L;
    public static final Long PLAIN=2L;
    public static final Long MARKDOWN=3L;

    private Long courseId=null;
    private String name=null;
    private String description=null;
    private Long descriptionFormat=null;

    public MoodleGroupings() {}

    public MoodleGroupings(Long courseId, String name, String description) {
        this.courseId=courseId;
        this.name=name;
        this.description=description;
        descriptionFormat=HTML;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDescriptionFormat() {
        return descriptionFormat;
    }

    public void setDescriptionFormat(Long descriptionFormat) {
        this.descriptionFormat = descriptionFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
