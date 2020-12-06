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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="category_name")
    private String categoryName;

}
