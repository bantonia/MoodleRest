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
import java.util.Hashtable;
import java.util.Vector;
import org.w3c.dom.NodeList;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>Class containing the static routines to manipulate Moodle groups and users within course groups.</p>
 *
 * @author Bill Antonia
 * @see MoodleGroup
 * @see MoodleGroupUser
 */
public class MoodleRestGroup implements Serializable {

   // private static final int BUFFER_MAX=4000;

    /**
     * <p>Method to create a new group in a Moodle course.</p>
     * 
     * @param group MoodleGroup
     * @return group MoodleGroup object
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroup createGroup(MoodleGroup group) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroup[] a=new MoodleGroup[1];
        a[0]=group;
        MoodleGroup[] gps=createGroups(a);
        return gps[0];
    }

    public MoodleGroup __createGroup(String url, String token, MoodleGroup group) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroup[] a=new MoodleGroup[1];
        a[0]=group;
        MoodleGroup[] gps=__createGroups(url, token, a);
        return gps[0];
    }

    /**
     * <p>Method to create new groups in Moodle courses.<br />
     * Groups to be created do not necessarily need to be within the same course.</p>
     * 
     * @param group MoodleGroup[]
     * @return group MoodleGroup[] Updated array of MoodleGroup objects. Group ids created by Moodle stored in the id attribute of each object.
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroup[] createGroups(MoodleGroup[] group) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Hashtable hash=new Hashtable();
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_CREATE_GROUPS.toString():MoodleServices.CORE_GROUP_CREATE_GROUPS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<group.length;i++) {
            if (group[i]==null) throw new MoodleRestGroupException();
            if (group[i].getCourseId()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getCourseId(), MoodleServices.ENCODING.toString()));
            if (group[i].getName()==null || group[i].getName().equals("")) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getName(), MoodleServices.ENCODING.toString()));
            if (group[i].getDescription()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getDescription(), MoodleServices.ENCODING.toString()));
            if (group[i].getEnrolmentKey()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][enrolmentkey]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getEnrolmentKey(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        for (int j=0;j<elements.getLength();j+=5) {
            hash.put(elements.item(j+2).getTextContent(), elements.item(j).getTextContent());
        }
        for (int i=0;i<group.length;i++) {
            if (hash.containsKey(group[i].getName()))
                group[i].setId(Long.parseLong((String)(hash.get(group[i].getName()))));
            else
                group[i]=null;
        }
        return group;
    }

    public MoodleGroup[] __createGroups(String url, String token, MoodleGroup[] group) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Hashtable hash=new Hashtable();
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_CREATE_GROUPS.toString():MoodleServices.CORE_GROUP_CREATE_GROUPS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<group.length;i++) {
            if (group[i]==null) throw new MoodleRestGroupException();
            if (group[i].getCourseId()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getCourseId(), MoodleServices.ENCODING.toString()));
            if (group[i].getName()==null || group[i].getName().equals("")) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getName(), MoodleServices.ENCODING.toString()));
            if (group[i].getDescription()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getDescription(), MoodleServices.ENCODING.toString()));
            if (group[i].getEnrolmentKey()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groups["+i+"][enrolmentkey]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+group[i].getEnrolmentKey(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        for (int j=0;j<elements.getLength();j+=5) {
            hash.put(elements.item(j+2).getTextContent(), elements.item(j).getTextContent());
        }
        for (int i=0;i<group.length;i++) {
            if (hash.containsKey(group[i].getName()))
                group[i].setId(Long.parseLong((String)(hash.get(group[i].getName()))));
            else
                group[i]=null;
        }
        return group;
    }

    /**
     * <p>Method to retrieve the information about a Moodle group from its id.</p>
     * 
     * @param groupid long
     * @return group MoodleGroup
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroup getGroupById(Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        MoodleGroup[] gps=getGroupsById(a);
        return gps[0];
    }

    public MoodleGroup __getGroupById(String url, String token, long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        MoodleGroup[] gps=__getGroupsById(url, token, a);
        return gps[0];
    }

    /**
     * <p>Method to fetch the details of a number of Moodle groups from their ids.</p>
     * 
     * @param groupids long[]
     * @return group MoodleGroup[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroup[] getGroupsById(Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleGroup group;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_GET_GROUPS.toString():MoodleServices.CORE_GROUP_GET_GROUPS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        group=null;
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroup(Long.parseLong(content));
                else {
                    v.add(group);
                    group=new MoodleGroup(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroup[] groups=new MoodleGroup[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroup)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }

    public MoodleGroup[] __getGroupsById(String url, String token, Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleGroup group;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_GET_GROUPS.toString():MoodleServices.CORE_GROUP_GET_GROUPS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        group=null;
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroup(Long.parseLong(content));
                else {
                    v.add(group);
                    group=new MoodleGroup(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroup[] groups=new MoodleGroup[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroup)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }

    /**
     * <p>Method to return the details of groups within a Moodle course from the course id.</p>
     * 
     * @param id long
     * @return groups MoodleGroup[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroup[] getGroupsFromCourseId(Long id) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleGroup group=null;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_GET_COURSE_GROUPS.toString():MoodleServices.CORE_GROUP_GET_COURSE_GROUPS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (id<1) throw new MoodleRestGroupException(); else  data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(id);
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroup(Long.parseLong(content));
                else  {
                    v.add(group);
                    group=new MoodleGroup(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroup[] groups=new MoodleGroup[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroup)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }

    public MoodleGroup[] __getGroupsFromCourseId(String url, String token, Long id) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleGroup group=null;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_GET_COURSE_GROUPS.toString():MoodleServices.CORE_GROUP_GET_COURSE_GROUPS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (id<1) throw new MoodleRestGroupException(); else  data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(id);
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroup(Long.parseLong(content));
                else  {
                    v.add(group);
                    group=new MoodleGroup(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroup[] groups=new MoodleGroup[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroup)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }

    /**
     * <p>Method to delete a Moodle group given its id.</p>
     * 
     * @param groupid long
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void deleteGroupById(Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        deleteGroupsById(a);
    }

    public void __deleteGroupById(String url, String token, Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        __deleteGroupsById(url, token, a);
    }

    /**
     * <p>Method to delete groups from Moodle given the ids of the groups.</p>
     * 
     * @param groupids long[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void deleteGroupsById(Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_DELETE_GROUPS.toString():MoodleServices.CORE_GROUP_DELETE_GROUPS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }

    public void __deleteGroupsById(String url, String token, Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_DELETE_GROUPS.toString():MoodleServices.CORE_GROUP_DELETE_GROUPS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }

    /**
     * <p>Method to retrieve details of the memberships of a Moodle group.</p>
     * 
     * @param groupid Long
     * @return groupUsers MoodleGroupUser[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroupUser[] getMembersFromGroupId(Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        return getMembersFromGroupIds(a);
    }

    public MoodleGroupUser[] __getMembersFromGroupId(String url, String token, Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        return __getMembersFromGroupIds(url, token, a);
    }

    /**
     * <p>Method to retrieve details of the memberships of a number of Moodle groups.</p>
     * 
     * @param groupids long[]
     * @return groupUsers MoodleGroupUser[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static MoodleGroupUser[] getMembersFromGroupIds(Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleGroupUser user;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_GET_GROUPMEMBERS.toString():MoodleServices.CORE_GROUP_GET_GROUP_MEMBERS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        user=null;
        for (int j=0;j<elements.getLength();j+=2) {
            String content1=elements.item(j).getTextContent();
            String content2=elements.item(j+1).getTextContent();
            user=new MoodleGroupUser(Long.parseLong(content1),Long.parseLong(content2));
            v.add(user);
        }
        if (user!=null)
            v.add(user);
        MoodleGroupUser[] users=new MoodleGroupUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleGroupUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    public MoodleGroupUser[] __getMembersFromGroupIds(String url, String token, Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Vector v=new Vector();
        MoodleGroupUser user;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_GET_GROUPMEMBERS.toString():MoodleServices.CORE_GROUP_GET_GROUP_MEMBERS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        user=null;
        for (int j=0;j<elements.getLength();j+=2) {
            String content1=elements.item(j).getTextContent();
            String content2=elements.item(j+1).getTextContent();
            user=new MoodleGroupUser(Long.parseLong(content1),Long.parseLong(content2));
            v.add(user);
        }
        if (user!=null)
            v.add(user);
        MoodleGroupUser[] users=new MoodleGroupUser[v.size()];
        for (int i=0;i<v.size();i++) {
            users[i]=(MoodleGroupUser)v.get(i);
        }
        v.removeAllElements();
        return users;
    }

    /**
     * <p>Method to add a users membership to a Moodle group.</p>
     * @param user MoodleGroupUser
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void addMemberToGroup(MoodleGroupUser user) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupUser[] users=new MoodleGroupUser[1];
        users[0]=user;
        addMembersToGroups(users);
    }

    public void __addMemberToGroup(String url, String token, MoodleGroupUser user) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupUser[] users=new MoodleGroupUser[1];
        users[0]=user;
        __addMembersToGroups(url, token, users);
    }

    /**
     * <p>Method to add a number of users memberships to a number of Moodle groups.</p>
     * 
     * @param users MoodleGroupUser[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void addMembersToGroups(MoodleGroupUser[] users) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_ADD_GROUPMEMBERS.toString():MoodleServices.CORE_GROUP_ADD_GROUP_MEMBERS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<users.length;i++) {
            if (users[i]==null) throw new MoodleRestGroupException();
            if (users[i].getGroupId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getGroupId(), MoodleServices.ENCODING.toString()));
            if (users[i].getUserId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getUserId(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }

    public void __addMembersToGroups(String url, String token, MoodleGroupUser[] users) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_ADD_GROUPMEMBERS.toString():MoodleServices.CORE_GROUP_ADD_GROUP_MEMBERS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<users.length;i++) {
            if (users[i]==null) throw new MoodleRestGroupException();
            if (users[i].getGroupId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getGroupId(), MoodleServices.ENCODING.toString()));
            if (users[i].getUserId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getUserId(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }

    /**
     * <p>Method to remove a users membership of a Moodle group.</p>
     * 
     * @param user MoodleGroupUser
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void deleteMemberOfGroup(MoodleGroupUser user) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupUser[] users=new MoodleGroupUser[1];
        users[0]=user;
        deleteMembersOfGroups(users);
    }

    public void __deleteMemberOfGroup(String url, String token, MoodleGroupUser user) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupUser[] users=new MoodleGroupUser[1];
        users[0]=user;
        __deleteMembersOfGroups(url, token, users);
    }

    /**
     * <p>Method to remove a number of users memberships from Moodle groups.</p>
     * 
     * @param users MoodleGroupUser[]
     * @throws MoodleRestGroupException
     * @throws UnsupportedEncodingException
     * @throws MoodleRestException
     */
    public static void deleteMembersOfGroups(MoodleGroupUser[] users) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_DELETE_GROUPMEMBERS.toString():MoodleServices.CORE_GROUP_DELETE_GROUP_MEMBERS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<users.length;i++) {
            if (users[i]==null) throw new MoodleRestGroupException();
            if (users[i].getGroupId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getGroupId(), MoodleServices.ENCODING.toString()));
            if (users[i].getUserId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getUserId(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }

    public void __deleteMembersOfGroups(String url, String token, MoodleGroupUser[] users) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_GROUP_DELETE_GROUPMEMBERS.toString():MoodleServices.CORE_GROUP_DELETE_GROUP_MEMBERS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<users.length;i++) {
            if (users[i]==null) throw new MoodleRestGroupException();
            if (users[i].getGroupId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getGroupId(), MoodleServices.ENCODING.toString()));
            if (users[i].getUserId()<1) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("members["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+users[i].getUserId(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    public static void assignGrouping(MoodleGroupGrouping[] groups) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_ASSIGN_GROUPING.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groups.length;i++) {
            if (groups[i]==null) throw new MoodleRestGroupException();
            if (groups[i].getGroupingid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][groupingid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getGroupingid(), MoodleServices.ENCODING.toString()));
            if (groups[i].getGroupid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getGroupid(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }

    public void __assignGrouping(String url, String token, MoodleGroupGrouping[] groups) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_ASSIGN_GROUPING.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groups.length;i++) {
            if (groups[i]==null) throw new MoodleRestGroupException();
            if (groups[i].getGroupingid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][groupingid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getGroupingid(), MoodleServices.ENCODING.toString()));
            if (groups[i].getGroupid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("assignments["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getGroupid(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    public static MoodleGroupGroupings[] createGroupings(MoodleGroupGroupings[] groups) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_CREATE_GROUPINGS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groups.length;i++) {
            if (groups[i]==null) throw new MoodleRestGroupException();
            if (groups[i].getCourseid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getCourseid(), MoodleServices.ENCODING.toString()));
            if (groups[i].getName()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getName(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescription()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescription(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescriptionformat()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][descriptionformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescriptionformat(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        for (int j=0, i=0;j<elements.getLength();j+=2) {
            if (j%10==0)
                groups[i++].setId(Long.parseLong(elements.item(j+1).getTextContent()));
        }
        return groups;
    }

    public MoodleGroupGroupings[] __createGroupings(String url, String token, MoodleGroupGroupings[] groups) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_CREATE_GROUPINGS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groups.length;i++) {
            if (groups[i]==null) throw new MoodleRestGroupException();
            if (groups[i].getCourseid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getCourseid(), MoodleServices.ENCODING.toString()));
            if (groups[i].getName()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getName(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescription()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescription(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescriptionformat()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][descriptionformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescriptionformat(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        for (int j=0, i=0;j<elements.getLength();j+=2) {
            if (j%10==0)
                groups[i++].setId(Long.parseLong(elements.item(j+1).getTextContent()));
        }
        return groups;
    }
    
    public static void deleteGroupingById(Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        deleteGroupingsById(a);
    }

    public void __deleteGroupingById(String url, String token, Long groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        Long[] a=new Long[1];
        a[0]=groupid;
        __deleteGroupingsById(url, token, a);
    }

    public static void deleteGroupingsById(Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_DELETE_GROUPINGS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupingids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }

    public void __deleteGroupingsById(String url, String token, Long[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_DELETE_GROUPINGS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i]<1) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupingids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i]);
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    public static MoodleGroupGroupings[] getGroupGroupingsFromCourseId(Long id) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException, MoodleGroupGroupingsException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        Vector v=new Vector();
        MoodleGroupGroupings group=null;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_GET_COURSE_GROUPINGS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (id<1 || id==null) throw new MoodleRestGroupException(); else  data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(id);
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                else  {
                    v.add(group);
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupGroupingsField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroupGroupings[] groups=new MoodleGroupGroupings[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroupGroupings)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }

    public MoodleGroupGroupings[] __getGroupGroupingsFromCourseId(String url, String token, Long id) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException, MoodleGroupGroupingsException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        Vector v=new Vector();
        MoodleGroupGroupings group=null;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_GET_COURSE_GROUPINGS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        if (id<1 || id==null) throw new MoodleRestGroupException(); else  data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(id);
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                else  {
                    v.add(group);
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupGroupingsField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroupGroupings[] groups=new MoodleGroupGroupings[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroupGroupings)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }
    
    public static MoodleGroupGroupings[] getGroupGroupingsFromCourseId(Long[] ids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException, MoodleGroupGroupingsException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        Vector v=new Vector();
        MoodleGroupGroupings group=null;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_GET_GROUPINGS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0; i<ids.length;i++)
          if (ids[i]<1 || ids[i]==null) throw new MoodleRestGroupException(); else  data.append("&").append(URLEncoder.encode("groupingids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(ids[i]);
        NodeList elements=MoodleCallRestWebService.call(data.toString());
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                else  {
                    v.add(group);
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupGroupingsField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroupGroupings[] groups=new MoodleGroupGroupings[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroupGroupings)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }

    public MoodleGroupGroupings[] __getGroupGroupingsFromCourseId(String url, String token, Long[] ids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException, MoodleGroupGroupingsException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        Vector v=new Vector();
        MoodleGroupGroupings group=null;
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_GET_GROUPINGS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0; i<ids.length;i++)
          if (ids[i]<1 || ids[i]==null) throw new MoodleRestGroupException(); else  data.append("&").append(URLEncoder.encode("groupingids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(ids[i]);
        NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
        for (int j=0;j<elements.getLength();j++) {
            String content=elements.item(j).getTextContent();
            String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            if (nodeName.equals("id")) {
                if (group==null)
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                else  {
                    v.add(group);
                    group=new MoodleGroupGroupings(Long.parseLong(content));
                }
            }
            if (group==null)
              throw new MoodleRestGroupException();
            group.setMoodleGroupGroupingsField(nodeName, content);
        }
        if (group!=null)
            v.add(group);
        MoodleGroupGroupings[] groups=new MoodleGroupGroupings[v.size()];
        for (int i=0;i<v.size();i++) {
            groups[i]=(MoodleGroupGroupings)v.get(i);
        }
        v.removeAllElements();
        return groups;
    }
    
    public static void unassignGrouping(MoodleGroupGrouping groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupGrouping[] a=new MoodleGroupGrouping[1];
        a[0]=groupid;
        unassignGroupings(a);
    }

    public void __unassignGrouping(String url, String token, MoodleGroupGrouping groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupGrouping[] a=new MoodleGroupGrouping[1];
        a[0]=groupid;
        __unassignGroupings(url, token, a);
    }
    
    public static void unassignGroupings(MoodleGroupGrouping[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_UNASSIGN_GROUPING.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i].getGroupingid()<1 || groupids[i]==null) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupingid["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i].getGroupingid());
            if (groupids[i].getGroupid()<1 || groupids[i]==null) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupid["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i].getGroupid());
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }
    
    public void __unassignGroupings(String url, String token, MoodleGroupGrouping[] groupids) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_UNASSIGN_GROUPING.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groupids.length;i++) {
            if (groupids[i].getGroupingid()<1 || groupids[i]==null) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupingid["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i].getGroupingid());
            if (groupids[i].getGroupid()<1 || groupids[i]==null) throw new MoodleRestGroupException(); data.append("&").append(URLEncoder.encode("groupid["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(groupids[i].getGroupid());
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    public static void updateGrouping(MoodleGroupGroupings groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupGroupings[] a=new MoodleGroupGroupings[1];
        a[0]=groupid;
        updateGroupings(a);
    }

    public void __updateGrouping(String url, String token, MoodleGroupGroupings groupid) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        MoodleGroupGroupings[] a=new MoodleGroupGroupings[1];
        a[0]=groupid;
        __updateGroupings(url, token, a);
    }
    
    public static void updateGroupings(MoodleGroupGroupings[] groups) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_UPDATE_GROUPINGS.toString();
        if (MoodleCallRestWebService.getAuth()==null)
            throw new MoodleRestGroupException();
        else
            data.append(MoodleCallRestWebService.getAuth());
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groups.length;i++) {
            if (groups[i]==null) throw new MoodleRestGroupException();
            if (groups[i].getCourseid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getCourseid(), MoodleServices.ENCODING.toString()));
            if (groups[i].getName()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getName(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescription()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescription(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescriptionformat()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][descriptionformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescriptionformat(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        MoodleCallRestWebService.call(data.toString());
    }

    public void __updateGroupings(String url, String token, MoodleGroupGroupings[] groups) throws MoodleRestGroupException, UnsupportedEncodingException, MoodleRestException {
        if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestGroupException(MoodleRestException.NO_LEGACY);
        StringBuilder data=new StringBuilder();
        String functionCall=MoodleServices.CORE_GROUP_UPDATE_GROUPINGS.toString();
        data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
        for (int i=0;i<groups.length;i++) {
            if (groups[i]==null) throw new MoodleRestGroupException();
            if (groups[i].getCourseid()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getCourseid(), MoodleServices.ENCODING.toString()));
            if (groups[i].getName()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getName(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescription()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescription(), MoodleServices.ENCODING.toString()));
            if (groups[i].getDescriptionformat()==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("groupings["+i+"][descriptionformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groups[i].getDescriptionformat(), MoodleServices.ENCODING.toString()));
        }
        data.trimToSize();
        (new MoodleCallRestWebService()).__call(url,data.toString());
    }
    
    public MoodleGroups getCourseUserGroups(Long courseId, Long userId) throws UnsupportedEncodingException, MoodleRestGroupException, MoodleRestException {
      return getCourseUserGroups(courseId, userId, 0);
    }
    
    public MoodleGroups getCourseUserGroups(Long courseId, Long userId, Integer groupingId) throws UnsupportedEncodingException, MoodleRestGroupException, MoodleRestException {
      StringBuilder data=new StringBuilder();
      String functionCall=MoodleServices.CORE_GROUP_GET_COURSE_USER_GROUPS.toString();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestGroupException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      if (courseId==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseId, MoodleServices.ENCODING.toString()));
      if (userId==null) throw new MoodleRestGroupException(); else data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
      if (groupingId==null) groupingId=0;
      data.append("&").append(URLEncoder.encode("groupingid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+groupingId, MoodleServices.ENCODING.toString()));
      data.trimToSize();
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      ArrayList<MoodleGroup> groups=null;
      ArrayList<MoodleWarning> warn=null;
      MoodleGroup group=null;
      MoodleWarning warning=null;
      for (int j=0; j<elements.getLength(); j++) {
        String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        String content=elements.item(j).getTextContent();
        String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (parent.equals("groups")) {
          if (nodeName.equals("id")) {
            if (groups==null) {
              groups=new ArrayList<MoodleGroup>();
            }
            group=new MoodleGroup();
            group.setCourseId(courseId);
            groups.add(group);
            group.setId(Long.parseLong(content));
          } else {
            group.setMoodleGroupField(nodeName, content);
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
      MoodleGroups groupsWithWarnings=new MoodleGroups();
      groupsWithWarnings.setGroups(groups);
      groupsWithWarnings.setWarnings(warn);
      return groupsWithWarnings;
    }
}
