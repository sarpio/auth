package com.sarpio.security.services;

import com.sarpio.security.controllers.dto.RoleDto;
import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.repository.RoleRepository;
import com.sarpio.security.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleDto findRoleById(Long id) {
        RoleDto dto = new RoleDto();
        RoleEntity roleEntity = roleRepository
                .findById(id)
                .orElseThrow();
        return EntityDtoMapper.map(roleEntity);
    }

    public RoleEntity addNewRole(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRole(dto.getRole());
        return roleRepository.save(entity);
    }

    public ResponseEntity deleteRoleById(Long id){
        //TODO Add code with checking if role is being used and assign user role instead
        roleRepository.findById(id)
                .orElseThrow();
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List<RoleDto> showAll() {
        return roleRepository.findAll().stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }
}
