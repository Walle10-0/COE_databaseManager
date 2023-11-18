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
public class TestController {
  @Autowired // This means to get the bean called userRepository
  private UserRepository userRepository;

  @Autowired // This means to get the bean called userRepository
  private DepartmentRepository departmentRepository;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/departments")
  public ResponseEntity<List<Department>> getAllDepartments() {
    return new ResponseEntity<>(departmentRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/department/{id}/users")
  public @ResponseBody List<User> getAllDepartmentUsers(@PathVariable int id) {
    // This returns a JSON or XML with the users
    // System.out.println("show users");
    return departmentRepository.findUsersByDepartmentId(id);
  }

  @GetMapping("/user/{id}")
  public @ResponseBody List<User> getUser(@PathVariable Long id) {
    // This returns a JSON or XML with the users
    // System.out.println("user");
    return userRepository.findByUID(id);
  }
}
