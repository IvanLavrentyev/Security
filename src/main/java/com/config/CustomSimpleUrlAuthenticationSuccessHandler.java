package com.config;

import com.model.User;
import com.repository.UserRepository;
import com.repository.UserRepositoryImpl;
import com.service.UserService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Configuration

public class CustomSimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

//    protected RedirectStrategy getRedirectStrategy() {
//        return redirectStrategy;
//    }
//
//    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
//        this.redirectStrategy = redirectStrategy;
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
                                                        throws IOException, ServletException {

        handle(httpServletRequest,httpServletResponse,authentication);

    }

    protected void handle (HttpServletRequest request,
                           HttpServletResponse response,
                           Authentication authentication) throws IOException, ServletException {

        String url = determineTargetURL(authentication);
        redirectStrategy.sendRedirect(request,response,url);
        clearAuth(request);

    }

    protected String determineTargetURL(Authentication authentication){
        boolean isAdmin = false;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();

        User user = userService.getUserByLogin(userName);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities){
            if (authority.getAuthority().contains("ADMIN")){
                isAdmin = true;
                break;
            }
        }
        if (isAdmin)
            return "/allUsers";
        else return "/userPage?id=" + user.getId();
    }

    protected void clearAuth(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null) session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

    }

}
