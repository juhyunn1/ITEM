package com.example.shop.repository;

import com.example.shop.domain.NewMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaNewMemberRepositoryTest {

  private EntityManager entityManager;

  @Autowired
  public JpaNewMemberRepositoryTest(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Test
  void 동일한_트랜잭션_엔티티_동일성_테스트() {
    String id = "1";
    NewMember m1 = entityManager.find(NewMember.class, id); // sql 사용
    NewMember m2 = entityManager.find(NewMember.class, id); // 캐시 사용

    System.out.println("m1: " + m1);
    System.out.println("m2: " + m2);

    assertThat(m1).equals(m2);
  }
}