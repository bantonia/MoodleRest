/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class MoodleRestCompletion implements Serializable {
  
  public MoodleCompletionStatuses getActivitiesCompletionStatus(Long courseId, Integer userId) throws MoodleRestCompletionException , UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COMPLETION_GET_ACTIVITIES_COMPLETION_STATUS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCompletionException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (courseId==null) throw new MoodleRestCompletionException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseId, MoodleServices.ENCODING.toString()));
    if (userId==null) throw new MoodleRestCompletionException(); else data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleCompletionStatus> statuses=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleCompletionStatus status=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("statuses")) {
        if (nodeName.equals("cmid")) {
          if (statuses==null) {
            statuses=new ArrayList<MoodleCompletionStatus>();
          }
          status=new MoodleCompletionStatus();
          statuses.add(status);
          status.setCmId(Long.parseLong(content));
        } else {
          status.setField(nodeName, content);
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
    MoodleCompletionStatuses statusesWithWarnings=new MoodleCompletionStatuses();
    statusesWithWarnings.setComments(statuses);
    statusesWithWarnings.setWarnings(warn);
    return statusesWithWarnings;
  }
}
