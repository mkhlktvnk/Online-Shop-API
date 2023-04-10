package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.domain.repository.OrderRepository;
import edu.bsuir.sneakersshop.service.OrderService;
import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.service.UserService;
import edu.bsuir.sneakersshop.web.payload.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Order> findAllByUserId(long userId, Pageable pageable) {
        return orderRepository.findAllByUserId(userId, pageable);
    }

    @Override
    @Transactional
    public Order makeOrder(long userId, OrderRequest orderRequest) {
        User user = userService.findById(userId);
        Product product = productService.findById(orderRequest.getProductId());

        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setSize(orderRequest.getProductSize());
        order.setQuantity(orderRequest.getProductQuantity());

        return orderRepository.save(order);
    }

}
