package com.repository;

import com.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void deleteUserById(long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void submitUser(User user) {
        long i = user.getId();
        User newUser = getUserById(i);
        newUser.setName(user.getName());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(user.getRoles());
        entityManager.merge(newUser);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public User getUserById (long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("select u from User u").getResultList();
        return users;
    }

    @Override
    @Transactional
    public User getUserByLoginAndPassword(String login, String password) {

        Query query = entityManager.createQuery("select u.id from User u where u.login =:login and u.password =:password");
        query.setParameter("login",  login);
        query.setParameter("password", password);
        long id = (Long) query.getSingleResult();
        return this.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = entityManager.createQuery("select u.id from User u where u.login =:login");
        query.setParameter("login",  login);
        long id = (Long) query.getSingleResult();
        return this.getUserById(id);
    }

    @Override
    public User getPrincipal() throws IllegalStateException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            String userName = ((UserDetails) principal).getUsername();
            return this.getUserByLogin(userName);
        }
        else throw new IllegalStateException("GetPrincipal failed. No such user");
    }
}
