/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.w3c.dom.NodeList;

/**
 *
 * @author root
 */
public class MoodleRestGrade implements Serializable {
  
  public static GradeArea[] getGradeDefinitions(Long[] cmIds, String areaName, boolean activeOnly) throws MoodleRestGradeException, UnsupportedEncodingException, MoodleRestException {
  if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGradeException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_GRADE_GET_DEFINITIONS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestGradeException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<cmIds.length; i++) {
      data.append("&").append(URLEncoder.encode("cmids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cmIds[i], MoodleServices.ENCODING.toString()));
    }
    data.append("&").append(URLEncoder.encode("areaname", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+areaName, MoodleServices.ENCODING.toString()));
    if (activeOnly) {
      data.append("&").append(URLEncoder.encode("activeonly", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(activeOnly?1:0), MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements = MoodleCallRestWebService.call(data.toString());
    
    return null;
  }
}
