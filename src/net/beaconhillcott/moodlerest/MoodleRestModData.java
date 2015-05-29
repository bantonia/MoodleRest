/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.w3c.dom.NodeList;
/**
 *
 * @author Bill Antonia
 */
public class MoodleRestModData {

  public MoodleRestModData() {
  }
  
  public MoodleDatabases getDatabasesCourses(Long[] courseIds) throws UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_DATA_GET_DATABASES_BY_COURSES.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestModDataException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (courseIds==null)
      throw new MoodleRestModDataException("courseIds cannot be null");
    else {
      if (courseIds.length>0) {
        for (int i=0; i<courseIds.length; i++)
          data.append("&").append(URLEncoder.encode("courseids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseIds[i], MoodleServices.ENCODING.toString()));
      } else {
        throw new MoodleRestModDataException("courseId array size must be larger than 0");
      }
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleDatabase> databases=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleDatabase database=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("databases")) {
        if (nodeName.equals("id")) {
          if (database==null) {
            databases=new ArrayList<MoodleDatabase>();
          }
          database=new MoodleDatabase();
          databases.add(database);
          database.setId(Long.parseLong(content));
        } else {
          database.setField(nodeName, content);
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
    MoodleDatabases databasesWithWarnings=new MoodleDatabases();
    databasesWithWarnings.setDatabases(databases);
    databasesWithWarnings.setWarnings(warn);
    return databasesWithWarnings;
  }
}
