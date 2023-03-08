package com.example.item.controller;

import com.example.item.domain.Item;
import com.example.item.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 컴퍼넌트 스캔 대상 << @Component가 포함되어 있다
@RequestMapping("/basic")
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/items") // .../basic/items로 들어오면
  public String getItems(Model model) {
    model.addAttribute("items", itemService.getItemList());
    return "/basic/items"; // items.html 호출
  }

  @GetMapping("/item/{id}") //.../basic/item/1과 같이 들어오면
  public String getItem(@PathVariable Long id, Model model) {
    model.addAttribute("item", itemService.getItemById(id).get());
    return "/basic/item";
  }

  @GetMapping("/edit/{id}")
  public String getEdit(@PathVariable Long id, Model model) {
    model.addAttribute("item", itemService.getItemById(id).get());
    return "/basic/editForm";
  }

  @GetMapping("/add")
  public String getAdd() {
    return "/basic/addForm";
  }
}
