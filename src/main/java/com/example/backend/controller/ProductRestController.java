package com.example.backend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productRepository.findAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchUpdateProduct(
            @PathVariable("id") Integer id,
            @RequestBody Product updatedProduct
    ) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if (updatedProduct.getBrand() != null) {
                product.setBrand(updatedProduct.getBrand());
            }
            if (updatedProduct.getModel() != null) {
                product.setModel(updatedProduct.getModel());
            }
            if (updatedProduct.getDescription() != null) {
                product.setDescription(updatedProduct.getDescription());
            }
            if (updatedProduct.getPrice() >= 0) {
                product.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getQuantity() >= 0) {
                product.setQuantity(updatedProduct.getQuantity());
            }

            productRepository.save(product);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateEntireProduct(
            @PathVariable("id") Integer id,
            @RequestBody Product updatedProduct
    ) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setBrand(updatedProduct.getBrand());
            product.setModel(updatedProduct.getModel());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());

            productRepository.save(product);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> headProducts() {
        List<Product> products = productRepository.findAll();
        int totalProductsNumber = products.size();
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalCount", String.valueOf(totalProductsNumber));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headProductById(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            headers.add("id", String.valueOf(product.getId()));

            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsProduct() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
