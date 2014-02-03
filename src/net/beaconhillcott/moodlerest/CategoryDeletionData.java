/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

/**
 *
 * @author root
 */
public class CategoryDeletionData {
  private Long id=null;
  private Long newParent=null;
  private Boolean recurse=null;

  public CategoryDeletionData() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getNewParent() {
    return newParent;
  }

  public void setNewParent(Long newParent) {
    this.newParent = newParent;
  }

  public Boolean isRecurse() {
    return recurse;
  }

  public void setRecurse(Boolean recurse) {
    this.recurse = recurse;
  }
  
}
