package com.example.item.service;

import com.example.item.domain.Item;
import com.example.item.domain.ItemDTO;
import com.example.item.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

public class ItemService {

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Long addItem(ItemDTO itemDTO) {
    Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getQty());
    return itemRepository.save(item).getId();
  }

  public List<Item> getItemList() {
    return itemRepository.findAll();
  }

  public Optional<Item> getItemById(Long id) {
    return itemRepository.findById(id);
  }

  public void updateItem(Long id, ItemDTO itemDTO) { //
    Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getQty()); // 여기서 item.id는 null
    itemRepository.update(id, item); // 여기서 item.id에 id가 들어감
  }
}
