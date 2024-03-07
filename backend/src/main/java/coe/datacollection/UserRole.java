package coe.datacollection;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    //role 1 - user, role 2 - department head (currently entire database), role 3 - dean (entire database)
    private int roleId; 

    @Column(name = "_user_role")
    private String assignedRole;

    //@OneToMany(mappedBy = "userRole")
    //private Set<User> users;

    public UserRole() {
        // default constructor
    }

    public UserRole(String assignedRole)
    {
        if (assignedRole == "user") {
            roleId = 1;
        }
        else if (assignedRole == "department head") {
            roleId = 2;
        }
        else if (assignedRole == "dean"){
            roleId = 3;
        }
        this.assignedRole = assignedRole;
    }

    public void setAssignedRole(String roleName) {
        if (roleName == "user") {
            roleId = 1;
        }
        else if (roleName == "department head") {
            roleId = 2;
        }
        else if (roleName == "dean"){
            roleId = 3;
        }
        assignedRole = roleName;}
}
