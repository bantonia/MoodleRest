/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import net.beaconhillcott.moodlerest.MoodleGradeArea.GradeDefinition.Guide;
import net.beaconhillcott.moodlerest.MoodleGradeArea.GradeDefinition.Guide.GuideComment;
import net.beaconhillcott.moodlerest.MoodleGradeArea.GradeDefinition.Guide.GuideCriteria;
import net.beaconhillcott.moodlerest.MoodleGradeArea.GradeDefinition.Rubric.RubricCriteria.Level;
import org.w3c.dom.NodeList;

/**
 *
 * @author root
 */
public class MoodleRestGrade implements Serializable {
  
  public static MoodleGradeArea[] getGradeDefinitions(Long[] cmIds, String areaName) throws MoodleRestGradeException, UnsupportedEncodingException, MoodleRestException {
    return getGradeDefinitions(cmIds, areaName, true, null);
  }
  
  public static MoodleGradeArea[] getGradeDefinitions(Long[] cmIds, String areaName, boolean activeOnly) throws MoodleRestGradeException, UnsupportedEncodingException, MoodleRestException {
    return getGradeDefinitions(cmIds, areaName, activeOnly, null);
  }
  
  public static MoodleGradeArea[] getGradeDefinitions(Long[] cmIds, String areaName, boolean activeOnly, MoodleWarning[] warnings) throws MoodleRestGradeException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestGradeException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_GRADING_GET_DEFINITIONS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestGradeException();
    }
    else {
      data.append(MoodleCallRestWebService.getAuth());
    }
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
    ArrayList<MoodleGradeArea> areas=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleGradeArea gradeArea=null;
    MoodleGradeArea.GradeDefinition definition=null;
    MoodleGradeArea.GradeDefinition.Rubric.RubricCriteria rubricCriteria=null;
    Level level=null;
    Guide guide=null;
    GuideCriteria guideCriteria=null;
    GuideComment guideComment=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("areas")) {
        if (nodeName.equals("cmid")) {
          if (areas==null) {
            areas=new ArrayList<MoodleGradeArea>();
          }
          gradeArea=new MoodleGradeArea();
          areas.add(gradeArea);
          gradeArea.setCmid(Long.parseLong(content));
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
          } else {
            if (parent.equals("levels")) {
              if (nodeName.equals("id")) {
                level = rubricCriteria.newLevel();
                level.setId(Long.parseLong(content));
              } else {
                level.setFieldValue(nodeName, content);
              }
            } else {
              if (parent.equals("guide_criteria")) {
                if (nodeName.equals("id")) {
                  if (guide==null) {
                    guide = definition.newGuide();
                  }
                  guideCriteria = guide.newGuideCriteria();
                  guideCriteria.setId(Long.parseLong(content));
                } else {
                  guideCriteria.setFieldValue(nodeName, content);
                }
              } else {
                if (parent.equals("guide_comment")) {
                  if (nodeName.equals("id")) {
                    if (guide==null) {
                      guide = definition.newGuide();
                    }
                    guideComment = guide.newGuideComment();
                    guideComment.setId(Long.parseLong(content));
                  } else {
                    guideComment.setFieldValue(nodeName, content);
                  }
                } else {
                  if (parent.equals("warnings")) {
                    if (nodeName.equals("item")) {
                      if (warn==null) {
                        warn=new ArrayList<MoodleWarning>();
                      }
                      warning=new MoodleWarning();
                      warn.add(warning);
                      warning.setItem(content);
                    } else {
                      warning.setMoodleWarningField(nodeName, content);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    if (warn!=null) {
      warnings=new MoodleWarning[warn.size()];
      warnings=warn.toArray(warnings);
    }
    MoodleGradeArea[] results=null;
    if (areas!=null) {
      results=new MoodleGradeArea[areas.size()];
      results=areas.toArray(results);
    }
    return results;
  }
}
