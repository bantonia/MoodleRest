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

import java.util.*;

/**
 *
 * @author root
 */
public class MoodleModAssignSubmissions {
    private Long assignmentid=null;
    private ArrayList<MoodleModAssignSubmission> submissions=null;

    public MoodleModAssignSubmissions() {}
    public MoodleModAssignSubmissions(Long assignmentid) { this.assignmentid=assignmentid; }

    public void setAssignmentId(Long assignmentid) { this.assignmentid=assignmentid; }
    public Long getAssignmentId() { return assignmentid; }

    public void addSubmission(MoodleModAssignSubmission submission) {
        if (submissions==null)
            submissions=new ArrayList();
        submissions.add(submission);
    }

    public MoodleModAssignSubmission[] getSubmissions() {
        return (MoodleModAssignSubmission[]) (submissions == null ? null : submissions.toArray());
    }
}
