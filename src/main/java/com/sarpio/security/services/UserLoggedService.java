package com.sarpio.security.services;

import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.model.UsersEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserLoggedService {

    public static Set<RoleEntity> ROLES;
    public static Long USER_ID;


    public static Long getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal != "anonymousUser") {
            UsersEntity user = (UsersEntity) authentication.getPrincipal();
            ROLES = user.getRoleEntities();
            USER_ID = user.getId();
            return user.getId().longValue();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        return authentication.getName();
        return 0L;
    }

    public static Set<RoleEntity> getRole() {
        if (ROLES == null) {
            Set<RoleEntity> setRoles = new HashSet<>();
            return setRoles;
        }
        return ROLES;
    }


    public static Long getUserId() {
        return USER_ID;
    }

    public static boolean isAdmin() {
        if (ROLES != null) {
            List<RoleEntity> result = new ArrayList<>(ROLES);
            result = result.stream().filter(r -> r.getRole().equals("ADMIN")).collect(Collectors.toList());
            if (result.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
