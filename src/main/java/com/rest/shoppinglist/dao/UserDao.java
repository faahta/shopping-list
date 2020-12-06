package com.rest.shoppinglist.dao;

import com.rest.shoppinglist.models.ConfirmationToken;
import com.rest.shoppinglist.models.Users;

import java.util.Optional;

/**
 * Created by Fassil on 05/12/20.
 */
public interface UserDao {
    Users findByEmail(String email);

    Optional<Users> findById(Integer id);

    void add(Users user, ConfirmationToken confirmationToken);


    Users getUserByEmail();

    void deletAccount(Integer userId);

    void updateUser(String username);
}
