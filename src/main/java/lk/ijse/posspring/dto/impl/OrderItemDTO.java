package lk.ijse.posspring.dto.impl;

import lk.ijse.posspring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO implements SuperDTO {
    private Long id; // Optional, depends on whether you need it
    private String itemId;
    private String orderId;// Store only the ID for DTO
    private int qty;
    private double unitPrice;
    private double total;
}
