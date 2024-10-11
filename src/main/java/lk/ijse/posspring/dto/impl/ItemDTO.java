package lk.ijse.posspring.dto.impl;

import lk.ijse.posspring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO {
    private String itemId;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
