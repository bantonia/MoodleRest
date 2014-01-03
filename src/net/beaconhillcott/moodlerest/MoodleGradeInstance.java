/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.beaconhillcott.moodlerest;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author root
 */
public class MoodleGradeInstance implements Serializable {
  
  private Long id=null;
  private Long raterId=null;
  private Long itemId=null;
  private String rawGrade=null;
  private Integer status=null;
  private String feedback=null;
  private DescriptionFormat feedbackFormat=null;
  private Long timeModified=null;
  private ArrayList<Guide> guide=null;
  private ArrayList<Rubric> rubric=null;
  
  public MoodleGradeInstance() {
  }

  public MoodleGradeInstance(Long id) {
    this.id=id;
  }
  
  public void setFieldValue(String name, Object value) {
    if (name!=null) {
      if (value!=null) {
        if (name.equals("id") || name.equals("raterid") || name.equals("itemid") || name.equals("status") || name.equals("feedbackformat") || name.equals("timemodified")) {
          if (!((String)value).equals("")) {
            if (name.equals("id")) {
              setId(Long.parseLong((String)value));
            }
            if (name.equals("raterid")) {
              setRaterId(Long.parseLong((String)value));
            }
            if (name.equals("itemid")) {
              setItemId(Long.parseLong((String)value));
            }
            if (name.equals("status")) {
              setStatus(Integer.parseInt((String)value));
            }
            if (name.equals("timemodified")) {
              setTimeModified(Long.parseLong((String)value));
            }
            if (name.equals("feedbackformat")) {
              for (DescriptionFormat key : DescriptionFormat.values()) {
                if ((""+key.toInt()).equals(value)) {
                  setFeedbackFormat(key);
                  break;
                }
              }
            }
          }
        }
      }
      if (name.equals("rawgrade") || name.equals("feedback")) {
        if (name.equals("rawgrade")) setRawGrade((String) value);
        if (name.equals("feedback")) setFeedback((String) value);
      }
    }
  }

  public ArrayList<Guide> getGuide() {
    return guide;
  }

  public void setGuide(ArrayList<Guide> guide) {
    this.guide = guide;
  }

  public ArrayList<Rubric> getRubric() {
    return rubric;
  }

  public void setRubric(ArrayList<Rubric> rubric) {
    this.rubric = rubric;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRaterId() {
    return raterId;
  }

  public void setRaterId(Long raterId) {
    this.raterId = raterId;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public String getRawGrade() {
    return rawGrade;
  }

  public void setRawGrade(String rawGrade) {
    this.rawGrade = rawGrade;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public DescriptionFormat getFeedbackFormat() {
    return feedbackFormat;
  }

  public void setFeedbackFormat(DescriptionFormat feedbackFormat) {
    this.feedbackFormat = feedbackFormat;
  }

  public Long getTimeModified() {
    return timeModified;
  }

  public void setTimeModified(Long timeModified) {
    this.timeModified = timeModified;
  }

  public Guide newGuide() {
    if (guide==null) {
      guide=new ArrayList<Guide>();
    }
    Guide guideTemp=new Guide();
    guide.add(guideTemp);
    return guideTemp;
  }

  public Rubric newRubric() {
    if (rubric==null) {
      rubric=new ArrayList<Rubric>();
    }
    Rubric rubricTemp=new Rubric();
    rubric.add(rubricTemp);
    return rubricTemp;
  }
  
  public class Guide {
    
    private ArrayList<Criteria> criteria=null;
    
    public Guide() {
    }

    public ArrayList<Criteria> getCriteria() {
      return criteria;
    }

    public void setCriteria(ArrayList<Criteria> criteria) {
      this.criteria = criteria;
    }
    
    public Criteria newCriteria() {
      return newCriteria(null);
    }
  
    public Criteria newCriteria(Long id) {
      if (criteria==null) {
        criteria=new ArrayList<Criteria>();
      }
      Criteria criteriaTemp=(id!=null?new Criteria(id):new Criteria());
      criteria.add(criteriaTemp);
      return criteriaTemp;
    }
  }

  public class Rubric {
    
    private ArrayList<Criteria> criteria=null;
    
    public Rubric() {
    }

    public ArrayList<Criteria> getCriteria() {
      return criteria;
    }

    public void setCriteria(ArrayList<Criteria> criteria) {
      this.criteria = criteria;
    }
    
    public Criteria newCriteria() {
      return newCriteria(null);
    }
  
    public Criteria newCriteria(Long id) {
      if (criteria==null) {
        criteria=new ArrayList<Criteria>();
      }
      Criteria criteriaTemp=(id!=null?new Criteria(id):new Criteria());
      criteria.add(criteriaTemp);
      return criteriaTemp;
    }
  }
  
  public class Criteria {

    private Long id=null;
    private Long criteriaId=null;
    private Long levelId=null;
    private String remark=null;
    private DescriptionFormat remarkFormat=null;
    private Double score=null; // This is unused in a rubric object.
    
    public Criteria() {
    }

    public Criteria(Long id) {
      this.id=id;
    }
    
    public void setFieldValue(String name, Object value) {
      if (name!=null) {
        if (value!=null) {
          if (name.equals("id") || name.equals("criteriaid") || name.equals("levelid") || name.equals("score") || name.equals("remarkformat")) {
            if (!((String)value).equals("")) {
              if (name.equals("id")) {
                setId(Long.parseLong((String)value));
              }
              if (name.equals("contextid")) {
                setCriteriaId(Long.parseLong((String)value));
              }
              if (name.equals("levelid")) {
                setLevelId(Long.parseLong((String)value));
              }
              if (name.equals("score")) {
                setScore(Double.parseDouble((String)value));
              }
              if (name.equals("remarkformat")) {
                for (DescriptionFormat key : DescriptionFormat.values()) {
                  if ((""+key.toInt()).equals(value)) {
                    setRemarkFormat(key);
                    break;
                  }
                }
              }
            }
          }
        }
        if (name.equals("remark")) {
          setRemark((String)value);
        }
      }
    }
    
    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
      this.criteriaId = criteriaId;
    }

    public Long getLevelId() {
      return levelId;
    }

    public void setLevelId(Long levelId) {
      this.levelId = levelId;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public DescriptionFormat getRemarkFormat() {
      return remarkFormat;
    }

    public void setRemarkFormat(DescriptionFormat remarkFormat) {
      this.remarkFormat = remarkFormat;
    }

    public Double getScore() {
      return score;
    }

    public void setScore(Double score) {
      this.score = score;
    }
  }
  
}
