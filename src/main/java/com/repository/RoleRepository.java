package com.repository;

import com.model.Role;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface RoleRepository {
    Set<Role> getRoles(String roleParam);

}
