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
public class GradeArea implements Serializable {
  
  private Long cmid=null;
  private Long contextId=null;
  private String component=null;
  private String activeMethod=null;
  
  private ArrayList<GradeDefinition> definitions=null;

  public GradeArea() {
    definitions=new ArrayList<GradeDefinition>();
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
    private ArrayList<Guide> guide=null;
    private Rubric rubric=null;

    public GradeDefinition() {
      guide=new ArrayList<Guide>();
      rubric=new Rubric();
    }
    
    public Guide newGuide() {
      Guide newGuide=new Guide();
      guide.add(newGuide);
      return newGuide;
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

    public ArrayList<Guide> getGuide() {
      return guide;
    }

    public void setGuide(ArrayList<Guide> guide) {
      this.guide = guide;
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
        private Double maxscore=null;

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

        public Double getMaxscore() {
          return maxscore;
        }

        public void setMaxscore(Double maxscore) {
          this.maxscore = maxscore;
        }

      }
    }

    public class Rubric implements Serializable {

      private ArrayList<RubricCriteria> rubricCriteria=null;
      
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

          public String getDescription() {
            return description;
          }

          public void setDescription(String definition) {
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
