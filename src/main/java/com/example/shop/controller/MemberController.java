package com.example.shop.controller;

import com.example.shop.domain.Member;
import com.example.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MemberController {

  MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/join")
  public String join(Model model) {
    model.addAttribute("member", new Member());
    return "member/join";
  }

  @PostMapping("/join")
  public String join(Member member, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("message", "회원가입 성공");
    memberService.join(member);
    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("member", new Member());
    return "member/login";
  }

  @PostMapping("/login")
  public String login(Member member, Model model, RedirectAttributes redirectAttributes) {
    if(memberService.login(member)) {
      return "redirect:/items";
    } else {
      redirectAttributes.addFlashAttribute("message", "로그인 실패");
      return "redirect:/";
    }

  }
}
