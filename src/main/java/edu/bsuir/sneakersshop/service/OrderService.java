package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.web.payload.request.OrderRequest;

public interface OrderService {
    Order makeOrder(long userId, OrderRequest orderRequest);
}
