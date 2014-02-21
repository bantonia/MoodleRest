/*
 *  Copyright (C) 2012 Bill Antonia
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.beaconhillcott.moodlerest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.w3c.dom.NodeList;
import java.io.Serializable;

/**
 * <p>Class to call the Moodle REST file web services.</p>
 * 
 * @author Bill Antonia
 */
public class MoodleRestFile implements Serializable {
  
    /**
     * <p>Method to return the details of files attached to a context.</p>
     * 
     * @param params MoodleFileParent
     * @return MoodleFileGetFiles
     * @throws MoodleRestFileException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleFileGetFiles getFiles(MoodleFileParent params) throws MoodleRestFileException, UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_FILE_GET_FILES.toString():MoodleServices.CORE_FILES_GET_FILES.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestFileException(MoodleRestException.AUTH_NULL);
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (params.contextid!=null) data.append("&").append(URLEncoder.encode("contextid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.contextid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": contextid");
    if (params.component!=null) data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.component, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": component");
    if (params.filearea!=null) data.append("&").append(URLEncoder.encode("filearea", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filearea, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filearea");
    if (params.itemid!=null) data.append("&").append(URLEncoder.encode("itemid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.itemid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": itemid");
    if (params.filepath!=null) data.append("&").append(URLEncoder.encode("filepath", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filepath, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filepath");
    if (params.filename!=null) data.append("&").append(URLEncoder.encode("filename", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filename, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filename");
    if (params.contextid==-1L) {
      if (params.modified!=null) data.append("&").append(URLEncoder.encode("modified", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.modified, MoodleServices.ENCODING.toString()));
      if (params.contextlevel!=null) data.append("&").append(URLEncoder.encode("modified", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.contextlevel, MoodleServices.ENCODING.toString()));
      if (params.instanceid!=null) data.append("&").append(URLEncoder.encode("instanceid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.instanceid, MoodleServices.ENCODING.toString()));
    }
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    MoodleFileGetFiles result=new MoodleFileGetFiles();
    MoodleFileParent fileParent=null;
    MoodleFileFile fileFile=null;
    for (int j=0;j<elements.getLength();j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
      if (parent.equals("KEY"))
        parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("parents") && nodeName.equals("contextid")) {
        if (fileParent!=null) {
          result.addParent(fileParent);
        }
        fileParent=new MoodleFileParent();
        fileParent.setMoodleFileParentField(nodeName, content);
      } else {
        if (parent.equals("parents")) {
          if (fileParent==null)
            throw new MoodleRestFileException();
          fileParent.setMoodleFileParentField(nodeName, content);
        } else {
          if (parent.equals("files") && nodeName.equals("contextid")) {
            if (fileFile!=null) {
              result.addFile(fileFile);
            }
            fileFile=new MoodleFileFile();
            fileFile.setMoodleFileFileField(nodeName, content);
          } else {
            if (fileFile==null)
              throw new MoodleRestFileException();
            fileFile.setMoodleFileFileField(nodeName, content);
          }
        }
      }
    }
    if (fileParent!=null)
      result.addParent(fileParent);
    if (fileFile!=null)
      result.addFile(fileFile);
    return result;
  }

  public MoodleFileGetFiles __getFiles(String url, String token, MoodleFileParent params) throws MoodleRestFileException, UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_FILE_GET_FILES.toString():MoodleServices.CORE_FILES_GET_FILES.toString();
    data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (params.contextid!=null) data.append("&").append(URLEncoder.encode("contextid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.contextid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": contextid");
    if (params.component!=null) data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.component, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": component");
    if (params.filearea!=null) data.append("&").append(URLEncoder.encode("filearea", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filearea, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filearea");
    if (params.itemid!=null) data.append("&").append(URLEncoder.encode("itemid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.itemid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": itemid");
    if (params.filepath!=null) data.append("&").append(URLEncoder.encode("filepath", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filepath, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filepath");
    if (params.filename!=null) data.append("&").append(URLEncoder.encode("filename", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filename, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filename");
    NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
    MoodleFileGetFiles result=new MoodleFileGetFiles();
    MoodleFileParent fileParent=null;
    MoodleFileFile fileFile=null;
    for (int j=0;j<elements.getLength();j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
      if (parent.equals("KEY"))
        parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("parents") && nodeName.equals("contextid")) {
        if (fileParent!=null) {
          result.addParent(fileParent);
        }
        fileParent=new MoodleFileParent();
        fileParent.setMoodleFileParentField(nodeName, content);
      } else {
        if (parent.equals("parents")) {
          if (fileParent==null)
            throw new MoodleRestFileException();
          fileParent.setMoodleFileParentField(nodeName, content);
        } else {
          if (parent.equals("files") && nodeName.equals("contextid")) {
            if (fileFile!=null) {
              result.addFile(fileFile);
            }
            fileFile=new MoodleFileFile();
            fileFile.setMoodleFileFileField(nodeName, content);
          } else {
            if (fileFile==null)
              throw new MoodleRestFileException();
            fileFile.setMoodleFileFileField(nodeName, content);
          }
        }
      }
    }
    if (fileParent!=null)
      result.addParent(fileParent);
    if (fileFile!=null)
      result.addFile(fileFile);
    return result;
  }
  
  /**
   * <p>Method to upload a file and attach to a context.</p>
   * @param params MoodleFileContent
   * @return MoodleFileFile
   * @throws MoodleRestFileException
   * @throws UnsupportedEncodingException
   * @throws MoodleRestException
   */
  public static MoodleFileFile upload(MoodleFileContent params) throws MoodleRestFileException, UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_FILE_UPLOAD.toString():MoodleServices.CORE_FILES_UPLOAD.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestFileException(MoodleRestException.AUTH_NULL);
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (params.contextid!=null) data.append("&").append(URLEncoder.encode("contextid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.contextid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": contextid");
    if (params.component!=null) data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.component, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": component");
    if (params.filearea!=null) data.append("&").append(URLEncoder.encode("filearea", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filearea, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filearea");
    if (params.itemid!=null) data.append("&").append(URLEncoder.encode("itemid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.itemid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": itemid");
    if (params.filepath!=null) data.append("&").append(URLEncoder.encode("filepath", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filepath, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filepath");
    if (params.filename!=null) data.append("&").append(URLEncoder.encode("filename", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filename, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filename");
    if (params.filecontent!=null) data.append("&").append(URLEncoder.encode("filecontent", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filecontent, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filecontent");
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    MoodleFileFile fileFile=null;
    for (int j=0;j<elements.getLength();j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
      if (parent.equals("KEY"))
        parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("RESPONSE") && nodeName.equals("contextid")) {
        fileFile=new MoodleFileFile();
        fileFile.setMoodleFileFileField(nodeName, content);
      } else {
        if (fileFile==null)
            throw new MoodleRestFileException();
        fileFile.setMoodleFileFileField(nodeName, content);
      }
    }
    return fileFile;
  }

  public MoodleFileFile __upload(String url, String token, MoodleFileContent params) throws MoodleRestFileException, UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_FILE_UPLOAD.toString():MoodleServices.CORE_FILES_UPLOAD.toString();
    data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (params.contextid!=null) data.append("&").append(URLEncoder.encode("contextid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.contextid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": contextid");
    if (params.component!=null) data.append("&").append(URLEncoder.encode("component", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.component, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": component");
    if (params.filearea!=null) data.append("&").append(URLEncoder.encode("filearea", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filearea, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filearea");
    if (params.itemid!=null) data.append("&").append(URLEncoder.encode("itemid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+params.itemid, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": itemid");
    if (params.filepath!=null) data.append("&").append(URLEncoder.encode("filepath", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filepath, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filepath");
    if (params.filename!=null) data.append("&").append(URLEncoder.encode("filename", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filename, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filename");
    if (params.filecontent!=null) data.append("&").append(URLEncoder.encode("filecontent", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(params.filecontent, MoodleServices.ENCODING.toString())); else throw new MoodleRestFileException(MoodleRestException.REQUIRED_PARAMETER+": filecontent");
    NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
    MoodleFileFile fileFile=null;
    for (int j=0;j<elements.getLength();j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
      if (parent.equals("KEY"))
        parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("RESPONSE") && nodeName.equals("contextid")) {
        fileFile=new MoodleFileFile();
        fileFile.setMoodleFileFileField(nodeName, content);
      } else {
        if (fileFile==null)
            throw new MoodleRestFileException();
        fileFile.setMoodleFileFileField(nodeName, content);
      }
    }
    return fileFile;
  }
  
}
