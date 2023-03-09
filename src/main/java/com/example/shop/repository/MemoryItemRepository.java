package com.example.shop.repository;

import com.example.shop.domain.Item;

import java.util.*;

public class MemoryItemRepository implements ItemRepository {

  private static final Map<Long, Item> map = new HashMap<>();
  private static Long seq = 0L; // 일련번호

  @Override
  public Item save(Item item) {
    item.setId(seq++);
    map.put(item.getId(), item);
    return item;
  }

  @Override
  public Optional<Item> findById(Long id) {
    return Optional.ofNullable(map.get(id)); // NPE 방지, 값이 없으면 빈 리스트 반환
  }

  @Override
  public List<Item> findAll() {
    return new ArrayList<>(map.values()); // ArrayList로 반환
  }

  @Override
  public void update(Long id, Item item) { // 수정된 Item 객체가 넘어온다
    Item temp = findById(id).get(); // Optional >> Item, 수정되기 전
    
    // id는 item에 이미 들어있으니깐 setId(id) 필요X
    temp.setName(item.getName());
    temp.setPrice(item.getPrice());
    temp.setQty(item.getQty());
    
    map.put(id, temp);
  }
}
