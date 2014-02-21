/*
 *  Copyright (C) 2013 Bill Antonia
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
import org.w3c.dom.NodeList;
import java.io.Serializable;

/**
 *
 * @author Bill Antonia
 */
public class MoodleRestCalendar implements Serializable {

  public MoodleRestCalendar() {}
  
  public static MoodleCalendar[] createCalendarEvents(MoodleCalendar[] calendar) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCalendarException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_CALENDAR_CREATE_CALENDAR_EVENTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0;i<calendar.length;i++) {
      if (calendar[i].getName()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" name"); else data.append("&").append(URLEncoder.encode("events["+i+"][name]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(calendar[i].getName(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getDescription()==null) data.append("&").append(URLEncoder.encode("events["+i+"][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode("null", MoodleServices.ENCODING.toString())); else data.append("&").append(URLEncoder.encode("events[0][description]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(calendar[i].getDescription(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getFormat()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getFormat(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getCourseId()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getCourseId(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getGroupId()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][groupid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getGroupId(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getRepeats()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][repeats]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getRepeats(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getEventtype()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][eventtype]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(calendar[i].getEventtype(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getTimestart()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][timestart]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getTimestart(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getTimeduration()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][timeduration]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getTimeduration(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getVisible()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][visible]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getVisible(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getSequence()!=null) data.append("&").append(URLEncoder.encode("events["+i+"][sequence]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getSequence(), MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    String lastId=null;
    ArrayList<MoodleCalendar>results=new ArrayList();
    MoodleCalendar updated=null;
    for (int j=0;j<elements.getLength();j++) {
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      // Ignore warnings at present
      if (nodeName.equals("item") || nodeName.equals("itemid") || nodeName.equals("warningcode") || nodeName.equals("message")) break;
      if (lastId!=null) {
        if (!nodeName.equals("id")) {
          if (updated!=null)
            updated.setMoodleCalendarField(nodeName, content);
          else
            throw new MoodleRestCalendarException(MoodleRestCalendarException.INCONSISTENT_DATA_PARSE);
        } else {
          results.add(updated);
          lastId=content;
          updated=new MoodleCalendar();
          updated.setMoodleCalendarField(nodeName, content);
        }
      } else {
        // Ignore warnings at present
        if (nodeName.equals("item") || nodeName.equals("itemid") || nodeName.equals("warningcode") || nodeName.equals("message")) break;
        // First id hit
        updated=new MoodleCalendar();
        lastId=content;
        updated.setMoodleCalendarField(nodeName, content);
      }
    }
    if (updated!=null)
      results.add(updated);
    return results.toArray(calendar);
  }

  public static MoodleCalendar createCalendarEvent(MoodleCalendar calendar) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCalendar[] calendars=new MoodleCalendar[1];
    calendars[0]=calendar;
    createCalendarEvents(calendars);
    return calendars[0];
  }
  
  public static void deleteCalendarEvents(MoodleCalendar[] calendar) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCalendarException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_CALENDAR_DELETE_CALENDAR_EVENTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0;i<calendar.length;i++) {
      if (calendar[i].getId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" id"); else data.append("&").append(URLEncoder.encode("events["+i+"][eventid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getId(), MoodleServices.ENCODING.toString()));
      if (calendar[i].getRepeats()==null) data.append("&").append(URLEncoder.encode("events["+i+"][repeat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode("0", MoodleServices.ENCODING.toString())); else data.append("&").append(URLEncoder.encode("events["+i+"][repeat]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getRepeats(), MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    MoodleCallRestWebService.call(data.toString());
  }
  
  public static MoodleCalendar deleteCalendarEvent(MoodleCalendar calendar) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCalendar[] calendars=new MoodleCalendar[1];
    calendars[0]=calendar;
    deleteCalendarEvents(calendars);
    return calendars[0];
  }
  
  public static MoodleCalendar[] getCalendarEvents(MoodleCalendar[] calendar, Integer userevents, Integer siteevents, Long timestart, Long timeend, Integer ignorehidden) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestCalendarException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_CALENDAR_GET_CALENDAR_EVENTS.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (calendar!=null) {
      for (int i=0;i<calendar.length;i++) {
        if (calendar[i].getId()!=null) data.append("&").append(URLEncoder.encode("events[eventids]["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getId(), MoodleServices.ENCODING.toString()));
        if (calendar[i].getCourseId()!=null) data.append("&").append(URLEncoder.encode("events[courseids]["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getCourseId(), MoodleServices.ENCODING.toString()));
        if (calendar[i].getGroupId()!=null) data.append("&").append(URLEncoder.encode("events[groupids]["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+calendar[i].getGroupId(), MoodleServices.ENCODING.toString()));
      }
    }
    if (userevents!=null) data.append("&").append(URLEncoder.encode("options[userevents]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(userevents==0?0:1), MoodleServices.ENCODING.toString()));
    if (siteevents!=null) data.append("&").append(URLEncoder.encode("options[siteevents]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(siteevents==0?0:1), MoodleServices.ENCODING.toString()));
    if (timestart!=null) data.append("&").append(URLEncoder.encode("options[timestart]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+timestart, MoodleServices.ENCODING.toString()));
    if (timeend!=null) data.append("&").append(URLEncoder.encode("options[timeend]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+timeend, MoodleServices.ENCODING.toString()));
    if (ignorehidden!=null) data.append("&").append(URLEncoder.encode("options[ignorehidden]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(ignorehidden==0?0:1), MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    String lastId=null;
    ArrayList<MoodleCalendar>results=new ArrayList();
    MoodleCalendar updated=null;
    for (int j=0;j<elements.getLength();j++) {
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      // Ignore warnings at present
      if (nodeName.equals("item") || nodeName.equals("itemid") || nodeName.equals("warningcode") || nodeName.equals("message")) break;
      if (lastId!=null) {
        if (!nodeName.equals("id")) {
          if (updated!=null)
            updated.setMoodleCalendarField(nodeName, content);
          else
            throw new MoodleRestCalendarException(MoodleRestCalendarException.INCONSISTENT_DATA_PARSE);
        } else {
          results.add(updated);
          lastId=content;
          updated=new MoodleCalendar();
          updated.setMoodleCalendarField(nodeName, content);
        }
      } else {
        // Ignore warnings at present
        if (nodeName.equals("item") || nodeName.equals("itemid") || nodeName.equals("warningcode") || nodeName.equals("message")) break;
        // First id hit
        updated=new MoodleCalendar();
        lastId=content;
        updated.setMoodleCalendarField(nodeName, content);
      }
    }
    if (updated!=null)
      results.add(updated);
    return results.toArray(calendar);
  }

  public static MoodleCalendar[] getCalendarEvents(MoodleCalendar calendar, Integer userevents, Integer siteevents, Long timestart, Long timeend, Integer ignorehidden) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCalendar[] calendars=new MoodleCalendar[1];
    calendars[0]=calendar;
    MoodleCalendar[] calendarEvents = getCalendarEvents(calendars, userevents, siteevents, timestart, timeend, ignorehidden);
    return calendarEvents;
  }
  
  public static MoodleCalendar[] getCalendarEvents(MoodleCalendar[] calendar) throws MoodleRestException, UnsupportedEncodingException {
    return getCalendarEvents(calendar, MoodleCalendar.GET_OPTION_USEREVENTS_DEFAULT, MoodleCalendar.GET_OPTION_SITEEVENTS_DEFAULT, MoodleCalendar.GET_OPTION_TIMESTART_DEFAULT, MoodleCalendar.GET_OPTION_TIMEEND_DEFAULT, MoodleCalendar.GET_OPTION_IGNOREHIDDEN_DEFAULT);
  }
  
  public static MoodleCalendar[] getCalendarEvents(MoodleCalendar calendar) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCalendar[] calendars=new MoodleCalendar[1];
    calendars[0]=calendar;
    MoodleCalendar[] calendarEvents = getCalendarEvents(calendars, MoodleCalendar.GET_OPTION_USEREVENTS_DEFAULT, MoodleCalendar.GET_OPTION_SITEEVENTS_DEFAULT, MoodleCalendar.GET_OPTION_TIMESTART_DEFAULT, MoodleCalendar.GET_OPTION_TIMEEND_DEFAULT, MoodleCalendar.GET_OPTION_IGNOREHIDDEN_DEFAULT);
    return calendarEvents;
  }
  
}
