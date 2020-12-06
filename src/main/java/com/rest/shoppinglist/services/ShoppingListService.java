package com.rest.shoppinglist.services;

import com.rest.shoppinglist.models.Item;
import com.rest.shoppinglist.models.ShoppingList;

import java.util.List;

/**
 * Created by Fassil on 05/12/20.
 */
public interface ShoppingListService {
    List<ShoppingList> getShoppingList(Integer userId);

    void createList(ShoppingList shoppingList);

    void addItemToList(Integer listId, Item item);

    void removeItem(Integer listId, Integer itemId);

    void deleteList(Integer listId);
}
