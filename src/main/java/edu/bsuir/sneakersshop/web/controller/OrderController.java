package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.service.OrderService;
import edu.bsuir.sneakersshop.web.mapper.OrderMapper;
import edu.bsuir.sneakersshop.web.model.OrderModel;
import edu.bsuir.sneakersshop.web.request.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
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
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel makeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.makeOrder(orderRequest);
        return orderMapper.mapToModel(order);
    }
}
