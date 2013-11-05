/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
* @author MoodyProject Team
*
*/
public enum MoodleContactState implements Serializable {
        ONLINE, // the contact is online at the time of fetch
        OFFLINE, // the contact is offline at the time of fetch
        STRANGERS // the user in this contact is a stranger (not a contact list user)
                                // at the time of fetch
}
