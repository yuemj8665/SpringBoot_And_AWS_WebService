package com.mjmj.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    /**
     * After
     *  : JUnit에서 단위 테스트가 끌날때마다 수행하는 메소드를 지정한다.
     *  보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기위해 사용한다.
     *  여러 테스트가 동시에 시행되면 테스트용 데이터 베이스인 H2에 그대로 데이터가 남기 때문에
     *  다음테스트 실행 시 테스트에 실패할 수 있기 때문
     */
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        /**
         * postRepository.save
         *  : 테이블 posts에 insert/update쿼리를 실행한다.
         *  id값이 있다면 update, 없다면 insert 쿼리가 실행된다.
         */
        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("MJMJ")
                        .build());

        // when
        /**
         * postsRepository.findAll
         *  : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
         */
        List<Posts> postsList = postsRepository.findAll(); //

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTiemEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,5,10,0,0,0);
        postsRepository.save(
                Posts.builder()
                    .title("title")
                    .author("MJMJ")
                    .content("content")
                    .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>>>> createdDate -> "+posts.getCreatedTime());
        System.out.println(">>>>>>>>>>> modifiedDate -> "+posts.getModifiedDate());

        assertThat(posts.getCreatedTime().isAfter(now));
        assertThat(posts.getModifiedDate().isAfter(now));
    }




}
