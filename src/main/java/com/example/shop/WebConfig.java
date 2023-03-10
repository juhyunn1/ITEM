package com.example.shop;

import com.example.shop.filter.LogFilter;
import com.example.shop.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {
  
  @Bean // 있어야 필터가 등록됨
  public FilterRegistrationBean LogFilter() { // 필터 간의 순서, 적용 패턴 등록
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

    filterRegistrationBean.setFilter(new LogFilter()); // LogFilter를 등록
    filterRegistrationBean.setOrder(1); // 순서 1번으로 설정
    filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청이 들어오는 URI에 대해서 다 적용, 모든 사이트에 대해 요청 들어오면 로그 남긴다

    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean LoginCheckFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

    filterRegistrationBean.setFilter(new LoginCheckFilter()); // LoginCheckFilter를 등록
    filterRegistrationBean.setOrder(2); // 순서 2번으로 설정
    filterRegistrationBean.addUrlPatterns("/*"); // 내부적으로 화이트리스트 처리 되어있음

    return filterRegistrationBean;
  }
}
