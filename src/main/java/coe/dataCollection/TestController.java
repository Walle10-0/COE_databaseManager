package coe.dateCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import coe.dataCollection.UserRepository;

@Controller // This means that this class is a Controller
public class TestController {
  @Autowired // This means to get the bean called userRepository
  private UserRepository userRepository;

  //@GetMapping("/all")
  @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
  public ResponseEntity<List<User>> getAllUsers() {
    // This returns a JSON or XML with the users
	System.out.println("Hit me!");
    return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
  }
}