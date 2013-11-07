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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NodeList;
import java.io.Serializable;

/**
 * <p>Class containing the static routines to manipulate the enrolment information within Moodle.<br />
 * Currently there is no method to obtain the contextid of a course through web services to initialise MoodleEnrolUser objects.</p>
 * 
 * @see MoodleCourseUser
 * @see MoodleEnrolUser
 * @author Bill Antonia
 */
public class MoodleRestEnrol implements Serializable {

  //  private static final int BUFFER_MAX=4000;

    /**
     * <p>Method to get all the enrolled users of a course with the given capability in the selected group who have the required active status.</p>
     * 
     * @param courseid Long
     * @param withcapability String
     * @param groupid long
     * @param onlyactive boolean
     * @return MoodleCourseUser[]
     * @throws MoodleRestEnrolException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleCourseUser[] getEnrolledUsers(Long courseid, String withcapability, Long groupid, Boolean onlyactive) throws MoodleRestEnrolException , UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        Vector v=new Vector();
        String functionCall=MoodleServices.MOODLE_ENROL_GET_ENROLLED_USERS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestEnrolException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (courseid==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseid, MoodleServices.ENCODING.toString()));
        if (withcapability==null || withcapability.equals("")) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("withcapability", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(withcapability, MoodleServices.ENCODING.toString()));
        if (groupid==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("groupid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groupid, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("onlyactive", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(onlyactive?1:0), MoodleServices.ENCODING.toString()));
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        MoodleCourseUser user;
        for (int j=0;j<elements.getLength();j+=2) {
            String content1=elements.item(j).getTextContent();
            String content2=elements.item(j+1).getTextContent();
            user=new MoodleCourseUser(Long.parseLong(content1),Long.parseLong(content2));
            v.add(user);
        }
        MoodleCourseUser[] users=new MoodleCourseUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleCourseUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public MoodleCourseUser[] __getEnrolledUsers(String url, String token, Long courseid, String withcapability, Long groupid, Boolean onlyactive) throws MoodleRestEnrolException , UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        Vector v=new Vector();
        String functionCall=MoodleServices.MOODLE_ENROL_GET_ENROLLED_USERS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (courseid==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseid, MoodleServices.ENCODING.toString()));
        if (withcapability==null || withcapability.equals("")) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("withcapability", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(withcapability, MoodleServices.ENCODING.toString()));
        if (groupid==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("groupid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groupid, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("onlyactive", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(onlyactive?1:0), MoodleServices.ENCODING.toString()));
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        MoodleCourseUser user;
        for (int j=0;j<elements.getLength();j+=2) {
            String content1=elements.item(j).getTextContent();
            String content2=elements.item(j+1).getTextContent();
            user=new MoodleCourseUser(Long.parseLong(content1),Long.parseLong(content2));
            v.add(user);
        }
        MoodleCourseUser[] users=new MoodleCourseUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleCourseUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }
    
    /**
     * <p>Method to get all the enrolled users of a course with the given capability in the selected group who have the required active status.</p>
     * 
     * @param courseid long
     * @param options OptionParameter[]
     * @return MoodleUser[]
     * @throws MoodleRestEnrolException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleUser[] getEnrolledUsers(Long courseid, OptionParameter[] options) throws MoodleRestEnrolException , UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestEnrolException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        Vector<MoodleUser> v=new Vector();
        String functionCall=MoodleServices.CORE_ENROL_GET_ENROLLED_USERS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestEnrolException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (courseid==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseid, MoodleServices.ENCODING.toString()));
        if (options!=null)
          for (int i=0; i<options.length; i++) {
            data.append("&").append(URLEncoder.encode("options["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(options[i].getName(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("options["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(options[i].getValue(), MoodleServices.ENCODING.toString()));
          }
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        MoodleUser user=null;
        for (int j=0;j<elements.getLength();j++) {
            if (user!=null && elements.item(j).getNodeName().equals("id")) {
              v.add(user);
              user=new MoodleUser();
            } else {
              if (elements.item(j).getNodeName().equals("id")) {
                user=new MoodleUser();
              }
            }
            //if (elements!=null && elements.item(j)!=null && elements.item(j).getNodeName()!=null && elements.item(j).getTextContent()!=null)
            if (user==null)
              throw new MoodleRestEnrolException();
            user.setMoodleUserField(elements.item(j).getNodeName(), elements.item(j).getTextContent());
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public MoodleUser[] __getEnrolledUsers(String url, String token, Long courseid, OptionParameter[] options) throws MoodleRestEnrolException , UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestEnrolException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        Vector<MoodleUser> v=new Vector();
        String functionCall=MoodleServices.CORE_ENROL_GET_ENROLLED_USERS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (courseid==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseid, MoodleServices.ENCODING.toString()));
        if (options!=null)
          for (int i=0; i<options.length; i++) {
            data.append("&").append(URLEncoder.encode("options["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(options[i].getName(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("options["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(options[i].getValue(), MoodleServices.ENCODING.toString()));
          }
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        MoodleUser user=null;
        for (int j=0;j<elements.getLength();j++) {
            if (user!=null && elements.item(j).getNodeName().equals("id")) {
              v.add(user);
              user=new MoodleUser();
            } else {
              if (elements.item(j).getNodeName().equals("id")) {
                user=new MoodleUser();
              }
            }
            //if (elements!=null && elements.item(j)!=null && elements.item(j).getNodeName()!=null && elements.item(j).getTextContent()!=null)
            if (user==null)
              throw new MoodleRestEnrolException();
            user.setMoodleUserField(elements.item(j).getNodeName(), elements.item(j).getTextContent());
        }
        MoodleUser[] users=new MoodleUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    /**
     * <p>Method to enrol a user in a Moodle course.<br />
     * Needs the contextid and roleid so problem!</p>
     * 
     * @param user MoodleEnrolUser
     * @throws UnsupportedEncodingException
     * @throws MoodleRestEnrolException
     * @throws MoodleRestException
     */
    public static void enrolUser(MoodleEnrolUser user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        MoodleEnrolUser[] a=new MoodleEnrolUser[1];
        a[0]=user;
        enrolUsers(a);
    }

