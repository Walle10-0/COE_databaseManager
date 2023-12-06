package coe.datacollection;

import coe.datacollection.EntityDependencies.URank;

public class RegistrationDTO {
    private String username;

    public String getUsername() {
        return username;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }   

    private URank rank;

    public URank getRank() {
        return rank;
    }

    private String department;

    public String getDepartment() {
        return department;
    }

    private String role;

    public String getRole() {
        return role;
    }
 
}
