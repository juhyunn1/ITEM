package com.example.shop.filter;

import com.example.shop.controller.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginCheckFilter implements Filter {
  
  // 화이트리스트 정해두고 화이트리스트에 없는 곳에서 필터 적용
  private static final String[] whitelist = {"/", "/login", "/join", "/logout"};

  // @Override
  // public void init(FilterConfig filterConfig) throws ServletException { // 오버라이드 안해도 됨
  //   log.info("LoginCheckFilter init");
  // }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request; // 다운캐스팅
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    String requestURI = httpServletRequest.getRequestURI(); // 주소에서 path부분 가져온다
    String uuid = UUID.randomUUID().toString(); // 유효한 식별자 생성 << HTTP 요청을 구분

    try {
      log.info("로그인 인증 필터 시작 requestURI : {}, uuid : {}", requestURI, uuid);

      if(!PatternMatchUtils.simpleMatch(whitelist, requestURI)) { // 화이트리스트에 없으면
        HttpSession session = httpServletRequest.getSession();
        
        if(session == null || session.getAttribute(SessionConst.NAME) == null) { // 세션이 없거나 가져온 세션에 정보가 없으면
          log.info("미인증 사용자의 요청 {} ", requestURI);
          httpServletResponse.sendRedirect("/login?redirectURI="+requestURI); // 정보를 파라미티로 가진 주소로 이동
          return; // 미인증 사용자 나보내기
        }
      }

      chain.doFilter(request, response); // 화이트리스트에 있으면 다음 필터로, 다음 필터가 있으면 필터를 호출, 필터가 없으면 서블릿을 호출
    } catch (IOException e) {
      throw e;
    } finally { // 항상 수행
      log.info("로그인 인증 필터 종료 requestURI : {}, uuid : {}", requestURI, uuid);
    }
  }

  // @Override
  // public void destroy() { // 오버라이드 안해도 됨
  //   log.info("LoginCheckFilter destroy");
  // }
}
