package com.mjmj.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target
 * : 이 어노테이션이 생성 될 위치를 지정한다.
 * : PARAMETER로 설정했으니 메소드의 파라미터로 선언된 객체에서만 사용 가능하다.
 * : 이 외에도 클래스 선언문에 쓸 수 있는 TYPE등이 있다.
 *
 * @interface
 * : 이 파일을 어노테이션 클래스로 지정한다.
 * : LoginUser 라는 이름을 가진 어노테이션이 생성되었다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
