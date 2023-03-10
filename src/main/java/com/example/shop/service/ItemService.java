package com.example.shop.service;

import com.example.shop.domain.Item;
import com.example.shop.domain.ItemDTO;
import com.example.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  // public ItemService(ItemRepository itemRepository) { // @RequiredArgsConstructor 있으면 필요 없다, 알아서 해줌
  //   this.itemRepository = itemRepository;
  // }

  @Transactional(readOnly = false) // @Transactional이 붙은 경우에만 예외 발생 시 롤백이 수행된다
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

  @Transactional(readOnly = false)
  public void updateItem(Long id, ItemDTO itemDTO) { //
    Item item = new Item(id, itemDTO.getName(), itemDTO.getPrice(), itemDTO.getQty()); // 여기서(save() 쓰기 전) item.id 부여
    itemRepository.save(item);
  }
}
