/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.beaconhillcott.moodlerest;

import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author root
 */
public class MoodleGradeArea implements Serializable {
  
  private Long cmid=null;
  private Long contextId=null;
  private String component=null;
  private String activeMethod=null;
  private ArrayList<GradeDefinition> definitions=null;

  public MoodleGradeArea() {
    definitions=new ArrayList<GradeDefinition>();
  }

  public void setFieldValue(String name, Object value) {
    if (value!=null) {
        if (name.equals("cmid") || name.equals("contextid")) {
          if (!((String)value).equals("")) {
            if (name.equals("cmid")) {
              setCmid(Long.parseLong((String)value));
            }
            if (name.equals("contextid")) {
              setContextId(Long.parseLong((String)value));
            }
          }
        }
        if (name.equals("component") || name.equals("activemethod")) {
          if (name.equals("component")) {
            setComponent((String)value);
          }
          if (name.equals("activemethod")) {
            setActiveMethod((String)value);
          }
        }
      if (name.equals("definitions")) {
        if (definitions==null) {
          definitions=new ArrayList<GradeDefinition>();
        }
        definitions.add((GradeDefinition) value);
      }
    }
  }
  
  public Long getCmid() {
    return cmid;
  }

  public void setCmid(Long cmid) {
    this.cmid = cmid;
  }

  public Long getContextId() {
    return contextId;
  }

  public void setContextId(Long contextId) {
    this.contextId = contextId;
  }

  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public ArrayList<GradeDefinition> getDefinitions() {
    return definitions;
  }

  public void setDefinitions(ArrayList<GradeDefinition> definitions) {
    this.definitions = definitions;
  }

  public String getActiveMethod() {
    return activeMethod;
  }

  public void setActiveMethod(String activeMethod) {
    this.activeMethod = activeMethod;
  }
  
  public GradeDefinition newGradeDefinition() {
    GradeDefinition gradeDefinition = new GradeDefinition();
    definitions.add(gradeDefinition);
    return gradeDefinition;
  }
  
  public class GradeDefinition implements Serializable {
  
    private Long id=null;
    private String method=null;
    private String name=null;
    private String description=null;
    private DescriptionFormat descriptionFormat=null;
    private Integer status=null;
    private Long copiedFromId=null;
    private Long timeCreated=null;
    private Long userCreated=null;
    private Long timeModified=null;
    private Long userModified=null;
    private Long timeCopied=null;
    private Guide guide=null;
    private Rubric rubric=null;

    public GradeDefinition() {
      guide=new Guide();
      rubric=new Rubric();
    }
    
    public void setFieldValue(String name, Object value) {
      if (value!=null) {
        if (name.equals("id") || name.equals("status") || name.equals("descriptionformat") || name.equals("copiedfromid") || name.equals("timecreated") || name.equals("usercreated") || name.equals("timemodified") || name.equals("usermodified") || name.equals("timecopied")) {
          if (!((String)value).equals("")) {
            if (name.equals("id")) {
              setId(Long.parseLong((String)value));
            }
            if (name.equals("status")) {
              setStatus(Integer.parseInt((String)value));
            }
            if (name.equals("copiedfromid")) {
              setCopiedFromId(Long.parseLong((String)value));
            }
            if (name.equals("timecreated")) {
              setTimeCreated(Long.parseLong((String)value));
            }
            if (name.equals("usercreated")) {
              setUserCreated(Long.parseLong((String)value));
            }
            if (name.equals("timemodified")) {
              setTimeModified(Long.parseLong((String)value));
            }
            if (name.equals("usermodified")) {
              setUserModified(Long.parseLong((String)value));
            }
            if (name.equals("timecopied")) {
              setTimeCopied(Long.parseLong((String)value));
            }
            if (name.equals("descriptionformat")) {
              for (DescriptionFormat key : DescriptionFormat.values()) {
                if ((""+key.toInt()).equals(value)) {
                  setDescriptionFormat(key);
                  break;
                }
              }
            }
          }
        }
        if (name.equals("name") || name.equals("description")) {
          if (name.equals("name")) {
            setName((String)value);
          }
          if (name.equals("description")) {
            setDescription((String)value);
          }
        }
        if (name.equals("guide")) {
          if (guide==null) {
            guide=new Guide();
          }
          guide=((Guide) value);
        }
        if (name.equals("rubric")) {
            rubric=(Rubric) value;
        }
      }
    }
    
