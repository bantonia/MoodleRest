/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class MoodleRetrievedMessages extends MoodleWarnings implements Serializable {
  ArrayList<MoodleRetrievedMessage> messages=null;

  public MoodleRetrievedMessages() {}

  public ArrayList<MoodleRetrievedMessage> getMessages() {
    return messages;
  }

  public void setMessages(ArrayList<MoodleRetrievedMessage> messages) {
    this.messages = messages;
  }
  
  public void addMessage(MoodleRetrievedMessage message) {
    if (messages==null) messages=new ArrayList<MoodleRetrievedMessage>();
    messages.add(message);
  }
}
