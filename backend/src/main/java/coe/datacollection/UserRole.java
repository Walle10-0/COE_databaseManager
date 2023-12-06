package coe.datacollection;

import jakarta.persistence.*;

@Entity
@Table(name = "_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    //role 1 - user, role 2 - department head (currently entire database), role 3 - dean (entire database)
    private String[] roleList; 

    @Column(name = "_user_role")
    private String assignedRole;

    //@OneToMany(mappedBy = "userRole")
    //private Set<User> users;

    public UserRole() {
        // default constructor
        this.roleList = new String[3];
    }

    //index [0] - user, index [1] - department head (currently entire database), index [2] - dean (entire database)
    public String[] getRoleList() {
        return roleList;
    }

    public String getAssignedRole() {
        return assignedRole;
    }

    public void setAssignedRole(String roleName) {
        if (roleName == "user") {
            assignedRole = roleList[0];
        }
        else if (roleName == "department head") {
            assignedRole = roleList[1];
        }
        else if (roleName == "dean"){
            assignedRole = roleList[2];
        }
    }
/*
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }*/

    // add hashCode, equals, and toString methods if needed
}
