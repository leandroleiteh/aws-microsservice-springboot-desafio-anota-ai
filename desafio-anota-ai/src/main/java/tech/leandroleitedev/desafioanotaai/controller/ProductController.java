package tech.leandroleitedev.desafioanotaai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leandroleitedev.desafioanotaai.domain.product.Product;
import tech.leandroleitedev.desafioanotaai.domain.product.ProductDto;
import tech.leandroleitedev.desafioanotaai.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        var newProduct = this.productService.insert(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        var productList = this.productService.getAll();
        return ResponseEntity.ok().body(productList);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        var productUpdate = this.productService.update(id, productDto);
        return ResponseEntity.ok().body(productUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
