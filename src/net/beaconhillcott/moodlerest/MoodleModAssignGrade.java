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
 * 
 * @author Bill Antonia
 */
class MoodleModAssignGrade {

    private Long id=null;
    private Long userid=null;
    private Long timecreated=null;
    private Long timemodified=null;
    private Long grader=null;
    private String grade=null;
    private Boolean locked;
    private Boolean mailed;

    public MoodleModAssignGrade() {}
    public MoodleModAssignGrade(Long id) { this.id=id; }

    public void setId(Long id) { this.id=id; }
    public void setUserId(Long userid) { this.userid=userid; }
    public void setTimeCreated(Long timecreated) { this.timecreated=timecreated; }
    public void setTimeModified(Long timemodified) { this.timemodified=timemodified; }
    public void setGrader(Long grader) { this.grader=grader; }
    public void setGrade(String grade) { this.grade=grade; }
    public void setLocked(Boolean locked) { this.locked=locked; }
    public void setMailed(Boolean mailed) { this.mailed=mailed; }

    public Long getId() { return id; }
    public Long getUserId() { return userid; }
    public Long getTimeCreated() { return timecreated; }
    public Long getTimeModified() { return timemodified; }
    public Long getGrader() { return grader; }
    public String getGrade() { return grade; }
    public boolean getLocked() { return locked; }
    public boolean getMailed() { return mailed; }

    public void setField(String field, String value) {
        if (field!=null && !field.isEmpty()) {
            if (field.equals("id")) setId(Long.parseLong(value));
            if (field.equals("userid")) setUserId(Long.parseLong(value));
            if (field.equals("timecreated")) setTimeCreated(Long.parseLong(value));
            if (field.equals("timemodified")) setTimeModified(Long.parseLong(value));
            if (field.equals("grader")) setGrader(Long.parseLong(value));
            if (field.equals("grade")) setGrade(value);
            if (field.equals("locked")) setLocked(value.equals("1"));
            if (field.equals("mailed")) setMailed(value.equals("1"));
        }
    }
}
