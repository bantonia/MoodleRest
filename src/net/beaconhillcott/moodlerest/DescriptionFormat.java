/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class DescriptionFormat implements Serializable {
  
  public static final int MOODLE=0;
  public static final int HTML=1;
  public static final int PLAIN=2;
  public static final int MARKDOWN=4;
  
}
