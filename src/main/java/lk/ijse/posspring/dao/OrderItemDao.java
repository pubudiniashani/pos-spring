package lk.ijse.posspring.dao;

import lk.ijse.posspring.entity.impl.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItemEntity, Integer> {
}
