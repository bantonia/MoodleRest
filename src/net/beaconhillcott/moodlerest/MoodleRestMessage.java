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
import java.util.ArrayList;
import java.util.Vector;
import org.w3c.dom.Node;

/**
 * <p>Class to call Moodle REST message web services.</p>
 * 
 * @author Bill Antonia
 */
public class MoodleRestMessage implements Serializable {
  
  /**
   * <p>Method to send a single message between users.</p>
   * 
   * @param message MoodleMessage
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
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestMessageException(MoodleRestException.NO_LEGACY);
        StringBuilder data = new StringBuilder();
        String functionCall=MoodleServices.CORE_MESSAGE_SEND_INSTANT_MESSAGES.toString();
        try {
            if (MoodleCallRestWebService.getAuth() == null) {
                throw new MoodleRestMessageException();
            } else {
                data.append(MoodleCallRestWebService.getAuth());
            }
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0; i<messages.length; i++) {
                if (messages[i].getToUserId()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_RECIPIENT);
                if (messages[i].getText()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_MESSAGE);
                data.append("&").append(URLEncoder.encode("messages["+i+"][touserid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(Long.toString(messages[i].getToUserId()), MoodleServices.ENCODING.toString()));
                data.append("&").append(URLEncoder.encode("messages["+i+"][text]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(messages[i].getText(), MoodleServices.ENCODING.toString()));
                if (messages[i].getClientMsgId()!=null) data.append("&").append(URLEncoder.encode("messages["+i+"][clientmsgid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(messages[i].getClientMsgId(), MoodleServices.ENCODING.toString()));
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
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestMessageException(MoodleRestException.NO_LEGACY);
        StringBuilder data = new StringBuilder();
        String functionCall=MoodleServices.CORE_MESSAGE_SEND_INSTANT_MESSAGES.toString();
        try {
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0; i<messages.length; i++) {
                if (messages[i].getToUserId()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_RECIPIENT);
                if (messages[i].getText()==null) throw new MoodleRestMessageException(MoodleRestMessageException.NO_MESSAGE);
                data.append("&").append(URLEncoder.encode("messages["+i+"][touserid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(Long.toString(messages[i].getToUserId()), MoodleServices.ENCODING.toString()));
                data.append("&").append(URLEncoder.encode("messages["+i+"][text]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(messages[i].getText(), MoodleServices.ENCODING.toString()));
                if (messages[i].getClientMsgId()!=null) data.append("&").append(URLEncoder.encode("messages["+i+"][clientmsgid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(messages[i].getClientMsgId(), MoodleServices.ENCODING.toString()));
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
    
    	public static MoodleContact[] getContacts()
			throws MoodleRestMessageException, MoodleRestException {
		String functionCall = null;
		StringBuilder data = new StringBuilder();
		MoodleContact[] contacts = null;

		if (MoodleCallRestWebService.isLegacy())
			throw new MoodleRestException(MoodleRestException.NO_LEGACY);
		else
			functionCall = MoodleServices.CORE_MESSAGE_GET_CONTACTS.toString();

		try {
			if (MoodleCallRestWebService.getAuth() == null)
				throw new MoodleRestMessageException();
			else
				data.append(MoodleCallRestWebService.getAuth());

			data.append("&");
			data.append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString()));
			data.append("=");
			data.append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));

			// get the contacts
			NodeList elements = MoodleCallRestWebService.call(data.toString());

			// parse the content. Each element is a "VALUE".
			Vector<MoodleContact> vector = new Vector<MoodleContact>();
			MoodleContact contact = null;

			for (int j = 0; j < elements.getLength(); j++) {

				//
				// instanciate the user if its null
				if (contact == null)
					contact = new MoodleContact(Long.valueOf("0"));
				//

				//
				// get current "VALUE" node
				Node node = elements.item(j);
				//

				//
				// get current node text value
				String nodeValue = node.getTextContent();
				//

				//
				// get current node "name" atribute
				String nodeName = node.getParentNode().getAttributes()
						.getNamedItem("name").getNodeValue();
				//

				contact.setMoodleUserContactField(nodeName, nodeValue);

				if (nodeName.equalsIgnoreCase("unread")) {
					//
					// filtering "KEY"s with sons other than "VALUE"
					String parentNodeName = null;
					Node parentNode = node.getParentNode().getParentNode()
							.getParentNode().getParentNode();

					if ((parentNode != null)
							&& (parentNode.getNodeName()
									.equalsIgnoreCase("KEY")))
						parentNodeName = parentNode.getAttributes()
								.getNamedItem("name").getNodeValue();
					//

					//
					// getting the state of the relation of the contact
					MoodleContactState nodeState = null;
					for (MoodleContactState state : MoodleContactState.values()) {
						if ((parentNodeName != null)
								&& (parentNodeName.equalsIgnoreCase(state
										.toString()))) {
							nodeState = state;
							break;
						}
					}
					//

					contact.setMoodleUserContactField("state", nodeState);
					vector.add(contact);
					contact = null;
				}
			}

			// instaciate the array of contacts with the size of the vector
			contacts = new MoodleContact[vector.size()];

			// Get the parsed contacts to an array
			for (int i = 0; i < vector.size(); i++)
				contacts[i] = vector.get(i);

			// Clear vector
			vector.removeAllElements();

		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(MoodleRestMessage.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		return contacts;
	}

	/**
	 * <p>
	 * Method to that perform actions on the contact provided an id
	 * </p>
	 * 
         * @param contact
         * @param action
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void actionContact(Long contact, MoodleContactAction action)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		Long[] a = { contact };

		actionContacts(a, action);
	}

	/**
	 * <p>
	 * Method to that perform actions on the contacts provided a list of ids
	 * </p>
	 * 
         * @param contacts
         * @param action
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void actionContacts(Long[] contacts, MoodleContactAction action)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		String functionCall = null;
		StringBuilder data = new StringBuilder();

		if (MoodleCallRestWebService.isLegacy())
			throw new MoodleRestException(MoodleRestException.NO_LEGACY);
		else {
			switch (action) {
			case DELETE:
				functionCall = MoodleServices.CORE_MESSAGE_DELETE_CONTACTS.toString();
				break;
			case CREATE:
				functionCall = MoodleServices.CORE_MESSAGE_CREATE_CONTACTS.toString();
				break;
			case BLOCK:
				functionCall = MoodleServices.CORE_MESSAGE_BLOCK_CONTACTS.toString();
				break;
			case UNBLOCK:
				functionCall = MoodleServices.CORE_MESSAGE_UNBLOCK_CONTACTS.toString();
				break;
			}
		}

		try {
			if (MoodleCallRestWebService.getAuth() == null)
				throw new MoodleRestUserException();
			else
				data.append(MoodleCallRestWebService.getAuth());

			data.append("&");
			data.append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString()));
			data.append("=");
			data.append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));

			for (int i = 0; i < contacts.length; i++) {
				if (contacts[i] < 1) {
					throw new MoodleRestMessageException(
							MoodleRestMessageException.PARAMETER_RANGE);
				} else {
					data.append("&");
					data.append(URLEncoder.encode("userids[" + i + "]",
							MoodleServices.ENCODING.toString()));
					data.append("=");
					data.append(contacts[i]);
				}
			}

			data.trimToSize();
			MoodleCallRestWebService.call(data.toString());
                        

		} catch (final UnsupportedEncodingException ex) {
			Logger.getLogger(MoodleRestMessage.class.getName()).log(
					Level.SEVERE, null, ex);
		}

	}

	// core_message_delete_contacts
	/**
	 * <p>
	 * Method to delete the contact from the contacts list of the user
	 * </p>
	 * 
	 * @param contact the id of the contact to delete.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void deleteContact(Long contact)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContact(contact, MoodleContactAction.DELETE);
	}

	// core_message_delete_contacts
	/**
	 * <p>
	 * Method to delete the provided contacts list from the main contacts list
	 * of the user
	 * </p>
	 * 
	 * @param contacts the ids list of the contacts to delete.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void deleteContacts(Long[] contacts)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContacts(contacts, MoodleContactAction.DELETE);
	}

	// core_message_create_contacts
	/**
	 * <p>
	 * Method to create the contact in the contacts list of the user
	 * </p>
	 * 
	 * @param contact the id of the contact to create.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void createContact(Long contact)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContact(contact, MoodleContactAction.CREATE);
	}

	// core_message_create_contacts
	/**
	 * <p>
	 * Method to create the provided contacts list from the main contacts list
	 * of the user
	 * </p>
	 * 
	 * @param contacts the ids list of the contacts to create.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void createContacts(Long[] contacts)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContacts(contacts, MoodleContactAction.CREATE);
	}

	// core_message_block_contacts
	/**
	 * <p>
	 * Method to block the contact in the contacts list of the user
	 * </p>
	 * 
	 * @param contact the id of the contact to block.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void blockContact(Long contact)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContact(contact, MoodleContactAction.BLOCK);
	}

	// core_message_block_contacts
	/**
	 * <p>
	 * Method to block the provided contacts list from the main contacts list of
	 * the user
	 * </p>
	 * 
	 * @param contacts the ids list of the contacts to block.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void blockContacts(Long[] contacts)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContacts(contacts, MoodleContactAction.BLOCK);
	}

	// core_message_unblock_contacts
	/**
	 * <p>
	 * Method to unblock the contact in the contacts list of the user
	 * </p>
	 * 
	 * @param contact the id of the contact to unblock.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void unblockContact(Long contact)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContact(contact, MoodleContactAction.UNBLOCK);
                actionContact(contact, MoodleContactAction.CREATE); // The unblock contact webservice deletes the contact connection from the database, this re-establishes the contact connection.
	}

	// core_message_unblock_contacts
	/**
	 * <p>
	 * Method to unblock the provided contacts list from the main contacts list
	 * of the user
	 * </p>
	 * 
	 * @param contacts the ids list of the contacts to unblock.
	 * @throws MoodleRestMessageException
	 * @throws MoodleRestException
	 * @throws UnsupportedEncodingException
	 */
	public static void unblockContacts(Long[] contacts)
			throws MoodleRestMessageException, UnsupportedEncodingException,
			MoodleRestException {
		actionContacts(contacts, MoodleContactAction.UNBLOCK);
                actionContacts(contacts, MoodleContactAction.CREATE); // The unblock contact webservice deletes the contact connection from the database, this re-establishes the contact connection.
	}
  
