package com.rest.shoppinglist.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Fassil on 06/12/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shopping_list_item")
public class ShoppingListItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    // @Transient
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    //  @Transient
    private Item item;
    public ShoppingListItem(ShoppingList list, Item item){
        this.shoppingList = list;
        this.item = item;
    }
}
