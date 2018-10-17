package com.service;

import com.model.Role;
import com.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
@Transactional
public class RoleService implements RoleRepository {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Set<Role> getRoles(String roleParam) {
        Set<Role> roleSet = roleRepository.getRoles(null);

        if (!roleParam.equals("ADMIN"))
            roleSet.removeIf(role -> role.getRole().equals("ADMIN"));

        return roleSet;
    }


}