  public static MoodleContact[] searchContacts(String searchText, boolean onlyMyCourses) throws MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestMessageException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data = new StringBuilder();
    String functionCall=MoodleServices.CORE_MESSAGE_SEARCH_CONTACTS.toString();
    MoodleContact[] results = null;
    try {
      if (MoodleCallRestWebService.getAuth() == null) {
        throw new MoodleRestMessageException();
      } else {
        data.append(MoodleCallRestWebService.getAuth());
      }
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("searchtext", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(searchText, MoodleServices.ENCODING.toString()));
      if (onlyMyCourses) {
        data.append("&").append(URLEncoder.encode("onlymycourses", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode("1", MoodleServices.ENCODING.toString()));
      }
      data.trimToSize();
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      String content;
      String nodeName;
      ArrayList<MoodleContact> moodleContacts=null;
      MoodleContact contact=null;
      for (int j=0; j<elements.getLength(); j++) {
        content=elements.item(j).getTextContent();
        nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (nodeName.equals("id")) {
          if (contact!=null) {
            moodleContacts.add(contact);
            contact=new MoodleContact();
            contact.getContactProfile().setId(Long.parseLong(content));
          } else {
            contact=new MoodleContact();
            contact.getContactProfile().setId(Long.parseLong(content));
          }
        } else {
          if (nodeName.equals("fullname") || nodeName.equals("profileimageurl") || nodeName.equals("profileimageurlsmall")) {
            contact.getContactProfile().setMoodleUserField(nodeName, content);
          }
        }
      }
      if (moodleContacts!=null) {
        moodleContacts.add(contact);
        results=new MoodleContact[1];
        results = moodleContacts.toArray(results);
      }
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(MoodleRestMessage.class.getName()).log(Level.SEVERE, null, ex);
    }
    return results;
  }
  
