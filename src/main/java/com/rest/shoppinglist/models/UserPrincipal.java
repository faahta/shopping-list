package com.rest.shoppinglist.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Created by Fassil on 05/12/20.
 */
public class UserPrincipal implements UserDetails {
    private Integer id;
    private String email;
    private String password;
    private String fullName;

    public UserPrincipal(Integer userId, String fullName, String email, String password) {
        this.id = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public static UserDetails create(Users user) {

        return new UserPrincipal(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword()

        );
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
