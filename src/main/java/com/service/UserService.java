package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements UserRepository {

    @Autowired
    UserRepository userRepositoryImpl;

    @Override
    public void addUser(User user) {
        userRepositoryImpl.addUser(user);
    }

    @Override
    public void submitUser(User user) {
        userRepositoryImpl.submitUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryImpl.getAllUsers();
    }

    @Override
    public void deleteUserById(long id) {
        userRepositoryImpl.deleteUserById(id);
    }

    @Override
    public User getUserById(long id) {
        return userRepositoryImpl.getUserById(id);
    }
}
