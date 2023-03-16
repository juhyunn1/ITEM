package com.example.shop.controller;

import com.example.shop.controller.session.MemberOnSession;
import com.example.shop.controller.session.SessionConst;
import com.example.shop.domain.Orders;
import com.example.shop.service.MemberService;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/add")
    public String order(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        MemberOnSession memberOnSession = (MemberOnSession)session.getAttribute(SessionConst.NAME);
        model.addAttribute("member", memberOnSession);
        return "order/orderForm";
    }

    @PostMapping("/add")
    public String order(@RequestParam Long id){
        System.out.println("id :" +id);
        orderService.order(id);
        return "redirect:/order/orders";
    }

    @GetMapping("/orders")
    public String orderList(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        MemberOnSession memberSession = (MemberOnSession) session.getAttribute(SessionConst.NAME);
        log.info("order controller ==> memberOnSession : {} ", memberSession);
        List<Orders> orders = orderService.findOrders(memberSession.getName());

        for (Orders order : orders){
            log.info("orders ==> {} ", order);
        }
        model.addAttribute("orders", orders);
        return "order/orderList";
    }
}
