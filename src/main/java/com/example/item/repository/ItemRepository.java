package com.example.item.repository;

import com.example.item.domain.Item;
import com.example.item.domain.ItemDTO;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

  public Item save(Item item);
  public Optional<Item> findById(Long id);
  public List<Item> findAll();
  public void update(Long id, Item item);
}
