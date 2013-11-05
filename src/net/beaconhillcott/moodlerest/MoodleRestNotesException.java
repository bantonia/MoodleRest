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
public class MoodleRestNotesException extends MoodleRestException {
  
  MoodleRestNotesException() {}

  /**
   *
   */
  public static final String  NOTES_NULL="Note cannot be null";
  /**
   *
   */
  public static final String  USERID_NOT_SET="User id not set";
  /**
   *
   */
  public static final String  PUBLISHSTATE_NULL="Publish state is null";
  /**
   *
   */
  public static final String  COURSEID_NOT_SET="Course id not set";
  /**
   *
   */
  public static final String  TEXT_NULL="Text is null";
  /**
   *
   */
  public static final String  FORMAT_INCORRECT="Format is incorrect, \"text\" or \"html\" only";
  /**
   *
   */
  public static final String  CLIENTNOTEID_NOT_SET="Client note id not set";
  /**
   * 
   */
  public static final String  NO_LEGACY_CALL="No legacy call";
  
    public MoodleRestNotesException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
