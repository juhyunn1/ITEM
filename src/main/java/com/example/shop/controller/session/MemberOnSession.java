package com.example.shop.controller.session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberOnSession { // 세션 저장소에 들어갈 사용자 정보

  private Long id;
  private String loginId;
  private String name;
}
