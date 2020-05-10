package com.mjmj.book.springboot.domain.posts;

import com.mjmj.book.springboot.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 1. @Entity
 *  : 테이블과 링크될 클래스임을 나타낸다
 *  기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
 *      ex) SalesManager.java -> sales_manager table
 *
 * 2. @ID
 *  : 테이블의 PK
 *
 * 3. @GeneratedValue
 *  : PK의 생성 규칙을 나타낸다.
 *  스프링 부트 2.0에서는 GenerationType.IDENITY를 추가해야만 auto_increment 가 된다.
 *
 * 4. @Column
 *  : 테이블의 컬럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다.
 *  사용하는 이유는 기본값 외에 추가로 변경 할 옵션이 필요한 경우 사용한다.
 *  문자열의 경우 VARCHAR(255)가 디폴트이며, 사이즈를 늘리고싶거나 타입을 변경하고 싶을 때 등 사용한다.
 *
 * 5. @NoArgConstructor
 *  : 기본 생성자 자동 추가
 *  이 클래스에서는 public Post(){} 와 같은 효과
 *
 * 6. @Getter
 *  : 클래스 내 모든 필드에 Getter 메소드 추가
 *
 * 7. @Builder
 *  : 해당 클래스의 빌더패턴 클래스 생성
 *  생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함된다.
 *
 *  + 서비스 초기 구축 단계에서는 테이블설계(Entity)가 빈번하게 변경되는데,
 *  이때 롬복 어노테이션들은 코드 변경량을 최소화 시켜주기 때문에 적극적으로 사용한다.
 *  ++ Setter가 없는 이유는 해당 클래스의 인스턴스 값이 언제 어디서 변해야 할지 코드상으로 명확하게 구분 할 수 없어,
 *  차후 기능 변경시 정말 복잡해지기때문에, [Entity클래스에서는 절대 Setter를 만들지 않는다.]
 *  다만, 해당 필드의 값변경이 필요하다면 그 목적과 의도를 분명히 나타내는 메소드를 하나 추가해야한다.
 */

@Getter // 6
@NoArgsConstructor // 5
@Entity // 1
public class Posts extends BaseTimeEntity {

    @Id // 2
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3
    private Long id;

    @Column(length = 500, nullable = false) // 4
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 7
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
