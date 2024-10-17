package lk.ijse.posspring.controller;

import lk.ijse.posspring.dto.impl.CustomerDTO;
import lk.ijse.posspring.exception.CustomerNotFoundException;
import lk.ijse.posspring.exception.DataPersistException;
import lk.ijse.posspring.service.CustomerService;
import lk.ijse.posspring.util.RegexProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO){
        logger.info("Request to save customer: {}", customerDTO);
        try {
            customerService.saveCustomer(customerDTO);
            logger.info("Customer saved successfully: {}", customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            logger.error("DataPersistException occurred while saving customer: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Exception occurred while saving customer: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomer(){
        logger.info("Request to get all customers");
        return customerService.getAllCustomers();
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(String customerId){
        logger.info("Request to delete customer: {}", customerId);

        try{
            if (!RegexProcess.customerId(customerId)){
                logger.warn("Invalid customer Id format: {}", customerId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.deleteCustomer(customerId);
            logger.info("Customer deleted successfully: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (CustomerNotFoundException e){
            logger.error("CustomerNotFoundException occurred for customerId: {}", customerId);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            logger.error("Exception occurred while deleting customer: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId ,
                             @RequestBody CustomerDTO updatedCustomerDTO){
        logger.info("Request to update customer: {}", customerId);
        try {
            if (!RegexProcess.customerId(customerId) || updatedCustomerDTO == null){
                logger.warn("Invalid input data for updating customer: customerId={}, customerDTO={}", customerId, updatedCustomerDTO);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.updateCustomer(customerId,updatedCustomerDTO);
            logger.info("Customer updated successfully: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException e){
            logger.error("CustomerNotFoundException occurred for customerId: {}", customerId);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Exception occurred while updating customer: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
