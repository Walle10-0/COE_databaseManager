package coe.datacollection;

import coe.datacollection.EntityDependencies.Teaching;
import coe.datacollection.EntityDependencies.UClasses;
import coe.datacollection.EntityDependencies.UServices;
import coe.datacollection.EntityDependencies.CLoad;
import coe.datacollection.EntityDependencies.URank;
import coe.datacollection.EntityDependencies.UStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Data
@Entity
@Table(name = "general_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})})
@SecondaryTable(name="research_scholarly", 
        pkJoinColumns=@PrimaryKeyJoinColumn(name="uid"))

public class User 
{
    public User() {
        // default constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid", table = "general_info", nullable = false)
    private Long userId;

    @Column(name = "last_name", table = "general_info")
    private String lastName;

    @Column(name = "first_name", table = "general_info")
    private String firstName;

    @Column(name = "username", table = "general_info")
    private String username;

    @Column(name = "pin", table = "general_info")
    private String pin;

    @ManyToOne
    @JoinColumn(name = "_dept", table = "general_info", nullable = false)
    private Department department;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "_user_role", table = "general_info", nullable = false)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "_rank", table = "general_info")
    private URank rank;

    public String getLastName() {
        return lastName;
    }
    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String password) {
        this.pin = password;
    }

    public void setDepartment(String deptName) {
        department.setDepartment(deptName);
    }

    public void setDepartment(Department deptName) {
        department = deptName;
    }

    public void setAssignedRole(String roleName) {
            userRole.setAssignedRole(roleName);
    }

    public void setAssignedRole(UserRole role) {
        this.userRole = role;
    }

    @ManyToOne
    @JoinColumn(name = "_load", table = "general_info")
    private CLoad load;

    public void setRank(String rankName) {
        rank.setRank(rankName);
    }

    public void setRank(URank rank) {
        this.rank = rank;
    }

    @ManyToOne
    @JoinColumn(name = "_status", table = "general_info")
    private UStatus status;

    // fields from research_scholarly

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
    
    @Column(name = "awards", table = "research_scholarly")
    private Long awards;

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
}
