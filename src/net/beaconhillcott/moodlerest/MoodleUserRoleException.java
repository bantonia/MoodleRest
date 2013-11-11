/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class MoodleUserRoleException extends MoodleException implements Serializable {
  
  public static final String INVALID_ROLE="Invalid role";
    
    public MoodleUserRoleException() {}

    public MoodleUserRoleException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
