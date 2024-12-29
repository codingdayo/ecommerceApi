package codingdayo.com.Ecommerce.service.impl;

import codingdayo.com.Ecommerce.dto.OrderItemDto;
import codingdayo.com.Ecommerce.dto.OrderRequest;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.entity.Order;
import codingdayo.com.Ecommerce.entity.OrderItem;
import codingdayo.com.Ecommerce.entity.Product;
import codingdayo.com.Ecommerce.entity.User;
import codingdayo.com.Ecommerce.enums.OrderStatus;
import codingdayo.com.Ecommerce.exceptions.NotFoundException;
import codingdayo.com.Ecommerce.mapper.EntityDtoMapper;
import codingdayo.com.Ecommerce.repository.OrderItemRepo;
import codingdayo.com.Ecommerce.repository.OrderRepo;
import codingdayo.com.Ecommerce.repository.ProductRepo;
import codingdayo.com.Ecommerce.service.interfac.OrderItemService;
import codingdayo.com.Ecommerce.service.interfac.UserService;
import codingdayo.com.Ecommerce.specification.OrderItemSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {


    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final UserService userService;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response placeOrder(OrderRequest orderRequest) {

        User user = userService.getLoginUser();
        //Map orderRequests items to order entitites;
        List<OrderItem> orderItems = orderRequest.getItems().stream().map(orderItemRequest -> {
            Product product = productRepo.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product Not Found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())));
            orderItem.setStatus(OrderStatus.PENDING);
            orderItem.setUser(user);
            return orderItem;
        }).collect(Collectors.toList());
    //    calculate the total price
        BigDecimal totalPrice = orderRequest.getTotalPrice() != null && orderRequest.getTotalPrice().compareTo(BigDecimal.ZERO) > 0
                ? orderRequest.getTotalPrice()
                : orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

    //    create Order entity
        Order order = new Order();
        order.setOrderItemList(orderItems);
        order.setTotalPrice(totalPrice);

    //    set the order reference in each orderItem
        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
        });

        orderRepo.save(order);

        return Response.builder()
                .status(200)
                .message("Order placed successfully")
                .build();
    }

    @Override
    public Response updateOderItemStatus(Long orderItemId, String status) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException("Order Item not found"));

        orderItem.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderItemRepo.save(orderItem);

        return Response.builder()
                .status(200)
                .message("Order updated successfully")
                .build();
    }

    @Override
    public Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable) {
        Specification<OrderItem> spec = Specification.where(OrderItemSpecification.hasStatus(status))
                .and(OrderItemSpecification.createdBetween(startDate, endDate))
                .and(OrderItemSpecification.hasItemId(itemId));

        Page<OrderItem> orderItemPage = orderItemRepo.findAll(spec, pageable);

        if (orderItemPage.isEmpty()){
           throw new NotFoundException("Order not Found");
        }
        List<OrderItemDto> orderItemDtos = orderItemPage.getContent().stream()
                .map(entityDtoMapper::mapOrderItemToDtoPlusProductAndUser)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .orderItemList(orderItemDtos)
                .totalPage(orderItemPage.getTotalPages())
                .totalElement(orderItemPage.getTotalElements())
                .build();
    }
}
