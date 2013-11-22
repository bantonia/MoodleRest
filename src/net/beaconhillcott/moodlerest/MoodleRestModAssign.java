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
import org.w3c.dom.NodeList;
import java.io.Serializable;
import java.util.ArrayList;
import net.beaconhillcott.moodlerest.MoodleModAssignAssignment.Submission;
import net.beaconhillcott.moodlerest.MoodleModAssignAssignment.Submission.Plugin;
import net.beaconhillcott.moodlerest.MoodleModAssignAssignment.Submission.Plugin.EditorField;
import net.beaconhillcott.moodlerest.MoodleModAssignAssignment.Submission.Plugin.FileArea;
import net.beaconhillcott.moodlerest.MoodleModAssignAssignment.Submission.Plugin.FileArea.File;
import net.beaconhillcott.moodlerest.MoodleModAssignGrade.Grade;

/**
 *
 * @author root
 */
public class MoodleRestModAssign implements Serializable {
  
  public static MoodleModAssignCourse[] getAssignments(Long[] courseIds) throws MoodleRestException, UnsupportedEncodingException {
    return getAssignments(courseIds, null, null);
  }
  
  public static MoodleModAssignCourse[] getAssignments(Long[] courseIds, String[] capabilities) throws MoodleRestException, UnsupportedEncodingException {
    return getAssignments(courseIds, capabilities, null);
  }
  
  public static MoodleModAssignCourse[] getAssignments(Long[] courseIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getAssignments(courseIds, null, warnings);
  }
  
