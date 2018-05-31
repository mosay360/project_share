package com.adminpanel.repository.repository;

import com.adminpanel.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User , Long>{
User findByUsername(String username);
User findByEmail(String email);

}
