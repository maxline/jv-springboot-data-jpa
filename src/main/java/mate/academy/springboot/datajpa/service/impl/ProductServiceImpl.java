package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No product found with id " + id));
    }

    public List<Product> findAll(Map<String, String> params) {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(
                () -> new EntityNotFoundException("No category found with id "
                        + product.getCategory().getId()));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No product to update with id " + id));
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> getByCategoryIn(List<String> categoryNames) {
        return productRepository.findAllByCategoryNameIn(categoryNames);
    }
}
