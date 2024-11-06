package tech.leandroleitedev.desafioanotaai.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.leandroleitedev.desafioanotaai.domain.category.Category;
import tech.leandroleitedev.desafioanotaai.domain.category.CategoryDto;
import tech.leandroleitedev.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import tech.leandroleitedev.desafioanotaai.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category insert(CategoryDto categoryDto){
        var newCategory = new Category(categoryDto);
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Transactional
    public Category update(String id, CategoryDto categoryDto) {
        var newCategory = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if (!categoryDto.title().isEmpty()) newCategory.setTitle(categoryDto.title());
        if (!categoryDto.description().isEmpty()) newCategory.setDescription(categoryDto.description());
        return this.categoryRepository.save(newCategory);

    }

    @Transactional
    public void delete(String id) {
        var newCategory = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.categoryRepository.delete(newCategory);
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }
}
