package com.repository;

import com.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository{

    User getUserById(long id);
    List<User> getAllUsers();
    void deleteUserById(long id);
    void addUser(User user);
    void submitUser(User user);

}
