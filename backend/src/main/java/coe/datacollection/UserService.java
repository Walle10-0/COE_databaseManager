package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private BCryptPasswordEncoder passwordEncoder; // For hashing and verifying passwords
    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        // Set other properties like department and roleName as needed

        // Hash the password
        String hashedPassword = hashPassword(user.getPassword());

        user.setPin(hashedPassword);

        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public boolean verifyPassword(String departmentName, Long userID, String password) {
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

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // retrieve all users
    public List<UserDTO> getAllUsers(String deptName) {
        List<Department> departments = departmentRepository.findByNameContaining(deptName);
        List<UserDTO> allUsers = new ArrayList<>();
        for (Department department : departments) {
            List<User> usersInDepartment = userRepository.findUsersByDepartment(department);
            allUsers.addAll(convertToDTO(usersInDepartment));
        }

        return allUsers;
    }

    // convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
		userDTO.setDepartment(user.getDepartment().getDepartmentName());
        userDTO.setRoleName(user.getUserRole().getRoleName());
		userDTO.setLoad(user.getLoad().getLoad());
		userDTO.setRank(user.getRank().getRank());
		userDTO.setStatus(user.getStatus().getStatus());
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

		    //user.setDepartment(genericRepository.findByString("Department", "deptName", dto.getDepartment()));
        //user.setUserRole(genericRepository.findByString("UserRole", "roleName", dto.getRoleName()));
		    /*
		    userDTO.setLoad(user.getLoad().getLoad());
		    userDTO.setRank(user.getRank().getRank());
		    userDTO.setStatus(user.getStatus().getStatus());
		
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
		    userDTO.setUgMentored(user.getUgMentored());*/
		
		    user.setTeaching(dto.getTeaching());
		    user.setClasses(dto.getClasses());
		    user.setServiceActivity(dto.getServiceActivity());

        // set anything else ...
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
	
	public List<UserDTO> findUsersByDepartmentId(int id) {
		return convertToDTO(userRepository.findUsersByDepartmentId(id));
	}

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return convertToDTO(user);
        }
        return null;
    }
}
