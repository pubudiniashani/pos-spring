package lk.ijse.posspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posspring.dao.ItemDao;
import lk.ijse.posspring.dao.OrderDao;
import lk.ijse.posspring.dao.OrderItemDao;
import lk.ijse.posspring.dto.impl.OrderDTO;
import lk.ijse.posspring.entity.impl.OrderEntity;
import lk.ijse.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl {

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




}
