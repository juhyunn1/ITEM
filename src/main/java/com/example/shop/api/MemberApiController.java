package com.example.shop.api;

import com.example.shop.domain.Member;
import com.example.shop.domainDto.MemberRequestDto;
import com.example.shop.domainDto.MemberRequestDtoForEdit;
import com.example.shop.domainDto.MemberResponseDto;
import com.example.shop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController // restful api 만들 수 있다, response body 안써도 됨
@RequiredArgsConstructor // 매개변수 있는 생성자 자동 주입
@RequestMapping("/api/v1/members")
public class MemberApiController {

  private final MemberService memberService;

  // @GetMapping
  public Member getMember() {
    return memberService.findMemberById(1L).get();
  }

  @PostMapping
  public MemberResponseDto addMember(@RequestBody @Valid MemberRequestDto memberRequestDTO) { // DTO에서 Validation과 관련된 어노테이션(@NotEmpty 등)과 함께 사용
    Member member = new Member(memberRequestDTO.getName(), memberRequestDTO.getLoginId(), memberRequestDTO.getPassword());
    Long joinedId = memberService.join(member);

    return new MemberResponseDto(joinedId);
  }


  // 미니프로젝트 참고
  // fetch(`.../...?userId={userId}`)와 같은 경우
  // @GetMapping
  public String inquiryMember1(@RequestParam String userId) {
    return userId;
    // 객체 넘기려면 id로 DTO 객체 찾아서 넘긴다
  }

  // fetch(`.../...?userId={userId}&name={name}`)와 같은 경우
  // @GetMapping
  public String inquiryMember2(@RequestParam Map<String, String> param) { // 파라미터로 데이터 전달하고 파라미터에 대한 정보가 없는 경우
    StringBuilder sb = new StringBuilder();
    param.entrySet().forEach( entry -> {
      sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
    });

    return sb.toString();
  }

  // @GetMapping
  public String inquiryMember3(MemberRequestDto memberRequestDTO) { // 파라미터로 데이터 전달하고 DTO 객체를 사용하는 경우, 필드명 같아야 매핑된다
    return memberRequestDTO.toString();
  }

  // fetch(`.../.../1`)와 같은 경우
  @GetMapping("/{id}")
  public String inquiryMember4(@PathVariable Long id) {
    Member member = memberService.findMemberById(id).get();
    return member.getPassword();
  }

  // @GetMapping
  public List<Member> listMember1() {
    return memberService.findMembers();
  }

  @Data
  @AllArgsConstructor
  static class Results<T> {
    private T result;
  }

  // @GetMapping
  public Results listMember2() {
    List<Member> members = memberService.findMembers();
    List<MemberResponseDto> memberDTOs = members.stream()
        .map( m -> new MemberResponseDto(m.getId())) // 각각의 Member 객체에서 id를 가져와서 MemberResponseDTO 객체 만들고
        .collect(Collectors.toList()); // MemberResponseDTO 객체 모아서 리스트로
    
    return new Results(memberDTOs);
  }

  // @PostMapping은 위에꺼랑 비슷하게

  @PutMapping("/{id}")
  public MemberResponseDto editMember2(@RequestBody @Valid MemberRequestDtoForEdit memberRequestDTOForEdit, @PathVariable Long id) {
    memberService.updateMember(memberRequestDTOForEdit);
    Member temp = memberService.findMemberByLoginId(memberRequestDTOForEdit.getLoginId()).get();
    System.out.println("가져온 Member: " + temp);

    return new MemberResponseDto(temp.getId());
  }

  @DeleteMapping("/{id}")
  public Long deleteMember(@PathVariable Long id) {
    return memberService.deleteMember(id);
  }
}
