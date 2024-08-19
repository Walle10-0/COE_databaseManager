package coe.datacollection;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider { 
      
    @Autowired
    private UserService userService; 
	
	@Autowired
    private UserRepository userRep; 
  
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException { 
        String username = authentication.getName(); 
        String password = authentication.getCredentials().toString(); 
		
		System.out.println("this is run!");
  
        User user = userRep.findById(Long.parseLong(username)).orElse(null);

		String passwordCheck = user.getPin();

		System.out.println("user : " + user.getFirstName());
		System.out.println("attempt : " + passwordCheck);
		System.out.println("actual : " + password);
  
        if (user == null || !password.equals(passwordCheck)) { 
			System.out.println("password failed");
            throw new BadCredentialsException("Invalid username or password"); 
        } 
  
        List<GrantedAuthority> authorities = new ArrayList<>(); 
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
        return new UsernamePasswordAuthenticationToken(username, password, authorities); 
    } 
  
    @Override
    public boolean supports(Class<?> authentication) { 
        return authentication.equals(UsernamePasswordAuthenticationToken.class); 
    } 
}