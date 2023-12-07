package coe.datacollection;

import jakarta.persistence.*;

@Entity
@Table(name = "_dept", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
    private Integer deptId;

    @Column(name = "_dept", nullable = false)
    private String deptName;

    // Standard getters and setters
    public int getId() {
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
