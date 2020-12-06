package com.rest.shoppinglist.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Fassil on 05/12/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name="email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name="password")
    private String password;
    @OneToMany
    private List<ShoppingList> shoppingList;

    public Users(String email, String encode, String fullName) {
        this.email = email;
        this.password = encode;
        this.fullName = fullName;
    }
}
