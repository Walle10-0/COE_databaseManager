package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MainController {
  //@Autowired // This means to get the bean called userRepository
  //private UserRepository userRepository;

  @Autowired //  Automatically gets userService
  private UserService userService;

  public MainController(UserService userService) {
      this.userService = userService;
  }

  @Autowired // This means to get the bean called departmentRepository
  private DepartmentRepository departmentRepository;

  private static final Logger log = LoggerFactory.getLogger(MainController.class);

  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
      String jwt = userService.authenticateAndGetJwt(authenticationRequest.getUsername(), authenticationRequest.getPassword());
      if (jwt != null) {
          return ResponseEntity.ok(new AuthenticationResponse(jwt));
      } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
      }
  }

  @Transactional (rollbackFor = Exception.class)
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Validated @RequestBody RegistrationDTO registrationDTO) {
    log.info("Received registration request: {}", registrationDTO);
    try{
      boolean istrue = userService.newUser(registrationDTO);
      if (istrue){
          return new ResponseEntity<>(HttpStatus.CREATED);
      } else {
          return new ResponseEntity<>(istrue, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      log.error("Transaction failed due to exception:", e);
      return new ResponseEntity<>("Registration failed due to an error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  } 

  // Handle login request and return user details
  // @PostMapping("/login")
  // public ResponseEntity<?> startLogin(@RequestBody LoginDTO loginDTO) {
  //     log.debug("Start MainController login request: ", loginDTO.getUsername(), loginDTO.getPassword());
  //     try {
  //         UserDTO user = userService.loginUser(loginDTO);
  //         if (user != null) {
  //             log.debug("User authenticated successfully.");
  //             return new ResponseEntity<>(user, HttpStatus.OK);
  //         } else {
  //             log.debug("Invalid login credentials provided.");
  //             return new ResponseEntity<>("Invalid login credentials.", HttpStatus.UNAUTHORIZED);
  //         }
  //     } catch (Exception e) {
  //         log.error("Login failed due to an internal error:", e);
  //         return new ResponseEntity<>("Login failed due to an internal error.", HttpStatus.INTERNAL_SERVER_ERROR);
  //     }
  // }  

  // This returns a JSON or XML with the users
  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
	System.out.println("Retrieved all users");
	return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
  }

  // neccesary?
  @GetMapping("/departments")
  public ResponseEntity<List<Department>> getAllDepartments() {
    return new ResponseEntity<>(departmentRepository.findAll(), HttpStatus.OK);
  }
  
  @GetMapping("/allValues/{name}")
  public ResponseEntity<List<String>> getAllValues(@PathVariable String name) {
    return new ResponseEntity<>(userService.getAllValues(name), HttpStatus.OK);
  }
  
  @GetMapping("/test/user/{id}")
  public ResponseEntity<User> testgetUser(@PathVariable Long id) {
    System.out.println("Retrieved user : " + id);
	return new ResponseEntity<>(userService.fromDTO(userService.getSingUser(id)), HttpStatus.OK);
  }

  // This returns a JSON or XML with the users of a single department
  @GetMapping("/department/{id}/users")
  public ResponseEntity<List<UserDTO>> getAllDepartmentUsers(@PathVariable int id) {
	System.out.println("Retrieved all users from department : " + id);
	return new ResponseEntity<>(userService.findUsersByDeptId(id), HttpStatus.OK);
  }

  // This returns a JSON for a single users
  /* @GetMapping("/user/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
    System.out.println("Retrieved user : " + id);
	return new ResponseEntity<>(userService.getSingUser(id), HttpStatus.OK);
  } */
  
  @PutMapping("/user/{id}")
  public ResponseEntity<UserDTO> createClient(@PathVariable Long id, @RequestBody UserDTO dto) {
	System.out.println("received");
	return new ResponseEntity<>(userService.updateUser(id, dto), HttpStatus.OK);
  }
}