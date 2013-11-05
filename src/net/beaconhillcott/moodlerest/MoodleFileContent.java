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
 * <p>Class to store the file content as a Base64 uuencoded string.</p>
 * <p>Note no encoding takes place, this is left up to the developer as it may be implementation dependent.</p>
 * <p>Extends MoodleFileFile.</p>
 *
 * @author Bill Antonia
 * @see MoodleFileFile
 */
public class MoodleFileContent extends MoodleFileFile implements Serializable {

    /**
     *
     */
    protected String filecontent=null;

    /**
     * <p>Constructor for bean compatability</p>
     */
    public MoodleFileContent() {}

    /**
     * <p>Method to set the file content as a Base64 uuencoded string.</p>
     * <p>Encoding is left to the developer as it may be implementation dependent. So must be encoded before being stored into the object.</p>
     *
     * @param filecontent String
     */
    public void setFileContent(String filecontent) {
        this.filecontent=filecontent;
    }

    /**
     * <p>Method to get the Base64 uuencoded string held in the object.</p>
     *
     * @return filecontent String
     */
    public String getFileContent() {
        return filecontent;
    }

    /**
     * <p>Method to set a field given its name and value as strings.</p>
     * 
     * @param field String
     * @param value String
     */
    public void setMoodleFileContentField(String field, String value) {
        setMoodleFileFileField(field, value);
        if (field.equals("filecontent") && !value.isEmpty()) filecontent=value;
    }
}
