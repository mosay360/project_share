package com.moccons.service.impl;


import com.moccons.domain.User;
import com.moccons.domain.security.PasswordResetToken;
import com.moccons.domain.security.UserRole;
import com.moccons.repository.PasswordResetTokenRepository;
import com.moccons.repository.RoleRepository;
import com.moccons.repository.UserRepository;
import com.moccons.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
   private  PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final  String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordTokenForUser(final  User user, final  String token) {
         final PasswordResetToken myToken= new PasswordResetToken(token, user);
         passwordResetTokenRepository.save(myToken);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles){
       User localUser= userRepository.findByUsername(user.getUsername());

       if (localUser!=null){
           LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
       }

       else{
           for (UserRole ur:userRoles){
               roleRepository.save(ur.getRole());
           }

           user.getUserRoles().addAll(userRoles);
           localUser=userRepository.save(user);
       }
        return localUser;
    }
}
