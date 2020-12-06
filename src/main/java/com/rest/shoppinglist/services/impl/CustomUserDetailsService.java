package com.rest.shoppinglist.services.impl;

import com.rest.shoppinglist.dao.UserDao;
import com.rest.shoppinglist.models.UserPrincipal;
import com.rest.shoppinglist.models.Users;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Created by Fassil on 05/12/20.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Integer id) throws NotFoundException {
        Users user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        return UserPrincipal.create(user);
    }
}
