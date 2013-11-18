/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
    ArrayList<GradeArea> areas=null;
    GradeArea gradeArea=null;
    GradeArea.GradeDefinition definition=null;
    GradeArea.GradeDefinition.Rubric.RubricCriteria rubricCriteria=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("areas")) {
        if (nodeName.equals("cmid")) {
          if (areas==null) {
            areas=new ArrayList<GradeArea>();
          }
          if (gradeArea==null) {
            gradeArea=new GradeArea();
            areas.add(gradeArea);
            gradeArea.setCmid(Long.parseLong(content));
          }
        } else {
          gradeArea.setFieldValue(nodeName, content);
        }
      } else {
        if (parent.equals("definitions")) {
          if (nodeName.equals("id")) {
            definition = gradeArea.newGradeDefinition();
            definition.setId(Long.parseLong(content));
          } else {
            definition.setFieldValue(nodeName, content);
          }
        } else {
          if (parent.equals("rubric_criteria")) {
            if (nodeName.equals("id")) {
              rubricCriteria = definition.getRubric().newRubricCriteria();
              rubricCriteria.setId(Long.parseLong(content));
            } else {
              rubricCriteria.setFieldValue(nodeName, content);
            }
            
          }
        }
      }
    }
    return null;
  }
}
