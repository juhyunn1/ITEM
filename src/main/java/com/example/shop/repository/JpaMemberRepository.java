package com.example.shop.repository;

import com.example.shop.domain.Member;
import com.example.shop.domainDto.MemberRequestDtoForEdit;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.*;

public class JpaMemberRepository implements MemberRepository {

  private final EntityManager entityManager;

  @Autowired
  public JpaMemberRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Member save(Member member) {
    System.out.println("JpaMemberRepository.findByLoginId : " + member);
    entityManager.persist(member); // id는 자동으로 들어감 << auto increment
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(entityManager.find(Member.class, id)); // id를 키로 찾아서 Member 클래스로 반환, NPE 방지, 값이 없으면 빈 리스트 반환
  }

  @Override
  public Optional<Member> findByLoginId(String LoginId) {
    Optional<Member> temp = entityManager.createQuery("select m from Member m where login_id = :loginId", Member.class)
        .setParameter("loginId", LoginId) // 쿼리문 안의 loginId를 매개변수 loginId로 넣어준다
        .getResultList() // 리스트로 가져옴
        .stream()
        .filter(m -> m.getLoginId().equals(LoginId))
        .findAny();

    System.out.println("JpaMemberRepository.findByLoginId : " + temp);
    return temp;
  }

  @Override
  public List<Member> findAll() {
    // m이란 이름의 Member 객체에서 이름이 setParameter()로 준 값과 같은 필드를 리스트로 가져온다
    // m은 SQL에서 앨리어스
    // select m은 select *와 같다
    return entityManager.createQuery("select m from Member m", Member.class)
        .getResultList(); // 리스트로 반환
  }

  @Override
  public void update(Long id, Member member) {

  }

  @Override
  public void update2(MemberRequestDtoForEdit memberRequestDtoForEdit) {
    Member temp = findByLoginId(memberRequestDtoForEdit.getLoginId()).get();
    temp.setPassword(memberRequestDtoForEdit.getPassword());
  }

  @Override
  public Long remove(Long id) {
    Member temp = findById(id).get();
    entityManager.remove(temp);

    return id;
  }
}
