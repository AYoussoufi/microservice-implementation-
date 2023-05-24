package com.microservice.order.service;

import com.microservice.order.dto.OrderLineItemsDto;
import com.microservice.order.dto.OrderRequest;
import com.microservice.order.model.Order;
import com.microservice.order.model.OrderLineItems;
import com.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest){

        Order createdOrder = createOrderObject(orderRequest);

        orderRepository.save(createdOrder);
        log.info("new Order {} has successfully created",createdOrder.getOrderNumber());
        return "Successfully Created";
    }

    private Order createOrderObject(OrderRequest orderRequest){
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream()
                        .map(this::mapToDto).toList())
                .build();
    }

    private OrderLineItems mapToDto(OrderLineItemsDto item) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setOrderNumber(item.getOrderNumber());
        orderLineItems.setPrice(item.getPrice());
        orderLineItems.setSkuCode(item.getSkuCode());
        orderLineItems.setQuantity(item.getQuantity());
        return orderLineItems;
    }
}
