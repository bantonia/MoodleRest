/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

/**
 *
 * @author root
 */
public class MoodleRestModForum {
  
  public static ModCourseForum[] getForumsByCourse(Long[] courseIds) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_FORUM_GET_FORUMS_BY_COURSES.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<courseIds.length; i++) {
      data.append("&").append(URLEncoder.encode("courseids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseIds[i], MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<ModCourseForum> forums=null;
    ModCourseForum forum=null;
    for (int j=0; j<elements.getLength(); j++) {
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (nodeName.equals("id")) {
          if (forums==null) {
            forums=new ArrayList<ModCourseForum>();
          }
          forum=new ModCourseForum(Long.parseLong(content));
          forums.add(forum);
        } else {
          forum.setFieldValue(nodeName, content);
        }
    }
    ModCourseForum[] results=null;
    if (forums!=null) {
      results=new ModCourseForum[forums.size()];
      results=forums.toArray(results);
    }
    return results;
  }
  
  public static ModForumDiscussion[] getForumDiscussions(Long[] forumIds) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_FORUM_GET_FORUM_DISCUSSIONS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<forumIds.length; i++) {
      data.append("&").append(URLEncoder.encode("forumids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+forumIds[i], MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<ModForumDiscussion> discussions=null;
    ModForumDiscussion discussion=null;
    for (int j=0; j<elements.getLength(); j++) {
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (nodeName.equals("id")) {
          if (discussions==null) {
            discussions=new ArrayList<ModForumDiscussion>();
          }
          discussion=new ModForumDiscussion(Long.parseLong(content));
          discussions.add(discussion);
        } else {
          discussion.setFieldValue(nodeName, content);
        }
    }
    ModForumDiscussion[] results=null;
    if (discussions!=null) {
      results=new ModForumDiscussion[discussions.size()];
      results=discussions.toArray(results);
    }
    return results;
  }
}
