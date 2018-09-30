package com.repository;

import com.model.User;
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


}
