package com.controller;

import com.config.AppConfig;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {

    private UserRepository userService;
    private RoleRepository roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserRepository userService, RoleRepository roleService, PasswordEncoder passwordEncoder) {
       this.userService = userService;
       this.roleService = roleService;
       this.passwordEncoder = passwordEncoder;
    }

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

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)  // todo look for @ModelAttribute
    public String addUser(@RequestParam String newUserName,
                          @RequestParam String newUserLogin,
                          @RequestParam String newUserPassword,
                          @RequestParam String roleParam,
                          ModelMap modelMap){

        if (!newUserName.isEmpty() && !newUserLogin.isEmpty() && !newUserPassword.isEmpty()){
            Set<Role> set = roleService.getRoles(roleParam);
            userService.addUser(new User(newUserName, newUserLogin, newUserPassword, set));
        }

        return getAllUsers(modelMap);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String editUser(@RequestParam long id,ModelMap modelMap){
        User user = userService.getUserById(id);
        String userOldPass = user.getPassword();
        user.setPassword("");
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("oldPassword" , userOldPass);
        return "editUser";
    }

    @RequestMapping(value = "/submitEditedUser", method = RequestMethod.POST)
    public String submitEditedUser(@RequestParam long hiddenID,
                                   @RequestParam String nameToEdit,
                                   @RequestParam String loginToEdit,
                                   @RequestParam String passwordToEdit,
                                   @RequestParam String oldPassword,
                                   @RequestParam String roleParam,
                                   ModelMap modelMap){

        if (passwordToEdit.isEmpty()) passwordToEdit = oldPassword;
        else passwordToEdit = passwordEncoder.encode(passwordToEdit);

        Set<Role> set = roleService.getRoles(roleParam);
        userService.submitUser(new User(hiddenID, nameToEdit, loginToEdit, passwordToEdit, set));
        return getAllUsers(modelMap);
    }

    @RequestMapping(value = "/viewUser", method = RequestMethod.GET)
    public String viewUser(@RequestParam long id, ModelMap modelMap){
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "userPage";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String getUserPage(@RequestParam long id, ModelMap modelMap){
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "userPage";
    }


    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(ModelMap modelMap){
        User user;

        try{
            user = userService.getPrincipal();
            modelMap.addAttribute("userName", user.getName());
        }catch (IllegalStateException e){
            modelMap.addAttribute("userName", e.getMessage());
        }
        return "accessDenied";
    }

    @RequestMapping (value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/login?logout";
    }

}
