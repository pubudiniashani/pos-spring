package lk.ijse.posspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posspring.dto.CustomerStatus;
import lk.ijse.posspring.dto.impl.CustomerDTO;
import lk.ijse.posspring.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return null;
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        return null;
    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {

    }
}
