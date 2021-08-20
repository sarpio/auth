package com.sarpio.product.service;

import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.model.CategoryEntity;
import com.sarpio.product.repository.CategoryRepository;
import com.sarpio.product.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity saveNewCategory(CategoryDto dto) {
        CategoryEntity entity = EntityDtoMapper.map(dto);
        categoryRepository.save(entity);
        return new ResponseEntity<CategoryDto>(EntityDtoMapper.map(entity), HttpStatus.OK);
    }

    public ResponseEntity deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    public ResponseEntity editCategory(Long id, CategoryDto dto) {
        if (!categoryRepository.existsById(id)) {
            return new ResponseEntity<>("Id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        dto.setId(id);
        CategoryEntity save = EntityDtoMapper.map(dto);
        categoryRepository.save(save);
        return new ResponseEntity(EntityDtoMapper.map(save), HttpStatus.OK);
    }

    public List<CategoryDto> listAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll()
                .stream().map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        return categories;
    }

    public CategoryDto getCategoryById(Long id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow();
        return EntityDtoMapper.map(entity);
    }
}
