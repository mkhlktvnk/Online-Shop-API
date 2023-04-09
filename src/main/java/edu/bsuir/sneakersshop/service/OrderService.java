package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.web.request.OrderRequest;

public interface OrderService {
    Order makeOrder(OrderRequest orderRequest);
}
