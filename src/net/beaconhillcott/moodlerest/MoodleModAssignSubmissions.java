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
 *
 * @author root
 */
public class MoodleModAssignSubmissions implements Serializable {
  
  private Long assignmentId=null;
  private ArrayList<Submission>submissions=null;
  
  public MoodleModAssignSubmissions() { submissions=new ArrayList<Submission>(); submissions=new ArrayList<Submission>(); }
  public MoodleModAssignSubmissions(Long assignmentId) { this.assignmentId=assignmentId; submissions=new ArrayList<Submission>(); submissions=new ArrayList<Submission>(); }

  public void setAssignmentId(Long assignmentId) { this.assignmentId=assignmentId; }
  public Long getAssignmentId() { return assignmentId; }
  public ArrayList<Submission> getSubmissions() { return submissions; }
  public void setSubmissions(ArrayList<Submission> submissions) { this.submissions = submissions; }
  
  public Submission newSubmission() {
    if (submissions==null) { submissions=new ArrayList<Submission>(); }
    Submission submission=new Submission();
    submissions.add(submission);
    return submission;
  }
  
  public Submission newSubmission(Long id) {
    if (submissions==null) { submissions=new ArrayList<Submission>(); }
    Submission submission=new Submission(id);
    submissions.add(submission);
    return submission;
  }
  
  public void setFieldValue(String name, String value) {
    if (value!=null) {
      if (!value.isEmpty()) {
          if (name.equals("assignmentid")) { setAssignmentId(Long.parseLong(value)); }
      }
    }
  }

  public class Submission {
    
    private Long id=null;
    private Long userId=null;
    private Long timeCreated=null;
    private Long timeModified=null;
    private String status=null;
    private Long groupId=null;
    private ArrayList<Plugin> plugins=null;

    public Submission() { plugins=new ArrayList<Plugin>(); }
    public Submission(Long id) { this.id=id; plugins=new ArrayList<Plugin>(); }
    
    public Plugin newPlugin() {
      if (plugins==null) { plugins=new ArrayList<Plugin>(); }
      Plugin plugin=new Plugin();
      plugins.add(plugin);
      return plugin;
    }
    
    public Plugin newPlugin(String type) {
      if (plugins==null) { plugins=new ArrayList<Plugin>(); }
      Plugin plugin=new Plugin(type);
      plugins.add(plugin);
      return plugin;
    }
    
