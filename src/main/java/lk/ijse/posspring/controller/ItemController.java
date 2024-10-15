package lk.ijse.posspring.controller;

import lk.ijse.posspring.dto.impl.ItemDTO;
import lk.ijse.posspring.exception.DataPersistException;
import lk.ijse.posspring.exception.ItemNotFoundException;
import lk.ijse.posspring.service.ItemService;
import lk.ijse.posspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO){
        try {
            itemService.saveItem(itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @DeleteMapping(value = "/itemId")
    public ResponseEntity<Void> deleteItem(String itemId) {
        try{
            if (!RegexProcess.itemId(itemId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable("itemId") String itemId ,
                                               @RequestBody ItemDTO updatedItemDTO){
        try {
            if (!RegexProcess.itemId(itemId) || updatedItemDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemId,updatedItemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
