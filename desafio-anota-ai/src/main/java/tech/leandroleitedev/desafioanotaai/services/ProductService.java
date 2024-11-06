package tech.leandroleitedev.desafioanotaai.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.leandroleitedev.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import tech.leandroleitedev.desafioanotaai.domain.product.Product;
import tech.leandroleitedev.desafioanotaai.domain.product.ProductDto;
import tech.leandroleitedev.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import tech.leandroleitedev.desafioanotaai.repositories.ProductRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Transactional
    public Product insert(ProductDto productDto) {
        var category = this.categoryService.getById(productDto.categoryId()).orElseThrow(CategoryNotFoundException::new);
        var newProduct = new Product(productDto);
        newProduct.setCategory(category);
        return this.productRepository.save(newProduct);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Transactional
    public Product update(String id, ProductDto productDto) {
        var product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (Objects.nonNull(productDto.categoryId())){
            this.categoryService.getById(productDto.categoryId()).ifPresent(product::setCategory);
        }

        if (!productDto.title().isEmpty()) product.setTitle(productDto.title());
        if (!productDto.description().isEmpty()) product.setDescription(productDto.description());
        if (Objects.nonNull(productDto.price())) product.setPrice(productDto.price());
        return this.productRepository.save(product);
    }

    @Transactional
    public void delete(String id) {
        var product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
    }
}
