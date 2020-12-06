package com.rest.shoppinglist.dao.impl;

import com.rest.shoppinglist.dao.ShoppingListDao;
import com.rest.shoppinglist.models.Item;
import com.rest.shoppinglist.models.ShoppingList;
import com.rest.shoppinglist.models.ShoppingListItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Fassil on 06/12/20.
 */
@Repository
public class ShoppingListDaoImpl implements ShoppingListDao {

    @PersistenceContext(name = "ShoppingListPersistence")
    EntityManager em;

    @Override
    @Transactional
    public List<ShoppingList> getShoppingList(Integer userId) {
        Query query = em.createQuery("select s.shoppingList from Users s where s.userId=:userId").setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void createShoppingList(ShoppingList shoppingList) {
        em.persist(shoppingList);
    }

    @Override
    @Transactional
    public void addItemToList(Integer listId, Item item) {
        em.persist(item);
        Query query =  em.createQuery("select s from ShoppingList s where s.id=:id").setParameter("id", listId);
        ShoppingListItem shoppingListItem = new ShoppingListItem((ShoppingList) query.getResultList(), item);
        em.persist(shoppingListItem);
    }

    @Override
    @Transactional
    public void removeItem(Integer listId, Integer itemId) {
        Query query = em.createQuery("delete from ShoppingListItem s where s.id=:listId AND s.item.id=:itemId").setParameter("listId", listId).setParameter("itemId", itemId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteShoppingList(Integer listId) {
        Query query = em.createQuery("delete from ShoppingList s where s.id=:listId ").setParameter("listId", listId);
        query.executeUpdate();
    }
}
