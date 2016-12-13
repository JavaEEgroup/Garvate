package com.gc.repository.repository;


import com.gc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Role getByDescription(String description);
}
