package lk.ijse.posspring.controller;

import lk.ijse.posspring.dto.impl.OrderDTO;
import lk.ijse.posspring.exception.DataPersistException;
import lk.ijse.posspring.exception.OrderNotFoundException;
import lk.ijse.posspring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO){

        logger.info("Request to save an order: {}", orderDTO);

        try {
            orderService.saveOrder(orderDTO);
            logger.info("Order saved successfully: {}", orderDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Exception occurred while saving an order: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders(){
        logger.info("Request to get all orders");
        return orderService.getAllOrders();
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Void> deleteOrder(String orderId){
        logger.info("Request to delete an order: {}", orderId);
        try{
            orderService.deleteOrder(orderId);
            logger.info("Order deleted successfully: {}", orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (OrderNotFoundException e){
            logger.error("OrderNotFoundException occurred: {}", orderId);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Exception occurred while deleting an order: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") String orderId){
            try {
                OrderDTO orderDTO = orderService.getOrderById(orderId);
                logger.info("Requested an order: {}",orderId);
                return new ResponseEntity<>(orderDTO, HttpStatus.OK);
            }catch (OrderNotFoundException e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                logger.error("Exception occurred while getting an order: {}", e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

}
