package lk.ijse.posspring.customStatusCodes;

import lk.ijse.posspring.dto.CustomerStatus;
import lk.ijse.posspring.dto.ItemStatus;
import lk.ijse.posspring.dto.OrderItemStatus;
import lk.ijse.posspring.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CustomerStatus, ItemStatus, OrderStatus, OrderItemStatus {

    private int statusCode;
    private String message;
}
