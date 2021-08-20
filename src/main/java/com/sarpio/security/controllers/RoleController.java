package com.sarpio.security.controllers;

import com.sarpio.security.controllers.dto.AddRoleDto;
import com.sarpio.security.controllers.dto.RoleDto;
import com.sarpio.security.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/")
    public List<RoleDto> allRole() {
        return roleService.showAll();
    }

    @PostMapping("/")
    public RoleDto addNewRole(@RequestBody RoleDto dto) {
        roleService.addNewRole(dto);
        return dto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") Long id) {
        return roleService.deleteRoleById(id);
    }
}
