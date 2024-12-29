package codingdayo.com.Ecommerce.repository;

import codingdayo.com.Ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameOrDescriptionContaining(String name, String description);





}