package com.example.shop;

import com.example.shop.repository.ItemRepository;
import com.example.shop.repository.JpaMemberRepository;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.MemoryItemRepository;
import com.example.shop.service.ItemService;
import com.example.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.Database;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  private final EntityManager entityManager;
  private final DataSource dataSource;

  public SpringConfig(EntityManager entityManager, DataSource dataSource) { // 생성자 주입
    this.entityManager = entityManager;
    this.dataSource = dataSource;
  }

  @Bean
  public ItemRepository itemRepository() {
    return new MemoryItemRepository();
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
