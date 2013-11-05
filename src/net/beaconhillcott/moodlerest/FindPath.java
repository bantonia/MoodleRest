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

import java.io.InputStream;
import java.io.Serializable;

/**
 * Path to the Entity file to be injected into the XML stream returned from a webservice.
 * Required as Moodle does not provide these and they are needed for the process of parsing the XML stream.
 *
 * @author Bill Antonia (Class only), file downloaded from the Internet.
 */
public class FindPath implements Serializable {

    /**
     * Stream resource
     */
    protected InputStream resource;

    /**
     * Method to get the XML Entity resource file as a stream
     */
    protected FindPath() {
        resource = this.getClass().getClassLoader().getResourceAsStream("net/beaconhillcott/moodlerest/EntityInjection.xml");
    }
}
