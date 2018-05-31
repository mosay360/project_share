package com.moccons.service;


import com.moccons.domain.User;
import com.moccons.domain.security.PasswordResetToken;
import com.moccons.domain.security.UserRole;
import java.util.Set;

public interface UserService {
 PasswordResetToken getPasswordResetToken(final String token);

 void createPasswordTokenForUser(final User user, final String token );
User findByUsername (String username);
User findByEmail (String email);
User createUser(User user, Set<UserRole> userRoles) throws Exception;
User save(User user);

}
