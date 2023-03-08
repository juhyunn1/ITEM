package com.example.item.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
