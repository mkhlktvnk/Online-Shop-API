package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.web.payload.request.OrderRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<Order> findAll(Pageable pageable);

    List<Order> findAllByUserId(long userId, Pageable pageable);

    Order makeOrder(long userId, OrderRequest orderRequest);
}
