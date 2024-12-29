package codingdayo.com.Ecommerce.repository;

import codingdayo.com.Ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
