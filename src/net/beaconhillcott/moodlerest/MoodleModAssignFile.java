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
public class MoodleModAssignFile {

    private Long id=null;
    private Long numfiles=0L;

    public MoodleModAssignFile() {}

    public MoodleModAssignFile(Long id) { this.id=id; }
    public MoodleModAssignFile(Long id, Long numfiles) { this.id=id; this.numfiles=numfiles; }

    public void setId(Long id) { this.id=id; }
    public void setNumFiles(Long numfiles) { this.numfiles=numfiles; }
    public Long getId() { return id; }
    public Long getNumFiles() { return numfiles; }
    public void incNumFiles() { numfiles++; }

    public void setField(String field, String value) {
        if (field!=null && !field.isEmpty()) {
            if (field.equals("id")) setId(Long.parseLong(value));
            if (field.equals("numfiles")) setNumFiles(Long.parseLong(value));
        }
    }
}
