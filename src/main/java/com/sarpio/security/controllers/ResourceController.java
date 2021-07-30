package com.sarpio.security.controllers;

import com.sarpio.security.services.UserLoggedService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResourceController {


    public final UserLoggedService loggedIn;

    @GetMapping({"/"})
    public String all(){
        String user = loggedIn.getUserName().toString();
        return "Hello user: " + user;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping({"/user"})
    public String helloUser(){
        return "Hello User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/admin"})
    public String helloAdmin(){
        return "Hello Admin";
    }
}
