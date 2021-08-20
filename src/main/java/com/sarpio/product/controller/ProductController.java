package com.sarpio.product.controller;

import com.sarpio.product.controller.dto.ProductDto;
import com.sarpio.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/")
    public ProductDto saveNewProduct(@Valid @RequestBody ProductDto dto) {
        dto.setId(null);
        return productService.saveNewProduct(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDto editProduct(@PathVariable("id") Long id, @RequestBody ProductDto dto) {
        return productService.editProduct(id, dto);
    }

    @GetMapping("/")
    public List<ProductDto> showProducts() {
        return productService.listAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

}
