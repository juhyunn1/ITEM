package com.example.shop;

import com.example.shop.repository.*;
import com.example.shop.service.ItemService;
import com.example.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

  private final EntityManager entityManager;

  public SpringConfig(EntityManager entityManager) { // 생성자 주입
    this.entityManager = entityManager;
  }

  @Bean
  public ItemRepository itemRepository() {
    // return new MemoryItemRepository();
    return new JpaItemRepository(entityManager);
  }

  @Bean
  public MemberRepository memberRepository() {
    return new JpaMemberRepository(entityManager);
  }

  // @Bean
  // public ItemService itemService() {
  //   return new ItemService(itemRepository());
  // }

  // @Bean
  // public MemberService memberService() {
  //   return new MemberService(memberRepository());
  // }
}
