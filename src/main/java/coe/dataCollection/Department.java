package coe.datacollection;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;

    @Column(name = "name", nullable = false)
    private String deptName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> deptUsers = new ArrayList<>();

    // Standard getters and setters
    public int getDeptId() {
        return deptId;
    }

    public void setId(int id) {
        this.deptId = id;
    }

    public String getDepartment() {
        return deptName;
    }

    public void setDepartment(String dept) {
        this.deptName = dept;
    }
}
