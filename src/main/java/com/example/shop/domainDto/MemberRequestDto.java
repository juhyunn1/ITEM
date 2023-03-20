package com.example.shop.domainDto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberRequestDto {

  @NotEmpty // 프런트 단에서 검사??, 필드가 모드 들어와야 한다
  private String name;

  @NotEmpty
  private String loginId;

  @NotEmpty
  private String password;

  @Override
  public String toString() {
    return "MemberRequestDTO{" +
        "name='" + name + '\'' +
        ", loginId='" + loginId + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
