package com.sarpio.security.services;

import com.sarpio.exception.RecordNotFoundException;
import com.sarpio.security.controllers.dto.RoleDto;
import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.repository.RoleRepository;
import com.sarpio.security.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleEntity addNewRole(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRole(dto.getRole());
        entity.setRoleId(null);
        return roleRepository.save(entity);
    }

    public ResponseEntity deleteRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            return new ResponseEntity("Cannot find id: " + id, HttpStatus.NOT_FOUND);
        }
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List<RoleDto> showAll() {
        return roleRepository.findAll().stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }
}
