package com.example.shop.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { // 핸들러(컨트롤러) 처리 전
    String requestURI = request.getRequestURI();
    String uuid = UUID.randomUUID().toString(); // 유효한 식별자 생성
    log.info("[Log-preHandle] requestURI: {}, uuid: {}, handler: {}", requestURI, uuid, handler);

    request.setAttribute("uuid", uuid); // uuid를 포함시켜서 넘긴다 << 세 단계에서 같은 uuid에 대한 처리하기 위해

    return true; // true면 핸들러(컨트롤러) 호출, false면 종료(afterCompletion으로)
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { // 핸들러 처리 후
    String uuid = request.getAttribute("uuid").toString();
    log.info("[Log-postHandle] uuid: {}, handler: {}, modelAndView: {}", uuid, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { // 모두 종료 후, 항상 실행
    String requestURI = request.getRequestURI();
    String uuid = (String) request.getAttribute("uuid");
    log.info("[Log-afterCompletion] requestURI: {}, uuid: {}, handler: {}", requestURI, uuid, handler);

    if(ex!= null) {
      log.error("[afterCompletion] error: ", ex);
    }
  }
}
