package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.service.OrderService;
import edu.bsuir.onlineshop.web.mapper.OrderMapper;
import edu.bsuir.onlineshop.web.model.OrderModel;
import edu.bsuir.onlineshop.web.payload.request.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final PagedResourcesAssembler<Order> pagedResourcesAssembler;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @GetMapping("/orders")
    public PagedModel<OrderModel> findAll(@PageableDefault Pageable pageable) {
        Page<Order> orders = orderService.findAll(pageable);
        return pagedResourcesAssembler.toModel(orders, orderMapper::mapToModel);
    }

    @GetMapping("/users/{userId}/orders")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public PagedModel<OrderModel> findAllByUserId(@PathVariable Long userId, @PageableDefault Pageable pageable) {
        Page<Order> orders = orderService.findAllByUserId(userId, pageable);
        return pagedResourcesAssembler.toModel(orders, orderMapper::mapToModel);
    }

    @GetMapping("/products/{productId}/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public PagedModel<OrderModel> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Order> orders = orderService.findAllByProductId(productId, pageable);
        return pagedResourcesAssembler.toModel(orders, orderMapper::mapToModel);
    }

    @GetMapping("/orders/{id}")
    public OrderModel findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return orderMapper.mapToModel(order);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel makeOrder(
            @AuthenticationPrincipal User user, @Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.makeOrder(user.getId(), orderRequest);
        return orderMapper.mapToModel(order);
    }
}
