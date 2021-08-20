package com.sarpio.product.service;

import com.sarpio.product.controller.dto.ProductDto;
import com.sarpio.product.model.CategoryEntity;
import com.sarpio.product.model.ProductEntity;
import com.sarpio.product.repository.CategoryRepository;
import com.sarpio.product.repository.ProductRepository;
import com.sarpio.product.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto saveNewProduct(ProductDto dto) {
        return getProductDto(dto);
    }

    public ResponseEntity deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {

        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("product with Id: " + id + " deleted!");
    }

    public ProductDto editProduct(Long id, ProductDto dto) {
        productRepository.findById(id)
                .orElseThrow();
        dto.setId(id);
        return getProductDto(dto);
    }

    private ProductDto getProductDto(ProductDto dto) {
        CategoryEntity category = categoryRepository
                .findById(dto.getCategory().getId())
                .orElseThrow();
        ProductEntity save = EntityDtoMapper.map(dto);
        save.setCategory(category);
        productRepository.save(save);
        return EntityDtoMapper.map(save);
    }

    public List<ProductDto> listAllProducts() {
        List<ProductEntity> prod = productRepository.findAll();
        List<ProductDto> products = productRepository.findAll()
                .stream().map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        return products;
    }

    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return EntityDtoMapper.map(productEntity);
    }
}
