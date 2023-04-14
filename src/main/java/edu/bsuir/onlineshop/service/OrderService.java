package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.web.payload.request.OrderRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<Order> findAll(Pageable pageable);

    Order findById(long id);

    Order findByUserAndOrderId(long userId, long orderId);

    List<Order> findAllByUserId(long userId, Pageable pageable);

    Order makeOrder(long userId, OrderRequest orderRequest);
}
