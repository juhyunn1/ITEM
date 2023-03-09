package com.example.shop.service;

import com.example.shop.domain.Member;
import com.example.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional // Test 끝나고 rollback 해준다
class MemberServiceTest {

  MemberService memberService;
  MemberRepository memberRepository;

  @Autowired
  public MemberServiceTest(MemberService memberService, MemberRepository memberRepository) {
    this.memberService = memberService;
    this.memberRepository = memberRepository;
  }

  @Test
  void join() {
    // given
    Member member = new Member("어흥이", "a", "a");

    // when
    Long id = memberService.join(member); // 회원가입 수행

    // then
    Member temp = memberService.findMemberById(id).get(); // 회원가입된 상태에서 id로 찾아와서
    System.out.println("member = " + member);
    System.out.println("temp = " + temp);
    assertThat(temp).isEqualTo(member); // 비교
  }
}