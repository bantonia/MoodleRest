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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NodeList;
import java.io.Serializable;

/**
 * <p>Class to call the Moodle REST WebServices.</p>
 * @author Bill Antonia
 */
public class MoodleRestWebService implements Serializable {
    
  //core_webservice_get_site_info
    /**
     * <p>Method to fetch information about the Moodle site.</p>
     * 
     * @return MoodleWebService
     * @throws MoodleRestWebServiceException
     * @throws MoodleRestException
     */
    public static MoodleWebService getSiteInfo() throws MoodleRestWebServiceException, MoodleRestException {
        MoodleWebService service=null;
        String functionCall = MoodleCallRestWebService.isLegacy() ? MoodleServices.MOODLE_WEBSERVICE_GET_SITEINFO.toString() : MoodleServices.CORE_WEBSERVICE_GET_SITE_INFO.toString();
        StringBuilder data = new StringBuilder();
        try {
            if (MoodleCallRestWebService.getAuth() == null) {
                throw new MoodleRestWebServiceException();
            } else {
                data.append(MoodleCallRestWebService.getAuth());
            }
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            Function function=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("#document") && nodeName.equals("sitename")) {
                    service=new MoodleWebService();
                    service.setSiteName(content);
                } else {
                    if (parent.equals("#document")) {
                      if (service==null)
                        throw new MoodleRestWebServiceException();
                      service.setFunctionField(nodeName, content);
                    } else {
                        if (parent.equals("functions") && nodeName.equals("name")) {
                            if (function!=null) {
                              if (service==null)
                                throw new MoodleRestWebServiceException();
                              service.addFunction(function);
                              function=new Function();
                              function.setName(content);
                            } else {
                                function=new Function();
                                function.setName(content);
                            }
                        } else {
                            if (parent.equals("functions")) {
                              if (function==null)
                                throw new MoodleRestWebServiceException();
                              function.setFunctionField(nodeName, content);
                            }
                        }
                    }
                }
            }
            if (function!=null) {
              if (service==null)
                throw new MoodleRestWebServiceException();
              service.addFunction(function);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }

    public MoodleWebService __getSiteInfo(String url, String token) throws MoodleRestWebServiceException, MoodleRestException {
        MoodleWebService service=null;
        String functionCall = MoodleCallRestWebService.isLegacy() ? MoodleServices.MOODLE_WEBSERVICE_GET_SITEINFO.toString() : MoodleServices.CORE_WEBSERVICE_GET_SITE_INFO.toString();
        StringBuilder data = new StringBuilder();
        try {
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            Function function=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("#document") && nodeName.equals("sitename")) {
                    service=new MoodleWebService();
                    service.setSiteName(content);
                } else {
                    if (parent.equals("#document")) {
                      if (service==null)
                        throw new MoodleRestWebServiceException();
                      service.setFunctionField(nodeName, content);
                    } else {
                        if (parent.equals("functions") && nodeName.equals("name")) {
                            if (function!=null) {
                              if (service==null)
                                throw new MoodleRestWebServiceException();
                              service.addFunction(function);
                              function=new Function();
                              function.setName(content);
                            } else {
                                function=new Function();
                                function.setName(content);
                            }
                        } else {
                            if (parent.equals("functions")) {
                              if (function==null)
                                throw new MoodleRestWebServiceException();
                              function.setFunctionField(nodeName, content);
                            }
                        }
                    }
                }
            }
            if (function!=null) {
              if (service==null)
                throw new MoodleRestWebServiceException();
              service.addFunction(function);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }
}
