package com.repository;

import com.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public Set<Role> getRoles(String roleParam) {
        Query query = entityManager.createQuery("select r from Role r");
        Set<Role> roles = new HashSet<>(query.getResultList());
        return roles;
    }
}
