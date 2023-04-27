package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.web.payload.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByUserId(long userId, Pageable pageable);

    Page<Order> findAllByProductId(long productId, Pageable pageable);

    Order findById(long id);

    Order makeOrder(long userId, OrderRequest orderRequest);

    boolean existsByUserIdAndOrderId(long userId, long productId);
}
