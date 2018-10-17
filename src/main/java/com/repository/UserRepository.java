package com.repository;

import com.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository{

    User getUserById(long id);
    User getUserByLoginAndPassword(String login, String password); // todo delete if no longer needed
    User getUserByLogin(String login);
    User getPrincipal();

    List<User> getAllUsers();
    void deleteUserById(long id);
    void addUser(User user);
    void submitUser(User user);

}
