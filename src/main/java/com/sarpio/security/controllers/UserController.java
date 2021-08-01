package com.sarpio.security.controllers;

import com.sarpio.security.controllers.dto.UserDto;
import com.sarpio.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Transactional(value = Transactional.TxType.REQUIRED)
    @PostMapping("/add")
    public UserDto addNewUser(@RequestBody UserDto dto) {
        return userService.saveUser(0L, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/all")
    public List<UserDto> showUsers() {
        return userService.showAllUsers();
    }

    @PutMapping("/edit/{id}")
    public UserDto editUser(@RequestBody UserDto dto, @PathVariable("id") Long id) {
        return userService.saveUser(id, dto);
    }
}
