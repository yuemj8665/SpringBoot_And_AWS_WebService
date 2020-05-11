package com.mjmj.book.springboot.web.dto;

import com.mjmj.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDTO(Posts entity){
        this.id =       entity.getId();
        this.title =    entity.getTitle();
        this.content =  entity.getContent();
        this.author =   entity.getAuthor();
    }
}
