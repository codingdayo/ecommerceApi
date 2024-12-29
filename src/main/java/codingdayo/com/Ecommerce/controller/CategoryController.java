package codingdayo.com.Ecommerce.controller;


import codingdayo.com.Ecommerce.dto.CategoryDto;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.service.interfac.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Response> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }


    @GetMapping("/get-by-id/{categoryId}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }


}
