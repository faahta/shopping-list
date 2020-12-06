package com.rest.shoppinglist.dao.impl;

import com.rest.shoppinglist.dao.ConfirmationTokenDao;
import com.rest.shoppinglist.models.ConfirmationToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by Fassil on 06/12/20.
 */
@Repository
public class ConfirmationTokenDaoImpl implements ConfirmationTokenDao {
    private static final Logger logger = LogManager.getLogger(ConfirmationTokenDaoImpl.class);

    @PersistenceContext(name = "ShoppingListPersistence")
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(ConfirmationToken confirmationToken) {
        entityManager.persist(confirmationToken);
    }

    @Override
    @Transactional
    public ConfirmationToken findByConfirmationToken(String confirmationToken) {
        ConfirmationToken confirmationToken1 = null;
        try {
            confirmationToken1 = entityManager.createQuery("select ct from ConfirmationToken ct where ct.confirmationToken=:confToken", ConfirmationToken.class).
                    setParameter("confToken", confirmationToken).getSingleResult();
        } catch (NoResultException nre){

        }
        return confirmationToken1;
    }
}
