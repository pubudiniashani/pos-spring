package lk.ijse.posspring.service;

import lk.ijse.posspring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
    void deleteItem(String itemId);
    void updateItem(String itemId, ItemDTO itemDTO);
}
