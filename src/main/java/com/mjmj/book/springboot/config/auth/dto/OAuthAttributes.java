package com.mjmj.book.springboot.config.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes
            ,String nameAttributeKey
            ,String name
            ,String email
            ,String picture) {
        this.attributes			= attributes;
        this.nameAttributeKey   = nameAttributeKey;
        this.name               = name;
        this.email              = email;
        this.picture            = picture;
    }

}
