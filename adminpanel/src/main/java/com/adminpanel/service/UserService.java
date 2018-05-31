package com.adminpanel.service;


import com.adminpanel.domain.User;
import com.adminpanel.domain.security.PasswordResetToken;

import com.adminpanel.domain.security.UserRole;

import java.util.Set;

public interface UserService {
 PasswordResetToken getPasswordResetToken(final String token);

 void createPasswordTokenForUser(final User user, final String token);

  User findByUsername(String username);

  User findByEmail(String email);

  User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);

}
