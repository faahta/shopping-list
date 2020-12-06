/**
 * Created by Fassil on 16/06/20.
 */
package com.rest.shoppinglist.payload;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class JwtResponse implements Serializable {
    private  final long serialVersionUID = -8091879091924046844L;
    private  String jwtToken;
    private  Integer userId;
    private  String fullName;
    private  String email;
    private  String dateOfBirth;
    private  String phoneNumber;
    private List<String> roles;

    public JwtResponse(String jwtToken, String email, String fullName) {
        this.jwtToken = jwtToken;
        this.email = email;
        this.fullName = fullName;
    }

    public JwtResponse(String jwt, Integer id, String fullName, String email) {
        this.jwtToken = jwt;
        this.userId = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
//    public String getPassword() {
//        return password;
//    }
}
