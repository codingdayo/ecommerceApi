package codingdayo.com.Ecommerce.repository;

import codingdayo.com.Ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {

}














//JpaSpecificationExecutor<OrderItem> interface in your repository provides a way to execute dynamic,
// type-safe queries using the JPA Criteria API.
//You can build queries based on user inputs or other runtime conditions.
//Example: A search filter for OrderItem that includes multiple optional criteria like status, dateRange, or priceRange.
//e.g. @Service
//public class OrderItemService {
//
//    @Autowired
//    private OrderItemRepo orderItemRepo;
//
//    public List<OrderItem> getShippedExpensiveItems() {
//        return orderItemRepo.findAll(
//            OrderItemSpecifications.hasStatus("SHIPPED")
//                .and(OrderItemSpecifications.hasTotalPriceGreaterThan(100))
//        );
//    }
//}