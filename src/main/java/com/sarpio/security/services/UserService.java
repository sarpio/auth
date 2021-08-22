package com.sarpio.security.services;

import com.sarpio.exception.RecordNotFoundException;
import com.sarpio.security.controllers.dto.RoleDto;
import com.sarpio.security.controllers.dto.UserDto;
import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.model.UsersEntity;
import com.sarpio.security.repository.UsersRepository;
import com.sarpio.security.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserDto> showAllUsers() {
        List<UserDto> users = usersRepository.findAll().stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        return new ResponseEntity(users, HttpStatus.OK);
    }

    public ResponseEntity deleteUserById(Long id) {
        usersRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity saveUser(Long id, UserDto dto) {
        Set<RoleDto> setRoleDto = dto.getRole();
        Set<RoleEntity> roleEntities = setRoleDto.stream().map(EntityDtoMapper::map).collect(Collectors.toSet());
        UsersEntity entity = EntityDtoMapper.map(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setActive(1);
        entity.setRoleEntities(roleEntities);
        entity.setId(id);
        UsersEntity save = usersRepository.save(entity);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    public ResponseEntity getUserById(Long id) {
        UsersEntity entity = usersRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Provided id: " + id + " not exists"));
        return new ResponseEntity(EntityDtoMapper.map(entity), HttpStatus.OK);
    }
}
