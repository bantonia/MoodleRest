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
        data.append("&").append(URLEncoder.encode("stringparams["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getName(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("stringparams["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getValue(), MoodleServices.ENCODING.toString()));
      }
    }
    data.trimToSize();
    NodeList elements = MoodleCallRestWebService.call(data.toString());
    // To be completed
    return null;
  }
  
}
