package com.sarpio.security.controllers;

import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.services.UserLoggedService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class ResourceController {


    @GetMapping({"/"})
    public String all() {
        Long user = UserLoggedService.getUserName();
        Set<RoleEntity> roles = UserLoggedService.getRole();
        boolean length = UserLoggedService.isAdmin();

        return "Hello user with Id: " + user + "'\n'" + "User roles are : " + roles.toString() + " size: " + roles.isEmpty() + " isAdmin: " + length;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping({"/user"})
    public String helloUser() {
        return "Hello User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/admin"})
    public String helloAdmin() {
        return "Hello Admin";
    }
}
