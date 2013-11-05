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
 *
 * @author Bill Antonia
 */
public class MoodleModAssignOnlineText implements Serializable {

    private Long id=null;
    private String onlinetext=null;

    public MoodleModAssignOnlineText() {}
    public MoodleModAssignOnlineText(Long id) { this.id=id; }
    public MoodleModAssignOnlineText(Long id, String onlinetext) { this.id=id; this.onlinetext=onlinetext; }

    public void setId( Long id) { this.id=id; }
    public void setOnlineText(String onlinetext) { this.onlinetext=onlinetext; }
    public Long getId() { return id; }
    public String getOnlineText() { return onlinetext; }

    public void setField(String field, String value) {
        if (field!=null && !field.isEmpty()) {
            if (field.equals("id")) setId(Long.parseLong(value));
            if (field.equals("onlinetext")) setOnlineText(value);
        }
    }
}
