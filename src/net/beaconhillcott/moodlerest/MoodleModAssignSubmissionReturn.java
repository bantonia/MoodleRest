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
import java.io.Serializable;

/**
 *
 * @author Bill Antonia
 */
public class MoodleModAssignSubmissionReturn implements Serializable {

    private ArrayList<MoodleModAssignSubmissions> assignments=null;
    private ArrayList<MoodleModAssignWarning> warnings=null;

    public MoodleModAssignSubmissionReturn() {}
    
    public void addAssignment(MoodleModAssignSubmissions assignment) {
        if (assignments==null)
            assignments=new ArrayList();
        assignments.add(assignment);
    }
    
    public void addWarning(MoodleModAssignWarning warning) {
        if (warnings==null)
            warnings=new ArrayList();
        warnings.add(warning);
    }
    
    public MoodleModAssignSubmissions[] getAssignments() {
        return assignments==null?null:(MoodleModAssignSubmissions[]) (assignments.toArray());
    }
    
    public MoodleModAssignWarning[] getWarnings() {
        return warnings==null?null:(MoodleModAssignWarning[]) (warnings.toArray());
    }
}
