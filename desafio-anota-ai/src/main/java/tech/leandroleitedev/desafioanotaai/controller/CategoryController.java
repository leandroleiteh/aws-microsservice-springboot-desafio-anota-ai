package tech.leandroleitedev.desafioanotaai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leandroleitedev.desafioanotaai.domain.category.Category;
import tech.leandroleitedev.desafioanotaai.domain.category.CategoryDto;
import tech.leandroleitedev.desafioanotaai.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        var newCategory = this.categoryService.insert(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        var categories = this.categoryService.getAll();
        return ResponseEntity.ok().body(categories);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        var categoryUpdate = this.categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(categoryUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
