package lk.ijse.posspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posspring.dao.OrderDao;
import lk.ijse.posspring.dao.OrderItemDao;
import lk.ijse.posspring.dto.impl.OrderItemDTO;
import lk.ijse.posspring.entity.impl.OrderEntity;
import lk.ijse.posspring.entity.impl.OrderItemEntity;
import lk.ijse.posspring.service.OrderItemService;
import lk.ijse.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrderItem(OrderItemDTO orderItemDTO) {
        OrderEntity orderEntity = orderDao.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItemEntity orderItemEntity = mapping.toOrderItemEntity(orderItemDTO);
        orderItemEntity.setOrder(orderEntity);
        orderItemDao.save(orderItemEntity);
    }
}
