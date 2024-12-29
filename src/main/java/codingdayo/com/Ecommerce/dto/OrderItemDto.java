package codingdayo.com.Ecommerce.dto;

import codingdayo.com.Ecommerce.entity.Order;
import codingdayo.com.Ecommerce.entity.Product;
import codingdayo.com.Ecommerce.entity.User;
import codingdayo.com.Ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;

    private int quantity;
    private BigDecimal price;
    private String status;
    private UserDto user;
    private ProductDto product;
    private OrderDto order;
    private LocalDateTime createdAt;


}
