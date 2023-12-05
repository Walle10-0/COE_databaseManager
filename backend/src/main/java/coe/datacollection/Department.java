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

    //@OneToMany(mappedBy="department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<User> deptUsers;

    // Standard getters and setters
    public int getId() {
        return deptId;
    }

    public void setId(int id) {
        this.deptId = id;
    }

    public String getDepartmentName() {
        return deptName;
    }

    public void setDepartment(String dept) {
        this.deptName = dept;
    }
}
