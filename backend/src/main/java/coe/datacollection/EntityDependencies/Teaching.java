package coe.datacollection.EntityDependencies;

import coe.datacollection.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "teaching")
public class Teaching {
    @Id
    @ManyToOne
    @JoinColumn(name = "uid")
	@JsonBackReference
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "_semester")
    private Semester semester;

    @Column(name = "new_preps")
    private int newPreps;

    @Column(name = "new_devs")
    private int newDevs;

    @Column(name = "overloads")
    private int overloads;

    // Constructors
    public Teaching() {
    }

    public Teaching(User user, Semester semester, Integer newPreps, Integer newDevs, Integer overloads) {
        this.user = user;
        this.semester = semester;
        this.newPreps = newPreps;
        this.newDevs = newDevs;
        this.overloads = overloads;
    }

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getNewPreps() {
        return newPreps;
    }

    public void setNewPreps(int newPreps) {
        this.newPreps = newPreps;
    }

    public int getNewDevs() {
        return newDevs;
    }

    public void setNewDevs(int newDevs) {
        this.newDevs = newDevs;
    }

    public int getOverloads() {
        return overloads;
    }

    public void setOverloads(int overloads) {
        this.overloads = overloads;
    }
}