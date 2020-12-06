package com.rest.shoppinglist.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Fassil on 05/12/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="item_name")
    private String itemName;

    @Column(name = "price")
    private Double price;

}
