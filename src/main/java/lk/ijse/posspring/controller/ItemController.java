package lk.ijse.posspring.controller;

import lk.ijse.posspring.dto.impl.ItemDTO;
import lk.ijse.posspring.exception.DataPersistException;
import lk.ijse.posspring.exception.ItemNotFoundException;
import lk.ijse.posspring.service.ItemService;
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
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO){

        logger.info("Request to save item: {}", itemDTO);

        try {
            itemService.saveItem(itemDTO);
            logger.info("Item saved successfully: {}", itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            logger.error("DataPersistException occurred while saving item: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Exception occurred while saving item: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){
        logger.info("Request to get all items");
        return itemService.getAllItems();
    }

    @DeleteMapping(value = "/itemId")
    public ResponseEntity<Void> deleteItem(String itemId) {
        logger.info("Request to delete item: {}", itemId);
        try{
            if (!RegexProcess.itemId(itemId)){
                logger.warn("Invalid item Id format: {}", itemId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            logger.info("Item deleted successfully: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (ItemNotFoundException e){
            logger.error("ItemNotFoundException occurred for item Id: {}", itemId);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Exception occurred while deleting item: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable("itemId") String itemId ,
                                               @RequestBody ItemDTO updatedItemDTO){

        logger.info("Request to update item: {}", itemId);

        try {
            if (!RegexProcess.itemId(itemId) || updatedItemDTO == null){
                logger.warn("Invalid input data for updating item: itemId={}, itemDTO={}", itemId, updatedItemDTO);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemId,updatedItemDTO);
            logger.info("Item updated successfully: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            logger.error("ItemNotFoundException occurred for itemId: {}", itemId);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Exception occurred while updating item: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
