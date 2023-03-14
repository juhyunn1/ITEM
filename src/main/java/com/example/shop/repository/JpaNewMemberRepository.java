package com.example.shop.repository;

import com.example.shop.domain.Member;
import com.example.shop.domain.NewMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaNewMemberRepository {

  // private final EntityManager entityManager;
  //
  // @Autowired
  // public JpaNewMemberRepository(EntityManager entityManager) {
  //   this.entityManager = entityManager;
  // }
  //
  // public String memberSave(NewMember member) {
  //   member.id = "4";
  //   member.username = "test";
  //   entityManager.persist(member);
  //   return member.id;
  // }

}
