package com.sarpio.security.controllers;

import com.sarpio.security.controllers.dto.UserDto;
import com.sarpio.security.services.UserService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Transactional(value = Transactional.TxType.REQUIRED)
    @PostMapping("/")
    public UserDto addNewUser(@ApiParam(name = "Add new user") @RequestBody @Valid UserDto dto) {
        return userService.saveUser(0L, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/")
    public List<UserDto> showUsers() {
        return userService.showAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto editUser(@Valid @RequestBody UserDto dto, @PathVariable("id") Long id) {
        return userService.saveUser(id, dto);
    }
}
