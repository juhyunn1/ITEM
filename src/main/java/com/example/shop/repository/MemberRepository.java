package com.example.shop.repository;

import com.example.shop.domain.Member;
import com.example.shop.domainDto.MemberRequestDtoForEdit;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

  public Member save(Member member);
  public Optional<Member> findById(Long id);
  public Optional<Member> findByLoginId(String LoginId);
  public List<Member> findAll();
  public void update(Long id, Member item);
  public void update2(MemberRequestDtoForEdit memberRequestDtoForEdit);
  public Long remove(Long id);
}
