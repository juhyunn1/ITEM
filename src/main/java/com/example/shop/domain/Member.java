package com.example.shop.domain;

import lombok.Getter; // 컴파일할 때 getter 만들어준다
import lombok.Setter;

import javax.persistence.*;

@Entity // JPA가 클래스 객체를 RDB에 매핑한다
@Table
@Setter @Getter
public class Member {

  @Id // javax.persistence.Id를 선택해야
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  private Long id;

  @Column(length = 10, nullable = false) // DB에 저장할 때 null 체크
  private String name;

  @Column(name = "login_id", length = 45, nullable = false) // loginId >> login_id 자동으로 필드 이름 변경
  private String loginId;

  @Column(length = 45, nullable = false)
  private String password;

  public Member() {}

  public Member(String name, String loginId, String password) {
    this.name = name;
    this.loginId = loginId;
    this.password = password;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", loginId='" + loginId + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
