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
 * <p>Class to relate a user to a role within a particular context.</p>
 * <p>Problem here as of 2011-02-13 there is no way of obtaining the contextid or roleid programatically through the standard web services interface.</p>
 * @see MoodleRestEnrol
 * @author Bill Antonia
 */
public class MoodleEnrolUser {

    private Long roleid=null;
    private Long userid=null;
    private Long contextid=null;

    /**
     * <p>Constructor for bean requirements.</p>
     */
    public MoodleEnrolUser() {}

    /**
     * <p>Constructor to create a MoodleEnrolUser object with known roleid, userid and contextid.</p>
     *
     * @param roleid Long
     * @param userid Long
     * @param contextid Long
     */
    public MoodleEnrolUser(Long roleid, Long userid, Long contextid) {
        this.roleid=roleid;
        this.userid=userid;
        this.contextid=contextid;
    }

    /**
     * <p>Method to set the roleid attribute of a MoodleEnrolUser object.</p>
     * <p>The roleid currently cannot be retrieved through the standard web services interfaces.</p>
     *
     * @param roleid Long
     */
    public void setRoleId(Long roleid) {
        this.roleid=roleid;
    }

    /**
     * <p>Method to set the userid attribute of a MoodleEnrolUser object.</p>
     * @param userid Long
     */
    public void setUserId(Long userid) {
        this.userid=userid;
    }

    /**
     * <p>Method to set the contextid attribute of a MoodleEnrolUser object.</p>
     * <p>The contextid currently cannot be retrieved through the standard web services interfaces.</p>
     *
     * @param contextid Long
     */
    public void setContextId(Long contextid) {
        this.contextid=contextid;
    }

    /**
     * <p>Method to get the roleid attribute of a MoodleEnrolUser object.</p>
     * <p>The roleid currently cannot be retrieved through the standard web services interfaces.</p>
     * 
     * @return roleid Long
     */
    public Long getRoleId() {
        return roleid;
    }

    /**
     * <p>Method to get the userid attribute of a MoodleEnrolUser object.</p>
     *
     * @return userid Long
     */
    public Long getUserId() {
        return userid;
    }

    /**
     * <p>Method to get the contextid attribute of a MoodleEnrolUser object.</p>
     * <p>The contextid currently cannot be retrieved through the standard web services interfaces.</p>
     *
     * @return contextid Long
     */
    public Long getContextId() {
        return contextid;
    }
}
