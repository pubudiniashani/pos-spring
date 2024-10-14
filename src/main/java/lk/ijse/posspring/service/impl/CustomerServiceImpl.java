package lk.ijse.posspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posspring.dao.CustomerDao;
import lk.ijse.posspring.dto.CustomerStatus;
import lk.ijse.posspring.dto.impl.CustomerDTO;
import lk.ijse.posspring.entity.impl.CustomerEntity;
import lk.ijse.posspring.exception.CustomerNotFoundException;
import lk.ijse.posspring.exception.DataPersistException;
import lk.ijse.posspring.service.CustomerService;
import lk.ijse.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Mapping customerMapping;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity savedCustomer = customerDao.save(customerMapping.toCustomerEntity(customerDTO));
        if (savedCustomer == null){
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMapping.asCustomerDTOlist(customerDao.findAll());
    }

   /* @Override
    public CustomerStatus getCustomer(String customerId) {
        return null;
    }*/

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> foundCustomer = customerDao.findById(customerId);
        if (!foundCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> findCustomer = customerDao.findById(customerId);
        if (!findCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer not founs");
        }else {
            findCustomer.get().setCustomerId(customerDTO.getCustomerId());
            findCustomer.get().setName(customerDTO.getName());
            findCustomer.get().setAddress(customerDTO.getAddress());
            findCustomer.get().setContact(customerDTO.getContact());
        }
    }
}
