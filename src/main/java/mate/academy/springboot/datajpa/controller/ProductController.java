package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto reqDto) {
        Product product = ProductDtoMapper.toModel(reqDto);
        return ProductDtoMapper.toDto(productService.save(product));
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(ProductDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return ProductDtoMapper.toDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto reqDto) {
        Product product = ProductDtoMapper.toModel(reqDto);
        product.setId(id);
        return ProductDtoMapper.toDto(productService.update(id, product));
    }

    @GetMapping("/by-price")
    List<ProductResponseDto> getByPriceBetween(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.getByPriceBetween(from, to).stream()
                .map(ProductDtoMapper::toDto)
                .toList();
    }

    @PostMapping("/by-categories")
    List<ProductResponseDto> getByCategories(@RequestParam List<String> categoryNames) {
        return productService.getByCategoryIn(categoryNames).stream()
                .map(ProductDtoMapper::toDto)
                .toList();
    }
}
