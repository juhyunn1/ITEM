package com.example.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
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
    private int orderQty;

    public static OrderItem createOrderItem(Item item, int orderPrice, int orderQty){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setOrderQty(orderQty);

        item.removeStock(orderQty); // 주문 수량만큼 재고를 감소시킨다.
        return orderItem;
    }

    public void cancel(){
        this.item.addStock(orderQty); // ???? 취소수량만큼 재고수량 원복
    }
}