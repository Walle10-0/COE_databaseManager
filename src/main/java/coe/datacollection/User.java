package coe.datacollection;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "general_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})})
@SecondaryTable(name="research_scholarly", 
        pkJoinColumns=@PrimaryKeyJoinColumn(name="uid"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid", table = "general_info", nullable = false)
    private Long userId;

    // fields from general_info
    @Column(name = "last_name", table = "general_info")
    private String lastName;

    @Column(name = "first_name", table = "general_info")
    private String firstName;

    @Column(name = "pin", table = "general_info")
    private char[] pin;

    @Column(name = "salt", table = "general_info")
    private char[] salt;

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
	@JsonManagedReference
    @JoinColumn(name = "_dept", table = "general_info", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "_user_role", table = "general_info", nullable = false)
    private UserRole userRole;

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

    @Column(name = "ug_mentored", table = "research_scholarly")
    private int ugMentored;

    // complex stuff
	@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teaching> teaching;

	@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UClasses> classes;

	@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UServices> serviceActivity;

/*
    // Standard getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long uid) {
        this.userId = uid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserRole getRole() {
        return userRole;
    }

    public void setRole(UserRole role) {
        this.userRole = role;
    }

    public char[] getPin() {
        return pin;
    }

    public void setPin(char[] newPin) {
        this.pin = newPin;
    }

    public char[] getSalt() {
        return salt;
    }

    public void setSalt(char[] theOcean) {
        this.salt = theOcean;
    }

    public CLoad getLoad() {
        return load;
    }

    public void setLoad(CLoad load) {
        this.load = load;
    }

    public URank getRank() {
        return rank;
    }

    public void setRank(URank rank) {
        this.rank = rank;
    }

    public UStatus getStatus() {
        return status;
    }

    public void setStatus(UStatus status) {
        this.status = status;
    }
	
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department dept) {
        this.department = dept;
    }

    public int getJournals() {
        return journals;
    }

    public void setJournals(int journals) {
        this.journals = journals;
    }

    public int getConferences() {
        return conferences;
    }

    public void setConferences(int conferences) {
        this.conferences = conferences;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public Long getGrants() {
        return grants;
    }

    public void setGrants(Long grants) {
        this.grants = grants;
    }

    public Long getResearchExperienceTotal() {
        return researchExperienceTotal;
    }

    public void setResearchExperienceTotal(Long researchExperienceTotal) {
        this.researchExperienceTotal = researchExperienceTotal;
    }

    public Long getResearchExperienceStudents() {
        return researchExperienceStudents;
    }

    public void setResearchExperienceStudents(Long researchExperienceStudents) {
        this.researchExperienceStudents = researchExperienceStudents;
    }

    public int getPhdAdvised() {
        return phdAdvised;
    }

    public void setPhdAdvised(int phdAdvised) {
        this.phdAdvised = phdAdvised;
    }

    public int getPhdCompleted() {
        return phdCompleted;
    }

    public void setPhdCompleted(int phdCompleted) {
        this.phdCompleted = phdCompleted;
    }

    public int getMsCompleted() {
        return msCompleted;
    }

    public void setMsCompleted(int msCompleted) {
        this.msCompleted = msCompleted;
    }

    public int getPatentInnovation() {
        return patentInnovation;
    }

    public void setPatentInnovation(int patentInnovation) {
        this.patentInnovation = patentInnovation;
    }

    public int getUgMentored() {
        return ugMentored;
    }

    public void setUgMentored(int newMentored) {
        this.ugMentored = newMentored;
    }

    public List<Teaching> getTeaching() {
        return this.teaching;
    }

    public void setTeaching(List<Teaching> newTeaching) {
        this.teaching = newTeaching;
    }

    public List<UClasses> getClasses() {
        return this.classes;
    }

    public void setClasses(List<UClasses> newClasses) {
        this.classes = newClasses;
    }

    public List<UServices> getServiceActivity() {
        return this.serviceActivity;
    }

    public void setServiceActivity(List<UServices> newServices) {
        this.serviceActivity = newServices;
    }*/
}
