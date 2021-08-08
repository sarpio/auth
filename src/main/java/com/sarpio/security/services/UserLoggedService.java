package com.sarpio.security.services;

import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserLoggedService {

    public static Set<RoleEntity> roleSet;
    public static Integer userId;

    public static Integer getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal != "anonymousUser") {
            UsersEntity user = (UsersEntity) authentication.getPrincipal();
            roleSet = user.getRoleEntities();
            userId = user.getId().intValue();
            return user.getId().intValue();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        return authentication.getName();
        return 0;
    }


}
