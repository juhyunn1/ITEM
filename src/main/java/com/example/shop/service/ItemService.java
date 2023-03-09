package com.example.shop.service;

import com.example.shop.domain.Item;
import com.example.shop.domain.ItemDTO;
import com.example.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
