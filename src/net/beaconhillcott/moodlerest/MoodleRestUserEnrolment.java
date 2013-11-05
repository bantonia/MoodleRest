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

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NodeList;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class MoodleRestUserEnrolment implements Serializable {
  
  public static void enrolUsers(MoodleUserEnrolment[] users) throws MoodleRestUserEnrolmentException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestUserEnrolmentException(MoodleRestException.NO_LEGACY);
        Hashtable hash=new Hashtable();
        String functionCall=MoodleServices.ENROL_MANUAL_ENROL_USERS.name();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestUserException();
            else
                data.append(MoodleCallRestWebService.getAuth());
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<users.length;i++) {
                if (users[i]==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserEnrolmentException.USER_NULL);
                if (users[i].getRoleId()==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserException.USERNAME_NULL); else data.append("&").append(URLEncoder.encode("enrolments["+i+"][roleid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getRoleId().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getUserId()==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserException.PASSWORD_NULL); else data.append("&").append(URLEncoder.encode("enrolments["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getUserId().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getCourseId()==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserException.PASSWORD_NULL); else data.append("&").append(URLEncoder.encode("enrolments["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getCourseId().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getTimeStart()!=null) data.append("&").append(URLEncoder.encode("enrolments["+i+"][timestart]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getTimeStart().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getTimeEnd()!=null) data.append("&").append(URLEncoder.encode("enrolments["+i+"][timeend]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getTimeEnd().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getSuspend()!=null) data.append("&").append(URLEncoder.encode("enrolments["+i+"][suspend]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getSuspend().toString(), MoodleServices.ENCODING.toString()));
            }
            data.trimToSize();
            NodeList elements=MoodleCallRestWebService.call(data.toString());
            for (int j=0;j<elements.getLength();j+=2) {
                hash.put(elements.item(j+1).getTextContent(), elements.item(j).getTextContent());
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUserEnrolment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void __enrolUsers(String url, String token, MoodleUserEnrolment[] users) throws MoodleRestUserEnrolmentException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestUserEnrolmentException(MoodleRestException.NO_LEGACY);
        Hashtable hash=new Hashtable();
        String functionCall=MoodleServices.ENROL_MANUAL_ENROL_USERS.name();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<users.length;i++) {
                if (users[i]==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserEnrolmentException.USER_NULL);
                if (users[i].getRoleId()==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserException.USERNAME_NULL); else data.append("&").append(URLEncoder.encode("enrolments["+i+"][roleid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getRoleId().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getUserId()==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserException.PASSWORD_NULL); else data.append("&").append(URLEncoder.encode("enrolments["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getUserId().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getCourseId()==null) throw new MoodleRestUserEnrolmentException(MoodleRestUserException.PASSWORD_NULL); else data.append("&").append(URLEncoder.encode("enrolments["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getCourseId().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getTimeStart()!=null) data.append("&").append(URLEncoder.encode("enrolments["+i+"][timestart]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getTimeStart().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getTimeEnd()!=null) data.append("&").append(URLEncoder.encode("enrolments["+i+"][timeend]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getTimeEnd().toString(), MoodleServices.ENCODING.toString()));
                if (users[i].getSuspend()!=null) data.append("&").append(URLEncoder.encode("enrolments["+i+"][suspend]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(users[i].getSuspend().toString(), MoodleServices.ENCODING.toString()));
            }
            data.trimToSize();
            NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
            for (int j=0;j<elements.getLength();j+=2) {
                hash.put(elements.item(j+1).getTextContent(), elements.item(j).getTextContent());
            }
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
