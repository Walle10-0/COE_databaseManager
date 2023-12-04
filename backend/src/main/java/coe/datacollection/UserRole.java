package coe.datacollection;

import jakarta.persistence.*;

@Entity
@Table(name = "_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "_user_role")
    private String roleName;

    //@OneToMany(mappedBy = "userRole")
    //private Set<User> users;

    public UserRole() {
        // default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
