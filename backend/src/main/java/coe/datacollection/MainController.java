package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
  //@Autowired // This means to get the bean called userRepository
  //private UserRepository userRepository;
  
  @Autowired //  Automatically gets userService
  private UserService userService;

  @Autowired // This means to get the bean called departmentRepository
  private DepartmentRepository departmentRepository;
  
  @Autowired
  private GenericRepository genericRepository;

  // This returns a JSON or XML with the users
  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
	System.out.println("Retrieved all users");
	return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  // neccesary?
  @GetMapping("/departments")
  public ResponseEntity<List<Department>> getAllDepartments() {
    return new ResponseEntity<>(departmentRepository.findAll(), HttpStatus.OK);
  }
  
  @GetMapping("/departmentNames")
  public ResponseEntity<List<String>> getAllDepartmentNames() {
    return new ResponseEntity<>(genericRepository.findStringVals("Department", "deptName"), HttpStatus.OK);
  }
  
  @GetMapping("/department/{name}")
  public ResponseEntity<List<Department>> getDepartmentofName(@PathVariable String name) {
	return new ResponseEntity<>(genericRepository.findByString("Department", "deptName", name), HttpStatus.OK);
  }

  // This returns a JSON or XML with the users of a single department
  @GetMapping("/department/{id}/users")
  public ResponseEntity<List<UserDTO>> getAllDepartmentUsers(@PathVariable int id) {
	System.out.println("Retrieved all users from department : " + id);
	return new ResponseEntity<>(userService.findUsersByDepartmentId(id), HttpStatus.OK);
  }

  // This returns a JSON for a single users
  @GetMapping("/user/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
    System.out.println("Retrieved user : " + id);
	return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
  }
}
