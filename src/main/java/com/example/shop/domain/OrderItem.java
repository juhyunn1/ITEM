package com.example.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders order;
    private int orderPrice;
    private int orderQuantity;
}