  public MoodleBlockedUsers getBlockedUsers(Long userId) throws MoodleRestCommentsException , UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_MESSAGE_GET_BLOCKED_USERS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestMessageException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (userId==null) throw new MoodleRestMessageException(); else data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleUser> users=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleUser user=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("users")) {
        if (nodeName.equals("id")) {
          if (users==null) {
            users=new ArrayList<MoodleUser>();
          }
          user=new MoodleUser();
          users.add(user);
          user.setId(Long.parseLong(content));
        } else {
          user.setMoodleUserField(nodeName, content);
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
    MoodleBlockedUsers usersWithWarnings=new MoodleBlockedUsers();
    usersWithWarnings.setUsers(users);
    usersWithWarnings.setWarnings(warn);
    return usersWithWarnings;
  }
  
  public static MarkReadMessage markMessageAsRead(Long messageId, Long timeRead) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_MESSAGE_MARK_MESSAGE_READ.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestMessageException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (messageId==null) { throw new MoodleRestException(MoodleRestException.REQUIRED_PARAMETER); }
    data.append("&").append(URLEncoder.encode("messageid", MoodleServices.ENCODING.toString())).append("=").append(messageId);
    if (timeRead==null) { throw new MoodleRestException(MoodleRestException.REQUIRED_PARAMETER); }
    data.append("&").append(URLEncoder.encode("timeread", MoodleServices.ENCODING.toString())).append("=").append(timeRead);
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    MarkReadMessage readMessage=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    String parent=null;
    for (int j=0;j<elements.getLength();j++) {
      try {
        parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      } catch (NullPointerException ex) {}
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (nodeName.equals("messageid")) {
        if (readMessage==null) {
          readMessage=new MarkReadMessage();
          readMessage.setMessageId(Long.parseLong(content));
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
    if (warn!=null) {
      if (readMessage==null) {
        readMessage=new MarkReadMessage();
      }
      readMessage.setWarnings(warn);
    }
    return readMessage;
  }
  
  public MoodleRetrievedMessages getMessages() throws UnsupportedEncodingException, MoodleRestException {
    return getMessages(0L, null, null, null, null, null, null);
  }
  
  public MoodleRetrievedMessages getMessages(Long userIdTo, Long userIdFrom, String type, Boolean read, Boolean newestFirst, Long limitFrom, Long limitNum) throws UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_MESSAGE_GET_MESSAGES.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestMessageException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (userIdTo==null) throw new MoodleRestMessageException(); else data.append("&").append(URLEncoder.encode("useridto", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userIdTo, MoodleServices.ENCODING.toString()));
    if (userIdFrom!=null) data.append("&").append(URLEncoder.encode("useridfrom", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userIdFrom, MoodleServices.ENCODING.toString()));
    if (type!=null) data.append("&").append(URLEncoder.encode("type", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+type, MoodleServices.ENCODING.toString()));
    if (read!=null) data.append("&").append(URLEncoder.encode("read", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(read?1:0), MoodleServices.ENCODING.toString()));
    if (newestFirst!=null) data.append("&").append(URLEncoder.encode("newestfirst", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(newestFirst?1:0), MoodleServices.ENCODING.toString()));
    if (limitFrom!=null) data.append("&").append(URLEncoder.encode("limitfrom", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+limitFrom, MoodleServices.ENCODING.toString()));
    if (limitNum!=null) data.append("&").append(URLEncoder.encode("limitnum", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+limitNum, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleRetrievedMessage> messages=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleRetrievedMessage message=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("messages")) {
        if (nodeName.equals("id")) {
          if (messages==null) {
            messages=new ArrayList<MoodleRetrievedMessage>();
          }
          message=new MoodleRetrievedMessage();
          messages.add(message);
          message.setId(Long.parseLong(content));
        } else {
          message.setField(nodeName, content);
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
    MoodleRetrievedMessages messagesWithWarnings=new MoodleRetrievedMessages();
    messagesWithWarnings.setMessages(messages);
    messagesWithWarnings.setWarnings(warn);
    return messagesWithWarnings;
  }
}
