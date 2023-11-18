package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    // // retrieve all users
    // public List<UserDTO> getAllUsers() {
    // return UserRepository.findAll().stream()
    // .map(this::convertToDTO)
    // .collect(Collectors.toList());
    // }

    public UserDTO getCurrentUser(Long currentUserId) {
        User currentUser = UserRepository.findById(currentUserId).orElse(null);
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
        userDTO.setRole(user.getRole());
        return userDTO;
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
        UserDTO currentUserDTO = getCurrentUser(currentUserId);
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
}
