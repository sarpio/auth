package com.sarpio.security.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class UserDto {


    private Long id;

    private String username;
    private String password;
    private String email;
    private Set<RoleDto> role;
}