  public static MoodleModAssignCourse[] getAssignments(Long[] courseIds, String[] capabilities, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_GET_ASSIGNMENTS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<courseIds.length; i++) {
      data.append("&").append(URLEncoder.encode("courseids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+courseIds[i], MoodleServices.ENCODING.toString()));
    }
    if (capabilities!=null) {
      for (int i=0; i<capabilities.length; i++) {
        data.append("&").append(URLEncoder.encode("capabilities["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+capabilities[i], MoodleServices.ENCODING.toString()));
      }
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleModAssignCourse> courses=null;
    MoodleModAssignCourse course=null;
    ArrayList<MoodleModAssignCourse.Assignment> assignments=null;
    MoodleModAssignCourse.Assignment assignment=null;
    MoodleModAssignCourse.Assignment.Config config=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("courses")) {
        if (courses==null) {
          courses=new ArrayList<MoodleModAssignCourse>();
        }
        if (nodeName.equals("id")) {
          course=new MoodleModAssignCourse();
          courses.add(course);
          course.setId(Long.parseLong(content));
        } else {
          course.setFieldValue(nodeName, content);
        }
      } else {
        if (parent.equals("assignments")) {
          if (nodeName.equals("id")) {
            assignment = course.newAssignment();
            assignment.setId(Long.parseLong(content));
          } else {
            assignment.setFieldValue(nodeName, content);
          }
        } else {
          if (parent.equals("configs")) {
            if (nodeName.equals("id")) {
              config = assignment.newConfig();
              config.setId(Long.parseLong(content));
            } else {
              config.setFieldValue(nodeName, content);
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
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
    MoodleModAssignCourse[] results=null;
    if (courses!=null) {
      results=new MoodleModAssignCourse[courses.size()];
      results=courses.toArray(results);
    }
    return results;
  }
  
  public static MoodleModAssignGrade[] getGrades(Long[] assignmentIds) throws MoodleRestException, UnsupportedEncodingException {
    return getGrades(assignmentIds, null, null);
  }
  
  public static MoodleModAssignGrade[] getGrades(Long[] assignmentIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getGrades(assignmentIds, null, warnings);
  }
  
  public static MoodleModAssignGrade[] getGrades(Long[] assignmentIds, Long since) throws MoodleRestException, UnsupportedEncodingException {
    return getGrades(assignmentIds, since, null);
  }
  
  public static MoodleModAssignGrade[] getGrades(Long[] assignmentIds, Long since, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_GET_GRADES.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<assignmentIds.length; i++) {
      data.append("&").append(URLEncoder.encode("assignmentids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentIds[i], MoodleServices.ENCODING.toString()));
    }
    if (since!=null) {
      data.append("&").append(URLEncoder.encode("since", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+since, MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleModAssignGrade> assignments=null;
    MoodleModAssignGrade assignment=null;
    Grade grade=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("assignments")) {
        if (nodeName.equals("assignmentid")) {
          if (assignments==null) {
            assignments=new ArrayList<MoodleModAssignGrade>();
          }
          assignment=new MoodleModAssignGrade();
          assignments.add(assignment);
          assignment.setAssignmentId(Long.parseLong(content));
        }
      } else {
        if (parent.equals("grades")) {
          if (nodeName.equals("id")) {
            grade = assignment.newGrade();
            grade.setId(Long.parseLong(content));
          } else {
            grade.setFieldValue(nodeName, content);
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
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
    MoodleModAssignGrade[] results=null;
    if (assignments!=null) {
      results=new MoodleModAssignGrade[assignments.size()];
      results=assignments.toArray(results);
    }
    return results;
  }
  
  public static MoodleModAssignAssignment[] getSubmissions(Long[] assignmentIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, null, null, null, warnings);
  }
  
  public static MoodleModAssignAssignment[] getSubmissions(Long[] assignmentIds, String status, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, status, null, null, warnings);
  }
  
  public static MoodleModAssignAssignment[] getSubmissionsBefore(Long[] assignmentIds, String status, Long time, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, status, null, time, warnings);
  }
  
  public static MoodleModAssignAssignment[] getSubmissionsSince(Long[] assignmentIds, String status, Long time, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, status, time, null, warnings);
  }
  
  public static MoodleModAssignAssignment[] getSubmissions(Long[] assignmentIds, String status, Long since, Long before, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_GET_SUBMISSIONS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<assignmentIds.length; i++) {
      data.append("&").append(URLEncoder.encode("assignmentids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentIds[i], MoodleServices.ENCODING.toString()));
    }
    if (status!=null) {
      data.append("&").append(URLEncoder.encode("status", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+status, MoodleServices.ENCODING.toString()));
    }
    if (since!=null) {
      data.append("&").append(URLEncoder.encode("since", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+since, MoodleServices.ENCODING.toString()));
    }
    if (before!=null) {
      data.append("&").append(URLEncoder.encode("before", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+before, MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleModAssignAssignment> assignments=null;
    MoodleModAssignAssignment assignment=null;
    ArrayList<Submission> submissions=null;
    Submission submission=null;
    ArrayList<Plugin> plugins=null;
    Plugin plugin=null;
    ArrayList<FileArea> areas=null;
    FileArea area=null;
    ArrayList<File> files=null;
    File file=null;
    ArrayList<EditorField> fields=null;
    EditorField field=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("assignments")) {
        if (nodeName.equals("assignmentid")) {
          if (assignments==null) {
            assignments=new ArrayList<MoodleModAssignAssignment>();
          }
          assignment=new MoodleModAssignAssignment();
          assignments.add(assignment);
          assignment.setAssignmentId(Long.parseLong(content));
          submissions=null;
          plugins=null;
          areas=null;
          files=null;
          fields=null;
        }
      } else {
        if (parent.equals("submissions")) {
          if (nodeName.equals("id")) {
            if (submissions==null) {
              submissions=new ArrayList<Submission>();
            }
            submission = assignment.newSubmission();
            submissions.add(submission);
            submission.setId(Long.parseLong(content));
            plugins=null;
            areas=null;
            files=null;
            fields=null;
          } else {
            submission.setFieldValue(nodeName, content);
          }
        } else {
          if (parent.equals("plugins")) {
            if (nodeName.equals("type")) {
              if (plugins==null) {
                plugins=new ArrayList<Plugin>();
              }
              plugin = submission.newPlugin();
              plugins.add(plugin);
              plugin.setType(content);
              areas=null;
              files=null;
              fields=null;
            } else {
              plugin.setFieldValue(nodeName, content);
            }
          } else {
            if (parent.equals("fileareas")) {
              if (nodeName.equals("area")) {
                if (areas==null) {
                  areas=new ArrayList<FileArea>();
                }
                area = plugin.newFileArea();
                areas.add(area);
                area.setArea(content);
                files=null;
              } else {
                area.setFieldValue(nodeName, content);
              }
            } else {
              if (parent.equals("files")) {
                if (nodeName.equals("filepath")) {
                  if (files==null) {
                    files=new ArrayList<File>();
                  }
                  file = area.newFile();
                  files.add(file);
                  file.setFieldValue(nodeName, content);
                } else {
                  file.setFieldValue(nodeName, content);
                }
              } else {
                if (parent.equals("editorfields")) {
                  if (nodeName.equals("name")) {
                    if (fields==null) {
                      fields=new ArrayList<EditorField>();
                    }
                    field = plugin.newEditorField();
                    fields.add(field);
                    field.setFieldValue(nodeName, content);
                  } else {
                    field.setFieldValue(nodeName, content);
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
        }
      }
    }
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
    MoodleModAssignAssignment[] results=null;
    if (assignments!=null) {
      results=new MoodleModAssignAssignment[assignments.size()];
      results=assignments.toArray(results);
    }
    return results;
  }
  
}
