package com.example.shop.service;

import com.example.shop.domain.Item;
import com.example.shop.domain.Member;
import com.example.shop.domain.OrderItem;
import com.example.shop.domain.Orders;
import com.example.shop.repository.ItemRepository;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
//@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Long order(Long member_id, Long id, int orderQuantity){
    Member member = memberRepository.findById(member_id).get();
    Item item = itemRepository.findById(id).get();
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderQuantity);

    // 여러건의 주문을 입력하려면
    // while 로 orderItems을 list로 여러건 생성한 후
    // 그 주문아이템들을 배열로 넘겨서 하나로 묶어서 order를 생성해야 함.

    Orders order = Orders.createOrder(member, orderItem);
    log.info("orderService ==> order : {} ",  order);
    orderRepository.save(order);
    return order.getId();
  }

  @Transactional
  public void cancelOrder(Long orderId){
    Orders order = orderRepository.findByOne(orderId);
    // jpa 가 dirty checking을 해서 자동으로 db에 update를 해줌.
    order.cancel();
  }
  public List<Orders> findOrders(String username){
    return orderRepository.findAll(username);
  }
}