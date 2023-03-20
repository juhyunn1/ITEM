package com.example.shop.domainDto;

import lombok.Data;

@Data
public class MemberResponseDto {

  private Long id;

  public MemberResponseDto(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "MemberResponseDTO{" +
        ", id='" + id + '\'' +
        '}';
  }
}
