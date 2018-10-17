package com.service;

import com.model.Role;
import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userService.getUserByLogin(login);

        if (user == null) throw new UsernameNotFoundException("Username is null in loadUserByName()");

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                                                                        user.getPassword(),
                                                        true,true,
                                            true,true,
                                                                    getAuthorities(user));
    }


    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> userAuthorities = new HashSet<>();

        for (Role role : user.getRoles()){
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return userAuthorities;
    }
}
