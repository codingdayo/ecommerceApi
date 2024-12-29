package codingdayo.com.Ecommerce.service.impl;

import codingdayo.com.Ecommerce.dto.CategoryDto;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.entity.Category;
import codingdayo.com.Ecommerce.entity.User;
import codingdayo.com.Ecommerce.exceptions.NotFoundException;
import codingdayo.com.Ecommerce.mapper.EntityDtoMapper;
import codingdayo.com.Ecommerce.repository.CategoryRepo;
import codingdayo.com.Ecommerce.service.interfac.CategoryService;
import codingdayo.com.Ecommerce.service.interfac.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategorySericeImpl implements CategoryService {


    private final CategoryRepo categoryRepo;
    private final EntityDtoMapper entityDtoMapper;


    @Override
    public Response createCategory(CategoryDto categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);

        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();

    }

    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not Found"));
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("Category update successful")
                .build();
    }

    @Override
    public Response getAllCategories() {
       List<Category> categories = categoryRepo.findAll();
       List<CategoryDto> categoryDtoList =
               categories.stream()
                       .map(entityDtoMapper::mapCategoryToDtoBasic)
                       .collect(Collectors.toList());

       return  Response.builder()
               .status(200)
               .message("Successful")
               .categoryList(categoryDtoList)
               .build();
    }

    @Override
    public Response getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not Found"));
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDtoBasic(category);

        return Response.builder()
                .status(200)
                .message("Successful")
                .category(categoryDto)
                .build();
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not Found"));
        categoryRepo.delete(category);

        return Response.builder()
                .status(200)
                .message("Category deleted successfully")
                .build();
    }
}
