package com.sarpio.security.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class AddRoleDto {
    private String role;
}
