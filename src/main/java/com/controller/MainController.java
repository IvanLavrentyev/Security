package com.controller;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserRepository userService;

    @RequestMapping( value = "/allUsers", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap){
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "allUsers";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam long id, ModelMap modelMap){
        userService.deleteUserById(id);
        return getAllUsers(modelMap);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam String newUserName,
                          @RequestParam String newUserLogin,
                          @RequestParam String newUserPassword,
                          ModelMap modelMap){
        userService.addUser(new User(newUserName, newUserLogin, newUserPassword));
        return getAllUsers(modelMap);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String editUser(@RequestParam long id,ModelMap modelMap){
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping(value = "/submitEditedUser", method = RequestMethod.POST)
    public String submitEditedUser(@RequestParam long hiddenID,
                                   @RequestParam String nameToEdit,
                                   @RequestParam String loginToEdit,
                                   @RequestParam String passwordToEdit,
                                   ModelMap modelMap){
        userService.submitUser(new User(hiddenID, nameToEdit, loginToEdit, passwordToEdit));
        return getAllUsers(modelMap);
    }
}
