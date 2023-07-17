package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product get(Long id);

    Product save(Product product);

    void delete(Long id);

    Product update(Long id, Product product);

    List<Product> getByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> getByCategoryIn(List<String> categoryNames);

    List<Product> findAll(Map<String, String> params);
}
