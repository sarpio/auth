package com.sarpio.security.services;

import com.sarpio.security.controllers.dto.RoleDto;
import com.sarpio.security.controllers.dto.UserDto;
import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.model.UsersEntity;
import com.sarpio.security.repository.UsersRepository;
import com.sarpio.security.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> showAllUsers() {
        return usersRepository.findAll().stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }

    public ResponseEntity deleteUserById(Long id) {
        usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User with Id: " + id + " not exists"));
        usersRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public UserDto saveUser(Long id, UserDto dto) {
        Set<RoleDto> setRoleDto = dto.getRole();
        Set<RoleEntity> roleEntities = setRoleDto.stream().map(EntityDtoMapper::map).collect(Collectors.toSet());
        UsersEntity entity = EntityDtoMapper.map(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setActive(1);
        entity.setRoleEntities(roleEntities);
        if (id != 0) {
            entity.setId(id);
        }
        usersRepository.save(entity);
        return EntityDtoMapper.map(entity);
    }

    public UserDto getUserById(Long id) {
        UsersEntity entity = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("No user found with Id: " + id));
        return EntityDtoMapper.map(entity);
    }
}
