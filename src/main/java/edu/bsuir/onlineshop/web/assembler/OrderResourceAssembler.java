package edu.bsuir.onlineshop.web.assembler;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.mapper.OrderMapper;
import edu.bsuir.onlineshop.web.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderResourceAssembler implements RepresentationModelAssembler<Order, OrderModel> {
    private final LinkHandler<OrderModel> linkHandler;
    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public OrderModel toModel(Order entity) {
        OrderModel model = mapper.mapToModel(entity);
        linkHandler.addLinks(model);
        return model;
    }
}
