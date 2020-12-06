package com.rest.shoppinglist.services.impl;

import com.rest.shoppinglist.dao.ShoppingListDao;
import com.rest.shoppinglist.models.Item;
import com.rest.shoppinglist.models.ShoppingList;
import com.rest.shoppinglist.services.ShoppingListService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fassil on 05/12/20.
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    final ShoppingListDao shoppingListDao;

    public ShoppingListServiceImpl(ShoppingListDao shoppingListDao) {
        this.shoppingListDao = shoppingListDao;
    }

    @Override
    public List<ShoppingList> getShoppingList(Integer userId) {
        return this.shoppingListDao.getShoppingList(userId);
    }

    @Override
    public void createList(ShoppingList shoppingList) {
        this.shoppingListDao.createShoppingList(shoppingList);
    }

    @Override
    public void addItemToList(Integer listId, Item item) {
        this.shoppingListDao.addItemToList(listId, item);
    }

    @Override
    public void removeItem(Integer listId, Integer itemId) {
        this.shoppingListDao.removeItem(listId, itemId);
    }

    @Override
    public void deleteList(Integer listId) {
        this.shoppingListDao.deleteShoppingList(listId);
    }
}
