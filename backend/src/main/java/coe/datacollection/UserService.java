package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.Data;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private GenericRepository genericRepository;
    private BCryptPasswordEncoder passwordEncoder; // For hashing and verifying passwords
    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validateName(RegistrationDTO regDTO) {
        List <User> users = userRepository.findAll();
            for (User testUser : users) {
                if (testUser.getLastName().equals(regDTO.getLastName())) {
                    if (testUser.getFirstName().equals(regDTO.getFirstName())) {
                        return true;
                    }                   
                }
            }
            return false;
    }

    public boolean newUser(RegistrationDTO registrationDTO)
    {
        if (registrationDTO!= null) {
            if (!validateName(registrationDTO)) {
                User user = new User();           
                user.setFirstName(registrationDTO.getFirstName());
                user.setLastName(registrationDTO.getLastName());
                user.setUsername(registrationDTO.getUsername());
                String hashedPassword = hashPassword(registrationDTO.getPassword());
                user.setPassword(hashedPassword);
                user.setRank(registrationDTO.getRank());
                user.setDepartment(registrationDTO.getDepartment());
                user.setAssignedRole(registrationDTO.getRole());
                user = userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean verifyPassword(String departmentName, Long userID, String password) 
    {
        Department tempDept = null;
        List <Department> departments = departmentRepository.findByNameContaining(departmentName);
        if (departments == null) {
            return false;
        }
        else {
            for (Department dept : departments) {
                if (dept.getDepartmentName().equals(departmentName)) {
                    tempDept = dept;
                }
                List<User> userInDept = userRepository.findUsersByDepartment(tempDept);
                for (User user : userInDept) {
                    if (user.getUserId().equals(userID)) {
                        return passwordEncoder.matches(password, user.getPassword());
                    }
                }
            }
            return false;
        }
    }

    private String hashPassword(String password) 
    {
        return passwordEncoder.encode(password);
    }

    // retrieve all users
    public List<UserDTO> allUsers() 
    {
        String deptName = "EECS";
        List <Department> department = departmentRepository.findByNameContaining(deptName);
        if (department == null) {
            return null;
        }
        else {
            for (Department dept : department) {
                if (dept.getDepartmentName().equals(deptName)) {
                    List<User> usersInDepartment = userRepository.findUsersByDepartment(dept);
                    List<UserDTO> allUsers = new ArrayList<>();
                    for (User user : usersInDepartment) {
                        allUsers.add(convertToDTO(user));
                    }
                    return allUsers;
                }
            }
            return null;
        }
    }

    // convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
		userDTO.setDepartment(user.getDepartment().getDepartmentName());
        userDTO.setUserRole(user.getUserRole().getAssignedRole());
		userDTO.setLoad(user.getLoad());
		userDTO.setRank(user.getRank());
		userDTO.setStatus(user.getStatus());
		userDTO.setJournals(user.getJournals());
		userDTO.setConferences(user.getConferences());
		userDTO.setBooks(user.getBooks());
		userDTO.setChapters(user.getChapters());
		userDTO.setGrants(user.getGrants());
		userDTO.setResearchExperienceTotal(user.getResearchExperienceTotal());
		userDTO.setResearchExperienceStudents(user.getResearchExperienceStudents());
		userDTO.setPhdAdvised(user.getPhdAdvised());
		userDTO.setPhdCompleted(user.getPhdCompleted());
		userDTO.setMsCompleted(user.getMsCompleted());
		userDTO.setPatentInnovation(user.getPatentInnovation());
		userDTO.setUgMentored(user.getUgMentored());
		userDTO.setTeaching(user.getTeaching());
		userDTO.setClasses(user.getClasses());
		userDTO.setServiceActivity(user.getServiceActivity());
        return userDTO;
    }
	
	// bulk convert
	private List<UserDTO> toDTO(List<User> user) {
        List<UserDTO> DTOList = new ArrayList<UserDTO>();
        for (User current : user) {
			DTOList.add(convertToDTO(current));
		}
		
        return DTOList;
    }

    // convert UserDTO to User
    public User fromDTO(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDepartment(dto.getDepartment());
        user.setAssignedRole(dto.getUserRole());
        user.setLoad(dto.getLoad());
        user.setRank(dto.getRank());
        user.setStatus(dto.getStatus());
        user.setJournals(dto.getJournals());
        user.setConferences(dto.getConferences());
        user.setBooks(dto.getBooks());
        user.setChapters(dto.getChapters());
        user.setGrants(dto.getGrants());
        user.setResearchExperienceTotal(dto.getResearchExperienceTotal());
        user.setResearchExperienceStudents(dto.getResearchExperienceStudents());
        user.setPhdAdvised(dto.getPhdAdvised());
        user.setPhdCompleted(dto.getPhdCompleted());
        user.setMsCompleted(dto.getMsCompleted());
        user.setPatentInnovation(dto.getPatentInnovation());
        user.setUgMentored(dto.getUgMentored());
        user.setTeaching(dto.getTeaching());
        user.setClasses(dto.getClasses());
        user.setServiceActivity(dto.getServiceActivity());

        return user;
    }

    // // export current user to JSON
    // public String exportCurrentUserToJSON(Long currentUserId) throws Exception {
    //     UserDTO currentUserDTO = getUser(currentUserId);
    //     if (currentUserDTO != null) {
    //         return ExportUtility.exportToJSON(List.of(currentUserDTO));
    //     }
    //     return null;
    // }
	
	public List<UserDTO> findUsersByDeptId(int id) {
		return toDTO(userRepository.findUsersByDepartmentId(id));
	}

    public UserDTO getSingUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return convertToDTO(user);
        }
        return null;
    }

    // create a new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    // retrieve all users
    public List<UserDTO> getAllUsers() {
		return toDTO(userRepository.findAll());
    }

    public UserDTO getUser(Long userId) {
        User currentUser = userRepository.findById(userId).orElse(null);
        return currentUser != null ? convertToDTO(currentUser) : null;
    }

    // update an existing user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User cUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Customize exception as
                                                                                           // needed
		User nUser = fromDTO(userDTO);
		System.out.println("id " + id);
		System.out.println("uid " + cUser.getUserId());
        cUser.setFirstName(nUser.getFirstName() == null ? cUser.getFirstName() : nUser.getFirstName());
		cUser.setLastName(nUser.getLastName() == null ? cUser.getLastName() : nUser.getLastName());
		cUser.setDepartment(nUser.getDepartment() == null ? cUser.getDepartment().getDepartmentName() : nUser.getDepartment().getDepartmentName());
		//cUser.setRoleName(nUser.getUserRole() == null ? cUser.getUserRole() : nUser.getUserRole());
		
		cUser.setLoad(nUser.getLoad() == null ? cUser.getLoad() : nUser.getLoad());
		cUser.setRank(nUser.getRank() == null ? cUser.getRank() : nUser.getRank());
		cUser.setStatus(nUser.getStatus() == null ? cUser.getStatus() : nUser.getStatus());
		
		cUser.setJournals(nUser.getJournals());
		cUser.setConferences(nUser.getConferences());
		cUser.setBooks(nUser.getBooks());
		cUser.setChapters(nUser.getChapters());
		cUser.setGrants(nUser.getGrants());
		cUser.setResearchExperienceTotal(nUser.getResearchExperienceTotal());
		cUser.setResearchExperienceStudents(nUser.getResearchExperienceStudents());
		cUser.setPhdAdvised(nUser.getPhdAdvised());
		cUser.setPhdCompleted(nUser.getPhdCompleted());
		cUser.setMsCompleted(nUser.getMsCompleted());
		cUser.setPatentInnovation(nUser.getPatentInnovation());
		cUser.setUgMentored(nUser.getUgMentored());
		cUser.setAwards(nUser.getAwards());
		
		//cUser.setTeaching(nUser.getTeaching() == null ? cUser.getTeaching() : nUser.getTeaching());
		//cUser.setClasses(nUser.getClasses() == null ? cUser.getClasses() : nUser.getClasses());
		//cUser.setServiceActivity(nUser.getServiceActivity() == null ? cUser.getServiceActivity() : nUser.getServiceActivity());

        cUser = userRepository.save(cUser);
        return convertToDTO(cUser);
    }

    // delete existing user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // convert User to UserDTO
    private UserDTO convertoDTO(User user) {
        UserDTO userDTO = new UserDTO();
		
        userDTO.setId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
		userDTO.setDepartment(user.getDepartment().getDepartmentName());
        userDTO.setUserRole(user.getUserRole().getAssignedRole());

		userDTO.setLoad(user.getLoad());
		userDTO.setRank(user.getRank());
		userDTO.setStatus(user.getStatus());
		
		userDTO.setJournals(user.getJournals());
		userDTO.setConferences(user.getConferences());
		userDTO.setBooks(user.getBooks());
		userDTO.setChapters(user.getChapters());
		userDTO.setGrants(user.getGrants());
		userDTO.setResearchExperienceTotal(user.getResearchExperienceTotal());
		userDTO.setResearchExperienceStudents(user.getResearchExperienceStudents());
		userDTO.setPhdAdvised(user.getPhdAdvised());
		userDTO.setPhdCompleted(user.getPhdCompleted());
		userDTO.setMsCompleted(user.getMsCompleted());
		userDTO.setPatentInnovation(user.getPatentInnovation());
		userDTO.setUgMentored(user.getUgMentored());
		userDTO.setAwards(user.getAwards());
		
		userDTO.setTeaching(user.getTeaching());
		userDTO.setClasses(user.getClasses());
		userDTO.setServiceActivity(user.getServiceActivity());
		
        return userDTO;
    }
	
	// bulk convert
	private List<UserDTO> convertToDTO(List<User> user) {
        List<UserDTO> DTOList = new ArrayList<UserDTO>();
        for (User current : user) {
			DTOList.add(convertToDTO(current));
		}
		
        return DTOList;
    }

    // convert UserDTO to User
    public User convertFromDTO(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

		user.setDepartment(genericRepository.findByString("Department", "deptName", dto.getDepartment()));
        user.setAssignedRole(dto.getUserRole());
		//user.setLoad(genericRepository.findByString("CLoad", "load", dto.getLoad()));
		//user.setRank(genericRepository.findByString("URank", "rank", dto.getRank()));
		//user.setStatus(genericRepository.findByString("UStatus", "status", dto.getStatus()));
		
		user.setJournals(dto.getJournals());
		user.setConferences(dto.getConferences());
		user.setBooks(dto.getBooks());
		user.setChapters(dto.getChapters());
		user.setGrants(dto.getGrants());
		user.setResearchExperienceTotal(dto.getResearchExperienceTotal());
		user.setResearchExperienceStudents(dto.getResearchExperienceStudents());
		user.setPhdAdvised(dto.getPhdAdvised());
		user.setPhdCompleted(dto.getPhdCompleted());
		user.setMsCompleted(dto.getMsCompleted());
		user.setPatentInnovation(dto.getPatentInnovation());
		user.setUgMentored(dto.getUgMentored());
		user.setAwards(dto.getAwards());
		
		    user.setTeaching(dto.getTeaching());
		    user.setClasses(dto.getClasses());
		    user.setServiceActivity(dto.getServiceActivity());

        // set anything else ...
        return user;
    }

    // export current user to JSON
    public String exportCurrentUserToJSON(Long currentUserId) throws Exception {
        UserDTO currentUserDTO = getSingUser(currentUserId);
        if (currentUserDTO != null) {
            return ExportUtility.exportToJSON(List.of(currentUserDTO));
        }
        return null;
    }
	
	public List<UserDTO> findUsersByDepartmentId(int id) {
		return toDTO(userRepository.findUsersByDepartmentId(id));
	}
	
	// get All Values for dropdown
	public List<String> getAllValues(String name)
	{
		List<String> result;
		switch (name) {
			case "department":
				result = genericRepository.findStringVals("Department", "deptName");
				break;
			case "load":
				result = genericRepository.findStringVals("CLoad", "load");
				break;
			case "rank":
				result = genericRepository.findStringVals("URank", "rank");
				break;
			case "status":
				result = genericRepository.findStringVals("UStatus", "status");
				break;
			case "semester":
				result = genericRepository.findStringVals("Semester", "fullName");
				break;
			default:
				result = new ArrayList<String>();
				break;
		}
		return result;
	}
}
