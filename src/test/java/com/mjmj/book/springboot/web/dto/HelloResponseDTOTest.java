package com.mjmj.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDTOTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name="test";
        int amount=1000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name,1000);

        /**
         * AssertThat
         *  : assertj라는 테스트 검증라이브러리의 검증 메소드
         *  검증하고 싶은 대상을 메소드 인자로 받는다.
         *  메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용 할 수 있다.
         * Junit 과 비교하여 assertj의 장점
         *  : CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않는다.
         *      - Junit의 AssertThat을 쓰게되면 is()와 같이 CoreMatchers 라이브러리가 필요하다.
         *  : 자동완성이 확실하게 지원된다.
         *      - IDE에서는 CoreMatchers와 같은 Matcher 라이브러리 자동완성이 막힌다.
         */
        //then
        assertThat(dto.getName()).isEqualTo(name); // isEqualTo : 동등메소드. 값이 같으면 성공이다.
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
