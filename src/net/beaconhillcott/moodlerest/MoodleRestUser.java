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

import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.*;
import org.w3c.dom.NodeList;

/**
 * <p>Class containing the static routines to create, update and delete Moodle users.</p>
 * 
 * @author Bill Antonia
 * @see MoodleUser
 */
public class MoodleRestUser implements Serializable {

    /**
     * <p>Method to create a new user within Moodle from a MoodleUser object.</p>
     * 
     * @param user MoodleUser with at least the username, password, lastname, firstname and email fields set
     * @return MoodleUser object updated with the user id within the Moodle site.
     * @throws MoodleRestUserException
     * @throws MoodleRestException
     */
    public static MoodleUser createUser(MoodleUser user) throws MoodleRestUserException, MoodleRestException {
        MoodleUser[] a=new MoodleUser[1];
        a[0]=user;
        MoodleUser[] usr=createUsers(a);
        return usr[0];
    }

    public MoodleUser __createUser(String url, String token, MoodleUser user) throws MoodleRestUserException, MoodleRestException {
        MoodleUser[] a=new MoodleUser[1];
        a[0]=user;
        MoodleUser[] usr=__createUsers(url, token, a);
        return usr[0];
    }

    /**
     * <p>Method to create new users within Moodle from an array of MoodleUser objects.</p>
     * 
     * @param user MoodleUser[] array of MoodleUser with at least the username, password, lastname, firstname and email fields set.
     * @return MoodleUser[] array of MoodleUser objects updated with the user id within the Moodle site.
     * @throws MoodleRestUserException
     * @throws MoodleRestException
     */
    public static MoodleUser[] createUsers(MoodleUser[] user) throws MoodleRestUserException, MoodleRestException {
        Hashtable hash=new Hashtable();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_CREATE_USERS.toString():MoodleServices.CORE_USER_CREATE_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());//data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(MoodleCallRestWebService.getToken(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestUserException(MoodleRestUserException.USER_NULL);
                if (user[i].getUsername()==null) throw new MoodleRestUserException(MoodleRestUserException.USERNAME_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][username]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getUsername(), MoodleServices.ENCODING.toString()));
                if (user[i].getPassword()==null) throw new MoodleRestUserException(MoodleRestUserException.PASSWORD_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][password]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPassword(), MoodleServices.ENCODING.toString()));
                if (user[i].getFirstname()==null) throw new MoodleRestUserException(MoodleRestUserException.FIRSTNAME_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][firstname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getFirstname(), MoodleServices.ENCODING.toString()));
                if (user[i].getLastname()==null) throw new MoodleRestUserException(MoodleRestUserException.LASTNAME_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][lastname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLastname(), MoodleServices.ENCODING.toString()));
                if (user[i].getEmail()==null) throw new MoodleRestUserException(MoodleRestUserException.EMAIL_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][email]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getEmail(), MoodleServices.ENCODING.toString()));
                if (user[i].getAuth()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][auth]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getAuth(), MoodleServices.ENCODING.toString()));
                if (user[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getIdNumber(), MoodleServices.ENCODING.toString()));
                if (user[i].getLang()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLang(), MoodleServices.ENCODING.toString()));
                if (user[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTheme(), MoodleServices.ENCODING.toString()));
                if (user[i].getTimezone()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][timezone]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTimezone(), MoodleServices.ENCODING.toString()));
                if (user[i].getMailFormat()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][mailformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(user[i].getMailFormat()?1:0), MoodleServices.ENCODING.toString()));
                if (user[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getDescription(), MoodleServices.ENCODING.toString()));
                if (user[i].getCity()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][city]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCity(), MoodleServices.ENCODING.toString()));
                if (user[i].getCountry()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][country]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCountry(), MoodleServices.ENCODING.toString()));
                if (user[i].getPreference()!=null) {
                  for (int j=0; j<user[i].getPreference().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getType(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
                if (user[i].getCustomFields()!=null) {
                  for (int j=0; j<user[i].getCustomFields().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getName(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            for (int j=0;j<elements.getLength();j+=2) {
                hash.put(elements.item(j+1).getTextContent(), elements.item(j).getTextContent());
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i=0; i<user.length; i++) {
            user[i].setId(Long.parseLong((String)(hash.get(user[i].getUsername()))));
        }
        return user;
    }

    public MoodleUser[] __createUsers(String url, String token, MoodleUser[] user) throws MoodleRestUserException, MoodleRestException {
        Hashtable hash=new Hashtable();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_CREATE_USERS.toString():MoodleServices.CORE_USER_CREATE_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestUserException(MoodleRestUserException.USER_NULL);
                if (user[i].getUsername()==null) throw new MoodleRestUserException(MoodleRestUserException.USERNAME_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][username]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getUsername(), MoodleServices.ENCODING.toString()));
                if (user[i].getPassword()==null) throw new MoodleRestUserException(MoodleRestUserException.PASSWORD_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][password]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPassword(), MoodleServices.ENCODING.toString()));
                if (user[i].getFirstname()==null) throw new MoodleRestUserException(MoodleRestUserException.FIRSTNAME_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][firstname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getFirstname(), MoodleServices.ENCODING.toString()));
                if (user[i].getLastname()==null) throw new MoodleRestUserException(MoodleRestUserException.LASTNAME_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][lastname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLastname(), MoodleServices.ENCODING.toString()));
                if (user[i].getEmail()==null) throw new MoodleRestUserException(MoodleRestUserException.EMAIL_NULL); else data.append("&").append(URLEncoder.encode("users["+i+"][email]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getEmail(), MoodleServices.ENCODING.toString()));
                if (user[i].getAuth()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][auth]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getAuth(), MoodleServices.ENCODING.toString()));
                if (user[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getIdNumber(), MoodleServices.ENCODING.toString()));
                if (user[i].getLang()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLang(), MoodleServices.ENCODING.toString()));
                if (user[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTheme(), MoodleServices.ENCODING.toString()));
                if (user[i].getTimezone()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][timezone]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTimezone(), MoodleServices.ENCODING.toString()));
                if (user[i].getMailFormat()!=null)data.append("&").append(URLEncoder.encode("users["+i+"][mailformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(user[i].getMailFormat()?1:0), MoodleServices.ENCODING.toString()));
                if (user[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getDescription(), MoodleServices.ENCODING.toString()));
                if (user[i].getCity()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][city]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCity(), MoodleServices.ENCODING.toString()));
                if (user[i].getCountry()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][country]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCountry(), MoodleServices.ENCODING.toString()));
                if (user[i].getPreference()!=null) {
                  for (int j=0; j<user[i].getPreference().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getType(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
                if (user[i].getCustomFields()!=null) {
                  for (int j=0; j<user[i].getCustomFields().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getName(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            for (int j=0;j<elements.getLength();j+=2) {
                hash.put(elements.item(j+1).getTextContent(), elements.item(j).getTextContent());
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i=0; i<user.length; i++) {
            user[i].setId(Long.parseLong((String)(hash.get(user[i].getUsername()))));
        }
        return user;
    }

    /**
     * <p>Used to update the information for a single user within the Moodle site.</p>
     * 
     * @param user MoodleUser object containing the updated user information.
     * @throws MoodleRestUserException
     * @throws MoodleRestException
     */
    public static void updateUser(MoodleUser user) throws MoodleRestUserException, MoodleRestException {
        MoodleUser[] a=new MoodleUser[1];
        a[0]=user;
        updateUsers(a);
    }

    public void __updateUser(String url, String token, MoodleUser user) throws MoodleRestUserException, MoodleRestException {
        MoodleUser[] a=new MoodleUser[1];
        a[0]=user;
        __updateUsers(url, token, a);
    }

    /**
     * <p>Used to modify information about users within the Moodle site.</p>
     * 
     * @param user MoodleUser[] array of MoodleUser objects containing the updated user information.
     * @throws MoodleRestUserException
     * @throws MoodleRestException 
     */
    public static void updateUsers(MoodleUser[] user) throws MoodleRestUserException, MoodleRestException {
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_UPDATE_USERS.toString():MoodleServices.CORE_USER_UPDATE_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestUserException(MoodleRestUserException.USER_NULL);
                if (user[i].getId()==null) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID); else data.append("&").append(URLEncoder.encode("users["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getId(), MoodleServices.ENCODING.toString()));
                if (user[i].getUsername()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][username]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getUsername(), MoodleServices.ENCODING.toString()));
                if (user[i].getPassword()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][password]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPassword(), MoodleServices.ENCODING.toString()));
                if (user[i].getFirstname()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][firstname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getFirstname(), MoodleServices.ENCODING.toString()));
                if (user[i].getLastname()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][lastname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLastname(), MoodleServices.ENCODING.toString()));
                if (user[i].getEmail()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][email]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getEmail(), MoodleServices.ENCODING.toString()));
                if (user[i].getAuth()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][auth]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getAuth(), MoodleServices.ENCODING.toString()));
                if (user[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getIdNumber(), MoodleServices.ENCODING.toString()));
                if (user[i].getLang()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLang(), MoodleServices.ENCODING.toString()));
                if (user[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTheme(), MoodleServices.ENCODING.toString()));
                if (user[i].getTimezone()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][timezone]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTimezone(), MoodleServices.ENCODING.toString()));
                if (user[i].getMailFormat()!=null)data.append("&").append(URLEncoder.encode("users["+i+"][mailformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(user[i].getMailFormat()?1:0), MoodleServices.ENCODING.toString()));
                if (user[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getDescription(), MoodleServices.ENCODING.toString()));
                if (user[i].getCity()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][city]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCity(), MoodleServices.ENCODING.toString()));
                if (user[i].getCountry()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][country]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCountry(), MoodleServices.ENCODING.toString()));
                if (user[i].getPreference()!=null) {
                  for (int j=0; j<user[i].getPreference().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getType(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
                if (user[i].getCustomFields()!=null) {
                  for (int j=0; j<user[i].getCustomFields().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getName(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
            }
            data.trimToSize();
            MoodleCallRestWebService.call(data.toString());
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void __updateUsers(String url, String token, MoodleUser[] user) throws MoodleRestUserException, MoodleRestException {
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_UPDATE_USERS.toString():MoodleServices.CORE_USER_UPDATE_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestUserException(MoodleRestUserException.USER_NULL);
                if (user[i].getId()==null) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID); else data.append("&").append(URLEncoder.encode("users["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getId(), MoodleServices.ENCODING.toString()));
                if (user[i].getUsername()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][username]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getUsername(), MoodleServices.ENCODING.toString()));
                if (user[i].getPassword()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][password]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPassword(), MoodleServices.ENCODING.toString()));
                if (user[i].getFirstname()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][firstname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getFirstname(), MoodleServices.ENCODING.toString()));
                if (user[i].getLastname()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][lastname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLastname(), MoodleServices.ENCODING.toString()));
                if (user[i].getEmail()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][email]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getEmail(), MoodleServices.ENCODING.toString()));
                if (user[i].getAuth()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][auth]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getAuth(), MoodleServices.ENCODING.toString()));
                if (user[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getIdNumber(), MoodleServices.ENCODING.toString()));
                if (user[i].getLang()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getLang(), MoodleServices.ENCODING.toString()));
                if (user[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTheme(), MoodleServices.ENCODING.toString()));
                if (user[i].getTimezone()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][timezone]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getTimezone(), MoodleServices.ENCODING.toString()));
                if (user[i].getMailFormat()!=null)data.append("&").append(URLEncoder.encode("users["+i+"][mailformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(user[i].getMailFormat()?1:0), MoodleServices.ENCODING.toString()));
                if (user[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getDescription(), MoodleServices.ENCODING.toString()));
                if (user[i].getCity()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][city]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCity(), MoodleServices.ENCODING.toString()));
                if (user[i].getCountry()!=null) data.append("&").append(URLEncoder.encode("users["+i+"][country]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCountry(), MoodleServices.ENCODING.toString()));
                if (user[i].getPreference()!=null) {
                  for (int j=0; j<user[i].getPreference().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getType(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][preferences]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getPreference().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
                if (user[i].getCustomFields()!=null) {
                  for (int j=0; j<user[i].getCustomFields().size(); j++) {
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getName(), MoodleServices.ENCODING.toString()));
                    data.append("&").append(URLEncoder.encode("users["+i+"][customfields]["+j+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(user[i].getCustomFields().get(j).getValue(), MoodleServices.ENCODING.toString()));
                  }
                }
            }
            data.trimToSize();
            (new MoodleCallRestWebService()).__call(url,data.toString());
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * <p>Method to delete the account of a single user within the Moodle site.</p>
     * @param user long The Moodle id of the user to delete.
     * @throws MoodleRestUserException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void deleteUser(Long user) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=user;
        deleteUsers(a);
    }

    public void __deleteUser(String url, String token, Long user) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=user;
        __deleteUsers(url, token, a);
    }

    /**
     * <p>Method used to delete a number of users within the Moodle site.</p>
     * 
     * @param userids long[] array of Moodle user ids
     * @throws MoodleRestUserException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void deleteUsers(Long[] userids) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_DELETE_USERS.toString():MoodleServices.CORE_USER_DELETE_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());//data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(MoodleCallRestWebService.getToken(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<userids.length;i++) {
                if (userids[i]<1) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID); else data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(userids[i]);
            }
            data.trimToSize();
            MoodleCallRestWebService.call(data.toString());
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void __deleteUsers(String url, String token, Long[] userids) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_DELETE_USERS.toString():MoodleServices.CORE_USER_DELETE_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<userids.length;i++) {
                if (userids[i]<1) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID); else data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(userids[i]);
            }
            data.trimToSize();
            (new MoodleCallRestWebService()).__call(url,data.toString());
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * <p>Method which gets the information of a single users from their Moodle id.</p>
     * 
     * @param userid long Moodle id of the user
     * @return MoodleUser object containing the data of the selected user.
     * @throws MoodleRestUserException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleUser getUserById(Long userid) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=userid;
        MoodleUser[] usr=getUsersById(a);
        return usr[0];
    }

    public MoodleUser __getUserById(String url, String token, Long userid) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=userid;
        MoodleUser[] usr=__getUsersById(url, token, a);
        return usr[0];
    }

    /**
     * Method which gets a number of MoodleUser objects containing the information of users from their Moodle ids.</p>
     * 
     * @param userids long[] array containing the Moodle ids of a number of users
     * @return MoodleUser[] array of MoodleUser objects containing the user data.
     * @throws MoodleRestUserException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleUser[] getUsersById(Long[] userids) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleUser user;
      //  boolean processed=false;
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_GET_USERS_BY_ID.toString():MoodleServices.CORE_USER_GET_USERS_BY_ID.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());//data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(MoodleCallRestWebService.getToken(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            UserCustomField customField=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            for (int i=0;i<userids.length;i++) {
                if (userids[i]<1) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID); data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(userids[i]);
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      try {
                        if (user==null)
                          throw new MoodleRestUserException();
                        user.setMoodleUserField(nodeName, content);
                      } catch (NullPointerException ex){
                        System.out.println("Error "+nodeName+" = "+content);
                      }
                    } else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                    if (preference!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addPreference(preference);
                                    }
                                    preference=new UserPreference();
                                    preference.setType(content);
                                } else {
                                    if (parent.equals("preferences")) {
                                      if (preference==null)
                                        throw new MoodleRestUserException();
                                      preference.setValue(content);
                                    } else {
                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                          if (enrolledCourse!=null) {
                                            if (user==null)
                                              throw new MoodleRestUserException();
                                            user.addEnrolledCourse(enrolledCourse);
                                          }
                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                        } else {
                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                              if (enrolledCourse==null)
                                                throw new MoodleRestUserException();
                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public MoodleUser[] __getUsersById(String url, String token, Long[] userids) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleUser user;
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_USER_GET_USERS_BY_ID.toString():MoodleServices.CORE_USER_GET_USERS_BY_ID.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            UserCustomField customField=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            for (int i=0;i<userids.length;i++) {
                if (userids[i]<1) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID); data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(userids[i]);
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      try {
                        if (user==null)
                          throw new MoodleRestUserException();
                        user.setMoodleUserField(nodeName, content);
                      } catch (NullPointerException ex){
                        System.out.println("Error "+nodeName+" = "+content);
                      }
                    } else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                    if (preference!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addPreference(preference);
                                    }
                                    preference=new UserPreference();
                                    preference.setType(content);
                                } else {
                                    if (parent.equals("preferences")) {
                                      if (preference==null)
                                        throw new MoodleRestUserException();
                                      preference.setValue(content);
                                    } else {
                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                          if (enrolledCourse!=null) {
                                            if (user==null)
                                              throw new MoodleRestUserException();
                                            user.addEnrolledCourse(enrolledCourse);
                                          }
                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                        } else {
                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                              if (enrolledCourse==null)
                                                throw new MoodleRestUserException();
                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }
    
    /**
     * <p>Method to return the details of courses in which users are enrolled</p>
     * 
     * @param userList
     * @return MoodleUser[]
     * @throws MoodleRestUserException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleUser[] getCourseUserProfiles(UserList[] userList) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException, MoodleUserRoleException {
        Vector v=new Vector();
        MoodleUser user;
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestUserException(MoodleRestException.NO_LEGACY);
        String functionCall=MoodleServices.CORE_USER_GET_COURSE_USER_PROFILES.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());//data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(MoodleCallRestWebService.getToken(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<userList.length;i++) {
                if (userList[i].getUserId()<1) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID);
                data.append("&").append(URLEncoder.encode("userlist["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(userList[i].getUserId());
                data.append("&").append(URLEncoder.encode("userlist["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(userList[i].getCourseId());
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            UserCustomField customField=null;
            UserGroup group=null;
            UserRole role=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    group=null;
                    role=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      if (user==null)
                        throw new MoodleRestUserException();
                      user.setMoodleUserField(nodeName, content);
                    }
                    else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("groups") && nodeName.equals("id")) {
                                    if (group!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addGroup(group);
                                    }
                                    group=new UserGroup();
                                    group.setUserGroupField(nodeName, content);
                                } else {
                                    if (parent.equals("groups")) {
                                      if (group==null)
                                        throw new MoodleRestUserException();
                                      group.setUserGroupField(nodeName, content);
                                    } else {
                                        if (parent.equals("roles") && nodeName.equals("roleid")) {
                                            if (role!=null) {
                                              if (user==null)
                                                throw new MoodleRestUserException();
                                              user.addRole(role);
                                            }
                                            role=new UserRole();
                                            role.setUserRoleField(nodeName, content);
                                        } else {
                                            if (parent.equals("roles")) {
                                              if (role==null)
                                                throw new MoodleRestUserException();
                                              role.setUserRoleField(nodeName, content);
                                            } else {
                                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                                    if (preference!=null) {
                                                      if (user==null)
                                                        throw new MoodleRestUserException();
                                                      user.addPreference(preference);
                                                    }
                                                    preference=new UserPreference();
                                                    preference.setType(content);
                                                } else {
                                                    if (parent.equals("preferences")) {
                                                      if (preference==null)
                                                        throw new MoodleRestUserException();
                                                      preference.setValue(content);
                                                    } else {
                                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                                          if (enrolledCourse!=null) {
                                                            if (user==null)
                                                              throw new MoodleRestUserException();
                                                            user.addEnrolledCourse(enrolledCourse);
                                                          }
                                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                                        } else {
                                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                                              if (enrolledCourse==null)
                                                                throw new MoodleRestUserException();
                                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public MoodleUser[] __getCourseUserProfiles(String url, String token, UserList[] userList) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException, MoodleUserRoleException {
        Vector v=new Vector();
        MoodleUser user;
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestUserException(MoodleRestException.NO_LEGACY);
        String functionCall=MoodleServices.CORE_USER_GET_COURSE_USER_PROFILES.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<userList.length;i++) {
                if (userList[i].getUserId()<1) throw new MoodleRestUserException(MoodleRestUserException.INVALID_USERID);
                data.append("&").append(URLEncoder.encode("userlist["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(userList[i].getUserId());
                data.append("&").append(URLEncoder.encode("userlist["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(userList[i].getCourseId());
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            UserCustomField customField=null;
            UserGroup group=null;
            UserRole role=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    group=null;
                    role=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      if (user==null)
                        throw new MoodleRestUserException();
                      user.setMoodleUserField(nodeName, content);
                    }
                    else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("groups") && nodeName.equals("id")) {
                                    if (group!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addGroup(group);
                                    }
                                    group=new UserGroup();
                                    group.setUserGroupField(nodeName, content);
                                } else {
                                    if (parent.equals("groups")) {
                                      if (group==null)
                                        throw new MoodleRestUserException();
                                      group.setUserGroupField(nodeName, content);
                                    } else {
                                        if (parent.equals("roles") && nodeName.equals("roleid")) {
                                            if (role!=null) {
                                              if (user==null)
                                                throw new MoodleRestUserException();
                                              user.addRole(role);
                                            }
                                            role=new UserRole();
                                            role.setUserRoleField(nodeName, content);
                                        } else {
                                            if (parent.equals("roles")) {
                                              if (role==null)
                                                throw new MoodleRestUserException();
                                              role.setUserRoleField(nodeName, content);
                                            } else {
                                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                                    if (preference!=null) {
                                                      if (user==null)
                                                        throw new MoodleRestUserException();
                                                      user.addPreference(preference);
                                                    }
                                                    preference=new UserPreference();
                                                    preference.setType(content);
                                                } else {
                                                    if (parent.equals("preferences")) {
                                                      if (preference==null)
                                                        throw new MoodleRestUserException();
                                                      preference.setValue(content);
                                                    } else {
                                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                                          if (enrolledCourse!=null) {
                                                            if (user==null)
                                                              throw new MoodleRestUserException();
                                                            user.addEnrolledCourse(enrolledCourse);
                                                          }
                                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                                        } else {
                                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                                              if (enrolledCourse==null)
                                                                throw new MoodleRestUserException();
                                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public static MoodleUser[] getUsers(Criteria[] criteria) throws MoodleRestUserException, MoodleRestException, MoodleUserRoleException {
        Vector v=new Vector();
        MoodleUser user;
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestUserException(MoodleRestException.NO_LEGACY);
        String functionCall=MoodleServices.CORE_USER_GET_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());//data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(MoodleCallRestWebService.getToken(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0; i<criteria.length; i++) {
                data.append("&").append(URLEncoder.encode("criteria["+i+"][key]", MoodleServices.ENCODING.toString())).append("=").append(criteria[i].getKey());
                data.append("&").append(URLEncoder.encode("criteria["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(criteria[i].getValue());
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            UserCustomField customField=null;
            UserGroup group=null;
            UserRole role=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    group=null;
                    role=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      if (user==null)
                        throw new MoodleRestUserException();
                      user.setMoodleUserField(nodeName, content);
                    }
                    else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("groups") && nodeName.equals("id")) {
                                    if (group!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addGroup(group);
                                    }
                                    group=new UserGroup();
                                    group.setUserGroupField(nodeName, content);
                                } else {
                                    if (parent.equals("groups")) {
                                      if (group==null)
                                        throw new MoodleRestUserException();
                                      group.setUserGroupField(nodeName, content);
                                    } else {
                                        if (parent.equals("roles") && nodeName.equals("roleid")) {
                                            if (role!=null) {
                                              if (user==null)
                                                throw new MoodleRestUserException();
                                              user.addRole(role);
                                            }
                                            role=new UserRole();
                                            role.setUserRoleField(nodeName, content);
                                        } else {
                                            if (parent.equals("roles")) {
                                              if (role==null) {
                                                throw new MoodleRestUserException();
                                              }
                                              role.setUserRoleField(nodeName, content);
                                            } else {
                                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                                    if (preference!=null) {
                                                      if (user==null) {
                                                        throw new MoodleRestUserException();
                                                      }
                                                      user.addPreference(preference);
                                                    }
                                                    preference=new UserPreference();
                                                    preference.setType(content);
                                                } else {
                                                    if (parent.equals("preferences")) {
                                                      if (preference==null) {
                                                        throw new MoodleRestUserException();
                                                      }
                                                      preference.setValue(content);
                                                    } else {
                                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                                          if (enrolledCourse!=null) {
                                                            if (user==null) {
                                                              throw new MoodleRestUserException();
                                                            }
                                                            user.addEnrolledCourse(enrolledCourse);
                                                          }
                                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                                        } else {
                                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                                              if (enrolledCourse==null)
                                                                throw new MoodleRestUserException();
                                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public MoodleUser[] __getUsers(String url, String token, Criteria[] criteria) throws MoodleRestUserException, MoodleRestException, MoodleUserRoleException {
        Vector v=new Vector();
        MoodleUser user;
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestUserException(MoodleRestException.NO_LEGACY);
        String functionCall=MoodleServices.CORE_USER_GET_USERS.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0; i<criteria.length; i++) {
                data.append("&").append(URLEncoder.encode("criteria["+i+"][key]", MoodleServices.ENCODING.toString())).append("=").append(criteria[i].getKey());
                data.append("&").append(URLEncoder.encode("criteria["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(criteria[i].getValue());
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            UserCustomField customField=null;
            UserGroup group=null;
            UserRole role=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    group=null;
                    role=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      if (user==null)
                        throw new MoodleRestUserException();
                      user.setMoodleUserField(nodeName, content);
                    }
                    else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("groups") && nodeName.equals("id")) {
                                    if (group!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addGroup(group);
                                    }
                                    group=new UserGroup();
                                    group.setUserGroupField(nodeName, content);
                                } else {
                                    if (parent.equals("groups")) {
                                      if (group==null)
                                        throw new MoodleRestUserException();
                                      group.setUserGroupField(nodeName, content);
                                    } else {
                                        if (parent.equals("roles") && nodeName.equals("roleid")) {
                                            if (role!=null) {
                                              if (user==null)
                                                throw new MoodleRestUserException();
                                              user.addRole(role);
                                            }
                                            role=new UserRole();
                                            role.setUserRoleField(nodeName, content);
                                        } else {
                                            if (parent.equals("roles")) {
                                              if (role==null)
                                                throw new MoodleRestUserException();
                                              role.setUserRoleField(nodeName, content);
                                            } else {
                                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                                    if (preference!=null) {
                                                      if (user==null)
                                                        throw new MoodleRestUserException();
                                                      user.addPreference(preference);
                                                    }
                                                    preference=new UserPreference();
                                                    preference.setType(content);
                                                } else {
                                                    if (parent.equals("preferences")) {
                                                      if (preference==null)
                                                        throw new MoodleRestUserException();
                                                      preference.setValue(content);
                                                    } else {
                                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                                          if (enrolledCourse!=null) {
                                                            if (user==null)
                                                              throw new MoodleRestUserException();
                                                            user.addEnrolledCourse(enrolledCourse);
                                                          }
                                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                                        } else {
                                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                                              if (enrolledCourse==null)
                                                                throw new MoodleRestUserException();
                                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }
    
    public static MoodleUser[] getUsersByField(UserFieldSearch field, String value) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
      String[] values=new String[1];
      values[0]=value;
      return getUsersByField(field, values);
    }
    
    public static MoodleUser[] getUsersByField(UserFieldSearch field, String[] values) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCalendarException(MoodleRestException.NO_LEGACY);  
      Vector v=new Vector();
      MoodleUser user;
      //  boolean processed=false;
        String functionCall=MoodleServices.CORE_USER_GET_USERS_BY_FIELD.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());//data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(MoodleCallRestWebService.getToken(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            UserCustomField customField=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            data.append("&").append(URLEncoder.encode("field", MoodleServices.ENCODING.toString())).append("=").append(field.toString());
            for (int i=0;i<values.length;i++) {
                if (values==null) throw new MoodleRestUserException();
                data.append("&").append(URLEncoder.encode("values["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(values[i]);
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      try {
                        if (user==null)
                          throw new MoodleRestUserException();
                        user.setMoodleUserField(nodeName, content);
                      } catch (NullPointerException ex){
                        System.out.println("Error "+nodeName+" = "+content);
                      }
                    } else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                    if (preference!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addPreference(preference);
                                    }
                                    preference=new UserPreference();
                                    preference.setType(content);
                                } else {
                                    if (parent.equals("preferences")) {
                                      if (preference==null)
                                        throw new MoodleRestUserException();
                                      preference.setValue(content);
                                    } else {
                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                          if (enrolledCourse!=null) {
                                            if (user==null)
                                              throw new MoodleRestUserException();
                                            user.addEnrolledCourse(enrolledCourse);
                                          }
                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                        } else {
                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                              if (enrolledCourse==null)
                                                throw new MoodleRestUserException();
                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }
    
    public MoodleUser[] __getUsersByField(String url, String token, UserFieldSearch field, String value) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
      String[] values=new String[1];
      values[0]=value;
      return __getUsersByField(url, token, field, values);
    }
    
    public MoodleUser[] __getUsersByField(String url, String token, UserFieldSearch field, String[] values) throws MoodleRestUserException, UnsupportedEncodingException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCalendarException(MoodleRestException.NO_LEGACY);  
      Vector v=new Vector();
      MoodleUser user;
      //  boolean processed=false;
        String functionCall=MoodleServices.CORE_USER_GET_USERS_BY_FIELD.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            UserCustomField customField=null;
            UserPreference preference=null;
            UserEnrolledCourse enrolledCourse=null;
            data.append("&").append(URLEncoder.encode("field", MoodleServices.ENCODING.toString())).append("=").append(field.toString());
            for (int i=0;i<values.length;i++) {
                if (values==null) throw new MoodleRestUserException();
                data.append("&").append(URLEncoder.encode("values["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(values[i]);
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            user=null;
            for (int j=0;j<elements.getLength();j++) {
                String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
                if (parent.equals("KEY"))
                    parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                String content=elements.item(j).getTextContent();
                String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                if (parent.equals("RESPONSE") && nodeName.equals("id")) {
                    if (user==null)
                        user=new MoodleUser(Long.parseLong(content));
                    else {
                        v.add(user);
                        user=new MoodleUser(Long.parseLong(content));
                    }
                    customField=null;
                    preference=null;
                    enrolledCourse=null;
                } else {
                    if (parent.equals("RESPONSE")) {
                      try {
                        if (user==null)
                          throw new MoodleRestUserException();
                        user.setMoodleUserField(nodeName, content);
                      } catch (NullPointerException ex){
                        System.out.println("Error "+nodeName+" = "+content);
                      }
                    } else {
                        if(parent.equals("customfields") && nodeName.equals("type")) {
                            if (customField!=null) {
                              if (user==null)
                                throw new MoodleRestUserException();
                              user.addCustomField(customField);
                            }
                            customField=new UserCustomField();
                            customField.setCustomFieldField(nodeName, content);
                        } else {
                            if (parent.equals("customfields")) {
                              if (customField==null)
                                throw new MoodleRestUserException();
                              customField.setCustomFieldField(nodeName, content);
                            } else {
                                if (parent.equals("preferences") && nodeName.equals("name")) {
                                    if (preference!=null) {
                                      if (user==null)
                                        throw new MoodleRestUserException();
                                      user.addPreference(preference);
                                    }
                                    preference=new UserPreference();
                                    preference.setType(content);
                                } else {
                                    if (parent.equals("preferences")) {
                                      if (preference==null)
                                        throw new MoodleRestUserException();
                                      preference.setValue(content);
                                    } else {
                                        if (parent.equals("enrolledcourses") && nodeName.equals("id")) {
                                          if (enrolledCourse!=null) {
                                            if (user==null)
                                              throw new MoodleRestUserException();
                                            user.addEnrolledCourse(enrolledCourse);
                                          }
                                          enrolledCourse=new UserEnrolledCourse(Long.parseLong(content));
                                        } else {
                                            if (parent.equals("enrolledcourses") && !nodeName.equals("id")) {
                                              if (enrolledCourse==null)
                                                throw new MoodleRestUserException();
                                              enrolledCourse.setUserEnrolledCourseField(nodeName, content);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (user!=null) {
                if (customField!=null)
                    user.addCustomField(customField);
                customField=null;
                if (preference!=null)
                    user.addPreference(preference);
                preference=null;
                if (enrolledCourse!=null)
                    user.addEnrolledCourse(enrolledCourse);
                enrolledCourse=null;
                v.add(user);
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }
}
