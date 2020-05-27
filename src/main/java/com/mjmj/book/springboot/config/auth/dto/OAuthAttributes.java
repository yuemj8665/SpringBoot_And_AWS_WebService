package com.mjmj.book.springboot.config.auth.dto;

import com.mjmj.book.springboot.domain.user.Role;
import com.mjmj.book.springboot.domain.user.User;
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

    /**
     * @param registrationId
     * @param userNameAttributeName
     * @param attributes
     * @return ofGoogle(userNameAttributeName,attributes);
     *
     * OAuth2User 에서 반환하는 사용자 정보는 Map이기때문에 값 하나하나를 변환해야 한다.
     * 들어오는 값이 네이버에서 들어왔으면 네이버로, 그 외에는 구글로 로그인한다.
     */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        if("Naver".equals(registrationId)){
            return ofNaver("id",attributes);
        }
        return ofGoogle(userNameAttributeName,attributes);
    }

    /**
     * 구글 로그인 연동시에 사용하는 클래스
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * 네이버 로그인시에 사용하는 클래스
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String,Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * @return User
     *
     * User 엔티티를 생성한다.
     * OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할떄 생성한다.
     * 가입할때 기본 권한을 GUEST로 주기위해서 Role의 빌더값에는 게스트로 준다.
     * OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성한다.
     */
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
