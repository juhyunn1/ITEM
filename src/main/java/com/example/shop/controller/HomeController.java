package com.example.shop.controller;

import com.example.shop.controller.session.MemberOnSession;
import com.example.shop.controller.session.SessionConst;
import com.example.shop.domain.Member;
import com.example.shop.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j // 로그 사용
@Controller
public class HomeController {

  MemberService memberService;

  public HomeController(MemberService memberService) {
    this.memberService = memberService;
  }

  // 세션 사용
  @GetMapping("/")
  public String home(HttpServletRequest request, Model model) {

    HttpSession session = request.getSession(false); // 기존에 세션이 있으면 반환, 없으면 null 반환
    
    if (session == null) // 로그인하지 않은 사람은
      return "home"; // home.html으로 이동
    else { // 로그인한 사람은
      MemberOnSession memberOnSession = (MemberOnSession)session.getAttribute(SessionConst.NAME); // loginMember라는 이름의 세션을 가져온다, Object를 반환하니깐 MemberOnSession으로 다운캐스팅
      model.addAttribute("member", memberOnSession);
      return "userHome"; // useHome.html으로 이동
    }
  }

  // 쿠키 사용
  // @GetMapping("/")
  // public String home(@CookieValue(value = "MemberId", required = false) Long memberId, Model model) { // MemberId라는 이름으로 들어온 쿠키값
  //   log.info("memberId={}", memberId);
  //   if (memberId == null) // 로그인하지 않은 사람은
  //     return "home"; // home.html으로 이동
  //   else { // 로그인한 사람은
  //     Member member = memberService.findMemberById(memberId).get();
  //     model.addAttribute("member", member);
  //     return "userHome"; // useHome.html으로 이동
  //   }
  // }

  // @GetMapping("/")
  // public String home() {
  //     return "home"; // home.html으로 이동
  // }
}
