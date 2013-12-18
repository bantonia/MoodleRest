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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import org.w3c.dom.NodeList;
import java.io.Serializable;

/**
 * <p>Class containing the static routines used to create or update Moodle courses.</p>
 * 
 * @author Bill Antonia
 * @see MoodleCourse
 */
public class MoodleRestCourse implements Serializable {
 
   // private static final int BUFFER_MAX=4000;

    /**
     * <p>Constructor to create a MoodleRestCourse object.<br />
     * Not required to be used as all member methods are static and are called via the class name.</p>
     */
    public MoodleRestCourse(){}

    /**
     * <p>Method to return an array of MoodleCourse objects of all courses within the installation.<br />
     * This call communicates with the Moodle WebServices.</p>
     * 
     * @return MoodleCourse[]
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static MoodleCourse[] getAllCourses() throws MoodleRestException, UnsupportedEncodingException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_COURSE_GET_COURSES.toString():MoodleServices.CORE_COURSE_GET_COURSES.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestCourseException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        //data.append("&").append(URLEncoder.encode("options[ids]", MoodleServices.ENCODING.toString()));
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        ArrayList<MoodleCourse> courses=null;
        OptionParameter param=null;
        MoodleCourse course=null;
        for (int j=0;j<elements.getLength();j++) {
          String parent;
          String content=elements.item(j).getTextContent();
          String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          try {
            parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          } catch (java.lang.NullPointerException ex) {
            parent="RESPONSE";
          }
          if (parent.equals("RESPONSE")) {
            if (nodeName.equals("id")) {
              if (courses==null) {
                courses=new ArrayList<MoodleCourse>();
              }
              course=new MoodleCourse(Long.parseLong(content));
              courses.add(course);
            } else {
              course.setMoodleCourseField(nodeName, content);
            }
          } else {
            if (parent.equals("courseformatoptions")) {
              if (nodeName.equals("name")) {
                param=new OptionParameter();
                course.addCourseformatoptions(param);
                param.setName(content);
              } else {
                param.setValue(content);
              }
            }
          }
        }
        MoodleCourse[] results=null;
        if (courses!=null) {
          results=new MoodleCourse[courses.size()];
          results=courses.toArray(results);
        }
        return results;
    }

    public MoodleCourse[] __getAllCourses(String url, String token) throws MoodleRestException, UnsupportedEncodingException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_COURSE_GET_COURSES.toString():MoodleServices.CORE_COURSE_GET_COURSES.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        //data.append("&").append(URLEncoder.encode("options[ids]", MoodleServices.ENCODING.toString()));
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        ArrayList<MoodleCourse> courses=null;
        OptionParameter param=null;
        MoodleCourse course=null;
        for (int j=0;j<elements.getLength();j++) {
          String parent;
          String content=elements.item(j).getTextContent();
          String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          try {
            parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          } catch (java.lang.NullPointerException ex) {
            parent="RESPONSE";
          }
          if (parent.equals("RESPONSE")) {
            if (nodeName.equals("id")) {
              if (courses==null) {
                courses=new ArrayList<MoodleCourse>();
              }
              course=new MoodleCourse(Long.parseLong(content));
              courses.add(course);
            } else {
              course.setMoodleCourseField(nodeName, content);
            }
          } else {
            if (parent.equals("courseformatoptions")) {
              if (nodeName.equals("name")) {
                param=new OptionParameter();
                course.addCourseformatoptions(param);
                param.setName(content);
              } else {
                param.setValue(content);
              }
            }
          }
        }
        MoodleCourse[] results=null;
        if (courses!=null) {
          results=new MoodleCourse[courses.size()];
          results=courses.toArray(results);
        }
        return results;
    }

    /**
     * <p>Method to return a MoodleCourse object given the id of the course within Moodle.<br />
     * This call communicates with the Moodle WebServices.</p>
     * 
     * @param id Moodle id of the course to fetch
     * @return MoodleCourse object
     * @throws MoodleRestCourseException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleCourse getCourseFromId(long id) throws MoodleRestCourseException, UnsupportedEncodingException, MoodleRestException {
        long[] a=new long[1];
        a[0]=id;
        MoodleCourse[] crs=getCoursesById(a);
        return crs[0];
    }

    /**
     * <p>Method to return an array of MoodleCourse objects given an array of id's of the courses within Moodle.<br />
     * This call communicates with the Moodle WebServices.</p>
     * 
     * @param courseids Array of Moodle course ids
     * @return MoodleCourse[] Array of MoodleCourse objects
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static MoodleCourse[] getCoursesById(long[] courseids) throws MoodleRestException, UnsupportedEncodingException{
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_COURSE_GET_COURSES.toString():MoodleServices.CORE_COURSE_GET_COURSES.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestCourseException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<courseids.length;i++) {
            if (courseids[i]<1) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" courseid"); data.append("&").append(URLEncoder.encode("options[ids]["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(courseids[i]);
        }
        data.trimToSize();
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        ArrayList<MoodleCourse> courses=null;
        OptionParameter param=null;
        MoodleCourse course=null;
        for (int j=0;j<elements.getLength();j++) {
          String parent;
          String content=elements.item(j).getTextContent();
          String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          try {
            parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          } catch (java.lang.NullPointerException ex) {
            parent="RESPONSE";
          }
          if (parent.equals("RESPONSE")) {
            if (nodeName.equals("id")) {
              if (courses==null) {
                courses=new ArrayList<MoodleCourse>();
              }
              course=new MoodleCourse(Long.parseLong(content));
              courses.add(course);
            } else {
              course.setMoodleCourseField(nodeName, content);
            }
          } else {
            if (parent.equals("courseformatoptions")) {
              if (nodeName.equals("name")) {
                param=new OptionParameter();
                course.addCourseformatoptions(param);
                param.setName(content);
              } else {
                param.setValue(content);
              }
            }
          }
        }
        MoodleCourse[] results=null;
        if (courses!=null) {
          results=new MoodleCourse[courses.size()];
          results=courses.toArray(results);
        }
        return results;
    }

    public MoodleCourse __getCourseFromId(String url, String token, long id) throws MoodleRestCourseException, UnsupportedEncodingException, MoodleRestException {
        long[] a=new long[1];
        a[0]=id;
        MoodleCourse[] crs=__getCoursesById(url, token, a);
        return crs[0];
    }

    public MoodleCourse[] __getCoursesById(String url, String token, long[] courseids) throws MoodleRestException, UnsupportedEncodingException{
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_COURSE_GET_COURSES.toString():MoodleServices.CORE_COURSE_GET_COURSES.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<courseids.length;i++) {
            if (courseids[i]<1) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" courseid"); data.append("&").append(URLEncoder.encode("options[ids]["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(courseids[i]);
        }
        data.trimToSize();
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        ArrayList<MoodleCourse> courses=null;
        OptionParameter param=null;
        MoodleCourse course=null;
        for (int j=0;j<elements.getLength();j++) {
          String parent;
          String content=elements.item(j).getTextContent();
          String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          try {
            parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          } catch (java.lang.NullPointerException ex) {
            parent="RESPONSE";
          }
          if (parent.equals("RESPONSE")) {
            if (nodeName.equals("id")) {
              if (courses==null) {
                courses=new ArrayList<MoodleCourse>();
              }
              course=new MoodleCourse(Long.parseLong(content));
              courses.add(course);
            } else {
              course.setMoodleCourseField(nodeName, content);
            }
          } else {
            if (parent.equals("courseformatoptions")) {
              if (nodeName.equals("name")) {
                param=new OptionParameter();
                course.addCourseformatoptions(param);
                param.setName(content);
              } else {
                param.setValue(content);
              }
            }
          }
        }
        MoodleCourse[] results=null;
        if (courses!=null) {
          results=new MoodleCourse[courses.size()];
          results=courses.toArray(results);
        }
        return results;
    }

    /**
     * <p>Method to create a MoodleCourse given the details of the course in a MoodleCourse object.<br />
     * This call communicates with the Moodle WebServices.</p>
     * 
     * @param course MoodleCourse object. Needs to have shortname,fullname and categoryid completed before this call.
     * @return MoodleCourse object with the id updated to the course id within Moodle.
     * @throws MoodleRestCourseException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleCourse createCourse(MoodleCourse course) throws MoodleRestCourseException, UnsupportedEncodingException, MoodleRestException {
        MoodleCourse[] a=new MoodleCourse[1];
        a[0]=course;
        MoodleCourse[] crs=createCourses(a);
        return crs[0];
    }

    /**
     * <p>Method to create a MoodleCourse given the details of the course in a MoodleCourse object.<br />
     * This call communicates with the Moodle WebServices.</p>
     * 
     * @param course MoodleCourse[]. Array of MoodleCourse each initialised with shortname,fullname and categoryid before the call.
     * @return MoodleCourse[]. Updated array, each MoodleCourse object within the array having their id values updated to that in Moodle.
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static MoodleCourse[] createCourses(MoodleCourse[] course) throws MoodleRestException, UnsupportedEncodingException {
        Hashtable hash=new Hashtable();
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_COURSE_CREATE_COURSES.toString():MoodleServices.CORE_COURSE_CREATE_COURSES.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestCourseException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<course.length;i++) {
            if (course[i]==null) throw new MoodleRestCourseException();
            if (course[i].getShortname()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" shortname"); else data.append("&").append(URLEncoder.encode("courses["+i+"][shortname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getShortname(), MoodleServices.ENCODING.toString()));
            if (course[i].getFullname()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" fullname"); else data.append("&").append(URLEncoder.encode("courses["+i+"][fullname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getFullname(), MoodleServices.ENCODING.toString()));
            if (course[i].getCategoryId()==-1) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" categoryid"); else data.append("&").append(URLEncoder.encode("courses["+i+"][categoryid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getCategoryId(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][summaryformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getSummaryFormat(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getFormat(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][showgrades]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getShowGrades(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][newsitems]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getNewsItems(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][numsections]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getNumSections(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][maxbytes]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getMaxBytes(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][showreports]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getShowReports(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][hiddensections]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getHiddenSections(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][groupmode]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getGroupMode(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][groupmodeforce]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getGroupModeForce(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][defaultgroupingid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getDefaultGroupingId(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][enablecompletion]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getEnableCompletion()?1:0), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][completionstartonenrol]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getCompletionStartOnEnrol()?1:0), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][completionnotify]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getCompletionNotify()?1:0), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][visible]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getVisible()?1:0), MoodleServices.ENCODING.toString()));
            if (course[i].getSummary()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][summary]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getSummary(), MoodleServices.ENCODING.toString()));
            if (course[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getIdNumber(), MoodleServices.ENCODING.toString()));
            if (course[i].getLang()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getLang(), MoodleServices.ENCODING.toString()));
            if (course[i].getForceTheme()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][forcetheme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getForceTheme(), MoodleServices.ENCODING.toString()));
            if (course[i].getStartDate()!=-1) data.append("&").append(URLEncoder.encode("courses["+i+"][startdate]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getStartDate(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        for (int j=0;j<elements.getLength();j+=2) {
            hash.put(elements.item(j+1).getTextContent(), elements.item(j).getTextContent());
        }
        for (int i=0;i<course.length;i++) {
            if (hash.containsKey(course[i].getShortname()))
                course[i].setId(Long.parseLong((String)(hash.get(course[i].getShortname()))));
            else
                course[i]=null;
        }
        return course;
    }
    
    public MoodleCourse __createCourse(String url, String token, MoodleCourse course) throws MoodleRestCourseException, UnsupportedEncodingException, MoodleRestException {
        MoodleCourse[] a=new MoodleCourse[1];
        a[0]=course;
        MoodleCourse[] crs=__createCourses(url, token, a);
        return crs[0];
    }

    public MoodleCourse[] __createCourses(String url, String token, MoodleCourse[] course) throws MoodleRestException, UnsupportedEncodingException {
        Hashtable hash=new Hashtable();
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_COURSE_CREATE_COURSES.toString():MoodleServices.CORE_COURSE_CREATE_COURSES.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<course.length;i++) {
            if (course[i]==null) throw new MoodleRestCourseException();
            if (course[i].getShortname()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" shortname"); else data.append("&").append(URLEncoder.encode("courses["+i+"][shortname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getShortname(), MoodleServices.ENCODING.toString()));
            if (course[i].getFullname()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" fullname"); else data.append("&").append(URLEncoder.encode("courses["+i+"][fullname]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getFullname(), MoodleServices.ENCODING.toString()));
            if (course[i].getCategoryId()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" categoryid"); else data.append("&").append(URLEncoder.encode("courses["+i+"][categoryid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getCategoryId(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][summaryformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getSummaryFormat(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getFormat(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][showgrades]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getShowGrades(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][newsitems]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getNewsItems(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][numsections]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getNumSections(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][maxbytes]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getMaxBytes(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][showreports]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getShowReports(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][hiddensections]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getHiddenSections(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][groupmode]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getGroupMode(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][groupmodeforce]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getGroupModeForce(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][defaultgroupingid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getDefaultGroupingId(), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][enablecompletion]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getEnableCompletion()?1:0), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][completionstartonenrol]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getCompletionStartOnEnrol()?1:0), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][completionnotify]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getCompletionNotify()?1:0), MoodleServices.ENCODING.toString()));
            data.append("&").append(URLEncoder.encode("courses["+i+"][visible]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(course[i].getVisible()?1:0), MoodleServices.ENCODING.toString()));
            if (course[i].getSummary()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][summary]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getSummary(), MoodleServices.ENCODING.toString()));
            if (course[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getIdNumber(), MoodleServices.ENCODING.toString()));
            if (course[i].getLang()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][lang]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getLang(), MoodleServices.ENCODING.toString()));
            if (course[i].getForceTheme()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][forcetheme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(course[i].getForceTheme(), MoodleServices.ENCODING.toString()));
            if (course[i].getStartDate()!=null) data.append("&").append(URLEncoder.encode("courses["+i+"][startdate]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+course[i].getStartDate(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        for (int j=0;j<elements.getLength();j+=2) {
            hash.put(elements.item(j+1).getTextContent(), elements.item(j).getTextContent());
        }
        for (int i=0;i<course.length;i++) {
            if (hash.containsKey(course[i].getShortname()))
                course[i].setId(Long.parseLong((String)(hash.get(course[i].getShortname()))));
            else
                course[i]=null;
        }
        return course;
    }
    
    //core_course_get_content
    /**
     * <p>Method to get the content of a course given the Moodle id of the course.</p>
     * 
     * @param courseId
     * @param options
     * @return MoodleCourseContent[]
     * @throws UnsupportedEncodingException
     * @throws MoodleRestCourseException
     * @throws MoodleRestException
     */
    public static MoodleCourseContent[] getCourseContent(long courseId, MoodleCourseContentOption[] options) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_GET_CONTENTS.toString();
      if (MoodleCallRestWebService.getAuth()==null)
          throw new MoodleRestCourseException();
      else
          data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseId, MoodleServices.ENCODING.toString()));
      if (options!=null)
        for (int i=0; i<options.length; i++) {
          data.append("&").append(URLEncoder.encode("options["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getName(), MoodleServices.ENCODING.toString()));
          data.append("&").append(URLEncoder.encode("options["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getValue(), MoodleServices.ENCODING.toString()));
        }
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      MoodleCourseContent result=null;
      MoodleModule module=null;
      MoodleModuleContent moduleContent=null;
      Vector<MoodleCourseContent> v=new Vector();
      for (int i=0;i<elements.getLength();i++) {
        String parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
        if (parent.equals("KEY"))
          parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(i).getTextContent();
        String nodeName=elements.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("RESPONSE") && nodeName.equals("id")) {
          if (result!=null) {
            if (module!=null) {
              if (moduleContent!=null)
                module.addContent(moduleContent);
              result.addMoodleModule(module);
            }
            v.add(result);
            module=null;
            moduleContent=null;
          }
          result=new MoodleCourseContent(Long.parseLong(content));
        } else {
          if (parent.equals("RESPONSE")) {
            result.setMoodleCourseContentField(nodeName, content);
          } else {
            if (parent.equals("modules") &&  nodeName.equals("id")) {
              if (module!=null) {
                if (moduleContent!=null)
                  module.addContent(moduleContent);
                result.addMoodleModule(module);
              }
              module=new MoodleModule(Long.parseLong(content));
              moduleContent=null;
            } else {
              if (parent.equals("modules")) {
                module.setMoodleModuleField(nodeName, content);
              } else {
                if (parent.equals("contents") &&  nodeName.equals("type")) {
                  if (moduleContent!=null) {
                    module.addContent(moduleContent);
                  }
                  moduleContent=new MoodleModuleContent();
                  moduleContent.setType(content);
                } else {
                  // Contents of module left other than the type
                  moduleContent.setMoodleModuleContentField(nodeName, content);
                }
              }
            }
          }
        }
      }
      if (result!=null) {
        if (module!=null) {
          if (moduleContent!=null)
            module.addContent(moduleContent);
          result.addMoodleModule(module);
        }
        v.add(result);
      }
      if (v.isEmpty())
        return null;
      MoodleCourseContent[] results=new MoodleCourseContent[v.size()];
      for (int i=0; i<v.size(); i++) {
        results[i]=v.get(i);
      }
      return results;
    }
    
    public MoodleCourseContent[] __getCourseContent(String url, String token, long courseId, MoodleCourseContentOption[] options) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_GET_CONTENTS.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseId, MoodleServices.ENCODING.toString()));
      if (options!=null)
        for (int i=0; i<options.length; i++) {
          data.append("&").append(URLEncoder.encode("options["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getName(), MoodleServices.ENCODING.toString()));
          data.append("&").append(URLEncoder.encode("options["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getValue(), MoodleServices.ENCODING.toString()));
        }
      NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
      MoodleCourseContent result=null;
      MoodleModule module=null;
      MoodleModuleContent moduleContent=null;
      Vector<MoodleCourseContent> v=new Vector();
      for (int i=0;i<elements.getLength();i++) {
        String parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
        if (parent.equals("KEY"))
          parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(i).getTextContent();
        String nodeName=elements.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("RESPONSE") && nodeName.equals("id")) {
          if (result!=null) {
            if (module!=null) {
              if (moduleContent!=null)
                module.addContent(moduleContent);
              result.addMoodleModule(module);
            }
            v.add(result);
            module=null;
            moduleContent=null;
          }
          result=new MoodleCourseContent(Long.parseLong(content));
        } else {
          if (parent.equals("RESPONSE")) {
            result.setMoodleCourseContentField(nodeName, content);
          } else {
            if (parent.equals("modules") &&  nodeName.equals("id")) {
              if (module!=null) {
                if (moduleContent!=null)
                  module.addContent(moduleContent);
                result.addMoodleModule(module);
              }
              module=new MoodleModule(Long.parseLong(content));
              moduleContent=null;
            } else {
              if (parent.equals("modules")) {
                module.setMoodleModuleField(nodeName, content);
              } else {
                if (parent.equals("contents") &&  nodeName.equals("type")) {
                  if (moduleContent!=null) {
                    module.addContent(moduleContent);
                  }
                  moduleContent=new MoodleModuleContent();
                  moduleContent.setType(content);
                } else {
                  // Contents of module left other than the type
                  moduleContent.setMoodleModuleContentField(nodeName, content);
                }
              }
            }
          }
        }
      }
      if (result!=null) {
        if (module!=null) {
          if (moduleContent!=null)
            module.addContent(moduleContent);
          result.addMoodleModule(module);
        }
        v.add(result);
      }
      if (v.isEmpty())
        return null;
      MoodleCourseContent[] results=new MoodleCourseContent[v.size()];
      for (int i=0; i<v.size(); i++) {
        results[i]=v.get(i);
      }
      return results;
    }

    /**
     * <p>From Moodle 2.3</p>
     * @param category
     * @return
     * @throws UnsupportedEncodingException
     * @throws MoodleRestCourseException
     * @throws MoodleRestException
     */
    public static MoodleCategory createCategory(MoodleCategory category) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      MoodleCategory[] categories=new MoodleCategory[1];
      categories[0]=category;
      MoodleCategory[] createdCategory = createCategories(categories);
      return createdCategory[0];
    }

    /**
     * <p>From Moodle 2.3</p>
     * @param categories
     * @return
     * @throws UnsupportedEncodingException
     * @throws MoodleRestCourseException
     * @throws MoodleRestException
     */
    public static MoodleCategory[] createCategories(MoodleCategory[] categories) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_CREATE_CATEGORIES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestCourseException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      Hashtable<String, MoodleCategory> catStore=new Hashtable();
      for (int i=0; i<categories.length; i++) {
        if (categories[i].getName()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" name"); data.append("&").append(URLEncoder.encode("categories["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getName(), MoodleServices.ENCODING.toString()));
        if (categories[i].getParent()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" parent"); data.append("&").append(URLEncoder.encode("categories["+i+"][parent]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getParent(), MoodleServices.ENCODING.toString()));
        if (categories[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getIdNumber(), MoodleServices.ENCODING.toString()));
        if (categories[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getDescription(), MoodleServices.ENCODING.toString()));
        if (categories[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getTheme(), MoodleServices.ENCODING.toString()));
        catStore.put(categories[i].getName(), categories[i]);
      }
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      long id=-1;
      for (int i=0;i<elements.getLength();i++) {
        String parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
        if (parent.equals("KEY"))
          parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(i).getTextContent();
        String nodeName=elements.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("RESPONSE") && nodeName.equals("id")) {
          id=Long.parseLong(content);
        } else {
          if (parent.equals("RESPONSE") && nodeName.equals("name")) {
            if (catStore.containsKey(content))
              catStore.get(content).setId(id);
          }
        }
      }
      return categories;
    }

    public MoodleCategory __createCategory(String url, String token, MoodleCategory category) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      MoodleCategory[] categories=new MoodleCategory[1];
      categories[0]=category;
      MoodleCategory[] createdCategory = __createCategories(url, token, categories);
      return createdCategory[0];
    }
    
    public MoodleCategory[] __createCategories(String url, String token, MoodleCategory[] categories) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_CREATE_CATEGORIES.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      Hashtable<String, MoodleCategory> catStore=new Hashtable();
      for (int i=0; i<categories.length; i++) {
        if (categories[i].getName()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" name"); data.append("&").append(URLEncoder.encode("categories["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getName(), MoodleServices.ENCODING.toString()));
        if (categories[i].getParent()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" parent"); data.append("&").append(URLEncoder.encode("categories["+i+"][parent]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getParent(), MoodleServices.ENCODING.toString()));
        if (categories[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getIdNumber(), MoodleServices.ENCODING.toString()));
        if (categories[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getDescription(), MoodleServices.ENCODING.toString()));
        if (categories[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getTheme(), MoodleServices.ENCODING.toString()));
        catStore.put(categories[i].getName(), categories[i]);
      }
      NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
      long id=-1;
      for (int i=0;i<elements.getLength();i++) {
        String parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
        if (parent.equals("KEY"))
          parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(i).getTextContent();
        String nodeName=elements.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("RESPONSE") && nodeName.equals("id")) {
          id=Long.parseLong(content);
        } else {
          if (parent.equals("RESPONSE") && nodeName.equals("name")) {
            if (catStore.containsKey(content))
              catStore.get(content).setId(id);
          }
        }
      }
      return categories;
    }
    
    /**
     * <p>From Moodle 2.3</p>
     * <p>If the contents are to be moved to a new parent, set the parent attribute of the MoodleCategory object to the id of the parent. Otherwise set it to -1.</p>
     * @param category
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static void deleteCategory(MoodleCategory category) throws MoodleRestException, UnsupportedEncodingException {
      MoodleCategory[] categories=new MoodleCategory[1];
      categories[0]=category;
      deleteCategories(categories);
    }
    
    /**
     * <p>From Moodle 2.3</p>
     * <p>If the contents are to be moved to a new parent, set the parent attribute of the MoodleCategory objects to the id of the parent. Otherwise set it to -1.</p>
     * @param categories
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static void deleteCategories(MoodleCategory[] categories) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_DELETE_CATEGORIES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestCourseException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0; i<categories.length; i++) {
        if (categories[i].getId()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("categories["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getId(), MoodleServices.ENCODING.toString()));
        if (categories[i].getParent()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][newparent]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getParent(), MoodleServices.ENCODING.toString()));
      }
      MoodleCallRestWebService.call(data.toString());
    }
    
    public void __deleteCategory(String url, String token, MoodleCategory category) throws MoodleRestException, UnsupportedEncodingException {
      MoodleCategory[] categories=new MoodleCategory[1];
      categories[0]=category;
      __deleteCategories(url, token, categories);
    }
    
    public void __deleteCategories(String url, String token, MoodleCategory[] categories) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_DELETE_CATEGORIES.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0; i<categories.length; i++) {
        if (categories[i].getId()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("categories["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getId(), MoodleServices.ENCODING.toString()));
        if (categories[i].getParent()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][newparent]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getParent(), MoodleServices.ENCODING.toString()));
      }
      (new MoodleCallRestWebService()).__call(url,data.toString());
    }

    /**
     * <p>From Moodle 2.3</p>
     * @param category
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static void updateCategory(MoodleCategory category) throws MoodleRestException, UnsupportedEncodingException {
      MoodleCategory[] categories=new MoodleCategory[1];
      categories[0]=category;
      updateCategories(categories);
    }

    /**
     * <p>From Moodle 2.3</p>
     * @param categories
     * @throws UnsupportedEncodingException
     * @throws MoodleRestCourseException
     * @throws MoodleRestException
     */
    public static void updateCategories(MoodleCategory[] categories) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_UPDATE_CATEGORIES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestCourseException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0; i<categories.length; i++) {
        if (categories[i].getId()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("categories["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getId(), MoodleServices.ENCODING.toString()));
        if (categories[i].getName()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getName(), MoodleServices.ENCODING.toString()));
        if (categories[i].getParent()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][parent]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getParent(), MoodleServices.ENCODING.toString()));
        if (categories[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getIdNumber(), MoodleServices.ENCODING.toString()));
        if (categories[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getDescription(), MoodleServices.ENCODING.toString()));
        //if (categories[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getTheme(), MoodleServices.ENCODING.toString()));;
      }
      MoodleCallRestWebService.call(data.toString());
    }

    public void __updateCategory(String url, String token, MoodleCategory category) throws MoodleRestException, UnsupportedEncodingException {
      MoodleCategory[] categories=new MoodleCategory[1];
      categories[0]=category;
      __updateCategories(url, token, categories);
    }
    
    public void __updateCategories(String url, String token, MoodleCategory[] categories) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_UPDATE_CATEGORIES.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0; i<categories.length; i++) {
        if (categories[i].getId()==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("categories["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getId(), MoodleServices.ENCODING.toString()));
        if (categories[i].getName()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getName(), MoodleServices.ENCODING.toString()));
        if (categories[i].getParent()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][parent]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getParent(), MoodleServices.ENCODING.toString()));
        if (categories[i].getIdNumber()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getIdNumber(), MoodleServices.ENCODING.toString()));
        if (categories[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getDescription(), MoodleServices.ENCODING.toString()));
        //if (categories[i].getTheme()!=null) data.append("&").append(URLEncoder.encode("categories["+i+"][theme]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categories[i].getTheme(), MoodleServices.ENCODING.toString()));;
      }
      (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    /**
     *
     * @return MoodleCategory[]
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static MoodleCategory[] getCategories() throws MoodleRestException, UnsupportedEncodingException {
      return getCategories(0, true);
    }

    /**
     *
     * @param categoryId
     * @return MoodleCategory[]
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static MoodleCategory[] getCategories(long categoryId) throws MoodleRestException, UnsupportedEncodingException {
      return getCategories(categoryId, true);
    }

    /**
     *
     * @param categoryId
     * @param subcategories
     * @return MoodleCategory[]
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static MoodleCategory[] getCategories(long categoryId, boolean subcategories) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_GET_CATEGORIES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestCourseException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (categoryId<0) throw new MoodleRestCourseException(MoodleRestException.PARAMETER_RANGE+" categoryid"); data.append("&").append(URLEncoder.encode("categoryid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categoryId, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("addsubcategories", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(subcategories?1:0), MoodleServices.ENCODING.toString()));
      NodeList elements = MoodleCallRestWebService.call(data.toString());
      ArrayList<MoodleCategory> categories=new ArrayList();
      MoodleCategory category=null;
      for (int i=0;i<elements.getLength();i++) {
        String parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
        if (parent.equals("KEY"))
          parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(i).getTextContent();
        String nodeName=elements.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("RESPONSE") && nodeName.equals("id")) {
          if (category==null)
            category=new MoodleCategory();
          else {
            categories.add(category);
            category=new MoodleCategory();
          }
          category.setId(Long.parseLong(content));
        } else {
          category.setField(nodeName, content);
        }
      }
      if (category!=null)
        categories.add(category);
      MoodleCategory[] result=null;
      if (categories.size()>0)
        result=new MoodleCategory[categories.size()];
      for (int i=0; i<categories.size(); i++)
        result[i]=categories.get(i);
      return result;
    }
    
    public MoodleCategory[] __getCategories(String url, String token) throws MoodleRestException, UnsupportedEncodingException {
      return __getCategories(url, token, 0L, true);
    }

    public MoodleCategory[] __getCategories(String url, String token, Long categoryId) throws MoodleRestException, UnsupportedEncodingException {
      return __getCategories(url, token, categoryId, true);
    }

    public MoodleCategory[] __getCategories(String url, String token, Long categoryId, Boolean subcategories) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_GET_CATEGORIES.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (categoryId<0) throw new MoodleRestCourseException(MoodleRestException.PARAMETER_RANGE+" categoryid"); data.append("&").append(URLEncoder.encode("categoryid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categoryId, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("addsubcategories", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(subcategories?1:0), MoodleServices.ENCODING.toString()));
      NodeList elements = (new MoodleCallRestWebService()).__call(url,data.toString());
      ArrayList<MoodleCategory> categories=new ArrayList();
      MoodleCategory category=null;
      for (int i=0;i<elements.getLength();i++) {
        String parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getNodeName();
        if (parent.equals("KEY"))
          parent=elements.item(i).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(i).getTextContent();
        String nodeName=elements.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("RESPONSE") && nodeName.equals("id")) {
          if (category==null)
            category=new MoodleCategory();
          else {
            categories.add(category);
            category=new MoodleCategory();
          }
          category.setId(Long.parseLong(content));
        } else {
          category.setField(nodeName, content);
        }
      }
      if (category!=null)
        categories.add(category);
      MoodleCategory[] result=null;
      if (categories.size()>0)
        result=new MoodleCategory[categories.size()];
      for (int i=0; i<categories.size(); i++)
        result[i]=categories.get(i);
      return result;
    }
    
    /**
     * <p>From Moodle 2.3</p>
     * <p></p>
     * @param Course id
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static void deleteCourse(Long courseId) throws MoodleRestException, UnsupportedEncodingException {
      Long[] courseIds=new Long[1];
      courseIds[0]=courseId;
      deleteCourses(courseIds);
    }
    
    /**
     * <p>From Moodle 2.3</p>
     * <p></p>
     * @param Array of course Ids
     * @throws MoodleRestException
     * @throws UnsupportedEncodingException
     */
    public static void deleteCourses(Long[] courseIds) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_DELETE_COURSES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestCourseException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0; i<courseIds.length; i++) {
        if (courseIds[i]==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("courseids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseIds[i], MoodleServices.ENCODING.toString()));
      }
      MoodleCallRestWebService.call(data.toString());
    }
    
    public void __deleteCourse(String url, String token, Long courseId) throws MoodleRestException, UnsupportedEncodingException {
      Long[] courseIds=new Long[1];
      courseIds[0]=courseId;
      __deleteCourses(url, token, courseIds);
    }
    
    public void __deleteCourses(String url, String token, Long[] courseIds) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_DELETE_COURSES.toString();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0; i<courseIds.length; i++) {
        if (courseIds[i]==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("courseids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseIds[i], MoodleServices.ENCODING.toString()));
      }
      (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    public void deleteModules(Long[] moduleIds) throws MoodleRestException, UnsupportedEncodingException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_DELETE_MODULES.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestCourseException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (moduleIds!=null) {
        for (int i=0; i<moduleIds.length; i++) {
          if (moduleIds[i]==null) throw new MoodleRestCourseException(MoodleRestException.REQUIRED_PARAMETER+" id"); data.append("&").append(URLEncoder.encode("cmids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+moduleIds[i], MoodleServices.ENCODING.toString()));
        }
        MoodleCallRestWebService.call(data.toString());
      }
    }
    
    public static MoodleDuplicatedCourse duplicateCourse(Long courseId, String fullName, Long categoryId) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      return duplicateCourse(courseId, fullName, categoryId, null, null);
    }
    
    public static MoodleDuplicatedCourse duplicateCourse(Long courseId, String fullName, Long categoryId, Boolean visible) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      return duplicateCourse(courseId, fullName, categoryId, visible, null);
    }
    
    public static MoodleDuplicatedCourse duplicateCourse(Long courseId, String fullName, Long categoryId, MoodleCourseContentOption[] options) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      return duplicateCourse(courseId, fullName, categoryId, null, options);
    }
    
    public static MoodleDuplicatedCourse duplicateCourse(Long courseId, String fullName, Long categoryId, Boolean visible, MoodleCourseContentOption[] options) throws UnsupportedEncodingException, MoodleRestCourseException, MoodleRestException {
      if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_COURSE_DUPLICATE_COURSE.toString();
      if (MoodleCallRestWebService.getAuth()==null)
          throw new MoodleRestCourseException();
      else
          data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (courseId==null || fullName==null || categoryId==null) { throw new MoodleRestException(MoodleRestException.PARAMETER_CANNOT_BE_NULL); }
      data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseId, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("fullname", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+fullName, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("categoryid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+categoryId, MoodleServices.ENCODING.toString()));
      if (visible!=null) { data.append("&").append(URLEncoder.encode("visible", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(visible?1:0), MoodleServices.ENCODING.toString())); }
      if (options!=null) {
        for (int i=0; i<options.length; i++) {
          data.append("&").append(URLEncoder.encode("options["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getName(), MoodleServices.ENCODING.toString()));
          data.append("&").append(URLEncoder.encode("options["+i+"][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+options[i].getValue(), MoodleServices.ENCODING.toString()));
        }
      }
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      MoodleDuplicatedCourse duplicant=null;
      for (int j=0; j<elements.getLength(); j++) {
        String content=elements.item(j).getTextContent();
        String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (nodeName!=null) {
          if (nodeName.equals("id")) {
            duplicant=new MoodleDuplicatedCourse(Long.parseLong(content));
          } else {
            duplicant.setFieldValue(nodeName, content);
          }
        }
      }
      return duplicant;
    }
}
