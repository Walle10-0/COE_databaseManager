package coe.datacollection;

import coe.datacollection.EntityDependencies.Teaching;
import coe.datacollection.EntityDependencies.UClasses;
import coe.datacollection.EntityDependencies.UServices;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
	// general info
    private Long id;
    private String lastName;
    private String firstName;
	private String department;
	private String roleName;
	
	// feilds from general_info
    private String load;
    private String rank;
    private String status;
	
	// feilds from research_scholarly
    private int journals;
    private int conferences;
    private int books;
    private int chapters;
    private Long grants;
	private Long awards;
    private Long researchExperienceTotal;
    private Long researchExperienceStudents;
    private int phdAdvised;
    private int phdCompleted;
    private int msCompleted;
    private int patentInnovation;
    private int ugMentored;
	
	// for testing
	private List<Teaching> teaching;
    private List<UClasses> classes;
    private List<UServices> serviceActivity;
}
