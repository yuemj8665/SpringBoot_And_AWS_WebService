package com.mjmj.book.springboot.web;

import com.mjmj.book.springboot.service.posts.PostsService;
import com.mjmj.book.springboot.web.dto.PostsResponseDTO;
import com.mjmj.book.springboot.web.dto.PostsSaveRequestDTO;
import com.mjmj.book.springboot.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    /**
     * 이전 스프링에서는 Autowired를 사용했지만 권장하지 않는다.
     * 생성자로 bean객체를 받도록하면 동일한 결과를 얻을 수 있다.
     * 그 생성자는 @RequiredArgsConstructor가 대신 생성해주고, 그걸 이용한다.
     *
     * 생성자를 안쓰고 롬복 어노테이션을 사용한 이유는 해당클래스의 의존성 관계가 계속 변경될 경우
     * 생성자 코드를 계속해서 수정해야하는 번거로움이 있기 때문임.
     *
     * 롬복이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나 기존 컴포넌트를 제거하는 등의 상황이 발생해도
     * 생성자 코드는 전혀 손대지 않아도 된다.
     */
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDTO requestDTO){
        return postsService.save(requestDTO);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDTO requestDTO){
    return postsService.update(id, requestDTO);

    }
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
        public PostsResponseDTO indById(@PathVariable Long id){
        return postsService.findById(id);
    }

}
