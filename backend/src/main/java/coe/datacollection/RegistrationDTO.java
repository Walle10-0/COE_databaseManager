package coe.datacollection;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String username;
    private String pin;
    private String firstName;
    private String lastName;
    private String rank;
    private String department;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }  

    public String getRank() {
        return rank;
    }

    public String getDepartment() {
        return department;
    } 

    public String getRole() {
        return role;
    }
 
}
