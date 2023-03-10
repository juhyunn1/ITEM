package com.example.shop.repository;

import com.example.shop.domain.Item;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.*;

@RequiredArgsConstructor
public class JpaItemRepository implements ItemRepository {

  private final EntityManager entityManager; // 상수라 반드시 특정값을 가져야함 >> @RequiredArgsConstructor가 알아서 처리

  @Override
  public Item save(Item item) {
    if(item.getId() == null) // 새로 만들어져서 저장하기 전의 Item 객체이면
      entityManager.persist(item); // id 새로 넣어서 저장
    else // 이미 존재하면
      entityManager.merge(item); // update

    return item;
  }

  @Override
  public Optional<Item> findById(Long id) {
    return Optional.ofNullable(entityManager.find(Item.class, id)); // NPE 방지, 값이 없으면 빈 리스트 반환
  }

  @Override
  public List<Item> findAll() {
    return entityManager.createQuery("select i from Item i", Item.class).getResultList();
  }

  @Override
  public void update(Long id, Item item) { // 수정된 Item 객체가 넘어온다

  }
}
