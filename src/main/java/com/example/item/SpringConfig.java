package com.example.item;

import com.example.item.repository.ItemRepository;
import com.example.item.repository.MemoryItemRepository;
import com.example.item.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public ItemRepository itemRepository() {
    return new MemoryItemRepository();
  }

  @Bean
  public ItemService itemService() {
    return new ItemService(itemRepository());
  }
}
