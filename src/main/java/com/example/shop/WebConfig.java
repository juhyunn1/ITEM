package com.example.shop;

import com.example.shop.filter.LogFilter;
import com.example.shop.filter.LoginCheckFilter;
import com.example.shop.interceptor.LogInterceptor;
import com.example.shop.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  
  // @Bean // 있어야 필터가 등록됨
  public FilterRegistrationBean LogFilter() { // 필터 간의 순서, 적용 패턴 등록
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

    filterRegistrationBean.setFilter(new LogFilter()); // LogFilter를 등록
    filterRegistrationBean.setOrder(1); // 순서 1번으로 설정
    filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청이 들어오는 URI에 대해서 다 적용, 모든 사이트에 대해 요청 들어오면 로그 남긴다

    return filterRegistrationBean;
  }

  // @Bean
  public FilterRegistrationBean LoginCheckFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

    filterRegistrationBean.setFilter(new LoginCheckFilter()); // LoginCheckFilter를 등록
    filterRegistrationBean.setOrder(2); // 순서 2번으로 설정
    filterRegistrationBean.addUrlPatterns("/*"); // 내부적으로 화이트리스트 처리 되어있음

    return filterRegistrationBean;
  }

  // @Override
  // public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 등록, 인터셉터는 Spring Context 안에 있기 때문에 @Bean 불필요
  //   registry.addInterceptor(new LogInterceptor())
  //       .order(1) // 순서 1번으로 설정
  //       .addPathPatterns("/**") // 모든 path에 대해서 적용
  //       .excludePathPatterns("/", "/error", "/favicon.ico"); // 이건 제외
  //
  //   registry.addInterceptor(new LoginCheckInterceptor())
  //       .order(2) // 순서 2번으로 설정
  //       .addPathPatterns("/**")
  //       .excludePathPatterns("/", "/login", "/join", "/logout", "/error", "/favicon.ico");
  //
  // }
}
