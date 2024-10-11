package lk.ijse.posspring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.posspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderItems")
public class OrderItemEntity implements SuperEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order; // Relationship with Order

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item; // Relationship with Item

    private int qty;
    private double unitPrice;
    private double total;
}
