package codingdayo.com.Ecommerce.service.interfac;

import codingdayo.com.Ecommerce.dto.CategoryDto;
import codingdayo.com.Ecommerce.dto.Response;

public interface CategoryService {

    Response createCategory(CategoryDto categoryRequest);

    Response updateCategory(Long categoryId, CategoryDto categoryRequest);
    Response getAllCategories();

    Response getCategoryById(Long categoryId);
    Response deleteCategory(Long categoryId);

}
