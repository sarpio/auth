package com.sarpio.security.services;

import com.sarpio.security.model.UsersEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserLoggedService {
    public Object getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal != "anonymousUser") {
            UsersEntity user = (UsersEntity) authentication.getPrincipal();
            return user.getId();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authentication.getName();
//        return userId;
    }
}
