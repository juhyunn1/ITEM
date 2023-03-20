package com.example.shop.controller;

import com.example.shop.controller.session.MemberOnSession;
import com.example.shop.controller.session.SessionConst;
import com.example.shop.controller.session.TestMemberOnSession;
import com.example.shop.domain.Item;
import com.example.shop.domain.Orders;
import com.example.shop.service.ItemService;
import com.example.shop.service.MemberService;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;
  private final MemberService memberService;
  private final ItemService itemService;

  @GetMapping("/add")
  public String order(HttpServletRequest request, Model model){
    // HttpSession session = request.getSession(false);
    // MemberSession memberSession = (MemberSession)session.getAttribute(SessionConst.NAME);
    TestMemberOnSession memberSession = new TestMemberOnSession();

    List<Item> items = itemService.getItemList();

    model.addAttribute("items", items);
    model.addAttribute("member", memberSession);

    return "order/orderForm";
  }

  @PostMapping("/add")
  public String order(@RequestParam Long id,
                      @RequestParam Long itemId,
                      @RequestParam int orderQty){

    orderService.order(id, itemId, orderQty);
    return "redirect:/order/orders";
  }

  @GetMapping("/orders")
  public String orderList(HttpServletRequest request, Model model){
    // HttpSession session = request.getSession(false);
    // MemberSession memberSession = (MemberSession) session.getAttribute(SessionConst.NAME);
    TestMemberOnSession memberSession = new TestMemberOnSession();
    log.info("order controller ==> membersession : {} ", memberSession);
    List<Orders> orders = orderService.findOrders(memberSession.getName());

    for (Orders order : orders){
      log.info("orders ==> {} ", order);
    }
    model.addAttribute("orders", orders);
    return "order/orderList";
  }

  @PostMapping("/cancel/{orderId}")
  public String cancelOrder(HttpServletRequest request, @PathVariable Long orderId) {
    // HttpSession session = request.getSession(false);
    // MemberSession memberSession = (MemberSession) session.getAttribute(SessionConst.NAME);
    TestMemberOnSession memberSession = new TestMemberOnSession();
    
    // 로그인 한 사람과 동일한 오더인 경우에만 삭제하도록 조건문 추가해야

    orderService.cancelOrder(orderId);

    return "redirect:/order/orders";
  }

}
