package com.mjmj.book.springboot.web.dto;

import com.mjmj.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity와 거의 유사한 클래스임에도 DTO를 추가로 만들었다.
 * 하지만 [Entity는 데이터베이스와 맞닿아있는 핵심 클래스이기때문에 Request/Response 클래스로 사용해서는 안된다].
 * Entity를 기준으로 테이블이 생성되고, 스키마가 변경되는데 단순 화면변경을 위해 Entity클래스를 변경하는건 너무 큰 변경이다.
 * 수많은 서비스 클래스나 비즈니스 로직들이 Entity 클래스를 기준으로 동작한다.
 * Entity클래스 변경으로 많은 클래스에 영향을 끼치지만, Request와 Response용 DTO는 View를 위한 클래스라 정말 자주변경이 필요하다.
 *
 * + View와 DB Layer의 역할 분리는 철저히 하자
 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDTO {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDTO(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
