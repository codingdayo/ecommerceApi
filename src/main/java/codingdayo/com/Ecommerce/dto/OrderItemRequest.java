package codingdayo.com.Ecommerce.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long productId;
    private int quantity;


}