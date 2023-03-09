package com.example.shop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ItemDTO { //

  private String name;
  private Integer price;
  private Integer qty;

  public ItemDTO(String name, Integer price, Integer qty) {
    this.name = name;
    this.price = price;
    this.qty = qty;
  }
}
