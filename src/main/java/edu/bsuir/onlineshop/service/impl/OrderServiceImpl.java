package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.repository.OrderRepository;
import edu.bsuir.onlineshop.service.OrderService;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import edu.bsuir.onlineshop.web.payload.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Page<Order> findAllByUserId(long userId, Pageable pageable) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.USER_NOT_FOUND_BY_ID, userId)
            );
        }
        return orderRepository.findAllByUserId(userId, pageable);
    }

    @Override
    @Transactional
    public Page<Order> findAllByProductId(long productId, Pageable pageable) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }
        return orderRepository.findAllByProductId(productId, pageable);
    }

    @Override
    @Cacheable(value = "order", key = "#id")
    public Order findById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.ORDER_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    @Transactional
    @CachePut(value = "order", key = "#result.id")
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
