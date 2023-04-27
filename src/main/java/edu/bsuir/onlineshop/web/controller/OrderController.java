package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.service.OrderService;
import edu.bsuir.onlineshop.web.model.OrderModel;
import edu.bsuir.onlineshop.web.payload.request.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RepresentationModelAssembler<Order, OrderModel> modelAssembler;

    @GetMapping("/orders")
    public ResponseEntity<PagedModel<OrderModel>> findAll(@PageableDefault Pageable pageable) {
        Page<Order> orders = orderService.findAll(pageable);
        PagedModel<OrderModel> page = pagedResourcesAssembler.toModel(orders, modelAssembler);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/users/{userId}/orders")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<PagedModel<OrderModel>> findAllByUserId(
            @PathVariable Long userId, @PageableDefault Pageable pageable) {
        Page<Order> orders = orderService.findAllByUserId(userId, pageable);
        PagedModel<OrderModel> page = pagedResourcesAssembler.toModel(orders, modelAssembler);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/products/{productId}/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PagedModel<OrderModel>> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Order> orders = orderService.findAllByProductId(productId, pageable);
        PagedModel<OrderModel> page = pagedResourcesAssembler.toModel(orders, modelAssembler);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderModel> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        OrderModel orderModel = modelAssembler.toModel(order);
        return ResponseEntity.ok(orderModel);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderModel> makeOrder(
            @AuthenticationPrincipal User user, @Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.makeOrder(user.getId(), orderRequest);
        OrderModel orderModel = modelAssembler.toModel(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderModel);
    }
}
