package com.mjmj.book.springboot.config.auth.dto;

import com.mjmj.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * SessionUser에는 인증된 사용자 정보만 필요하다. 그 외에 필요한 정보는 없으니
 * name, email, picture 세 개만 필드로 선언한다.
 *
 * ++
 * 왜 User 클래스를 사용하지 않는가??
 * Fail to convert from type [java.lang.Object] to type[byte[]] for value '~~~' 에러가 발생하기 떄문
 * 1. 세션에 저장하기 위해 User클래스를 세션에 저장하려고 하니 User 클래스에 직렬화를 하지않아서 에러가 발생한다.
 * 2. 만약 User 클래스에 직렬화를 넣기에는 너무 많은걸 생각해야한다.
 * User class는 현재 Entity이다. Entity는 언제 다른 Entity와 엮일지 모른다.
 * 만약 다른 자식 엔티티를 갖게되면 직렬화 대상에 그 자식까지 포함되게 되므로 성능이슈, 부수효과가 발생할 가능성이 높다.
 * 그러느니 차라리 직렬화 기능을 가진 SessionDTO를 하나 따로 만드는게 더 효율적이다.
 *
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser (User user){
        this.name	 = user.getName();
        this.email	 = user.getEmail();
        this.picture = user.getPicture();
    }
}
