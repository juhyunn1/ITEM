package com.example.shop.service;

import com.example.shop.domain.Member;
import com.example.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true) // reaadOnly가 true이면 c, u, d가 안됨 >> c, u, d가 필요한 곳에서는 별도로 @Transactional 해주어야
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Transactional
  public Long join(Member member) throws IllegalStateException { // 예외 여기서 처리안하고 MemberController로 던진다
    validateDuplicateLoginId(member.getLoginId());
    return memberRepository.save(member).getId();
  }
  
  public void validateDuplicateLoginId(String loginId) {
    if(!findMemberByLoginId(loginId).isEmpty()) // 이미 동일한 loginId가 존재하면
      throw new IllegalStateException("이미 존재하는 아이디 입니다."); // 예외를 호출한 함수로 던진다
  }

  public Optional<Member> findMemberById(Long id) {
    return memberRepository.findById(id);
  }

  public Optional<Member> findMemberByLoginId(String loginId) {
    return memberRepository.findByLoginId(loginId);
  }

  // public boolean login(Member member) {
  //   System.out.println("MemberService.login : " + member);
  //   AtomicBoolean result = new AtomicBoolean(false);
  //   findMemberByLoginId(member.getLoginId()).ifPresent(thisMember -> {
  //     if (member.getPassword().equals(thisMember.getPassword())) {
  //       result.set(true);
  //     }
  //   });
  //   return result.get();
  // }
}
