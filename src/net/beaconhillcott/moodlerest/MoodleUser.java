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

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Class to hold the state of a MoodleUser object. Used when creating, updating or fetching user information in Moodle.
 * @see MoodleRestUser
 * @author Bill Antonia
 */
public class MoodleUser implements Serializable {

    /**
     *
     */
    public static final boolean EMAIL_FORMAT_NONE = false;
    /**
     *
     */
    public static final boolean EMAIL_FORMAT_HTML = true;
    
    private Long id=null;
    private String username=null;
    private String password=null;
    private String firstname=null;
    private String lastname=null;
    private String email=null;
    private String auth=null;
    private String idnumber=null;
    private String lang=null;
    private String theme=null;
    private String timezone=null;
    private Boolean mailformat=EMAIL_FORMAT_HTML;
    private String description=null;
    private String city=null;
    private String country=null;
    private String fullname = null;
    
    private String address=null;
    private String phone1=null;
    private String phone2=null;
    private String icq=null;
    private String skype=null;
    private String yahoo=null;
    private String msn=null;
    private String department=null;
    private String institution=null;
    private String interests=null;
    private Long firstaccess=null;
    private Long lastaccess=null;
    private Double confirmed=0.0;
    private Long descriptionformat=null;
    private String url=null;
    private String profileimageurlsmall=null;
    private String profileimageurl=null;
    private ArrayList<UserCustomField> customfields=null;
    private ArrayList<UserGroup> groups=null;
    private ArrayList<UserRole> roles=null;
    private ArrayList<UserPreference> preferences=null;
    private ArrayList<UserEnrolledCourse> enrolledcourses=null;

    /**
     *
     */
    public MoodleUser() {}

    /**
     *
     * @param id
     */
    public MoodleUser(Long id) {
        this.id=id;
    }

    /**
     *
     * @param username
     * @param password
     * @param firstname
     * @param lastname
     * @param email
     */
    public MoodleUser(String username, String password, String firstname, String lastname, String email) {
        this.username=username;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
    }

    /**
     *
     * @param nodeName
     * @param content
     */
    public void setMoodleUserField(String nodeName,String content) {
        if (nodeName.equals("id")) setId(Long.valueOf(content));
        if (nodeName.equals("username")) setUsername(content);
        if (nodeName.equals("firstname")) setFirstname(content);
        if (nodeName.equals("lastname")) setLastname(content);
        if (nodeName.equals("email")) setEmail(content);
        if (nodeName.equals("auth")) setAuth(content);
        if (nodeName.equals("idnumber") && content!=null) setIdNumber(content);
        if (nodeName.equals("lang") && content!=null) setLang(content);
        if (nodeName.equals("theme") && content!=null) setTheme(content);
        if (nodeName.equals("timezone") && content!=null) setTimezone(content);
        if (nodeName.equals("mailformat") && content!=null) setMailFormat(Integer.parseInt(content)==0?EMAIL_FORMAT_NONE:EMAIL_FORMAT_HTML);
        if (nodeName.equals("description") && content!=null) setDescription(content);
        if (nodeName.equals("descriptionformat") && content!=null) setDescriptionFormat(Long.valueOf(content));
        if (nodeName.equals("city") && content!=null) setCity(content);
        if (nodeName.equals("country") && content!=null) setCountry(content);
        if (nodeName.equals("fullname")) { setFullname(content);}
        if (nodeName.equals("address") && content!=null) setAddress(content);
        if (nodeName.equals("phone1") && content!=null) setPhone1(content);
        if (nodeName.equals("phone2") && content!=null) setPhone2(content);
        if (nodeName.equals("icq") && content!=null) setICQ(content);
        if (nodeName.equals("skype") && content!=null) setSkype(content);
        if (nodeName.equals("yahoo") && content!=null) setYahoo(content);
        if (nodeName.equals("msn") && content!=null) setMSN(content);
        if (nodeName.equals("department") && content!=null) setDepartment(content);
        if (nodeName.equals("institution") && content!=null) setInstitution(content);
        if (nodeName.equals("interests") && content!=null) setInterests(content);
        if (nodeName.equals("firstaccess") && content!=null) setFirstAccess(Long.valueOf(content));
        if (nodeName.equals("lastaccess") && content!=null) setLastAccess(Long.valueOf(content));
        if (nodeName.equals("confirmed") && content!=null) setConfirmed(Double.valueOf(content));
        if (nodeName.equals("url") && content!=null) setURL(content);
        if (nodeName.equals("profileimageurlsmall") && content!=null) setProfileImageURLSmall(content);
        if (nodeName.equals("profileimageurl") && content!=null) setProfileImageURL(content);
    }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

    
    /**
     *
     * @param type
     * @param value
     * @param name
     * @param shortname
     */
    public void addCustomField(String type, String value, String name, String shortname) {
      if (customfields==null)
        customfields=new ArrayList();
      UserCustomField field=new UserCustomField(type, value, name, shortname);
      customfields.add(field);
    }
    
