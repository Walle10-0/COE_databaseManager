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
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})})
//@SecondaryTable(name="research_scholarly", 
//        pkJoinColumns=@PrimaryKeyJoinColumn(name="uid"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid", nullable = false)
    private Long userId;

    // fields from users
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "pin")
    private char[] pin;

    @Column(name = "salt")
    private char[] salt;

    @ManyToOne
    @JoinColumn(name = "_load")
    private CLoad load;

    @ManyToOne
    @JoinColumn(name = "_rank")
    private URank rank;

    @ManyToOne
    @JoinColumn(name = "_status")
    private UStatus status;

    @ManyToOne
    @JoinColumn(name = "_dept", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "_user_role", nullable = false)
    private UserRole userRole;

    // feilds from research_scholarly

    @Column(name = "jour_pubs")
    private int journals;

    @Column(name = "conf_pubs")
    private int conferences;

    @Column(name = "books")
    private int books;

    @Column(name = "chapters")
    private int chapters;

    @Column(name = "grants")
    private Long grants;
	
	@Column(name = "awards")
    private Long awards;

    @Column(name = "res_exp_total")
    private Long researchExperienceTotal;

    @Column(name = "res_exp_students")
    private Long researchExperienceStudents;

    @Column(name = "phd_advised")
    private int phdAdvised;

    @Column(name = "phd_completed")
    private int phdCompleted;

    @Column(name = "ms_completed")
    private int msCompleted;

    @Column(name = "patent_innovation")
    private int patentInnovation;

    @Column(name = "ug_mentored")
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
