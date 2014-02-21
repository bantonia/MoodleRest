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
 * <p>Class to create an object which will hold the parent information of a file from a call to MoodleRestFile.getFiles.</p>
 *
 * @author Bill Antonia
 * @see MoodleRestFile
 */
public class MoodleFileParent implements Serializable {

    /**
     *
     */
    protected Long contextid=null;
    /**
     *
     */
    protected String component=null;
    /**
     *
     */
    protected String filearea=null;
    /**
     *
     */
    protected Long itemid=null;
    /**
     *
     */
    protected String filepath=null;
    /**
     *
     */
    protected String filename=null;
    
    protected Long modified=null;
    protected String contextlevel=null;
    protected Long instanceid=null;

    /**
     *
     */
    public MoodleFileParent() {}

    /**
     * <p>Method to set the contextid attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param contextid long
     * @see MoodleRestFile
     */
    public void setContextId(Long contextid) {
        this.contextid=contextid;
    }

    /**
     * <p>Method to set the component attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param component String
     * @see MoodleRestFile
     */
    public void setComponent(String component) {
        this.component=component;
    }

    /**
     * <p>Method to set the filearea attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param filearea String
     * @see MoodleRestFile
     */
    public void setFileArea(String filearea) {
        this.filearea=filearea;
    }

    /**
     * <p>Method to set the itemid attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param itemid long
     * @see MoodleRestFile
     */
    public void setItemId(Long itemid) {
        this.itemid=itemid;
    }

    /**
     * <p>Method to set the filepath attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param filepath String
     * @see MoodleRestFile
     */
    public void setFilePath(String filepath) {
        this.filepath=filepath;
    }

    /**
     * <p>Method to set the filename attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param filename String
     * @see MoodleRestFile
     */
    public void setFileName(String filename) {
        this.filename=filename;
    }

    /**
     * <p>Method to get the contextid attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @return long
     * @see MoodleRestFile
     */
    public Long getContextId() {
        return contextid;
    }

    /**
     * <p>Method to get the component attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @return String
     * @see MoodleRestFile
     */
    public String getComponent() {
        return component;
    }

    /**
     * <p>Method to get the filearea attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @return String
     * @see MoodleRestFile
     */
    public String getFileArea() {
        return filearea;
    }

    /**
     * <p>Method to get the itemid attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @return long
     * @see MoodleRestFile
     */
    public Long getItemId() {
        return itemid;
    }

    /**
     * <p>Method to get the filepath attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @return String
     * @see MoodleRestFile
     */
    public String getFilePath() {
        return filepath;
    }

    /**
     * <p>Method to get the filename attribute of a MoodleFileParent object from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @return String
     * @see MoodleRestFile
     */
    public String getFileName() {
        return filename;
    }

    /**
     * <p>Method to set an attribute of a MoodleFileParent object given its field name and value as strings from data returned by a call to MoodleRestFile.getfiles.</p>
     *
     * @param field String
     * @param value String
     * @see MoodleRestFile
     */
    public void setMoodleFileParentField(String field, String value) {
        if (field.equals("contextid") && !value.isEmpty()) setContextId(Long.valueOf(value));
        if (field.equals("component") && !value.isEmpty()) setComponent(value);
        if (field.equals("filearea") && !value.isEmpty()) setFileArea(value);
        if (field.equals("itemid") && !value.isEmpty()) setItemId(Long.valueOf(value));
        if (field.equals("filepath") && !value.isEmpty()) setFilePath(value);
        if (field.equals("filename") && !value.isEmpty()) setFileName(value);
    }
    
}
