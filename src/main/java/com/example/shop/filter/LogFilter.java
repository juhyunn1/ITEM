package com.example.shop.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("LogFilter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request; // 다운캐스팅
    String requestURI = httpServletRequest.getRequestURI();
    String uuid = UUID.randomUUID().toString(); // 유효한 식별자 생성

    try {
      log.info("로그 필터 시작 requestURI : {}, uuid : {}", requestURI, uuid);
      chain.doFilter(request, response); // 다음 필터가 있으면 필터를 호출, 필터가 없으면 서블릿을 호출
    } catch (IOException e) {
      throw e;
    } finally { // 항상 수행
      log.info("로그 필터 종료 requestURI : {}, uuid : {}", requestURI, uuid);
    }
  }

  @Override
  public void destroy() {
    log.info("LogFilter destroy");
  }
}