    public void __enrolUser(String url, String token, MoodleEnrolUser user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        MoodleEnrolUser[] a=new MoodleEnrolUser[1];
        a[0]=user;
        __enrolUsers(url, token, a);
    }

    /**
     * <p>Method to enrol a number of users on Moodle courses.<br />
     * Needs the contextids and roleids so problem!</p>
     * 
     * @param user MoodleEnrolUser[]
     * @throws UnsupportedEncodingException
     * @throws MoodleRestEnrolException
     * @throws MoodleRestException
     */
    public static void enrolUsers(MoodleEnrolUser[] user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        try {
            StringBuilder data=new StringBuilder();
            String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_ROLE_ASSIGN.toString():MoodleServices.CORE_ROLE_ASSIGN_ROLES.toString();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestEnrolException();
            else
                data.append(MoodleCallRestWebService.getAuth());
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestEnrolException();
                if (user[i].getRoleId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][roleid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getRoleId(), MoodleServices.ENCODING.toString()));
                if (user[i].getUserId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getUserId(), MoodleServices.ENCODING.toString()));
                if (user[i].getContextId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][contextid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getContextId(), MoodleServices.ENCODING.toString()));
            }
            data.trimToSize();
            MoodleCallRestWebService.call(data.toString());
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestEnrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void __enrolUsers(String url, String token, MoodleEnrolUser[] user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        try {
            StringBuilder data=new StringBuilder();
            String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_ROLE_ASSIGN.toString():MoodleServices.CORE_ROLE_ASSIGN_ROLES.toString();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestEnrolException();
                if (user[i].getRoleId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][roleid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getRoleId(), MoodleServices.ENCODING.toString()));
                if (user[i].getUserId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getUserId(), MoodleServices.ENCODING.toString()));
                if (user[i].getContextId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][contextid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getContextId(), MoodleServices.ENCODING.toString()));
            }
            data.trimToSize();
            (new MoodleCallRestWebService()).__call(url,data.toString());
        }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestEnrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * <p>Method to un-enrol a user from a Moodle course.</p>
     * 
     * @param user MoodleEnrolUser
     * @throws UnsupportedEncodingException
     * @throws MoodleRestEnrolException
     */
    public static void unenrolUser(MoodleEnrolUser user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        MoodleEnrolUser[] a=new MoodleEnrolUser[1];
        a[0]=user;
        unenrolUsers(a);
    }

    public void __unenrolUser(String url, String token, MoodleEnrolUser user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        MoodleEnrolUser[] a=new MoodleEnrolUser[1];
        a[0]=user;
        __unenrolUsers(url, token, a);
    }

    /**
     * <p>Method to un-enrol a number of users from Moodle courses.</p>
     * 
     * @param user MoodleEnrolUser[]
     * @throws UnsupportedEncodingException
     * @throws MoodleRestEnrolException
     */
    public static void unenrolUsers(MoodleEnrolUser[] user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_ROLE_UNASSIGN.toString():MoodleServices.CORE_ROLE_UNASSIGN_ROLES.toString();
        try {
            StringBuilder data=new StringBuilder();
            if (MoodleCallRestWebService.getAuth()==null)
                throw new MoodleRestEnrolException();
            else
                data.append(MoodleCallRestWebService.getAuth());
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestEnrolException();
                if (user[i].getRoleId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("unassignments["+i+"][roleid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getRoleId(), MoodleServices.ENCODING.toString()));
                if (user[i].getUserId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("unassignments["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getUserId(), MoodleServices.ENCODING.toString()));
                if (user[i].getContextId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("unassignments["+i+"][contextid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getContextId(), MoodleServices.ENCODING.toString()));
            }
            data.trimToSize();
            MoodleCallRestWebService.call(data.toString());
         }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestEnrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void __unenrolUsers(String url, String token, MoodleEnrolUser[] user) throws UnsupportedEncodingException, MoodleRestEnrolException, MoodleRestException {
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_ROLE_UNASSIGN.toString():MoodleServices.CORE_ROLE_UNASSIGN_ROLES.toString();
        try {
            StringBuilder data=new StringBuilder();
            data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
            for (int i=0;i<user.length;i++) {
                if (user[i]==null) throw new MoodleRestEnrolException();
                if (user[i].getRoleId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("unassignments["+i+"][roleid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getRoleId(), MoodleServices.ENCODING.toString()));
                if (user[i].getUserId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("unassignments["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getUserId(), MoodleServices.ENCODING.toString()));
                if (user[i].getContextId()==null) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("unassignments["+i+"][contextid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+user[i].getContextId(), MoodleServices.ENCODING.toString()));
            }
            data.trimToSize();
            (new MoodleCallRestWebService()).__call(url,data.toString());
         }  catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MoodleRestEnrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //core_enrol_get_users_courses
    /**
     * <p>Method which will return the details of courses a user is enrolled on given the Moodle user id.</p>
     * 
     * @param userId
     * @return MoodleCourse[]
     * @throws UnsupportedEncodingException
     * @throws MoodleRestEnrolException
     * @throws MoodleRestCourseException
     * @throws MoodleRestException
     */
    public static MoodleCourse[] getUsersCourses(Long userId) throws UnsupportedEncodingException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestEnrolException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      Vector<MoodleCourse> v=new Vector();
      String functionCall=MoodleServices.CORE_ENROL_GET_USERS_COURSES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestEnrolException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (userId==null || userId<=0) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      MoodleCourse course=null;
      for (int j=0;j<elements.getLength();j++) {
        if (course!=null && elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals("id")) {
          v.add(course);
          course=new MoodleCourse();
        } else {
          if (elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals("id")) {
            course=new MoodleCourse();
          }
        }
        try {
          //if (elements!=null && elements.item(j)!=null && elements.item(j).getNodeName()!=null && elements.item(j).getTextContent()!=null) {
          if (course==null)
            throw new MoodleRestEnrolException();
          course.setMoodleCourseField(elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue(), elements.item(j).getTextContent());
          //}
        } catch (NullPointerException ex) {
          System.out.println("NullPointerException: NodeName="+elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue()+" NodeValue="+elements.item(j).getTextContent());
        }
      }
      if (course!=null)
        v.add(course);
      MoodleCourse[] courses=new MoodleCourse[v.size()];
      for (int i=0;i<v.size();i++) {
          courses[i]=v.get(i);
      }
      v.removeAllElements();
      for (int i=0; i<courses.length; i++) {
        courses[i]=MoodleRestCourse.getCourseFromId(courses[i].getId());
      }
      return courses;
    }

    public MoodleCourse[] __getUsersCourses(String url, String token, Long userId) throws UnsupportedEncodingException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestEnrolException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      Vector<MoodleCourse> v=new Vector();
      String functionCall=MoodleServices.CORE_ENROL_GET_USERS_COURSES.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (userId==null || userId<=0) throw new MoodleRestEnrolException(); else data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
      NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
      MoodleCourse course=null;
      for (int j=0;j<elements.getLength();j++) {
        if (course!=null && elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals("id")) {
          v.add(course);
          course=new MoodleCourse();
        } else {
          if (elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals("id")) {
            course=new MoodleCourse();
          }
        }
        try {
          //if (elements!=null && elements.item(j)!=null && elements.item(j).getNodeName()!=null && elements.item(j).getTextContent()!=null) {
          if (course==null)
            throw new MoodleRestEnrolException();
          course.setMoodleCourseField(elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue(), elements.item(j).getTextContent());
          //}
        } catch (NullPointerException ex) {
          System.out.println("NullPointerException: NodeName="+elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue()+" NodeValue="+elements.item(j).getTextContent());
        }
      }
      if (course!=null)
        v.add(course);
      MoodleCourse[] courses=new MoodleCourse[v.size()];
      for (int i=0;i<v.size();i++) {
          courses[i]=v.get(i);
      }
      v.removeAllElements();
      for (int i=0; i<courses.length; i++) {
        courses[i]=MoodleRestCourse.getCourseFromId(courses[i].getId());
      }
      return courses;
    }

}
