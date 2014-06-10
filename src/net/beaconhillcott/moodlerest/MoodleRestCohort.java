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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.w3c.dom.NodeList;
import java.io.Serializable;

/**
 * 
 * 
 * @author Bill Antonia
 * @see MoodleUser
 */
public class MoodleRestCohort implements Serializable {
  
  public static Hashtable<Long, ArrayList> getCohortsMemberIds(Long[] cohortIds) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_GET_COHORT_MEMBERS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCohortException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (cohortIds!=null) {
      for (int i=0; i<cohortIds.length; i++)
        if (cohortIds[i]==null) throw new MoodleRestCohortException(MoodleRestException.REQUIRED_PARAMETER+" cohortid"); else data.append("&").append(URLEncoder.encode("cohortids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohortIds[i], MoodleServices.ENCODING.toString()));
    } else
      throw new MoodleRestCohortException(MoodleRestException.REQUIRED_PARAMETER+" cohortid array");
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    Hashtable<Long, ArrayList> response=null;
    ArrayList<Long> memberids=null;
    Long cohortid=null;
    for (int j=0;j<elements.getLength();j++) {
      if (elements.item(j).getParentNode().getAttributes().getNamedItem("name")!=null) {
        if (elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals("cohortid")) {
          if (response==null) {
            cohortid=Long.parseLong((String)elements.item(j).getTextContent());
            response=new Hashtable<Long, ArrayList>();
            memberids=new ArrayList<Long>();
          } else {
            response.put(cohortid, memberids);
            cohortid=Long.parseLong((String)elements.item(j).getTextContent());
            memberids=new ArrayList<Long>();
          }
        }
      } else {
        if (memberids!=null)
          memberids.add(Long.parseLong(elements.item(j).getTextContent()));
        else
          throw new MoodleRestCalendarException(MoodleRestCalendarException.INCONSISTENT_DATA_PARSE);
      }
    }
    if (response!=null)
      response.put(cohortid, memberids);
    return response;
  }

  public static ArrayList<Long> getCohortMemberIds(Long cohortId) throws MoodleRestException, UnsupportedEncodingException {
    Long[] cohortIds=new Long[1];
    cohortIds[0]=cohortId;
    Hashtable<Long, ArrayList> cohortsMemberIds = getCohortsMemberIds(cohortIds);
    if (cohortsMemberIds==null)
      return null;
    return cohortsMemberIds.get(cohortId);
  }

  public static MoodleCohort[] getCohorts(Long[] cohortIds) throws MoodleRestException, UnsupportedEncodingException, MoodleCohortException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_GET_COHORTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (cohortIds!=null) {
      for (int i=0; i<cohortIds.length; i++)
        if (cohortIds[i]==null) throw new MoodleRestCohortException(MoodleRestException.REQUIRED_PARAMETER+" cohortid"); else data.append("&").append(URLEncoder.encode("cohortids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohortIds[i], MoodleServices.ENCODING.toString()));
    } else
      throw new MoodleRestCohortException(MoodleRestException.REQUIRED_PARAMETER+" cohortid array");
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleCohort> cohorts=null;
    MoodleCohort cohort=null;
    String content=null;
    String nodeName=null;
    for (int j=0;j<elements.getLength();j++) {
      content=elements.item(j).getTextContent();
      nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (nodeName.equals("id")) {
        if (cohorts==null) {
          cohorts=new ArrayList<MoodleCohort>();
          cohort=new MoodleCohort();
          cohort.setMoodleCohortField(nodeName, content);
        } else {
          cohorts.add(cohort);
          cohort=new MoodleCohort();
          cohort.setMoodleCohortField(nodeName, content);
        }
      } else {
        if (cohort!=null) {
          cohort.setMoodleCohortField(nodeName, content);
        }
        else {
          throw new MoodleRestCalendarException(MoodleRestCohortException.INCONSISTENT_DATA_PARSE);
        }
      }
    }
    if (cohorts!=null) {
      if (cohort!=null)
          cohort.setMoodleCohortField(nodeName, content);
        else
          throw new MoodleRestCalendarException(MoodleRestCohortException.INCONSISTENT_DATA_PARSE);
      cohorts.add(cohort);
    }
    MoodleCohort[] returnData=new MoodleCohort[1];
    if (cohorts==null)
          return null;
    return cohorts.toArray(returnData);
  }

  public static MoodleCohort getCohort(Long cohortId) throws MoodleRestException, UnsupportedEncodingException, MoodleCohortException {
    Long[] cohortIds=new Long[1];
    cohortIds[0]=cohortId;
    MoodleCohort[] cohorts = getCohorts(cohortIds);
    if (cohorts==null)
      return null;
    return cohorts[0];
  }

  public static void deleteCohorts(Long[] cohortIds) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_DELETE_COHORTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (cohortIds!=null) {
      for (int i=0; i<cohortIds.length; i++)
        if (cohortIds[i]==null) throw new MoodleRestCohortException(MoodleRestException.REQUIRED_PARAMETER+" cohortid"); else data.append("&").append(URLEncoder.encode("cohortids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohortIds[i], MoodleServices.ENCODING.toString()));
    } else
      throw new MoodleRestCohortException(MoodleRestException.REQUIRED_PARAMETER+" cohortid array");
    data.trimToSize();
    MoodleCallRestWebService.call(data.toString());
  }

  public static void deleteCohort(Long cohortId) throws MoodleRestException, UnsupportedEncodingException {
    Long[] cohortIds=new Long[1];
    cohortIds[0]=cohortId;
    deleteCohorts(cohortIds);
  }

  public static void deleteCohortsMembers(Hashtable<Long, ArrayList> cohortsMembers) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_DELETE_COHORT_MEMBERS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (cohortsMembers!=null) {
      Set<Long> cohorts = cohortsMembers.keySet();
      Iterator<Long> cohortIterator = cohorts.iterator();
      int j=0;
      while (cohortIterator.hasNext()) {
        Long cohort = cohortIterator.next();
        ArrayList members = cohortsMembers.get(cohort);
        for (int i=0; i<members.size(); i++) {
          Long member = (Long)members.get(i);
          data.append("&").append(URLEncoder.encode("members["+j+"][cohortid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohort, MoodleServices.ENCODING.toString()));
          data.append("&").append(URLEncoder.encode("members["+j+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+member, MoodleServices.ENCODING.toString()));
        }
        j++;
      }
      data.trimToSize();
      MoodleCallRestWebService.call(data.toString());
    }
  }

  public static void deleteCohortMembers(Long cohort, ArrayList cohortMembers) throws MoodleRestException, UnsupportedEncodingException {
    Hashtable<Long, ArrayList> cohortsMembers=new Hashtable<Long, ArrayList>();
    cohortsMembers.put(cohort, cohortMembers);
    deleteCohortsMembers(cohortsMembers);
  }
  
  public static MoodleCohort[] createCohorts(MoodleCohort[] cohorts) throws UnsupportedEncodingException, MoodleRestCohortException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_CREATE_COHORTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCohortException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (cohorts!=null) {
      Hashtable<String, MoodleCohort> source=new Hashtable<String, MoodleCohort>();
      for (int i=0; i<cohorts.length; i++) {
        source.put(cohorts[i].getName(), cohorts[i]);
      }
      for (int i=0; i<cohorts.length; i++) {
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][categorytype][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getCategoryTypeType(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][categorytype][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getCategoryTypeValue(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getName(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getIdNumber(), MoodleServices.ENCODING.toString()));
        if (cohorts[i].getDescription()!=null) data.append("&").append(URLEncoder.encode("cohorts["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getDescription(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][descriptionformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getDescriptionFormat().toInt(), MoodleServices.ENCODING.toString()));    
      }
      data.trimToSize();
      NodeList elements = MoodleCallRestWebService.call(data.toString());
      String content=null;
      String nodeName=null;
      Long id=null;
      for (int j=0;j<elements.getLength();j++) {
        content=elements.item(j).getTextContent();
        nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
        if (nodeName.equals("id"))
          id=Long.parseLong(content);
        if (nodeName.equals("name"))
          source.get(content).setId(id);
      }
    }
    return cohorts;
  }
  
  public static MoodleCohort createCohort(MoodleCohort cohort) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCohort[] cohorts=new MoodleCohort[1];
    cohorts[0]=cohort;
    MoodleCohort[] createCohorts = createCohorts(cohorts);
    return createCohorts[0];
  }
  
  public static void addCohortMembers(CohortMember[] members) throws MoodleRestCohortException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_ADD_COHORT_MEMBERS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCohortException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (members!=null) {
      for (int i=0; i<members.length; i++) {
        data.append("&").append(URLEncoder.encode("members["+i+"][cohorttype][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+members[i].getCohortTypeId().toString(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("members["+i+"][cohorttype][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+members[i].getCohortIdValue(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("members["+i+"][usertype][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+members[i].getUserTypeId().toString(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("members["+i+"][usertype][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+members[i].getUserIdValue(), MoodleServices.ENCODING.toString()));
      }
      data.trimToSize();
      MoodleCallRestWebService.call(data.toString());
    }
  }
  
  public static void addCohortMember(CohortMember member) throws MoodleRestException, UnsupportedEncodingException {
    CohortMember[] members=new CohortMember[1];
    members[0]=member;
    addCohortMembers(members);
  }
  
  public static void updateCohorts(MoodleCohort[] cohorts) throws MoodleRestCohortException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestCohortException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_COHORT_UPDATE_COHORTS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestCohortException();
    }
    else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (cohorts!=null) {
      for (int i=0; i<cohorts.length; i++) {
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getId(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][categorytype][type]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getCategoryTypeType(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][categorytype][value]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getCategoryTypeValue(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getName(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][idnumber]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getIdNumber(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getDescription(), MoodleServices.ENCODING.toString()));
        data.append("&").append(URLEncoder.encode("cohorts["+i+"][descriptionformat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+cohorts[i].getDescriptionFormat(), MoodleServices.ENCODING.toString()));    
      }
      data.trimToSize();
      MoodleCallRestWebService.call(data.toString());
    }
  }
  
}
