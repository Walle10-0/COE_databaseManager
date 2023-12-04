package coe.datacollection.EntityDependencies;

import coe.datacollection.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "classes")
public class UClasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "uid")
	@JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "_semester")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "_catalog")
    private Catalog catalog;

    @Column(name = "students")
    private int students;

    // Constructors
    public UClasses() {
    }

    public UClasses(User user, Semester semester, Catalog catalog, int students) {
        this.user = user;
        this.semester = semester;
        this.catalog = catalog;
        this.students = students;
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

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }
}