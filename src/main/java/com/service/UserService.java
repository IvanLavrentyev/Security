package com.service;

import com.model.User;
import com.model.Role;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserService implements UserRepository {

    @Autowired
    private UserRepository userRepositoryImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userRepositoryImpl.getUserByLoginAndPassword(login,password);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepositoryImpl.getUserByLogin(login);
    }

    @Override
    public User getPrincipal() {
        return userRepositoryImpl.getPrincipal();
    }
}
