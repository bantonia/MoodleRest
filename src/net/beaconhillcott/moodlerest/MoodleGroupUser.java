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
 * <p>Class to store the details of the connection between a user and a Moodle group.</p>
 *
 * @see MoodleRestGroup
 * @author Bill Antonia
 */
public class MoodleGroupUser implements Serializable {

    private Long groupid=null;
    private Long userid=null;

    /**
     * <p>Constructor for bean requirements</p>
     */
    public MoodleGroupUser() {}

    /**
     * <p>Constructor to create a MoodleGroupUser object given the group and user ids.</p>
     *
     * @param groupid Long
     * @param userid Long
     */
    public MoodleGroupUser(Long groupid, Long userid) {
        this.groupid=groupid;
        this.userid=userid;
    }

    /**
     * <p>Method to set the groupid attribute of a MoodleGroupUser object.</p>
     *
     * @param groupid Long
     */
    public void setGroupId(Long groupid) {
        this.groupid=groupid;
    }

    /**
     * <p>Method to set the userid attribute of a MoodleGroupUser object.</p>
     *
     * @param userid Long
     */
    public void setUserId(Long userid) {
        this.userid=userid;
    }

    /**
     * <p>Method to get the groupid attribute of a MoodleGroupUser object.</p>
     *
     * @return groupid Long
     */
    public Long getGroupId() {
        return groupid;
    }

    /**
     * <p>Method to get the userid attribute of a MoodleGroupUser object.</p>
     * 
     * @return userid Long
     */
    public Long getUserId() {
        return userid;
    }
}
