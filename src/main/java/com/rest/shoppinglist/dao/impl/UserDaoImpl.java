package com.rest.shoppinglist.dao.impl;

import com.rest.shoppinglist.dao.UserDao;
import com.rest.shoppinglist.models.ConfirmationToken;
import com.rest.shoppinglist.models.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * Created by Fassil on 05/12/20.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @PersistenceContext(name = "ShoppingListPersistence")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Users findByEmail(String email) {
        Users user = null;
        try {
            user = entityManager.createQuery("select u from Users u where u.email=:email", Users.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException nre){
        }
        return user;
    }

    @Override
    @Transactional
    public Optional<Users> findById(Integer id) {
        Users user = entityManager.find(Users.class, id);
        return Optional.of(user);
    }

    @Override
    @Transactional
    public void add(Users user, ConfirmationToken confirmationToken) {
        //entityManager.persist(user);
        entityManager.persist(confirmationToken);

    }

    @Override
    public Users getUserByEmail() {
        return null;
    }

    @Override
    public void deletAccount(Integer userId) {

    }

    @Override
    public void updateUser(String username) {

    }
}
