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

/**
 *
 * @author Bill Antonia
 */
public class MoodleRestCalendar {

  public MoodleRestCalendar() {}
  
  public static MoodleCalendar[] createCalendarEvents(MoodleCalendar[] calendar) throws MoodleRestException, UnsupportedEncodingException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_CALENDAR_CREATE_CALENDAR_EVENTS;
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING));
    for (int i=0;i<calendar.length;i++) {
      if (calendar[i].getName()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" name"); else data.append("&").append(URLEncoder.encode("events["+i+"][name]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(calendar[i].getName(), MoodleServices.ENCODING));
      if (calendar[i].getDescription()==null) data.append("&").append(URLEncoder.encode("events["+i+"][description]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode("null", MoodleServices.ENCODING)); else data.append("&").append(URLEncoder.encode("events[0][description]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(calendar[i].getDescription(), MoodleServices.ENCODING));
      if (calendar[i].getFormat()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" format"); else data.append("&").append(URLEncoder.encode("events["+i+"][format]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getFormat(), MoodleServices.ENCODING));
      if (calendar[i].getCourseId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" courseid"); else data.append("&").append(URLEncoder.encode("events["+i+"][courseid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getCourseId(), MoodleServices.ENCODING));
      if (calendar[i].getGroupId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" groupid"); else data.append("&").append(URLEncoder.encode("events["+i+"][groupid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getGroupId(), MoodleServices.ENCODING));
      if (calendar[i].getRepeats()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" repeats"); else data.append("&").append(URLEncoder.encode("events["+i+"][repeats]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getRepeats(), MoodleServices.ENCODING));
      if (calendar[i].getEventtype()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" eventtype"); else data.append("&").append(URLEncoder.encode("events["+i+"][eventtype]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(calendar[i].getEventtype(), MoodleServices.ENCODING));
      if (calendar[i].getTimestart()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" timestart"); else data.append("&").append(URLEncoder.encode("events["+i+"][timestart]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getTimestart(), MoodleServices.ENCODING));
      if (calendar[i].getTimeduration()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" timeduration"); else data.append("&").append(URLEncoder.encode("events["+i+"][timeduration]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getTimeduration(), MoodleServices.ENCODING));
      if (calendar[i].getVisible()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" visible"); else data.append("&").append(URLEncoder.encode("events["+i+"][visible]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getVisible(), MoodleServices.ENCODING));
      if (calendar[i].getSequence()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" sequence"); else data.append("&").append(URLEncoder.encode("events["+i+"][sequence]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getSequence(), MoodleServices.ENCODING));
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
          updated.setMoodleCalendarField(nodeName, content);
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
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_CALENDAR_DELETE_CALENDAR_EVENTS;
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING));
    for (int i=0;i<calendar.length;i++) {
      if (calendar[i].getId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" id"); else data.append("&").append(URLEncoder.encode("events["+i+"][eventid]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getId(), MoodleServices.ENCODING));
      if (calendar[i].getRepeats()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" repeats"); else data.append("&").append(URLEncoder.encode("events["+i+"][repeats]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getRepeats(), MoodleServices.ENCODING));
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
  
  public static MoodleCalendar[] getCalendarEvents(MoodleCalendar[] calendar, int userevents, int siteevents, long timestart, long timeend, int ignorehidden) throws MoodleRestException, UnsupportedEncodingException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_CALENDAR_GET_CALENDAR_EVENTS;
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCalendarException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING));
    for (int i=0;i<calendar.length;i++) {
      if (calendar[i].getId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" eventids"); else data.append("&").append(URLEncoder.encode("events[eventids]["+i+"]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getId(), MoodleServices.ENCODING));
      if (calendar[i].getCourseId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" courseids"); else data.append("&").append(URLEncoder.encode("events[courseids]["+i+"]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getCourseId(), MoodleServices.ENCODING));
      if (calendar[i].getGroupId()==null) throw new MoodleRestCalendarException(MoodleRestException.REQUIRED_PARAMETER+" groupids"); else data.append("&").append(URLEncoder.encode("events[groupids]["+i+"]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+calendar[i].getGroupId(), MoodleServices.ENCODING));
    }
    data.append("&").append(URLEncoder.encode("options[userevents]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+(userevents==0?0:1), MoodleServices.ENCODING));
    data.append("&").append(URLEncoder.encode("options[siteevents]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+(siteevents==0?0:1), MoodleServices.ENCODING));
    data.append("&").append(URLEncoder.encode("options[timestart]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+timestart, MoodleServices.ENCODING));
    data.append("&").append(URLEncoder.encode("options[timeend]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+timeend, MoodleServices.ENCODING));
    data.append("&").append(URLEncoder.encode("options[ignorehidden]", MoodleServices.ENCODING)).append("=").append(URLEncoder.encode(""+(ignorehidden==0?0:1), MoodleServices.ENCODING));
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
          updated.setMoodleCalendarField(nodeName, content);
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

  public static MoodleCalendar[] getCalendarEvent(MoodleCalendar calendar, int userevents, int siteevents, long timestart, long timeend, int ignorehidden) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCalendar[] calendars=new MoodleCalendar[1];
    calendars[0]=calendar;
    MoodleCalendar[] calendarEvents = getCalendarEvents(calendars, userevents, siteevents, timestart, timeend, ignorehidden);
    return calendarEvents;
  }
  
  public static MoodleCalendar[] getCalendarEvents(MoodleCalendar[] calendar) throws MoodleRestException, UnsupportedEncodingException {
    return getCalendarEvents(calendar, MoodleCalendar.GET_OPTION_USEREVENTS_DEFAULT, MoodleCalendar.GET_OPTION_SITEEVENTS_DEFAULT, MoodleCalendar.GET_OPTION_TIMESTART_DEFAULT, MoodleCalendar.GET_OPTION_TIMEEND_DEFAULT, MoodleCalendar.GET_OPTION_IGNOREHIDDEN_DEFAULT);
  }
  
  public static MoodleCalendar[] getCalendarEvent(MoodleCalendar calendar) throws MoodleRestException, UnsupportedEncodingException {
    MoodleCalendar[] calendars=new MoodleCalendar[1];
    calendars[0]=calendar;
    MoodleCalendar[] calendarEvents = getCalendarEvents(calendars, MoodleCalendar.GET_OPTION_USEREVENTS_DEFAULT, MoodleCalendar.GET_OPTION_SITEEVENTS_DEFAULT, MoodleCalendar.GET_OPTION_TIMESTART_DEFAULT, MoodleCalendar.GET_OPTION_TIMEEND_DEFAULT, MoodleCalendar.GET_OPTION_IGNOREHIDDEN_DEFAULT);
    return calendarEvents;
  }
  
}
