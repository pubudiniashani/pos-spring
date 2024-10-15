package lk.ijse.posspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posspring.dao.ItemDao;
import lk.ijse.posspring.dto.impl.ItemDTO;
import lk.ijse.posspring.entity.impl.ItemEntity;
import lk.ijse.posspring.exception.CustomerNotFoundException;
import lk.ijse.posspring.exception.DataPersistException;
import lk.ijse.posspring.service.ItemService;
import lk.ijse.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping itemMapping;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity savedItem = itemDao.save(itemMapping.toItemEntity(itemDTO));
        if (savedItem == null){
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
       return itemMapping.asItemDTOlist(itemDao.findAll());
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> foundItem = itemDao.findById(itemId);
        if (!foundItem.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            itemDao.deleteById(itemId);
        }
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        Optional<ItemEntity> findItem = itemDao.findById(itemId);
        if (!findItem.isPresent()){
            throw new CustomerNotFoundException("Item not found");
        }else {
            findItem.get().setItemId(itemDTO.getItemId());
            findItem.get().setDescription(itemDTO.getDescription());
            findItem.get().setUnitPrice(itemDTO.getUnitPrice());
            findItem.get().setQtyOnHand(itemDTO.getQtyOnHand());
        }
    }
}
