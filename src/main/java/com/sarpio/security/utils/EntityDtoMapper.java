package com.sarpio.security.utils;

import com.sarpio.security.controllers.dto.RoleDto;
import com.sarpio.security.controllers.dto.UserDto;
import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.model.UsersEntity;
import org.springframework.beans.BeanUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class EntityDtoMapper {

    public static UserDto map(UsersEntity entity) {
        UserDto dto = new UserDto();
        Set<RoleEntity> roleEntitiesSet = entity.getRoleEntities();
        Set<RoleDto> roleDtoSet = roleEntitiesSet.stream().map(EntityDtoMapper::map).collect(Collectors.toSet());
        dto.setRole(roleDtoSet);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static UsersEntity map(UserDto dto) {
        UsersEntity entity = new UsersEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static RoleDto map(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setRole_id(entity.getRoleId());
//        dto.setRole(entity.getRole());
        return dto;
    }

    public static RoleEntity map(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
//        BeanUtils.copyProperties(dto, entity);
        entity.setRoleId(dto.getRole_id());
        entity.setRole(dto.getRole());
        return entity;
    }
}
