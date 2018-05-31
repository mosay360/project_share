package com.moccons;
import com.moccons.domain.User;
import com.moccons.domain.security.Role;
import com.moccons.domain.security.UserRole;
import com.moccons.service.UserService;
import com.moccons.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class  MoncconsApplication implements CommandLineRunner {

@Autowired
    private UserService userService;
	public static void main(String[] args) {
 		SpringApplication.run(MoncconsApplication.class, args);
	}

@Override
	public void  run(String... args) throws Exception{
	User user1 = new User();
	user1.setFirstname("Kwame");
	user1.setLastname("Nsiah");
	user1.setUsername("moses");
	user1.setPassword(SecurityUtility.passwordEncoder().encode("nsiah"));
	user1.setEmail("mosay360@gmail.com");
	Set<UserRole> userRoles= new HashSet<>();
	Role role1= new Role();
	role1.setRoleId(1);
	role1.setName("ROLE_USER");
	userRoles.add(new UserRole(user1, role1));
	userService.createUser(user1, userRoles);
    }

}
