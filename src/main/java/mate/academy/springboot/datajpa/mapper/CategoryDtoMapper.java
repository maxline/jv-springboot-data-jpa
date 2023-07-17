package mate.academy.springboot.datajpa.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDtoMapper {

    public static Category toModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDto toDto(Category model) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
