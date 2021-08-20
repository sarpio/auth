package com.sarpio.product.controller;

import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity saveNewCategory(@Valid @RequestBody CategoryDto dto) {
        categoryService.saveNewCategory(dto);
        return ResponseEntity.ok("New Category Saved!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@Valid @PathVariable("id") @Min(1) Long id) {
        if (categoryService.getCategoryById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Deleted category with Id: " + id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDto dto) {
        return categoryService.editCategory(id, dto);
    }

    @GetMapping("/")
    public List<CategoryDto> showCategories() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable(name = "id") Long id, HttpServletResponse response) {
        return categoryService.getCategoryById(id);
    }
}
