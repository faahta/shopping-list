package com.rest.shoppinglist.dao;

import com.rest.shoppinglist.models.ConfirmationToken;

/**
 * Created by Fassil on 06/12/20.
 */
public interface ConfirmationTokenDao {
    void save(ConfirmationToken confirmationToken);
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