    /**
     *
     * @param field
     */
    public void addCustomField(UserCustomField field) {
      if (customfields==null)
        customfields=new ArrayList();
      customfields.add(field);
    }
    
    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public void addGroup(Long id, String name, String description) {
      if (groups==null)
        groups=new ArrayList();
      UserGroup group=new UserGroup(id, name, description);
      groups.add(group);
    }
    
    /**
     *
     * @param group
     */
    public void addGroup(UserGroup group) {
      if (groups==null)
        groups=new ArrayList();
      groups.add(group);
    }
    
    /**
     *
     * @param roleid
     * @param name
     * @param shortname
     * @param sortorder
     */
    public void addRole(Long roleid, String name, String shortname, Integer sortorder) {
      if (roles==null)
        roles=new ArrayList();
      UserRole role=new UserRole(roleid, name, shortname, sortorder);
      roles.add(role);
    }
    
    /**
     *
     * @param role
     */
    public void addRole(UserRole role) {
      if (roles==null)
        roles=new ArrayList();
      roles.add(role);
    }
    
    /**
     *
     * @param name
     * @param value
     */
    public void addPreference(String name, String value) {
      if (preferences==null)
        preferences=new ArrayList();
      UserPreference preference=new UserPreference(name, value);
      preferences.add(preference);
    }
    
    /**
     *
     * @param preference
     */
    public void addPreference(UserPreference preference) {
      if (preferences==null)
        preferences=new ArrayList();
      preferences.add(preference);
    }
    
    /**
     *
     * @param id
     * @param fullname
     * @param shortname
     */
    public void addEnrolledCourse(Long id, String fullname, String shortname) {
      if (enrolledcourses==null)
        enrolledcourses=new ArrayList();
      UserEnrolledCourse course=new UserEnrolledCourse(id, fullname, shortname);
      enrolledcourses.add(course);
    }
    
