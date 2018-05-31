package com.adminpanel.repository.repository;

import com.adminpanel.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByName(String name);
}
