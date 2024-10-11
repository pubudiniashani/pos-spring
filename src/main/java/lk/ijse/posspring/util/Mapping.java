package lk.ijse.posspring.util;

import lk.ijse.posspring.dto.impl.CustomerDTO;
import lk.ijse.posspring.dto.impl.ItemDTO;
import lk.ijse.posspring.dto.impl.OrderDTO;
import lk.ijse.posspring.dto.impl.OrderItemDTO;
import lk.ijse.posspring.entity.impl.CustomerEntity;
import lk.ijse.posspring.entity.impl.ItemEntity;
import lk.ijse.posspring.entity.impl.OrderEntity;
import lk.ijse.posspring.entity.impl.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public List<CustomerDTO> asCustomerDTOlist(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDTO>>() {}.getType());

    }

    public ItemEntity toItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, ItemEntity.class);
    }

    public ItemDTO toItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDTO.class);
    }

    public List<ItemDTO> asItemDTOlist(List<ItemEntity> itemEntities){
        return modelMapper.map(itemEntities, new TypeToken<List<ItemDTO>>() {}.getType());

    }

    public OrderEntity toOrderEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, OrderEntity.class);
    }

    public OrderDTO toOrderDTO(OrderEntity orderEntity){
        return modelMapper.map(orderEntity, OrderDTO.class);
    }

    public List<OrderDTO> asOrderDTOlist(List<OrderEntity> orderEntities){
        return modelMapper.map(orderEntities, new TypeToken<List<OrderDTO>>() {}.getType());

    }

    public OrderItemEntity toOrderItemEntity(OrderItemDTO orderItemDTO){
        return modelMapper.map(orderItemDTO, OrderItemEntity.class);
    }

    public OrderItemDTO toOrderItemDTO(OrderItemEntity orderItemEntity){
        return modelMapper.map(orderItemEntity, OrderItemDTO.class);
    }

    public List<OrderItemDTO> asOrderItemDTOlist(List<OrderItemEntity> orderItemEntities){
        return modelMapper.map(orderItemEntities, new TypeToken<List<OrderItemDTO>>() {}.getType());

    }




}