    /**
     *
     * @param course
     */
    public void addEnrolledCourse(UserEnrolledCourse course) {
      if (enrolledcourses==null)
        enrolledcourses=new ArrayList();
      enrolledcourses.add(course);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<UserCustomField> getCustomFields() {
      return customfields;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<UserGroup> getGroups() {
      return groups;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<UserRole> getRoles() {
      return roles;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<UserPreference> getPreference() {
      return preferences;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<UserEnrolledCourse> getEnrolledCourses() {
      return enrolledcourses;
    }
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }
    
    /**
     *
     * @return
     */
    public String getAuth() {
        return auth;
    }
    
    /**
     *
     * @return
     */
    public String getIdNumber() {
        return idnumber;
    }

    /**
     *
     * @return
     */
    public String getLang() {
        return lang;
    }

    /**
     *
     * @return
     */
    public String getTheme() {
        return theme;
    }

    /**
     *
     * @return
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @return
     */
    public Boolean getMailFormat() {
        return mailformat;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }
    
    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }
    
    /**
     *
     * @return
     */
    public String getPhone1() {
        return phone1;
    }
    
    /**
     *
     * @return
     */
    public String getPhone2() {
        return phone2;
    }
    
    /**
     *
     * @return
     */
    public String getICQ() {
        return icq;
    }
    
    /**
     * 
     * @return
     */
    public String getSkype() {
      return skype;  
    }
    
    /**
     *
     * @return
     */
    public String getYahoo() {
        return yahoo;
    }
    
    /**
     *
     * @return
     */
    public String getMSN() {
        return msn;
    }
    
    /**
     *
     * @return
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     *
     * @return
     */
    public String getInstitution() {
        return institution;
    }
    
    /**
     *
     * @return
     */
    public String getInterests() {
        return interests;
    }
    
    /**
     *
     * @return
     */
    public String getURL() {
        return url;
    }
    
    /**
     *
     * @return
     */
    public Long getFirstAccess() {
        return firstaccess;
    }
    
    /**
     *
     * @return
     */
    public Long getLastAccess() {
        return lastaccess;
    }
    
    /**
     *
     * @return
     */
    public String getProfileImageURLSmall() {
      return profileimageurlsmall;
    }

    /**
     *
     * @return
     */
    public String getProfileImageURL() {
      return profileimageurl;
    }
    
    /**
     * 
     * @return
     */
    public Double getConfirmed() {
      return confirmed;
    }
    
    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id=id;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username=username;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password=password;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname=firstname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname=lastname;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email=email;
    }

    /**
     *
     * @param auth
     */
    public void setAuth(String auth) {
        this.auth=auth;
    }

    /**
     *
     * @param idnumber
     */
    public void setIdNumber(String idnumber) {
        this.idnumber=idnumber;
    }

    /**
     *
     * @param lang
     */
    public void setLang(String lang) {
        this.lang=lang;
    }

    /**
     *
     * @param theme
     */
    public void setTheme(String theme) {
        this.theme=theme;
    }

    /**
     *
     * @param timezone
     */
    public void setTimezone(String timezone) {
        this.timezone=timezone;
    }

    /**
     *
     * @param mailformat
     */
    public void setMailFormat(Boolean mailformat) {
        this.mailformat=mailformat;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description=description;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city=city;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country=country;
    }

    /**
     *
     * @return
     */
    public boolean isEmailHTMLFormat() {
        return mailformat;
    }
    
    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address=address;
    }
    
    /**
     *
     * @param phone1
     */
    public void setPhone1(String phone1) {
        this.phone1=phone1;
    }
    
    /**
     *
     * @param phone2
     */
    public void setPhone2(String phone2) {
        this.phone2=phone2;
    }
    
    /**
     *
     * @param icq
     */
    public void setICQ(String icq) {
        this.icq=icq;
    }
    
    /**
     *
     * @param yahoo
     */
    public void setYahoo(String yahoo) {
        this.yahoo=yahoo;
    }
    
    /**
     *
     * @param msn
     */
    public void setMSN(String msn) {
        this.msn=msn;
    }
    
    /**
     *
     * @param skype
     */
    public void setSkype(String skype) {
        this.skype=skype;
    }
    
    /**
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department=department;
    }
    
    /**
     *
     * @param interests
     */
    public void setInterests(String interests) {
        this.interests=interests;
    }
    
    /**
     *
     * @param institution
     */
    public void setInstitution(String institution) {
        this.institution=institution;
    }
    
    /**
     *
     * @param url
     */
    public void setURL(String url) {
        this.url=url;
    }

    /**
     *
     * @param firstaccess
     */
    public void setFirstAccess(Long firstaccess) {
        this.firstaccess=firstaccess;
    }
    
    /**
     *
     * @param lastaccess
     */
    public void setLastAccess(Long lastaccess) {
        this.lastaccess=lastaccess;
    }
    
    /**
     * 
     * @param confirmed
     */
    public void setConfirmed(Double confirmed) {
      this.confirmed=confirmed;
    }
    
    /**
     *
     * @param profileimageurlsmall
     */
    public void setProfileImageURLSmall(String profileimageurlsmall) {
      this.profileimageurlsmall=profileimageurlsmall;
    }

    /**
     *
     * @param profileimageurl
     */
    public void setProfileImageURL(String profileimageurl) {
      this.profileimageurl=profileimageurl;
    }

    /**
     * 
     * @param descriptionformat
     */
    public void setDescriptionFormat(Long descriptionformat) {
    this.descriptionformat=descriptionformat;
  }
  
  /**
   * 
   * @return
   */
  public long getDescriptionFormat() {
    return descriptionformat;
  }
}
