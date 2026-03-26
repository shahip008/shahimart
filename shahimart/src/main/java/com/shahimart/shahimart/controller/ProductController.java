package com.shahimart.shahimart.controller;

import com.shahimart.shahimart.dto.ProductDto;
import com.shahimart.shahimart.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final IProductService iProductService;
    private static final Logger log =
    LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() { // DTO Pattern
        log.trace("TRACE: Entering method getProducts()");
        log.debug("DEBUG: Entering method getProducts()");
        log.info("INFO: Fetching all products");
        log.warn("WARN: Product list might be empty");
        log.error("ERROR: Exception occurred while fetching products");
        System.out.println("Entering into ProductController  getProducts method fedf");
        List<ProductDto> productList = iProductService.getProducts();
        return ResponseEntity.ok().body(productList);
    }
}
