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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import net.beaconhillcott.moodlerest.MoodleModAssignSubmissions.Submission;
import net.beaconhillcott.moodlerest.MoodleModAssignSubmissions.Submission.Plugin;
import net.beaconhillcott.moodlerest.MoodleModAssignSubmissions.Submission.Plugin.EditorField;
import net.beaconhillcott.moodlerest.MoodleModAssignSubmissions.Submission.Plugin.FileArea;
import net.beaconhillcott.moodlerest.MoodleModAssignSubmissions.Submission.Plugin.FileArea.File;
import net.beaconhillcott.moodlerest.MoodleModAssignGrades.Grade;
import net.beaconhillcott.moodlerest.MoodleModAssignUserFlags.UserFlags;
import net.beaconhillcott.moodlerest.MoodleModAssignUserMappings.UserMappings;
import org.w3c.dom.NodeList;

/**
 *
 * @author root
 */
public class MoodleRestModAssign implements Serializable {
  
  public static MoodleModAssignAssignments[] getAssignments(Long[] courseIds) throws MoodleRestException, UnsupportedEncodingException {
    return getAssignments(courseIds, null, null);
  }
  
  public static MoodleModAssignAssignments[] getAssignments(Long[] courseIds, String[] capabilities) throws MoodleRestException, UnsupportedEncodingException {
    return getAssignments(courseIds, capabilities, null);
  }
  
  public static MoodleModAssignAssignments[] getAssignments(Long[] courseIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getAssignments(courseIds, null, warnings);
  }
  
  public static MoodleModAssignAssignments[] getAssignments(Long[] courseIds, String[] capabilities, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
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
    ArrayList<MoodleModAssignAssignments> courses=null;
    MoodleModAssignAssignments course=null;
    ArrayList<MoodleModAssignAssignments.Assignment> assignments=null;
    MoodleModAssignAssignments.Assignment assignment=null;
    MoodleModAssignAssignments.Assignment.Config config=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("courses")) {
        if (courses==null) {
          courses=new ArrayList<MoodleModAssignAssignments>();
        }
        if (nodeName.equals("id")) {
          course=new MoodleModAssignAssignments();
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
    MoodleModAssignAssignments[] results=null;
    if (courses!=null) {
      results=new MoodleModAssignAssignments[courses.size()];
      results=courses.toArray(results);
    }
    return results;
  }
  
  public static MoodleModAssignGrades[] getGrades(Long[] assignmentIds) throws MoodleRestException, UnsupportedEncodingException {
    return getGrades(assignmentIds, null, null);
  }
  
  public static MoodleModAssignGrades[] getGrades(Long[] assignmentIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getGrades(assignmentIds, null, warnings);
  }
  
  public static MoodleModAssignGrades[] getGrades(Long[] assignmentIds, Long since) throws MoodleRestException, UnsupportedEncodingException {
    return getGrades(assignmentIds, since, null);
  }
  
  public static MoodleModAssignGrades[] getGrades(Long[] assignmentIds, Long since, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
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
    ArrayList<MoodleModAssignGrades> assignments=null;
    MoodleModAssignGrades assignment=null;
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
            assignments=new ArrayList<MoodleModAssignGrades>();
          }
          assignment=new MoodleModAssignGrades();
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
    MoodleModAssignGrades[] results=null;
    if (assignments!=null) {
      results=new MoodleModAssignGrades[assignments.size()];
      results=assignments.toArray(results);
    }
    return results;
  }
  
