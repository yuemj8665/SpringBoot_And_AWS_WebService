package com.mjmj.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 1. RunWith :
 *  테스트를 진행 할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
 *  여기서는 SPringRunner라는 스프링 실행지를 사용한다
 *  즉, 스프링 부트 테스트와 JUnit의 연결자 역할을 한다
 *  
 * 2. WebMvcTest : 
 *  여러 스프링 테스트 어노테이션 중 Web(spring MVC)에 집중 할 수 있는 어노테이션이다
 *  선언 할 경우 @Controller, ControllerAdvice 등을 사용 할 수 있다
 *  단, @Service, Component, Repository 등은 사용 할 수 없다.
 *  여기서는 컨트롤러만 사용하기에 선언한다.
 *  
 * 3. Autowired
 *  스프링이 관리하는 Bean을 주입받는다
 *  
 * 4. Private MonkMvc mvc
 *  Web api를 테스트 할 때 사용한다.
 *  스프링 MVC테스트의 시작점이다
 *  이 클래스를 통해 Http GET, POST등에 대한 API 테스트를 진행 할 수 있다.
 *  
 * 5.  mvc.perform(get("/hello"))
 *  MockMvc 를 통해 /hello 주소로 GET요청을 한다
 *  체이닝이 지원되어 아래와 같이 여러 검증을 이어서 선언 가능하다
 *  
 * 6. andExpect(status().isOk())
 *  mvc.perform의 결과를 검증
 *  HTTP Header의 Status를 검증
 *  우리가 흔히 알고있는 200,400,500 등의 상태를 검증
 *  여기서 OK, 즉 200인지아닌지 검증
 *  
 * 7. andExpect(content().string(hello));
 *  mvc.perform의 결과를 검증
 *  응답 본문의 내용을 검증
 *  Contoller에서 hello를 리턴하기때문에 이 값이 맞는지 검증
 */
@RunWith(SpringRunner.class)
@WebMvcTest //
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDTO가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                    .param("name",name)
                    .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
