package com.example.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="order_id")
  private Long id;

  @ManyToOne  // 단방향 연관관계
  @JoinColumn(name="member_id") // FK = member_id
  private Member member;

  private LocalDateTime orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  //order를 persist 하게 되면 orderItem도 강제로 persist 진행
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  public void addOrderItem(OrderItem orderItem){
    orderItem.setOrder(this);  // this = order
    orderItems.add(orderItem);
  }

  /// orderItem 추가예정 , delivery , 결제
  public static Orders createOrder(Member member, OrderItem... orderItems){
    Orders order = new Orders();
    order.setMember(member);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.ORDER);
    for(OrderItem orderItem : orderItems){
      order.addOrderItem(orderItem);
    }
    return order;
  }

  // orderitem 추가 예정
  public void cancel(){
    status = OrderStatus.CANCEL;
    // orderitem 추가 예정 . 아이템 별 하나씩 cancel
    for(OrderItem orderItem : orderItems) {
      orderItem.cancel(); // 각 아이템 별로 주문 수량이 재고에 반영
    }
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", member=" + member +
        ", orderDate=" + orderDate +
        ", status=" + status +
        '}';
  }
}