package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    // create a new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // add logic for setting roles...

        // Implement validation and processing logic...

        user = UserRepository.save(user);
        return convertToDTO(user);
    }

    // retrieve all users
    public List<UserDTO> getAllUsers() {
		return convertToDTO(UserRepository.findAll());
    }

    public UserDTO getUser(Long userId) {
        User currentUser = UserRepository.findById(userId).orElse(null);
        return currentUser != null ? convertToDTO(currentUser) : null;
    }

    // update an existing user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Customize exception as
                                                                                           // needed
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // add anything for updating roles...

        user = UserRepository.save(user);
        return convertToDTO(user);
    }

    // delete existing user
    public void deleteUser(Long id) {
        UserRepository.deleteById(id);
    }

    // convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
		
        userDTO.setId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
		userDTO.setDepartmentName(user.getDepartment().getDepartment());
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
	
	// we should avoid this function
	// use lookups instead
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

        // set anything else ...

        return user;
    }

    // export current user to JSON
    public String exportCurrentUserToJSON(Long currentUserId) throws Exception {
        UserDTO currentUserDTO = getUser(currentUserId);
        if (currentUserDTO != null) {
            return ExportUtility.exportToJSON(List.of(currentUserDTO));
        }
        return null;
    }

    // // export all users to JSON
    // public String exportAllUsersToJSON() throws Exception {
    // List<UserDTO> userDTOs = getAllUsers();
    // return ExportUtility.exportToJSON(userDTOs);
    // }
	
	public List<UserDTO> findUsersByDepartmentId(int id) {
		return convertToDTO(UserRepository.findUsersByDepartmentId(id));
	}
}
