package codingdayo.com.Ecommerce.service.interfac;

import codingdayo.com.Ecommerce.dto.OrderRequest;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface OrderItemService {

    Response placeOrder(OrderRequest orderRequest);

    Response updateOderItemStatus(Long orderItemId, String status);

    Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable);

}
