package coe.dataCollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "general_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})})
@SecondaryTable(name="research_scholarly", 
        pkJoinColumns=@PrimaryKeyJoinColumn(name="uid"))

public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "uid", table = "general_info")
  private Long uid;
  
  // feilds from general_info
  
  @Column(name = "last_name", table = "general_info")
  private String lastName;

  @Column(name = "first_name", table = "general_info")
  private String firstName;
  
  @ManyToOne
  @JoinColumn(name = "_load", table = "general_info")
  private CLoad load;
  
  @ManyToOne
  @JoinColumn(name = "_rank", table = "general_info")
  private URank rank;
  
  @ManyToOne
  @JoinColumn(name = "_status", table = "general_info")
  private UStatus status;
  
  @ManyToOne
  @JoinColumn(name = "_dept", table = "general_info")
  private Department dept;
  
  // feilds from research_scholarly
  
  @Column(name = "jour_pubs", table = "research_scholarly")
  private int journals;
  
  @Column(name = "conf_pubs", table = "research_scholarly")
  private int conferences;
  
  @Column(name = "books", table = "research_scholarly")
  private int books;
  
  @Column(name = "chapters", table = "research_scholarly")
  private int chapters;
  
  @Column(name = "grants", table = "research_scholarly")
  private Long grants;
  
  @Column(name = "res_exp_total", table = "research_scholarly")
  private Long researchExperienceTotal;
  
  @Column(name = "res_exp_students", table = "research_scholarly")
  private Long researchExperienceStudents;
  
  @Column(name = "phd_advised", table = "research_scholarly")
  private int phdAdvised;
  
  @Column(name = "phd_completed", table = "research_scholarly")
  private int phdCompleted;
  
  @Column(name = "ms_completed", table = "research_scholarly")
  private int msCompleted;
  
  @Column(name = "patent_innovation", table = "research_scholarly")
  private int patentInnovation;

	// make setters and getters
}