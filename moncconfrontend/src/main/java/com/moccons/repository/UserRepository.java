package com.moccons.repository;
import   com.moccons.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User , Long>{
User findByUsername(String username);
User findByEmail(String email);

}
