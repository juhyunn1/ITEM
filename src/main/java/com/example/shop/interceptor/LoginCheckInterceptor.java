package com.example.shop.interceptor;

import com.example.shop.controller.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

  // 로그인되어 있지 않으면 더 이상 진행하지 않고 종료 = 핸들러로 넘어가지 않는다 = preHandle만 있으면 됨
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestPath = request.getRequestURI();
    String uuid = UUID.randomUUID().toString();

    HttpSession session = request.getSession(false); // 없을 때 새로만들면 안됨
    if(session != null && session.getAttribute(SessionConst.NAME) != null) { // 해당 이름으로 세션이 있으면 = 로그인되어 있음
      log.info("[LoginCheck-preHandle] requestPath: {}, uuid: {}, session: {}", requestPath, uuid, session.getAttribute(SessionConst.NAME));
      return true; // 핸들러(컨트롤러)로 이동
    }
    log.error("[LoginCheck-preHandle] 미인증 사용자");
    response.sendRedirect("/login?requestPath="+requestPath); // 정보를 파라미티로 가진 주소로 이동, 로그인 화면으로 보내되 로그인하면 requetPath로 redirect
    return false; // 로그인되어 있지 않으면 로그인 화면으로 이동하고 종료
  }
}
