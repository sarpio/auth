package com.sarpio.security.controllers;

import com.sarpio.security.controllers.dto.UserDto;
import com.sarpio.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @Transactional(value = Transactional.TxType.REQUIRED)
    @PostMapping
    public ResponseEntity addNewUser(@Valid @RequestBody UserDto dto) {
        return userService.saveUser(null, dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        if (userService.getUserById(id)==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted User with Id: " + id, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> showUsers() {
        return userService.showAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity editUser(@Valid @RequestBody UserDto dto, @PathVariable("id") Long id) {
        return userService.saveUser(id, dto);
    }
}
