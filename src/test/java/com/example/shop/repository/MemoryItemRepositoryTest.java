package com.example.shop.repository;

import com.example.shop.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryItemRepositoryTest {

  MemoryItemRepository memoryItemRepository = new MemoryItemRepository();

  @Test
  @DisplayName("상품 저장 테스트")
  void save() {
    // given
    Item item = new Item("a", 1000, 10);
    
    // when
    Item temp = memoryItemRepository.save(item);
    
    // then
    Optional<Item> found = memoryItemRepository.findById(temp.getId());
    if(found.isPresent())
      assertThat(found.get()).isEqualTo(temp); // 저장해서 꺼내온거랑 넣은거(temp)랑 같은지 비교
    else
      System.out.println("객체가 없습니다.");
  }

  @Test
  void findAll() {
    // given
    Item item1 = new Item("a", 1000, 10);
    Item item2 = new Item("b", 2000, 20);
    Item item3 = new Item("c", 3000, 30);

    // when
    memoryItemRepository.save(item1);
    memoryItemRepository.save(item2);
    memoryItemRepository.save(item3);

    // then
    assertThat(memoryItemRepository.findAll()).hasSize(3);
    assertThat(memoryItemRepository.findAll()).contains(item1, item2, item3);
    assertThat(memoryItemRepository.findAll().get(0)).isEqualTo(item1);
    assertThat(memoryItemRepository.findAll().get(1)).isEqualTo(item2);
    assertThat(memoryItemRepository.findAll().get(2)).isEqualTo(item3);
  }

  @Test
  void update() {
    // given
    Item item = memoryItemRepository.save(new Item("a", 1000, 10)); // 새로운 내용을 가지는 Item 객체
    System.out.println("수정 전 item : " + item);

    // when
    Item temp = memoryItemRepository.findById(item.getId()).get();
    temp.setName("b");
    temp.setPrice(2000);
    temp.setQty(30);

    System.out.println("수정 후 temp : " + temp);
    System.out.println("수정 후 item : " + item); // item이 수정 전/후에 다르다 << item, temp가 같은 객체를 가리킨다 << 저장한 걸 가지고 참조했으니까

    memoryItemRepository.update(temp.getId(), temp);

    // then
    assertThat(temp.getId()).isEqualTo(item.getId());
    assertThat(temp.getPrice()).isEqualTo(item.getPrice());
    assertThat(temp.getQty()).isEqualTo(item.getQty());
  }
}