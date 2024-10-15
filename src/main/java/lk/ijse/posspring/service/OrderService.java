package lk.ijse.posspring.service;

import lk.ijse.posspring.dto.impl.CustomerDTO;
import lk.ijse.posspring.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {

     void saveOrder(OrderDTO orderDTO);

    List<OrderDTO> getAllOrders();

     void deleteOrder(String orderId);
}
