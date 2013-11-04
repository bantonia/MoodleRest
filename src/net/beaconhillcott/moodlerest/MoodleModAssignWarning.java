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
public class MoodleModAssignWarning {

    private String element=null;
    private Long elementid=null;
    private Long messageid=null;
    private String message=null;

    public MoodleModAssignWarning() {}

    public void setElement(String element) { this.element=element; }
    public void setElementId(Long elementid) { this.elementid=elementid; }
    public void setMessageId(Long messageid) { this.messageid=messageid; }
    public void setMessage(String message) { this.message=message; }

    public String getElement() { return element; }
    public Long getElementId() { return elementid; }
    public Long getMessageId() { return messageid; }
    public String getMessage() { return message; }

    public void setField(String field, String value) {
        if (field!=null && !field.isEmpty()) {
            if (field.equals("element")) setElement(value);
            if (field.equals("elementid")) setElementId(Long.parseLong(value));
            if (field.equals("messageid")) setMessageId(Long.parseLong(value));
            if (field.equals("message")) setMessage(value);
        }
    }
}
