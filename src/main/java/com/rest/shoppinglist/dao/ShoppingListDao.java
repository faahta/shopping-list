package com.rest.shoppinglist.dao;

import com.rest.shoppinglist.models.Item;
import com.rest.shoppinglist.models.ShoppingList;

import java.util.List;

/**
 * Created by Fassil on 06/12/20.
 */
public interface ShoppingListDao {
    List<ShoppingList> getShoppingList(Integer userId);

    void createShoppingList(ShoppingList shoppingList);

    void addItemToList(Integer listId, Item item);

    void removeItem(Integer listId, Integer itemId);

    void deleteShoppingList(Integer listId);
}
