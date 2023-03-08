package com.example.item.domain;

import lombok.Getter; // 컴파일할 때 getter 만들어준다
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Item {

  private Long id;
  private String name;
  private Integer price;
  private Integer qty;

  public Item(String name, Integer price, Integer qty) {
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

  // @Override
  // public String toString() {
  //   return "Item [id=" + id + ", name=" + name + ", price=" + price + "]";
  // }
}
