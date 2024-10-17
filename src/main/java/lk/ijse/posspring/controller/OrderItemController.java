package lk.ijse.posspring.controller;

import lk.ijse.posspring.dto.impl.OrderItemDTO;
import lk.ijse.posspring.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    @PostMapping
    public ResponseEntity<Void> saveOrderDetail(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            orderItemService.saveOrderItem(orderItemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}