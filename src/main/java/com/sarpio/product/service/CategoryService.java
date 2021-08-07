package com.sarpio.product.service;

import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.model.CategoryEntity;
import com.sarpio.product.repository.CategoryRepository;
import com.sarpio.product.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto saveNewCategory(CategoryDto dto) {
        CategoryEntity entity = EntityDtoMapper.map(dto);
        categoryRepository.save(entity);
        return EntityDtoMapper.map(entity);
    }

    public ResponseEntity deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No category with Id: " + id));
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public CategoryDto editCategory(Long id, CategoryDto dto) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no category with Id: " + id));
        dto.setId(id);
        CategoryEntity save = EntityDtoMapper.map(dto);
        categoryRepository.save(save);
        return EntityDtoMapper.map(save);
    }

    public List<CategoryDto> listAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll()
                .stream().map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        return categories;
    }

    public CategoryDto getCategoryById(Long id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no category with Id: " + id));
        return EntityDtoMapper.map(entity);
    }
}
