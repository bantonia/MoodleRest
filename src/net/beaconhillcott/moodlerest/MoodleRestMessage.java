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

/**
 * <p>Class to call Moodle REST message web services.</p>
 * 
 * @author Bill Antonia
 */
public class MoodleRestMessage {
  
  /**
   * <p>Method to send a single message between users.</p>
   * 
   * @param MoodleMessage message
   * @return MoodleMessage 
   * @throws MoodleRestMessageException
   * @throws MoodleRestException 
   */
  public static MoodleMessage sendInstantMessage(MoodleMessage message) throws MoodleRestMessageException, MoodleRestException {
    MoodleMessage[] messages=new MoodleMessage[1];
    messages[0]=message;
    messages=sendInstantMessages(messages);
    return messages[0];
  }

  public MoodleMessage __sendInstantMessage(String url, String token, MoodleMessage message) throws MoodleRestMessageException, MoodleRestException {
    MoodleMessage[] messages=new MoodleMessage[1];
    messages[0]=message;
    messages=__sendInstantMessages(url, token, messages);
    return messages[0];
  }
  
  //core_message_send_instant_messages
    /**
     * <p>Method to send messages between the caller and other users.</p>
   * 
     * @param messages
     * @return MoodleMessage[]
     * @throws MoodleRestMessageException
     * @throws MoodleRestException
     */
    public static MoodleMessage[] sendInstantMessages(MoodleMessage[] messages) throws MoodleRestMessageException, MoodleRestException {
        StringBuilder data = new StringBuilder();
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestMessageException(MoodleRestException.NO_LEGACY);
        String functionCall=MoodleServices.CORE_MESSAGE_SEND_INSTANT_MESSAGES;
        try {
            if (MoodleCallRestWebService.getAuth() == null) {
                throw new MoodleRestMessageException();
            } else {
                data.append(MoodleCallRestWebService.getAuth());
            }
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING));
            for (int i=0; i<messages.length; i++) {
                if (messages[i].getToUserId()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_RECIPIENT);
                if (messages[i].getText()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_MESSAGE);
                data.append("&").append(URLEncoder.encode("messages["+i+"][touserid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(Long.toString(messages[i].getToUserId()), MoodleServices.ENCODING));
                data.append("&").append(URLEncoder.encode("messages["+i+"][text]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(messages[i].getText(), MoodleServices.ENCODING));
                if (messages[i].getClientMsgId()!=null) data.append("&").append(URLEncoder.encode("messages["+i+"][clientmsgid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(messages[i].getClientMsgId(), MoodleServices.ENCODING));
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            for (int j=0, i=-1;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("msgid")) {
                    i++;
                    messages[i].setMsgId(Long.parseLong(content));
                } else {
                    messages[i].setMoodleMesageField(nodeName, content);
                    if (nodeName.equals("errormessage") && content.length()!=0)
                        throw new MoodleRestMessageException(content);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

    public MoodleMessage[] __sendInstantMessages(String url, String token, MoodleMessage[] messages) throws MoodleRestMessageException, MoodleRestException {
        StringBuilder data = new StringBuilder();
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestMessageException(MoodleRestException.NO_LEGACY);
        String functionCall=MoodleServices.CORE_MESSAGE_SEND_INSTANT_MESSAGES;
        try {
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING));
            for (int i=0; i<messages.length; i++) {
                if (messages[i].getToUserId()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_RECIPIENT);
                if (messages[i].getText()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_MESSAGE);
                data.append("&").append(URLEncoder.encode("messages["+i+"][touserid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(Long.toString(messages[i].getToUserId()), MoodleServices.ENCODING));
                data.append("&").append(URLEncoder.encode("messages["+i+"][text]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(messages[i].getText(), MoodleServices.ENCODING));
                if (messages[i].getClientMsgId()!=null) data.append("&").append(URLEncoder.encode("messages["+i+"][clientmsgid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(messages[i].getClientMsgId(), MoodleServices.ENCODING));
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            for (int j=0, i=-1;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("msgid")) {
                    i++;
                    messages[i].setMsgId(Long.parseLong(content));
                } else {
                    messages[i].setMoodleMesageField(nodeName, content);
                    if (nodeName.equals("errormessage") && content.length()!=0)
                        throw new MoodleRestMessageException(content);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }
}
