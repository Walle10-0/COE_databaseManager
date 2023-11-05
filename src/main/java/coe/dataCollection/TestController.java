package coe.dataCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import coe.dataCollection.User;
import coe.dataCollection.Department;
import coe.dataCollection.UserRepository;
import coe.dataCollection.DepartmentRepository;

@Controller // This means that this class is a Controller
@RequestMapping("/demo")
public class TestController {
  @Autowired // This means to get the bean called userRepository
  private UserRepository userRepository;
  
  @Autowired // This means to get the bean called userRepository
  private DepartmentRepository departmentRepository;

  @GetMapping("/users")
  public @ResponseBody List<User> getAllUsers() {
    // This returns a JSON or XML with the users
	System.out.println("execute order 66");
    return userRepository.findAll();
  }
  
  @GetMapping("/departments")
  public @ResponseBody List<Department> getAllDepartments() {
    // This returns a JSON or XML with the users
	System.out.println("execute order 67");
    return departmentRepository.findAll();
  }
}