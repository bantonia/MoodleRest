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
public class MoodleRestStrings implements Serializable {
  
  public static String getString(String stringId, String component, String lang, OptionParameter[] options) throws MoodleRestStringsException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestStringsException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_GET_STRING.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestStringsException();
    }
    else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("stringid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+stringId, MoodleServices.ENCODING.toString()));
    if (component!=null) data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+component, MoodleServices.ENCODING.toString()));
    if (lang!=null) data.append("&").append(URLEncoder.encode("lang", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+lang, MoodleServices.ENCODING.toString()));
    if (options!=null) {
      for (int i=0; i<options.length; i++) {
        if (options.length>1) data.append("&").append(URLEncoder.encode("stringparams["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getName(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("stringparams["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getValue(), MoodleServices.ENCODING.toString()));
      }
    }
    data.trimToSize();
    NodeList elements = MoodleCallRestWebService.call(data.toString());
    return elements.item(0).getTextContent();
  }
  
  public static ComponentString[] getComponentStrings() throws MoodleRestStringsException, UnsupportedEncodingException, MoodleRestException {
    return getComponentStrings("moodle", null);
  }
  
  public static ComponentString[] getComponentStrings(String component) throws MoodleRestStringsException, UnsupportedEncodingException, MoodleRestException {
    return getComponentStrings(component, null);
  }
  
  public static ComponentString[] getComponentStrings(String component, String lang) throws MoodleRestStringsException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestStringsException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_GET_COMPONENT_STRINGS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestStringsException();
    }
    else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+component, MoodleServices.ENCODING.toString()));
    if (lang!=null) data.append("&").append(URLEncoder.encode("lang", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+lang, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements = MoodleCallRestWebService.call(data.toString());
    ArrayList<ComponentString> componentStrings=null;
    ComponentString componentString=null;
    for (int j=0; j<elements.getLength(); j++) {
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (nodeName.equals("stringid")) {
        if (componentStrings==null) {
          componentStrings=new ArrayList<ComponentString>();
        }
        componentString=new ComponentString(content);
        componentStrings.add(componentString);
      } else {
        componentString.setString(content);
      }
    }
    ComponentString[] results=null;
    if (componentStrings!=null) {
      results=new ComponentString[componentStrings.size()];
      results=componentStrings.toArray(results);
    }
    return results;
  }
  
  public static ComponentString[] getStrings(ComponentString[] params) throws MoodleRestStringsException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestStringsException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_GET_STRINGS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestStringsException();
    }
    else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<params.length; i++) {
      data.append("&").append(URLEncoder.encode("strings["+i+"][stringid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params[i].getStringId(), MoodleServices.ENCODING.toString()));
      if (params[i].getComponent()!=null) data.append("&").append(URLEncoder.encode("strings["+i+"][component]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params[i].getComponent(), MoodleServices.ENCODING.toString()));
      if (params[i].getLang()!=null) data.append("&").append(URLEncoder.encode("strings["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params[i].getLang(), MoodleServices.ENCODING.toString()));
      if (params[i].getStringParams()!=null) {
        if (params[i].getStringParams().length==1) {
          data.append("&").append(URLEncoder.encode("strings["+i+"][stringparams][0][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params[i].getStringParams()[0].getValue(), MoodleServices.ENCODING.toString()));
        } else {
          for (int j=0; j<params[i].getStringParams().length; j++) {
            data.append("&").append(URLEncoder.encode("strings["+i+"][stringparams]["+j+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params[i].getStringParams()[j].getName(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("strings["+i+"][stringparams]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params[i].getStringParams()[j].getValue(), MoodleServices.ENCODING.toString()));
          }
        }
      }
    }
    data.trimToSize();
    NodeList elements = MoodleCallRestWebService.call(data.toString());
    ArrayList<ComponentString> componentStrings=null;
    ComponentString componentString=null;
    for (int j=0; j<elements.getLength(); j++) {
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (nodeName.equals("stringid")) {
        if (componentStrings==null) {
          componentStrings=new ArrayList<ComponentString>();
        }
        componentString=new ComponentString(content);
        componentStrings.add(componentString);
      } else {
        componentString.setString(content);
      }
    }
    ComponentString[] results=null;
    if (componentStrings!=null) {
      results=new ComponentString[componentStrings.size()];
      results=componentStrings.toArray(results);
    }
    return results;
  }
  
}
