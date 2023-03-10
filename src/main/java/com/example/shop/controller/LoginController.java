package com.example.shop.controller;

import com.example.shop.controller.session.MemberOnSession;
import com.example.shop.controller.session.SessionConst;
import com.example.shop.domain.Member;
import com.example.shop.service.MemberService;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberService memberService;

  @GetMapping("/login")
  public String login() {
  // public String login(Model model) {
    // model.addAttribute("member", new Member());
    return "login";
  }

  // 쿠키 사용
  // @PostMapping("/login")
  // public String login(@ModelAttribute Member member, RedirectAttributes redirectAttributes, HttpServletResponse response) { // form에서 받아온 필드만 member에 매핑
  //   String loginId = member.getLoginId();
  //   Optional<Member> temp = memberService.findMemberByLoginId(loginId);// member 가 존재해야, member.password == 화면에서 입력한 password 랑 일치해야
  //   System.out.println("LoginController : " + member);
  //   System.out.println("LoginController : " + temp);
  //
  //   // 문제 상황 : temp empty 인 경우 [ ] 빈 리스트, NoSuchElementException 발생
  //   if(!temp.isEmpty() && temp.get().getPassword().equals(member.getPassword())) { // 성공
  //     Cookie cookie = new Cookie("MemberId", String.valueOf(temp.get().getId())); // 두 인자가 모두 String,
  //     // log.info("member : {}", member);
  //     response.addCookie(cookie); // response에 쿠키 추가
  //
  //     return "redirect:/"; // @GetMapping("/")으로 처리
  //   }
  //   else {
  //     redirectAttributes.addFlashAttribute("message", "로그인 실패");
  //     return "redirect:/login";
  //   }
  // }

  // @PostMapping("/logout")
  // public String logout(HttpServletResponse response) {
  //   Cookie cookie = new Cookie("MemberId", null);
  //   cookie.setMaxAge(0); // 유효기간을 0으로 설정해서 쿠키 삭제
  //   response.addCookie(cookie);
  //
  //   return "redirect:/";
  // }

  // 세션 사용
  @PostMapping("/login")
  public String login(
      @ModelAttribute Member member, // form에서 받아온 필드만 member에 매핑, form에 없는 필드는 null
      RedirectAttributes redirectAttributes,
      HttpServletRequest request,
      @RequestParam(defaultValue = "/") String redirectPath
  ) {
    String loginId = member.getLoginId();
    Optional<Member> temp = memberService.findMemberByLoginId(loginId);// member 가 존재해야, member.password == 화면에서 입력한 password 랑 일치해야
    System.out.println("LoginController : " + member);
    System.out.println("LoginController : " + temp);

    // false && false >> false  두번째 조건식은 pass
    // false && true >> false   두번째 조건식은 pass
    // true && false >> false   두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사
    // true && true >> true     두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사

    // 문제 상황 : temp empty 인 경우 [ ] 빈 리스트, NoSuchElementException 발생
    if(!temp.isEmpty() && temp.get().getPassword().equals(member.getPassword())) { // 성공
      // 세션 저장소에 넣을 memberOnSession 객체 생성, 세팅
      MemberOnSession memberOnSession = new MemberOnSession();
      memberOnSession.setId(temp.get().getId());
      memberOnSession.setLoginId(temp.get().getLoginId());
      memberOnSession.setName(temp.get().getName());

      HttpSession session = request.getSession(true); // 기존에 세션이 있으면 반환, 없으면 새로 만든다
      session.setAttribute(SessionConst.NAME, memberOnSession); // loginMember라는 이름으로 memberOnSession을 session에 추가

      return "redirect:" + redirectPath;
    }
    else {
      redirectAttributes.addFlashAttribute("message", "로그인 실패");
      return "redirect:/login"; // @GetMapping("/login")으로 처리
    }
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false); // 없으면 null 반환

    if(session != null) // 있으면
      session.invalidate(); // 삭제

    return "redirect:/";
  }
}
