package com.mjmj.book.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String picture;

    /**
     *  @Enumerated(EnumType.STRING)
     *  JPA로 데이터베이스를 저장 할 때 Enum 값이 어떤 형태로 저장될지 결정한다
     *  기본값 : int(숫자)
     *  숫자로 저장되면 데이터베이스르 확인할때 그 값이 무슨코드를 의미하는지 알 수가 없다.
     *  그래서 문자열 (EnumType.STRING)으로 저장되도록 선언한다.
     */
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;

    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