  public static MoodleModAssignSubmissions[] getSubmissions(Long[] assignmentIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, null, null, null, warnings);
  }
  
  public static MoodleModAssignSubmissions[] getSubmissions(Long[] assignmentIds, String status, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, status, null, null, warnings);
  }
  
  public static MoodleModAssignSubmissions[] getSubmissionsBefore(Long[] assignmentIds, String status, Long time, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, status, null, time, warnings);
  }
  
  public static MoodleModAssignSubmissions[] getSubmissionsSince(Long[] assignmentIds, String status, Long time, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    return getSubmissions(assignmentIds, status, time, null, warnings);
  }
  
  public static MoodleModAssignSubmissions[] getSubmissions(Long[] assignmentIds, String status, Long since, Long before, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
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
    ArrayList<MoodleModAssignSubmissions> assignments=null;
    MoodleModAssignSubmissions assignment=null;
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
            assignments=new ArrayList<MoodleModAssignSubmissions>();
          }
          assignment=new MoodleModAssignSubmissions(Long.parseLong(content));
          assignments.add(assignment);
        }
      } else {
        if (parent.equals("submissions")) {
          if (nodeName.equals("id")) {
            submissions=assignment.getSubmissions();
            submission = assignment.newSubmission(Long.parseLong(content));
            submissions.add(submission);
          } else {
            submission.setFieldValue(nodeName, content);
          }
        } else {
          if (parent.equals("plugins")) {
            if (nodeName.equals("type")) {
              plugins=submission.getPlugins();
              plugin = submission.newPlugin(content);
              plugins.add(plugin);
            } else {
              plugin.setFieldValue(nodeName, content);
            }
          } else {
            if (parent.equals("fileareas")) {
              if (nodeName.equals("area")) {
                areas=plugin.getAreas();
                area = plugin.newFileArea(content);
                areas.add(area);
              } else {
                area.setFieldValue(nodeName, content);
              }
            } else {
              if (parent.equals("files")) {
                if (nodeName.equals("filepath")) {
                  files=area.getFiles();
                  file = area.newFile(content);
                  files.add(file);
                } else {
                  file.setFieldValue(nodeName, content);
                }
              } else {
                if (parent.equals("editorfields")) {
                  if (nodeName.equals("name")) {
                    fields=plugin.getEditorFields();
                    field = plugin.newEditorField(content);
                    fields.add(field);
                  } else {
                    field.setFieldValue(nodeName, content);
                  }
                } else {
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
    MoodleModAssignSubmissions[] results=null;
    if (assignments!=null) {
      results=new MoodleModAssignSubmissions[assignments.size()];
      results=assignments.toArray(results);
    }
    return results;
  }
  
  public static void lockSubmissions(Long assignmentId, Long[] userIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    common(MoodleServices.MOD_ASSIGN_LOCK_SUBMISSIONS, assignmentId, userIds, null, warnings);
  }
  
  public static void revealIdentities(Long assignmentId, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    common(MoodleServices.MOD_ASSIGN_REVEAL_IDENTITIES, assignmentId, null, null, warnings);
  }
  
  public static void revertSubmissionsToDraft(Long assignmentId, Long[] userIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    common(MoodleServices.MOD_ASSIGN_REVERT_SUBMISSIONS_TO_DRAFT, assignmentId, userIds, null, warnings);
  }
  
  public static void unlockSubmissions(Long assignmentId, Long[] userIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    common(MoodleServices.MOD_ASSIGN_UNLOCK_SUBMISSIONS, assignmentId, userIds, null, warnings);
  }
  
  private static void common(MoodleServices service, Long assignmentId, Long[] userIds, Object results, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=service.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    if (userIds!=null) {
      for (int i=0; i<userIds.length; i++) {
        data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userIds[i], MoodleServices.ENCODING.toString()));
      }
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
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
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
  }
  
  public static MoodleModAssignUserFlags[] getUserFlags(Long[] assignmentIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_GET_USER_FLAGS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<assignmentIds.length; i++) {
      data.append("&").append(URLEncoder.encode("assignmentids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentIds[i], MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleModAssignUserFlags> assignments=null;
    MoodleModAssignUserFlags assignment=null;
    ArrayList<UserFlags> userFlags=null;
    UserFlags flags=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("assignments")) {
        if (nodeName.equals("assignmentid")) {
          if (assignments==null) {
            assignments=new ArrayList<MoodleModAssignUserFlags>();
          }
          assignment=new MoodleModAssignUserFlags(Long.parseLong(content));
          assignments.add(assignment);
        }
      } else {
        if (parent.equals("userflags")) {
          if (nodeName.equals("id")) {
            userFlags=assignment.getUserFlags();
            flags = assignment.newUserFlags(Long.parseLong(content));
            userFlags.add(flags);
          } else {
            flags.setFieldValue(nodeName, content);
          }
        } else {
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
      }
    }
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
    MoodleModAssignUserFlags[] results=null;
    if (assignments!=null) {
      results=new MoodleModAssignUserFlags[assignments.size()];
      results=assignments.toArray(results);
    }
    return results;
  }
  
  public static MoodleModAssignUserMappings[] getUserMappings(Long[] assignmentIds, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_GET_USER_FLAGS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    for (int i=0; i<assignmentIds.length; i++) {
      data.append("&").append(URLEncoder.encode("assignmentids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentIds[i], MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<MoodleModAssignUserMappings> assignments=null;
    MoodleModAssignUserMappings assignment=null;
    ArrayList<UserMappings> userMappings=null;
    UserMappings mappings=null;
    ArrayList<MoodleWarning> warn=null;
    MoodleWarning warning=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (parent.equals("assignments")) {
        if (nodeName.equals("assignmentid")) {
          if (assignments==null) {
            assignments=new ArrayList<MoodleModAssignUserMappings>();
          }
          assignment=new MoodleModAssignUserMappings(Long.parseLong(content));
          assignments.add(assignment);
        }
      } else {
        if (parent.equals("mappings")) {
          if (nodeName.equals("id")) {
            userMappings=assignment.getUserMappings();
            mappings = assignment.newUserMappings(Long.parseLong(content));
            userMappings.add(mappings);
          } else {
            mappings.setFieldValue(nodeName, content);
          }
        } else {
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
      }
    }
    if (warn!=null) {
      if (warnings!=null) {
        warnings=new MoodleWarning[warn.size()];
        warnings=warn.toArray(warnings);
      }
    }
    MoodleModAssignUserMappings[] results=null;
    if (assignments!=null) {
      results=new MoodleModAssignUserMappings[assignments.size()];
      results=assignments.toArray(results);
    }
    return results;
  }
  
  public static void saveGrade(Long assignmentId, Long userId, Double grade, Boolean addAtempt, String workFlowState, Boolean applyToAll, String feedbackText, DescriptionFormat format, Integer fileManager) throws MoodleRestException, UnsupportedEncodingException {
    saveGrade(assignmentId,  userId,  grade, -1, addAtempt, workFlowState, applyToAll, feedbackText, format, fileManager);
  }
  
  public static void saveGrade(Long assignmentId, Long userId, Double grade, Integer attemptNumber, Boolean addAtempt, String workFlowState, Boolean applyToAll, String feedbackText, DescriptionFormat format, Integer fileManager) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_SAVE_GRADE.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("userid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userId, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("grade", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+grade, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("attemptnumber", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+attemptNumber, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("addattempt", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(addAtempt?1:0), MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("workflowstate", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+workFlowState, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("applytoall", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(applyToAll?1:0), MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[assignfeedbackcomments_editor][text]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+feedbackText, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[assignfeedbackcomments_editor][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+format.toInt(), MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[files_filemanager]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+fileManager, MoodleServices.ENCODING.toString()));
    data.trimToSize();
    MoodleCallRestWebService.call(data.toString());
  }
  
  public static void saveSubmission(Long assignmentId, String feedbackText, DescriptionFormat format, Integer itemId, Integer fileManager, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_SAVE_SUBMISSION.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[onlinetext_editor][text]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+feedbackText, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[onlinetext_editor][format]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+format.toInt(), MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[onlinetext_editor][itemid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+itemId, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("plugindata[files_filemanager]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+fileManager, MoodleServices.ENCODING.toString()));
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
  
    public static void saveUserExtensions(Long assignmentId, Long[] userIds, Long[] dates, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_SAVE_USER_EXTENSIONS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    for (int i=0; i<userIds.length; i++) {
      data.append("&").append(URLEncoder.encode("userids["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userIds[i], MoodleServices.ENCODING.toString()));
    }
    for (int i=0; i<dates.length; i++) {
      data.append("&").append(URLEncoder.encode("dates["+i+"]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+dates[i], MoodleServices.ENCODING.toString()));
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
    
  public static void submitForGrading(Long assignmentId, Integer acceptSubmissionStatement, MoodleWarning[] warnings) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_SUBMIT_FOR_GRADING.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("acceptsubmissionstatement", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+acceptSubmissionStatement, MoodleServices.ENCODING.toString()));
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
  
  public ModAssignUserFlagResult[] setUserFlags(Long assignmentId, ModAssignUserFlags[] userFlags) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_SET_USER_FLAGS.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    for (int i=0; i<userFlags.length; i++) {
      if (userFlags[i].getUserId()==null) throw new MoodleRestModAssignException("Required parameter: userid"); else data.append("&").append(URLEncoder.encode("userflags["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userFlags[i].getUserId(), MoodleServices.ENCODING.toString()));
      if (userFlags[i].getLocked()!=null) data.append("&").append(URLEncoder.encode("userflags["+i+"][locked]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(userFlags[i].getLocked()?1:0), MoodleServices.ENCODING.toString()));
      if (userFlags[i].getMailed()!=null) data.append("&").append(URLEncoder.encode("userflags["+i+"][mailed]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(userFlags[i].getMailed()?1:0), MoodleServices.ENCODING.toString()));
      if (userFlags[i].getExtensionDueDate()!=null) data.append("&").append(URLEncoder.encode("userflags["+i+"][extensionduedate]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userFlags[i].getExtensionDueDate(), MoodleServices.ENCODING.toString()));
      if (userFlags[i].getWorkflowState()!=null) data.append("&").append(URLEncoder.encode("userflags["+i+"][workflowstate]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userFlags[i].getWorkflowState(), MoodleServices.ENCODING.toString()));
      if (userFlags[i].getAllocatedMarker()!=null) data.append("&").append(URLEncoder.encode("userflags["+i+"][allocatedmarker]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+userFlags[i].getAllocatedMarker(), MoodleServices.ENCODING.toString()));
    }
    data.trimToSize();
    NodeList elements=MoodleCallRestWebService.call(data.toString());
    ArrayList<ModAssignUserFlagResult> flagResults=null;
    ModAssignUserFlagResult flagResult=null;
    for (int j=0; j<elements.getLength(); j++) {
      String parent=elements.item(j).getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      String content=elements.item(j).getTextContent();
      String nodeName=elements.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
      if (nodeName.equals("id")) {
        if (flagResults==null) {
          flagResults=new ArrayList<ModAssignUserFlagResult>();
        }
        flagResult=new ModAssignUserFlagResult(Long.parseLong(content));
        flagResults.add(flagResult);
      } else {
        flagResult.setFieldValue(nodeName, content);
      }
    }
    ModAssignUserFlagResult[] results=null;
    if (flagResults!=null) {
      results=new ModAssignUserFlagResult[flagResults.size()];
      results=flagResults.toArray(results);
    }
    return results;
  }
  
  public static void saveGrades(Long assignmentId, Boolean applyToAll, ModAssignGrades grades) throws MoodleRestException, UnsupportedEncodingException {
    if (MoodleCallRestWebService.isLegacy()) {
      throw new MoodleRestException(MoodleRestException.NO_LEGACY);
    }
    StringBuilder data=new StringBuilder();
    String functionCall=MoodleServices.MOD_ASSIGN_SAVE_GRADES.toString();
    if (MoodleCallRestWebService.getAuth()==null) {
      throw new MoodleRestModAssignException();
    } else {
      data.append(MoodleCallRestWebService.getAuth());
    }
    data.append("&").append(URLEncoder.encode("wsfunction", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(functionCall, MoodleServices.ENCODING.toString()));
    if (assignmentId==null) throw new MoodleRestModAssignException("Required parameter: assignmentId"); else data.append("&").append(URLEncoder.encode("assignmentid", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+assignmentId, MoodleServices.ENCODING.toString()));
    if (applyToAll==null) throw new MoodleRestModAssignException("Required parameter: applyToAll"); else data.append("&").append(URLEncoder.encode("applytoall", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+(applyToAll?1:0), MoodleServices.ENCODING.toString()));
    if (grades==null)
      throw new MoodleRestModAssignException("Required parameter: grades");
    else
      for (int i=0; i<grades.getGrades().size(); i++) {
        data.append("&").append(URLEncoder.encode("grades["+i+"][userid]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+grades.getGrades().get(i).getUserId(), MoodleServices.ENCODING.toString()));
        if (grades.getGrades().get(i).getAdvancedGradingData()==null && grades.getGrades().get(i).getGrade()!=null) data.append("&").append(URLEncoder.encode("grades["+i+"][grade]", MoodleServices.ENCODING.toString())).append("=").append(URLEncoder.encode(""+grades.getGrades().get(i).getGrade(), MoodleServices.ENCODING.toString()));
        
      }
  }
  
}
