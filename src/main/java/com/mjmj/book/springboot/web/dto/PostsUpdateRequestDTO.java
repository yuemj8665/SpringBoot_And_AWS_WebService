package com.mjmj.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsUpdateRequestDTO {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }


}