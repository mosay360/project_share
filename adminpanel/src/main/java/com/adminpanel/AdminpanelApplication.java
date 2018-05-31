package com.adminpanel;

import com.adminpanel.domain.User;
import com.adminpanel.domain.security.Role;
import com.adminpanel.domain.security.UserRole;
import com.adminpanel.service.UserService;
import com.adminpanel.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AdminpanelApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AdminpanelApplication.class, args);
	}
	public void run(String... args) throws Exception{
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("mosay360@gmaail.com");
		Set<UserRole> userRoles= new HashSet<>();
		Role role1 = new Role();
		 role1.setRoleId(0);
		 role1.setName("ROLE_ADMIN");
		 userRoles.add(new UserRole(user1, role1));

		 userService.createUser(user1, userRoles);
	}

}
