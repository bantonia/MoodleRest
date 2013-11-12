/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.util.ArrayList;

/**
 *
 * @author root
 */
public class CourseEnrolledUserCapability {

  private Long courseId=null;
  private ArrayList<Capability> capabilities=null;
  
  public CourseEnrolledUserCapability() {
    this.courseId=new Long(0);
    this.capabilities=new ArrayList<Capability>();
  }

  public CourseEnrolledUserCapability(Long courseId) {
    this.courseId=courseId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public ArrayList<Capability> getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(ArrayList<Capability> capabilities) {
    this.capabilities = capabilities;
  }
  
  public void addCapability(Capability capability) {
    this.capabilities.add(capability);
  }
}
