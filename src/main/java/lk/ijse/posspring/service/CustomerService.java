package lk.ijse.posspring.service;

import lk.ijse.posspring.dto.CustomerStatus;
import lk.ijse.posspring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerStatus getCustomer(String customerId);
    void deleteCustomer(String customerId);
    void updateCustomer(String customerId, CustomerDTO customerDTO);

}
