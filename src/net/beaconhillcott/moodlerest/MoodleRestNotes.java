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
import java.util.logging.*;
import org.w3c.dom.NodeList;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>Class containing static Methods to call Moodle REST notes web services.</p>
 * 
 * @author Bill Antonia
 */
public class MoodleRestNotes implements Serializable {
  
  //private static final int BUFFER_MAX=4000;
  
  /**
   * <p>Method to attach a single note to a user.</p>
   * 
   * @param note
   * @return MoodleNote
   * @throws MoodleRestNotesException
   * @throws MoodleRestException
   */
  public static MoodleNote createNote(MoodleNote note) throws MoodleRestNotesException, MoodleRestException {
    MoodleNote[] a=new MoodleNote[1];
    a[0]=note;
    MoodleNote[] n=createNotes(a);
    return n[0];
  }

  public MoodleNote __createNote(String url, String token, MoodleNote note) throws MoodleRestNotesException, MoodleRestException {
    MoodleNote[] a=new MoodleNote[1];
    a[0]=note;
    MoodleNote[] n=__createNotes(url, token, a);
    return n[0];
  }
  
  //core_notes_create_notes

  /**
   * <p>Method to attach notes to users.</p>
   * 
   * @param notes MoodleNote[]
   * @return MoodleNote[]
   * @throws MoodleRestNotesException
   * @throws MoodleRestException
   */
  public static MoodleNote[] createNotes(MoodleNote[] notes) throws MoodleRestNotesException, MoodleRestException {
    int processedCount=0;
    String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_NOTES_CREATE_NOTES.toString():MoodleServices.CORE_NOTES_CREATE_NOTES.toString();
    try {
      StringBuilder data=new StringBuilder();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestNotesException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0;i<notes.length;i++) {
        if (notes[i]==null) throw new MoodleRestNotesException(MoodleRestNotesException.NOTES_NULL);
        if (notes[i].getUserId()==null) throw new MoodleRestNotesException(MoodleRestNotesException.USERID_NOT_SET); else data.append("&").append(URLEncoder.encode("notes["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+notes[i].getUserId(), MoodleServices.ENCODING.toString()));
        if (notes[i].getPublishState()==null) throw new MoodleRestNotesException(MoodleRestNotesException.PUBLISHSTATE_NULL); else data.append("&").append(URLEncoder.encode("notes["+i+"][publishstate]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getPublishState(), MoodleServices.ENCODING.toString()));
        if (notes[i].getCourseId()==null) throw new MoodleRestNotesException(MoodleRestNotesException.COURSEID_NOT_SET); else data.append("&").append(URLEncoder.encode("notes["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+notes[i].getCourseId(), MoodleServices.ENCODING.toString()));
        if (notes[i].getText()==null) throw new MoodleRestNotesException(MoodleRestNotesException.TEXT_NULL); else data.append("&").append(URLEncoder.encode("notes["+i+"][text]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getText(), MoodleServices.ENCODING.toString()));
        if (notes[i].getFormat()==null) { notes[i].setFormat("text"); }
        if (notes[i].getFormat().equals("text") || notes[i].getFormat().equals("html"))
          data.append("&").append(URLEncoder.encode("notes["+i+"][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getFormat(), MoodleServices.ENCODING.toString()));
        else
          throw new MoodleRestNotesException(MoodleRestNotesException.FORMAT_INCORRECT);
        if (notes[i].getClientNoteId()!=null) data.append("&").append(URLEncoder.encode("notes["+i+"][clientnoteid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getClientNoteId(), MoodleServices.ENCODING.toString()));
      }
      data.trimToSize();
      NodeList elements=MoodleCallRestWebService.call(data.toString());
      for (int j=0;j<elements.getLength();j+=3,processedCount++) {
        for (int k=0; k<3; k++) {
          String content=elements.item(j+k).getTextContent();
          String nodeName=elements.item(j+k).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          notes[processedCount].setMoodleNoteField(nodeName, content);
        }
      }
    }  catch (UnsupportedEncodingException ex) {
      Logger.getLogger(MoodleRestNotes.class.getName()).log(Level.SEVERE, null, ex);
    }
    return notes;
  }

  public MoodleNote[] __createNotes(String url, String token, MoodleNote[] notes) throws MoodleRestNotesException, MoodleRestException {
    int processedCount=0;
    String functionCall=MoodleCallRestWebService.isLegacy()?MoodleServices.MOODLE_NOTES_CREATE_NOTES.toString():MoodleServices.CORE_NOTES_CREATE_NOTES.toString();
    try {
      StringBuilder data=new StringBuilder();
      data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0;i<notes.length;i++) {
        if (notes[i]==null) throw new MoodleRestNotesException(MoodleRestNotesException.NOTES_NULL);
        if (notes[i].getUserId()==null) throw new MoodleRestNotesException(MoodleRestNotesException.USERID_NOT_SET); else data.append("&").append(URLEncoder.encode("notes["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+notes[i].getUserId(), MoodleServices.ENCODING.toString()));
        if (notes[i].getPublishState()==null) throw new MoodleRestNotesException(MoodleRestNotesException.PUBLISHSTATE_NULL); else data.append("&").append(URLEncoder.encode("notes["+i+"][publishstate]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getPublishState(), MoodleServices.ENCODING.toString()));
        if (notes[i].getCourseId()==null) throw new MoodleRestNotesException(MoodleRestNotesException.COURSEID_NOT_SET); else data.append("&").append(URLEncoder.encode("notes["+i+"][courseid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+notes[i].getCourseId(), MoodleServices.ENCODING.toString()));
        if (notes[i].getText()==null) throw new MoodleRestNotesException(MoodleRestNotesException.TEXT_NULL); else data.append("&").append(URLEncoder.encode("notes["+i+"][text]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getText(), MoodleServices.ENCODING.toString()));
        if (notes[i].getFormat()==null) { notes[i].setFormat("text"); }
        if (notes[i].getFormat().equals("text") || notes[i].getFormat().equals("html"))
          data.append("&").append(URLEncoder.encode("notes["+i+"][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getFormat(), MoodleServices.ENCODING.toString()));
        else
          throw new MoodleRestNotesException(MoodleRestNotesException.FORMAT_INCORRECT);
        if (notes[i].getClientNoteId()!=null) data.append("&").append(URLEncoder.encode("notes["+i+"][clientnoteid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(notes[i].getClientNoteId(), MoodleServices.ENCODING.toString()));
      }
      data.trimToSize();
      NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
      for (int j=0;j<elements.getLength();j+=3,processedCount++) {
        for (int k=0; k<3; k++) {
          String content=elements.item(j+k).getTextContent();
          String nodeName=elements.item(j+k).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
          notes[processedCount].setMoodleNoteField(nodeName, content);
        }
      }
    }  catch (UnsupportedEncodingException ex) {
      Logger.getLogger(MoodleRestNotes.class.getName()).log(Level.SEVERE, null, ex);
    }
    return notes;
  }
  
  public static void deleteNotes(Long[] ids) throws MoodleRestNotesException, MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestNotesException(MoodleRestException.NO_LEGACY);
    //MoodleWarning[] warnings=null;
    String functionCall=MoodleServices.CORE_NOTES_DELETE_NOTES.toString();

      StringBuilder data=new StringBuilder();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestNotesException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0;i<ids.length;i++) {
        if (ids[i]==null) throw new MoodleRestNotesException(); else data.append("&").append(URLEncoder.encode("notes["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(ids[i]);
      }
      data.trimToSize();
      NodeList elements=MoodleCallRestWebService.call(data.toString());
    //return warnings;
  }
  
  public static void getNotes(Long[] ids) throws MoodleRestNotesException, MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestNotesException(MoodleRestException.NO_LEGACY);
    //MoodleWarning[] warnings=null;
    String functionCall=MoodleServices.CORE_NOTES_GET_NOTES.toString();

      StringBuilder data=new StringBuilder();
      if (MoodleCallRestWebService.getAuth()==null)
        throw new MoodleRestNotesException();
      else
        data.append(MoodleCallRestWebService.getAuth());
      data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
      for (int i=0;i<ids.length;i++) {
        if (ids[i]==null) throw new MoodleRestNotesException(); else data.append("&").append(URLEncoder.encode("notes["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(ids[i]);
      }
      data.trimToSize();
      NodeList elements=MoodleCallRestWebService.call(data.toString());
    //return warnings;
  }
  
  public static void __deleteNotes(String url, String token, Long[] ids) throws MoodleRestNotesException, MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestNotesException(MoodleRestException.NO_LEGACY);
    //MoodleWarning[] warnings=null;
    String functionCall=MoodleServices.CORE_NOTES_DELETE_NOTES.toString();
    StringBuilder data=new StringBuilder();
    data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0;i<ids.length;i++) {
      if (ids[i]==null) throw new MoodleRestNotesException(); else data.append("&").append(URLEncoder.encode("notes["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(ids[i]);
    }
    data.trimToSize();
    NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
    //return warnings;
  }
  
  public static void __getNotes(String url, String token, Long[] ids) throws MoodleRestNotesException, MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestNotesException(MoodleRestException.NO_LEGACY);
    //MoodleWarning[] warnings=null;
    String functionCall=MoodleServices.CORE_NOTES_GET_NOTES.toString();
    StringBuilder data=new StringBuilder();
    data.append(URLEncoder.encode("wstoken", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(token, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0;i<ids.length;i++) {
      if (ids[i]==null) throw new MoodleRestNotesException(); else data.append("&").append(URLEncoder.encode("notes["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(ids[i]);
    }
    data.trimToSize();
    NodeList elements=(new MoodleCallRestWebService()).__call(url,data.toString());
    //return warnings;
  }
  
  public static void updateNotes(MoodleNote[] notes) throws MoodleRestNotesException, UnsupportedEncodingException, MoodleRestException {
    updateNotes(notes, null);
  }
  
  public static void updateNotes(MoodleNote[] notes, MoodleWarning[] warnings) throws MoodleRestNotesException, UnsupportedEncodingException, MoodleRestException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestNotesException(MoodleRestException.NO_LEGACY);
    String functionCall=MoodleServices.CORE_NOTES_UPDATE_NOTES.toString();
    StringBuilder data=new StringBuilder();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestNotesException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<notes.length; i++) {
      data.append("&").append(URLEncoder.encode("notes["+i+"][id]", MoodleServices.ENCODING.toString())).append("=").append(""+notes[i].getNoteId());
      data.append("&").append(URLEncoder.encode("notes["+i+"][publishstate]", MoodleServices.ENCODING.toString())).append("=").append(notes[i].getPublishState());
      data.append("&").append(URLEncoder.encode("notes["+i+"][text]", MoodleServices.ENCODING.toString())).append("=").append(notes[i].getText());
      data.append("&").append(URLEncoder.encode("notes["+i+"][format]", MoodleServices.ENCODING.toString())).append("=").append(""+notes[i].getDescriptionFormat().toInt());
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("warnings")) {
        if (nodeName.equals("item")) {
          if (warn==null) {
            warn=new ArrayList<MoodleWarning>();
          }
          warning=new MoodleWarning(content);
          warn.add(warning);
        } else {
          warning.setMoodleWarningField(nodeName, content);
        }
      }
    }
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
  }
  
  public static MoodleListStatus notesViewNotes() throws MoodleRestException, UnsupportedEncodingException {
    return notesViewNotes(null, null);
  }
  
  public static MoodleListStatus notesViewNotes(Long courseId) throws MoodleRestException, UnsupportedEncodingException {
    return notesViewNotes(courseId, null);
  }
  
  public static MoodleListStatus notesViewNotes(Long courseId, Long userId) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_NOTES_VIEW_NOTES.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestCourseException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (courseId==null) { courseId=0L; }
    data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(courseId);
    if (userId!=null) data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(userId);
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    MoodleListStatus listStatus=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    String parent=null;
    for (int j=0;j<elements.getLength();j++) {
      try {
        parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      } catch (NullPointerException ex) {}
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (nodeName.equals("status")) {
        if (listStatus==null) {
          listStatus=new MoodleListStatus();
          listStatus.setStatus((content.equals("1")));
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
      if (listStatus==null) {
        listStatus=new MoodleListStatus();
      }
      listStatus.setWarnings(warn);
    }
    return listStatus;
  }
  
  public MoodleNotes getCourseNotes(Long courseId, Long userId) throws UnsupportedEncodingException, MoodleRestException {
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.CORE_NOTES_GET_COURSE_NOTES.toString();
    if (MoodleCallRestWebService.getAuth()==null)
      throw new MoodleRestMessageException();
    else
      data.append(MoodleCallRestWebService.getAuth());
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (courseId==null) throw new MoodleRestNotesException(); else data.append("&").append(URLEncoder.encode("courseid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseId, MoodleServices.ENCODING.toString()));
    if (userId!=null) data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleNote> siteNotes=null;
    ArrayList<MoodleNote> courseNotes=null;
    ArrayList<MoodleNote> personalNotes=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleNote siteNote=null;
    MoodleNote courseNote=null;
    MoodleNote personalNote=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("sitenotes")) {
        if (nodeName.equals("id")) {
          if (siteNote==null) {
            siteNotes=new ArrayList<MoodleNote>();
          }
          siteNote=new MoodleNote();
          siteNotes.add(siteNote);
          siteNote.setNoteId(Long.parseLong(content));
        } else {
          siteNote.setMoodleNoteField(nodeName, content);
        }
      } else {
        if (parent.equals("coursenotes")) {
          if (nodeName.equals("id")) {
            if (courseNote==null) {
              courseNotes=new ArrayList<MoodleNote>();
            }
            courseNote=new MoodleNote();
            courseNotes.add(courseNote);
            courseNote.setNoteId(Long.parseLong(content));
          } else {
            courseNote.setMoodleNoteField(nodeName, content);
          }
        } else {
          if (parent.equals("personalnotes")) {
            if (nodeName.equals("id")) {
              if (personalNote==null) {
                personalNotes=new ArrayList<MoodleNote>();
              }
              personalNote=new MoodleNote();
              personalNotes.add(personalNote);
              personalNote.setNoteId(Long.parseLong(content));
            } else {
              personalNote.setMoodleNoteField(nodeName, content);
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
      }
    }
    MoodleNotes notesWithWarnings=new MoodleNotes();
    notesWithWarnings.setSiteNotes(siteNotes);
    notesWithWarnings.setCourseNotes(courseNotes);
    notesWithWarnings.setPersonalNotes(personalNotes);
    notesWithWarnings.setWarnings(warn);
    return notesWithWarnings;
  }
}
