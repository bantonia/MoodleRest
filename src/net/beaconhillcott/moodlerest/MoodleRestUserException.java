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
public class MoodleRestUserException extends MoodleRestException {

    /**
     *
     */
    public static final String TOKEN_NULL="Token cannot be null";
    /**
     *
     */
    public static final String USERNAME_NULL="Username cannot be null";
    /**
     *
     */
    public static final String PASSWORD_NULL="Password cannot be null";
    /**
     *
     */
    public static final String FIRSTNAME_NULL="Firstname cannot be null";
    /**
     *
     */
    public static final String LASTNAME_NULL="Lastname cannot be null";
    /**
     *
     */
    public static final String EMAIL_NULL="Email cannot be null";
    /**
     *
     */
    public static final String URL_NULL="URL cannot be null";
    /**
     *
     */
    public static final String USER_NULL="User cannot be null";
    /**
     *
     */
    public static final String INVALID_USERID="Bad user id";
    /**
     *
     */
    public static final String INVALID_USER="Invalid user";
    /**
     *
     */
    public static final String NO_LEGACY_CALL="No legacy call";

    MoodleRestUserException() {}

    MoodleRestUserException(String msg) {
        super(msg);
    }

}
