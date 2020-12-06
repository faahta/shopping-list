package com.rest.shoppinglist.controllers;

import com.rest.shoppinglist.models.Item;
import com.rest.shoppinglist.models.ShoppingList;
import com.rest.shoppinglist.services.ShoppingListService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Fassil on 05/12/20.
 */

@RestController
public class ShoppingListController {

    final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(name = "/v1/api/{userId}/shopping-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShoppingList(@PathVariable("userId") Integer userId){ ;
        return ResponseEntity.ok().body( this.shoppingListService.getShoppingList(userId));
    }

    @PostMapping(name="/v1/api/shopping-list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createShoppingList(@RequestBody ShoppingList shoppingList){
        this.shoppingListService.createList(shoppingList);
    }
    @DeleteMapping(name="/v1/api/shopping-list/{listId}")
    public void deleteShoppingList(@PathVariable("listId") Integer listId){
        this.shoppingListService.deleteList(listId);
    }


    @PostMapping(name = "/v1/api/shopping-list/{listId}/item")
    public void addItemToList(@PathVariable("listId") Integer listId, @RequestBody Item item){
        this.shoppingListService.addItemToList(listId, item);
    }
    @DeleteMapping(name="/v1/api/shopping-list/{listId}/{itemId}")
    public void removeItemFromList(@PathVariable("listId") Integer listId, @PathVariable("itemId") Integer itemId){
        this.shoppingListService.removeItem(listId, itemId);
    }
}
