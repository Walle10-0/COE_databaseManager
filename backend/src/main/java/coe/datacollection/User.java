package coe.datacollection;

import coe.datacollection.Teaching;
import coe.datacollection.UClasses;
import coe.datacollection.UServices;

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

}
