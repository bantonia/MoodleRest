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
 * @author Bill Antonia
 */
public class MoodleRestComments implements Serializable {

  public MoodleRestComments() {
  }
  
  public MoodleComments getComments(String contextLevel, Long instanceId, String component, Integer itemId, String area) throws MoodleRestCommentsException , UnsupportedEncodingException, MoodleRestException {
    return getComments(contextLevel, instanceId, component, itemId, area, 0);
  }
  
  public MoodleComments getComments(String contextLevel, Long instanceId, String component, Integer itemId) throws MoodleRestCommentsException , UnsupportedEncodingException, MoodleRestException {
    return getComments(contextLevel, instanceId, component, itemId, "", 0);
  }
  
  public MoodleComments getComments(String contextLevel, Long instanceId, String component, Integer itemId, Integer page) throws MoodleRestCommentsException , UnsupportedEncodingException, MoodleRestException {
    return getComments(contextLevel, instanceId, component, itemId, "", page);
  }
  
  public MoodleComments getComments(String contextLevel, Long instanceId, String component, Integer itemId, String area, Integer page) throws MoodleRestCommentsException , UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COMMENT_GET_COMMENTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCommentsException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (contextLevel==null) throw new MoodleRestCommentsException(); else data.append("&").append(URLEncoder.encode("contextlevel", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+contextLevel, MoodleServices.ENCODING.toString()));
    if (instanceId==null) throw new MoodleRestCommentsException(); else data.append("&").append(URLEncoder.encode("instanceid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+instanceId, MoodleServices.ENCODING.toString()));
    if (component==null) throw new MoodleRestCommentsException(); else data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+component, MoodleServices.ENCODING.toString()));
    if (itemId==null) throw new MoodleRestCommentsException(); else data.append("&").append(URLEncoder.encode("itemid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+itemId, MoodleServices.ENCODING.toString()));
    if (area==null) area="";
    if (page==null) page=0;
    data.append("&").append(URLEncoder.encode("area", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+area, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("page", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+page, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleComment> comments=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleComment comment=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("comments")) {
        if (nodeName.equals("id")) {
          if (comments==null) {
            comments=new ArrayList<MoodleComment>();
          }
          comment=new MoodleComment();
          comments.add(comment);
          comment.setId(Long.parseLong(content));
        } else {
          comment.setField(nodeName, content);
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
    MoodleComments commentsWithWarnings=new MoodleComments();
    commentsWithWarnings.setComments(comments);
    commentsWithWarnings.setWarnings(warn);
    return commentsWithWarnings;
  }
}
