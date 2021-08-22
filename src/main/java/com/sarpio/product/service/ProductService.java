package com.sarpio.product.service;

import com.sarpio.exception.RecordNotFoundException;
import com.sarpio.product.controller.dto.ProductDto;
import com.sarpio.product.model.CategoryEntity;
import com.sarpio.product.model.ProductEntity;
import com.sarpio.product.repository.CategoryRepository;
import com.sarpio.product.repository.ProductRepository;
import com.sarpio.product.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<ProductDto> saveNewProduct(ProductDto dto) {
        return getProductDto(dto);
    }

    public ResponseEntity deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
        return new ResponseEntity("Product with Id: " + id + " not exists", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("product with Id: " + id + " deleted!");
    }

    public ResponseEntity editProduct(Long id, ProductDto dto) {
        productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("There is no product with Id: " + id));
        dto.setId(id);
        return getProductDto(dto);
    }

    private ResponseEntity getProductDto(ProductDto dto) {
        CategoryEntity category = categoryRepository
                .findById(dto.getCategory().getId())
                .orElseThrow();
        ProductEntity save = EntityDtoMapper.map(dto);
        save.setCategory(category);
        productRepository.save(save);
        return new ResponseEntity(EntityDtoMapper.map(save), HttpStatus.OK);
    }

    public ResponseEntity listAllProducts() {
        List<ProductDto> products = productRepository.findAll()
                .stream().map(EntityDtoMapper::map)
                .collect(Collectors.toList());
        return new ResponseEntity(products, HttpStatus.OK);
    }

    public ResponseEntity getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Product with Id: " + id + " not exists"));
        return new ResponseEntity(EntityDtoMapper.map(productEntity), HttpStatus.OK);
    }
}
