package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.repository.OrderRepository;
import edu.bsuir.onlineshop.service.OrderService;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.service.exception.EntityNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import edu.bsuir.onlineshop.web.payload.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final MessagesSource messages;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage(MessageKey.ORDER_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    @Transactional
    public Order findByUserAndOrderId(long userId, long orderId) {
        if (!userService.existsById(userId)) {
            throw new EntityNotFoundException(
                    messages.getMessage(MessageKey.ORDER_NOT_FOUND_BY_ID, userId)
            );
        }
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage(MessageKey.ORDER_NOT_FOUND_BY_ID, orderId)
                ));
    }

    @Override
    public Page<Order> findAllByUserId(long userId, Pageable pageable) {
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
