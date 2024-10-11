package lk.ijse.posspring.dto.impl;

import lk.ijse.posspring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDTO {
    private String orderId;
    private String customerId; // Store only the ID for DTO
    private Date orderDate;
    private double total;
    private double discount;
    private double newTotal;
    private double paidAmount;
    private double balance;
    private List<OrderItemDTO> orderDetails; // List of order details
}
