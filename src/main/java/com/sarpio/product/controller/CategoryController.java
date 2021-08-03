package com.sarpio.product.controller;

import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public CategoryDto saveNewCategory(@RequestBody CategoryDto dto) {
        dto.setId(null);
        return categoryService.saveNewCategory(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public CategoryDto editCategory(@PathVariable("id") Long id, @RequestBody CategoryDto dto) {
        return categoryService.editCategory(id, dto);
    }

    @GetMapping("/")
    public List<CategoryDto> showCategories() {
        return categoryService.listAllCategories();
    }
}
