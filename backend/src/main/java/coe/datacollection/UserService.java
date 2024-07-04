package coe.datacollection;
import coe.datacollection.EntityDependencies.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GenericRepository genericRepository;

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
		return convertToDTO(userRepository.findAll());
	}

	public UserDTO getUser(Long userId) {
		User currentUser = userRepository.findById(userId).orElse(null);
		return currentUser != null ? convertToDTO(currentUser) : null;
	}

	// update an existing user
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User cUser = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Customize exception as needed
		User nUser = convertFromDTO(userDTO);
		System.out.println("id " + id);
		System.out.println("uid " + cUser.getUserId());
		cUser.setFirstName(nUser.getFirstName() == null ? cUser.getFirstName() : nUser.getFirstName());
		cUser.setLastName(nUser.getLastName() == null ? cUser.getLastName() : nUser.getLastName());
		cUser.setDepartment(nUser.getDepartment() == null ? cUser.getDepartment() : nUser.getDepartment());
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
		
		cUser.setClasses(nUser.getClasses() == null ? cUser.getClasses() : nUser.getClasses());
		cUser.setServiceActivity(nUser.getServiceActivity() == null ? cUser.getServiceActivity() : nUser.getServiceActivity());

		cUser = userRepository.save(cUser);
		return convertToDTO(cUser);
	}

	// delete existing user
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	// convert User to UserDTO
	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getUserId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setDepartment(user.getDepartment().getDepartment());
		userDTO.setRoleName(user.getUserRole().getRoleName());
		
		userDTO.setLoad(user.getLoad() == null ? null : user.getLoad().getLoad());
		userDTO.setRank(user.getRank() == null ? null : user.getRank().getRank());
		userDTO.setStatus(user.getStatus() == null ? null : user.getStatus().getStatus());
		
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
	
	// function for repeated code
	private Semester findSemester(Semester currentSemester, List<Semester> mySemesters)
	{
		Semester theSemester = null;
		if (currentSemester != null && currentSemester.getSemesterName() != null && currentSemester.getYear() >= 1970)
		{
			for(Semester s : mySemesters)
			{
				if(s.getFullName().equalsIgnoreCase(currentSemester.getFullName()))
				{
					theSemester = s;
				}
			}
			if(theSemester == null)
			{
				theSemester = genericRepository.findSemester(currentSemester.getSemesterName(), currentSemester.getYear());
				mySemesters.add(theSemester);
			}
		}
		return theSemester;
	}

	// convert UserDTO to User
	public User convertFromDTO(UserDTO dto) {
		User user = new User();
		user.setUserId(dto.getId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());

		user.setDepartment(genericRepository.findByString("Department", "deptName", dto.getDepartment()));
		user.setUserRole(genericRepository.findByString("UserRole", "roleName", dto.getRoleName()));
		user.setLoad(genericRepository.findByString("CLoad", "load", dto.getLoad()));
		user.setRank(genericRepository.findByString("URank", "rank", dto.getRank()));
		user.setStatus(genericRepository.findByString("UStatus", "status", dto.getStatus()));
		
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
		
		List<Semester> mySemesters = new ArrayList<Semester>();
		Semester theSemester;
			
		List<UClasses> finalClasses = new ArrayList<UClasses>(dto.getClasses());
		for(UClasses i : dto.getClasses())
		{
			i.setUser(user);
			
			theSemester = findSemester(i.getSemester(), mySemesters);
			if(theSemester != null)
			{
				i.setSemester(theSemester);
			}
			else
			{
				finalClasses.remove(i);
			}
		}
		user.setClasses(finalClasses);
			
		List<UServices> finalServices = new ArrayList<UServices>(dto.getServiceActivity());
		for(UServices i : dto.getServiceActivity())
		{
			i.setUser(user);
			
			if (i.getLevel() != null)
			{
				i.setLevel(genericRepository.findByString("SLevel", "level", i.getLevel().getLevel()));
			}
			else
			{
				finalServices.remove(i);
			}
			
			if (i.getDescription() == null)
			{
				i.setDescription(" ");
			}
			
			theSemester = findSemester(i.getSemester(), mySemesters);
			if(theSemester != null)
			{
				i.setSemester(theSemester);
			}
			else
			{
				finalServices.remove(i);
			}
		}
		user.setServiceActivity(finalServices);

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
	
	public List<UserDTO> findUsersByDepartmentId(int id) {
		return convertToDTO(userRepository.findUsersByDepartmentId(id));
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
			case "semesterName":
				//result = genericRepository.findStringVals("Semester", "semesterName"); //findStringVals("Semester", "name");
				result = Arrays.asList("Fall", "Spring", "Summer", "Academic Year");
				break;
			case "level":
				result = genericRepository.findStringVals("SLevel", "level");
				break;
			case "repeatType":
				result = Arrays.asList("New Prep", "New Dev", "Repeat");
				break;
			default:
				result = new ArrayList<String>();
				break;
		}
		return result;
	}
}
