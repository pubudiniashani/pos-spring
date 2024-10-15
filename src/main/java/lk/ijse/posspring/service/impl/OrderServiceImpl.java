package lk.ijse.posspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posspring.customStatusCodes.SelectedErrorStatus;
import lk.ijse.posspring.dao.ItemDao;
import lk.ijse.posspring.dao.OrderDao;
import lk.ijse.posspring.dao.OrderItemDao;
import lk.ijse.posspring.dto.OrderStatus;
import lk.ijse.posspring.dto.impl.OrderDTO;
import lk.ijse.posspring.entity.impl.OrderEntity;
import lk.ijse.posspring.exception.CustomerNotFoundException;
import lk.ijse.posspring.exception.OrderNotFoundException;
import lk.ijse.posspring.service.OrderService;
import lk.ijse.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private Mapping mapping;

    public void saveOrder(OrderDTO orderDTO){
        OrderEntity saveOrder = orderDao.save(mapping.toOrderEntity(orderDTO));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return mapping.asOrderDTOlist(orderDao.findAll());
    }

    @Override
    public void deleteOrder(String orderId) {
        Optional<OrderEntity> order  = orderDao.findById(orderId);
        if (!order.isPresent()){
            throw new CustomerNotFoundException("Order not found");
        }else {
            orderDao.deleteById(orderId);
        }
    }

    @Override
    public OrderDTO getOrderById(String orderId) {
        Optional<OrderEntity> orderEntityOpt = orderDao.findById(orderId);
        if (orderEntityOpt.isEmpty()) {
            throw new OrderNotFoundException("Order not found ");
        }
        return null;
    }


}
