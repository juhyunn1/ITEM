package com.example.shop.domainDto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberRequestDtoForEdit {

  @NotEmpty
  private String loginId;

  @NotEmpty
  private String password;

  @Override
  public String toString() {
    return "MemberRequestDTO{" +
        ", loginId='" + loginId + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
