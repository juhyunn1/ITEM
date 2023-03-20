package com.example.shop.domainDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ItemDto { //

  private String name;
  private Integer price;
  private Integer qty;

  public ItemDto(String name, Integer price, Integer qty) {
    this.name = name;
    this.price = price;
    this.qty = qty;
  }
}
