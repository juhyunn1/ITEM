package com.example.shop.service;

import com.example.shop.domain.Member;
import com.example.shop.domain.Orders;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long order(Long member_id){
        Member member = memberRepository.findById(member_id).get();
        log.info("orderservice ==> member : {} ", member);
        // order + orderItem + item
        Orders order = Orders.createOrder(member);
        log.info("orderService ==> order : {} ",  order);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Orders order = orderRepository.findByOne(orderId);
        // jpa 가 dirty checking을 해서 자동으로 db에 update를 해줌
        order.cancel();
    }
    public List<Orders> findOrders(String username){
        return orderRepository.findAll(username);
    }
}
