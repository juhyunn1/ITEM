package com.example.shop.controller;

import com.example.shop.domain.ItemDTO;
import com.example.shop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // 컴퍼넌트 스캔 대상 << @Component가 포함되어 있다
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/items") // .../items로 들어오면
  public String getItems(Model model) {
    model.addAttribute("items", itemService.getItemList());
    return "/item/items"; // items.html 호출
  }

  @GetMapping("/item/{id}") //.../item/1과 같이 들어오면
  public String getItem(@PathVariable Long id, Model model) {
    model.addAttribute("item", itemService.getItemById(id).get());
    return "/item/item";
  }

  @GetMapping("/edit/{id}")
  public String getEdit(@PathVariable Long id, Model model) {
    model.addAttribute("item", itemService.getItemById(id).get());
    return "/item/editForm";
  }

  @PostMapping("/edit/{id}") // post 방식으로 들어오면
  public String postEdit(
      @PathVariable Long id,
      @ModelAttribute("item") ItemDTO itemDTO,
      RedirectAttributes redirectAttributes
  ) {
    System.out.println(itemDTO);
    itemService.updateItem(id, itemDTO);

    // redirect할 때 파라미터 추가해서 redirect
    // 속성을 redirect 주소안에 path variable로 주면 주소에, 아니면 쿼리 스트링으로(param.속성_이름)으로 접근 가능
    redirectAttributes.addAttribute("id", id);
    redirectAttributes.addAttribute("status", true);
    return "redirect:/item/item/{id}"; // 상세화면으로 이동, id는 redirectAttributes의 id, redirect하면 get 방식으로 처리
  }

  @GetMapping("/add")
  public String getAdd() {
    return "/item/addForm";
  }

  @PostMapping("/add")
  public String postAdd(@ModelAttribute("item") ItemDTO itemDTO) { // item이라는 이름으로 ItemDTO에 넣어서 모델 설정, 파라미터 없으면 itemDTO를 이름으로 사용, 클래스 필드명과 input의 name 속성이 같아야
  // public String postAdd(@RequestParam String name, @RequestParam(required = true) int price, @RequestParam Integer qty) { // 클래스에 Integer로 되어있어서 int 쓸때는 null값 방지위해 required=true 설정
    System.out.println("item : " + itemDTO);
    itemService.addItem(itemDTO);
    return "redirect:/items";
  }
}
