package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.service.OrderService;
import edu.bsuir.sneakersshop.web.mapper.OrderMapper;
import edu.bsuir.sneakersshop.web.model.OrderModel;
import edu.bsuir.sneakersshop.web.payload.request.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @GetMapping("/orders")
    public List<OrderModel> findAll(@PageableDefault Pageable pageable) {
        List<Order> orders = orderService.findAll(pageable);
        return orderMapper.mapToModel(orders);
    }

    @GetMapping("/orders/{id}")
    public OrderModel findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return orderMapper.mapToModel(order);
    }

    @GetMapping("/users/{userId}/orders")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public List<OrderModel> findAllByUserId(@PathVariable Long userId, @PageableDefault Pageable pageable) {
        List<Order> orders = orderService.findAllByUserId(userId, pageable);
        return orderMapper.mapToModel(orders);
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public OrderModel findByUserAndOrderId(@PathVariable Long userId, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        return orderMapper.mapToModel(order);
    }

    @PostMapping("/users/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public OrderModel makeOrder(@PathVariable Long userId, @Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.makeOrder(userId, orderRequest);
        return orderMapper.mapToModel(order);
    }
}
