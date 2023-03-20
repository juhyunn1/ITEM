package com.example.shop.service.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

  @Autowired
  MessageSource ms;

  @Test
  void helloTest() {
    String hello = ms.getMessage("hello", null, null);
    assertThat(hello).isEqualTo("안녕");
  }

  @Test
  void helloTestWithArgs() {
    String hello = ms.getMessage("hello.name", new Object[]{"어흥이", "으릉이"}, null); // 배열을 생성해서 인자로
    assertThat(hello).isEqualTo("안녕 어흥이 으릉이");
  }

  @Test
  void helloTestEng() {
    String hello = ms.getMessage("hello", null, Locale.ENGLISH);// messages_en.properties에서 hello에 해당하는 값 가져온다
    assertThat(hello).isEqualTo("hello");
  }

  @Test
  void noCodeTest() {
    assertThatThrownBy(() -> ms.getMessage("hello2", null, null))
        .isInstanceOf(NoSuchMessageException.class);
  }

  @Test
  void noCodeDefaultTest() {
    String result = ms.getMessage("hello2", null, "기본 메시지입니다", null);
    assertThat(result).isEqualTo("기본 메시지입니다");
  }
}