    public Guide newGuide() {
      guide=new Guide();
      return guide;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getMethod() {
      return method;
    }

    public void setMethod(String method) {
      this.method = method;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public DescriptionFormat getDescriptionFormat() {
      return descriptionFormat;
    }

    public void setDescriptionFormat(DescriptionFormat descriptionFormat) {
      this.descriptionFormat = descriptionFormat;
    }

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

    public Long getCopiedFromId() {
      return copiedFromId;
    }

    public void setCopiedFromId(Long copiedFromId) {
      this.copiedFromId = copiedFromId;
    }

    public Long getTimeCreated() {
      return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
      this.timeCreated = timeCreated;
    }

    public Long getUserCreated() {
      return userCreated;
    }

    public void setUserCreated(Long userCreated) {
      this.userCreated = userCreated;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Long getTimeModified() {
      return timeModified;
    }

    public void setTimeModified(Long timeModified) {
      this.timeModified = timeModified;
    }

    public Long getUserModified() {
      return userModified;
    }

    public void setUserModified(Long userModified) {
      this.userModified = userModified;
    }

    public Long getTimeCopied() {
      return timeCopied;
    }

    public void setTimeCopied(Long timeCopied) {
      this.timeCopied = timeCopied;
    }

    public Guide getGuide() {
      return guide;
    }

    public void setGuide(Guide guide) {
      guide = guide;
    }

    public Rubric getRubric() {
      return rubric;
    }

    public void setRubric(Rubric rubric) {
      this.rubric = rubric;
    }

    public class Guide implements Serializable {
      private ArrayList<GuideCriteria> guideCriteria=null;
      private ArrayList<GuideComment> guideComment=null;
      
      public void setFieldValue(String name, Object value) {
        if (value!=null) {
          if (name.equals("guidecriteria") || name.equals("guidecomment")) {
            if (name.equals("guidecriteria")) {
              if (guideCriteria==null) {
                guideCriteria=new ArrayList<GuideCriteria>();
              }
              guideCriteria.add((GuideCriteria) value);
            }
            if (name.equals("guidecomment")) {
              if (guideComment==null) {
                guideComment=new ArrayList<GuideComment>();
              }
              guideComment.add((GuideComment) value);
            }
          }
        }
      }
      
      public GuideCriteria newGuideCriteria() {
        GuideCriteria guideCriteria=new GuideCriteria();
        this.guideCriteria.add(guideCriteria);
        return guideCriteria;
      }
      
      public GuideComment newGuideComment() {
        GuideComment guideComment=new GuideComment();
        this.guideComment.add(guideComment);
        return guideComment;
      }

      public Guide() {
        guideCriteria=new ArrayList<GuideCriteria>();
        guideComment=new ArrayList<GuideComment>();
      }

      public ArrayList<GuideCriteria> getGuideCriteria() {
        return guideCriteria;
      }

      public void setGuideCriteria(ArrayList<GuideCriteria> guideCriteria) {
        this.guideCriteria = guideCriteria;
      }

      public ArrayList<GuideComment> getGuideComment() {
        return guideComment;
      }

      public void setGuideComment(ArrayList<GuideComment> guideComment) {
        this.guideComment = guideComment;
      }

      public class GuideComment {

        private Long id=null;
        private Long sortOrder=null;
        private String description=null;
        private DescriptionFormat descriptionFormat=null;

        public void setFieldValue(String name, String value) {
          if (value!=null) {
            if (name.equals("id") || name.equals("sortorder") || name.equals("description") || name.equals("descriptionformat")) {
              if (!(value).equals("")) {
                if (name.equals("id")) {
                  setId(Long.parseLong(value));
                }
                if (name.equals("sortorder")) {
                  setSortOrder(Long.parseLong(value));
                }
                if (name.equals("descriptionformat")) {
                  for (DescriptionFormat key : DescriptionFormat.values()) {
                    if ((""+key.toInt()).equals(value)) {
                      setDescriptionFormat(key);
                      break;
                    }
                  }
                }
              }
              if (name.equals("description")) {
                setDescription(value);
              }
            }
          }
        }
        
        public GuideComment() {
        }

        public Long getId() {
          return id;
        }

        public void setId(Long id) {
          this.id = id;
        }

        public Long getSortOrder() {
          return sortOrder;
        }

        public void setSortOrder(Long sortOrder) {
          this.sortOrder = sortOrder;
        }

        public String getDescription() {
          return description;
        }

        public void setDescription(String description) {
          this.description = description;
        }

        public DescriptionFormat getDescriptionFormat() {
          return descriptionFormat;
        }

        public void setDescriptionFormat(DescriptionFormat descriptionFormat) {
          this.descriptionFormat = descriptionFormat;
        }
      }

      public class GuideCriteria implements Serializable {

        private Long id=null;
        private Long sortOrder=null;
        private String description=null;
        private DescriptionFormat descriptionFormat=null;
        private String shortName=null;
        private String descriptionMarkers=null;
        private DescriptionFormat descriptionMarkersFormat=null;
        private Double maxScore=null;
        
        public void setFieldValue(String name, String value) {
          if (value!=null) {
            if (name.equals("id") || name.equals("sortorder") || name.equals("description") || name.equals("descriptionformat") || name.equals("shortname") || name.equals("descriptionmarkers") || name.equals("descriptionmarkersformat") || name.equals("maxscore")) {
              if (!(value).equals("")) {
                if (name.equals("id")) {
                  setId(Long.parseLong(value));
                }
                if (name.equals("sortorder")) {
                  setSortOrder(Long.parseLong(value));
                }
                if (name.equals("descriptionformat")) {
                  for (DescriptionFormat key : DescriptionFormat.values()) {
                    if ((""+key.toInt()).equals(value)) {
                      setDescriptionFormat(key);
                      break;
                    }
                  }
                }
                if (name.equals("descriptionmarkersformat")) {
                  for (DescriptionFormat key : DescriptionFormat.values()) {
                    if ((""+key.toInt()).equals(value)) {
                      setDescriptionMarkersFormat(key);
                      break;
                    }
                  }
                }
                if (name.equals("maxscore")) {
                  setMaxScore(Double.parseDouble(value));
                }
              }
              if (name.equals("description")) {
                setDescription(value);
              }
              if (name.equals("shortname")) {
                setShortName(value);
              }
              if (name.equals("descriptionmarkers")) {
                setDescriptionMarkers(value);
              }
            }
          }
        }

        public GuideCriteria() {
        }

        public Long getId() {
          return id;
        }

        public void setId(Long id) {
          this.id = id;
        }

        public Long getSortOrder() {
          return sortOrder;
        }

        public void setSortOrder(Long sortOrder) {
          this.sortOrder = sortOrder;
        }

        public String getDescription() {
          return description;
        }

        public void setDescription(String description) {
          this.description = description;
        }

        public DescriptionFormat getDescriptionFormat() {
          return descriptionFormat;
        }

        public void setDescriptionFormat(DescriptionFormat descriptionFormat) {
          this.descriptionFormat = descriptionFormat;
        }

        public String getShortName() {
          return shortName;
        }

        public void setShortName(String shortName) {
          this.shortName = shortName;
        }

        public String getDescriptionMarkers() {
          return descriptionMarkers;
        }

        public void setDescriptionMarkers(String descriptionMarkers) {
          this.descriptionMarkers = descriptionMarkers;
        }

        public DescriptionFormat getDescriptionMarkersFormat() {
          return descriptionMarkersFormat;
        }

        public void setDescriptionMarkersFormat(DescriptionFormat descriptionMarkersFormat) {
          this.descriptionMarkersFormat = descriptionMarkersFormat;
        }

        public Double getMaxScore() {
          return maxScore;
        }

        public void setMaxScore(Double maxScore) {
          this.maxScore = maxScore;
        }

      }
    }

    public class Rubric implements Serializable {

      private ArrayList<RubricCriteria> rubricCriteria=null;
      
      public void setFieldValue(String name, RubricCriteria value) {
        if (value!=null) {
          if (name.equals("rubric")) {
            if (rubricCriteria==null) {
              rubricCriteria=new ArrayList<RubricCriteria>();
            }
            rubricCriteria.add((RubricCriteria) value);
          }
        }
      }
      
      public RubricCriteria newRubricCriteria() {
        RubricCriteria rubricCriteria=new RubricCriteria();
        this.rubricCriteria.add(rubricCriteria);
        return rubricCriteria;
      }

      public Rubric() {
        rubricCriteria=new ArrayList<RubricCriteria>();
      }

      public class RubricCriteria implements Serializable {
        private Long id=null;
        private Integer sortOrder=null;
        private String description=null;
        private DescriptionFormat descriptionFormat=null;
        private ArrayList<Level> levels=null;

        public void setFieldValue(String name, String value) {
          if (value!=null) {
            if (name.equals("id") || name.equals("sortorder") || name.equals("description") || name.equals("descriptionformat")) {
              if (!(value).equals("")) {
                if (name.equals("id")) {
                  setId(Long.parseLong(value));
                }
                if (name.equals("sortorder")) {
                  setSortOrder(Integer.parseInt(value));
                }
                if (name.equals("descriptionformat")) {
                  for (DescriptionFormat key : DescriptionFormat.values()) {
                    if ((""+key.toInt()).equals(value)) {
                      setDescriptionFormat(key);
                      break;
                    }
                  }
                }
              }
              if (name.equals("description")) {
                setDescription(value);
              }
            }
          }
        }
        
        public Level newLevel() {
          Level level=new Level();
          levels.add(level);
          return level;
        }
        
        public class Level {
          private Long id=null;
          private Double score=null;
          private String definition=null;
          private DescriptionFormat definitionFormat=null;
          
          public void setFieldValue(String name, String value) {
            if (value!=null) {
              if (name.equals("id") || name.equals("score") || name.equals("definition") || name.equals("definitionformat")) {
                if (!(value).equals("")) {
                  if (name.equals("id")) {
                    setId(Long.parseLong(value));
                  }
                  if (name.equals("score")) {
                    setScore(Double.parseDouble(value));
                  }
                  if (name.equals("definitionformat")) {
                    for (DescriptionFormat key : DescriptionFormat.values()) {
                      if ((""+key.toInt()).equals(value)) {
                        setDefinitionFormat(key);
                        break;
                      }
                    }
                  }
                }
                if (name.equals("definition")) {
                  setDefinition(value);
                }
              }
            }
          }

          public Level() {
          }

          public Long getId() {
            return id;
          }

          public void setId(Long id) {
            this.id = id;
          }

          public Double getScore() {
            return score;
          }

          public void setScore(Double score) {
            this.score = score;
          }

          public String getDefinition() {
            return definition;
          }

          public void setDefinition(String definition) {
            this.definition = definition;
          }

          public DescriptionFormat getDefinitionFormat() {
            return definitionFormat;
          }

          public void setDefinitionFormat(DescriptionFormat definitionFormat) {
            this.definitionFormat = definitionFormat;
          }
        }

        public RubricCriteria() {
          levels=new ArrayList<Level>();
        }

        public Long getId() {
          return id;
        }

        public void setId(Long id) {
          this.id = id;
        }

        public Integer getSortOrder() {
          return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
          this.sortOrder = sortOrder;
        }

        public String getDescription() {
          return description;
        }

        public void setDescription(String description) {
          this.description = description;
        }

        public DescriptionFormat getDescriptionFormat() {
          return descriptionFormat;
        }

        public void setDescriptionFormat(DescriptionFormat descriptionFormat) {
          this.descriptionFormat = descriptionFormat;
        }

        public ArrayList<Level> getLevels() {
          return levels;
        }

        public void setLevels(ArrayList<Level> levels) {
          this.levels = levels;
        }
      }

      public ArrayList<RubricCriteria> getRubricCriteria() {
        return rubricCriteria;
      }

      public void setRubricCriteria(ArrayList<RubricCriteria> rubricCriteria) {
        this.rubricCriteria = rubricCriteria;
      }

    }
  }
}
