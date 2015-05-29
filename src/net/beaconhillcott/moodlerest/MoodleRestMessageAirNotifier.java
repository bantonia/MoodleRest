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
public class MoodleRestMessageAirNotifier implements Serializable {

  public MoodleRestMessageAirNotifier() {
  }
  
  public static MoodleUserLists areNotificationPreferencesConfigured(Long[] userIds) throws UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MESSAGE_AIRNOTIFIER_ARE_NOTIFICATION_PREFERENCES_CONFIGURED.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestMessageAirNotifierException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (userIds==null)
      throw new MoodleRestMessageAirNotifierException();
    else {
      if (userIds.length>0) {
        for (int i=0; i<userIds.length; i++)
          data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userIds[i], MoodleServices.ENCODING.toString()));
      } else {
        throw new MoodleRestMessageAirNotifierException();
      }
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<UserList> userLists=null;
    ArrayList<MoodleWarning> warn=null;
    UserList userList=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("users")) {
        if (nodeName.equals("userid")) {
          if (userList==null) {
            userLists=new ArrayList<UserList>();
          }
          userList=new UserList();
          userLists.add(userList);
          userList.setUserId(Long.parseLong(content));
        } else {
          if (nodeName.equals("configured"))
            userList.setConfigured(!content.equals("0"));
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
    MoodleUserLists usersWithWarnings=new MoodleUserLists();
    usersWithWarnings.setUsers(userLists);
    usersWithWarnings.setWarnings(warn);
    return usersWithWarnings;
  }
  
  public static Boolean isSystemConfigured() throws UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MESSAGE_AIRNOTIFIER_IS_SYSTEM_CONFIGURED.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    return !(elements.item(0).getTextContent().equals("0"));
  }
}