    public void setFieldValue(String name, String value) {
      if (value!=null) {
        if (!value.isEmpty()) {
          if (name.equals("id") || name.equals("userid") || name.equals("timecreated")|| name.equals("timemodified")|| name.equals("groupid")) {
            if (name.equals("id")) { setId(Long.parseLong(value)); }
            if (name.equals("userid")) { setUserId(Long.parseLong(value)); }
            if (name.equals("timecreated")) { setTimeCreated(Long.parseLong(value)); }
            if (name.equals("timemodified")) { setTimeModified(Long.parseLong(value)); }
            if (name.equals("groupid")) { setGroupId(Long.parseLong(value)); }
          } else {
            if (name.equals("status")) {
              setStatus(value);
            }
          }
        }
      }
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getUserId() {
      return userId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }

    public Long getTimeCreated() {
      return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
      this.timeCreated = timeCreated;
    }

    public Long getTimeModified() {
      return timeModified;
    }

    public void setTimeModified(Long timeModified) {
      this.timeModified = timeModified;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public Long getGroupId() {
      return groupId;
    }

    public void setGroupId(Long groupId) {
      this.groupId = groupId;
    }

    public ArrayList<Plugin> getPlugins() {
      return plugins;
    }

    public void setPlugins(ArrayList<Plugin> plugins) {
      this.plugins = plugins;
    }
  
    public class Plugin {

      private String type=null;
      private String name=null;
      private ArrayList<FileArea> areas=null;
      private ArrayList<EditorField> editorFields=null;
      
      public Plugin() { areas=new ArrayList<FileArea>(); editorFields=new ArrayList<EditorField>(); }
      public Plugin(String type) { this.type=type; areas=new ArrayList<FileArea>(); editorFields=new ArrayList<EditorField>(); }
      
      public void setFieldValue(String name, String value) {
        if (value!=null) {
          if (name.equals("type")) {
            setType(value);
          }
          if (name.equals("Name")) {
            setName(value);
          }
        }
      }

      public FileArea newFileArea() {
        if (areas==null) { areas=new ArrayList<FileArea>(); }
        FileArea area=new FileArea();
        areas.add(area);
        return area;
      }
      
      public FileArea newFileArea(String areaName) {
        if (areas==null) { areas=new ArrayList<FileArea>(); }
        FileArea area=new FileArea(areaName);
        areas.add(area);
        return area;
      }
      
      public EditorField newEditorField() {
        if (editorFields==null) { editorFields=new ArrayList<EditorField>(); }
        EditorField field=new EditorField();
        editorFields.add(field);
        return field;
      }
      
      public EditorField newEditorField(String name) {
        if (editorFields==null) { editorFields=new ArrayList<EditorField>(); }
        EditorField field=new EditorField(name);
        editorFields.add(field);
        return field;
      }
      
      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public ArrayList<FileArea> getAreas() {
        return areas;
      }

      public void setAreas(ArrayList<FileArea> areas) {
        this.areas = areas;
      }

      public ArrayList<EditorField> getEditorFields() {
        return editorFields;
      }

      public void setEditorFields(ArrayList<EditorField> editorFields) {
        this.editorFields = editorFields;
      }
      
      public class FileArea {
        
        public String area=null;
        public ArrayList<File>files=null;
        
        public FileArea(){ files=new ArrayList<File>(); }
        public FileArea(String area){ this.area=area; files=new ArrayList<File>(); }
        
        public void setFieldValue(String name, String value) {
          if (value!=null) {
            if (name.equals("area")) {
              setArea(value);
            }
          }
        }

        public String getArea() {
          return area;
        }

        public void setArea(String area) {
          this.area = area;
        }

        public ArrayList<File> getFiles() {
          return files;
        }

        public void setFiles(ArrayList<File> files) {
          this.files = files;
        }
        
        public File newFile() {
          if (files==null) { files=new ArrayList<File>(); }
          File file=new File();
          files.add(file);
          return file;
        }
        
        public File newFile(String filePath) {
          if (files==null) { files=new ArrayList<File>(); }
          File file=new File(filePath);
          files.add(file);
          return file;
        }
        
        public class File {
          
          private String filePath=null;
          
          public File(){}
          public File(String filePath){ this.filePath=filePath; }

          public void setFieldValue(String name, String value) {
            if (value!=null) {
              if (name.equals("filepath")) {
                setFilePath(value);
              }
            }
          }
          
          public String getFilePath() {
            return filePath;
          }

          public void setFilePath(String filePath) {
            this.filePath = filePath;
          }
          
        }
      }
      
      public class EditorField {
        
        private String name=null;
        private String description=null;
        private String text=null;
        private Integer format=null;

        public EditorField() {}
        public EditorField(String name) { this.name=name; }

        public void setFieldValue(String name, String value) {
          if (value!=null) {
            if (!value.isEmpty()) {
              if (name.equals("format")) { setFormat(Integer.parseInt(value)); }
            } else {
              if (name.equals("name")) {
                setName(value);
              }
              if (name.equals("description")) {
                setDescription(value);
              }
              if (name.equals("text")) {
                setText(value);
              }
            }
          }
        }
        
        public String getName() {
          return name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public String getDescription() {
          return description;
        }

        public void setDescription(String description) {
          this.description = description;
        }

        public String getText() {
          return text;
        }

        public void setText(String text) {
          this.text = text;
        }

        public Integer getFormat() {
          return format;
        }

        public void setFormat(Integer format) {
          this.format = format;
        }
        
      }
    }
  }
}
