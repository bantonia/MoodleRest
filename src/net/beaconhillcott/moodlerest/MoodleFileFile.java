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
 * <p>Class to store the file information results once it has been uploaded</p>
 * <p>Extends MoodleFileParent</p>
 *
 * @author Bill Antonia
 * @see MoodleFileParent
 */
public class MoodleFileFile extends MoodleFileParent implements Serializable {

    private Boolean isdir=false;
    private String url=null;

    /**
     * <p>Constructor for bean compatability</p>
     */
    public MoodleFileFile() {}

    /**
     * <p>Method to set the isdir attribute.</p>
     * <p>Only useful in construction of the object attributes by the call to the MoodleRestFile.upload webservice.</p>
     *
     * @param isdir boolean
     */
    public void setIsDir(Boolean isdir) {
        this.isdir=isdir;
    }

    /**
     * <p>Method to set the url attribute.</p>
     * <p>Only useful in construction of the object attributes by the call to the MoodleRestFile.upload webservice.</p>
     *
     * @param url String
     */
    public void setURL(String url) {
        this.url=url;
    }

    /**
     * <p>Method to test the isdir attribute.</p>
     *
     * @return boolean
     */
    public Boolean isDir() {
        return isdir;
    }
    
    /**
     * <p>Method to get the isdir attribute.</p>
     *
     * @return boolean
     */
    public Boolean getIsDir() {
        return isdir;
    }

    /**
     * <p>Method to get the url attribute.</p>
     *
     * @return String
     */
    public String getURL() {
        return url;
    }

    /**
     * <p>Method to set a field given its name and value as strings.</p>
     * <p>Only useful in construction of the object attributes by the call to the MoodleRestFile.upload webservice.</p>
     *
     * @param field String
     * @param value String
     */
    public void setMoodleFileFileField(String field, String value) {
        setMoodleFileParentField(field, value);
        if (field.equals("isdir") && !value.isEmpty()) setIsDir(Integer.parseInt(value)!=0);
        if (field.equals("url") && !value.isEmpty()) setURL(value);
    }
}
