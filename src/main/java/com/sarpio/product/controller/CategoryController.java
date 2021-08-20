package com.sarpio.product.controller;

import com.sarpio.exception.ApiRequestException;
import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.service.CategoryService;
import com.sun.net.httpserver.Headers;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity saveNewCategory(@Valid @RequestBody CategoryDto dto) {
        CategoryDto categoryDto = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        dto.setId(null);
        try {
            categoryDto = categoryService.saveNewCategory(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return categoryService.saveNewCategory(dto);
        return new ResponseEntity(categoryDto, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") @Min(1) Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public CategoryDto editCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDto dto) {
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
