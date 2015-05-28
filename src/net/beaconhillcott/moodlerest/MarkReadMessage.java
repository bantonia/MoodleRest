/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.Serializable;

/**
 *
 * @author Bill Antonia
 */
class MarkReadMessage extends MoodleWarnings implements Serializable {
  private Long messageId;

  public MarkReadMessage() {
  }

  public MarkReadMessage(Long messageId) {
    this.messageId = messageId;
  }

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }
  
}
