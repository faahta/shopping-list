package com.rest.shoppinglist.services;

import com.rest.shoppinglist.models.Users;

import javax.mail.MessagingException;
import java.util.UUID;

/**
 * Created by Fassil on 06/12/20.
 */
public interface UserService {
    boolean checkIfUserExist(String email);

    void registerUser(Users user) throws MessagingException;

    void deleteUserAccount(Integer userId);

    boolean getUserById(Integer userId);

    Users getUserByEmail(String email);

    void updateUser(String username);
}
