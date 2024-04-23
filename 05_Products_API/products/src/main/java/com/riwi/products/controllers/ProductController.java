package com.riwi.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.products.entities.Products;
import com.riwi.products.services.service_abstract.IProductService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok(this.productService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Products> insert(@RequestBody Products products) {        
        return ResponseEntity.ok(this.productService.save(products));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Products> get(@PathVariable Long id){
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Products> update(@PathVariable Long id, @RequestBody Products products){
        return ResponseEntity.ok(this.productService.update(id, products));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}