package com.mjmj.book.springboot.web;

import com.mjmj.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    /**
     * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할때 앞경로와 뒤의 파일 확장가는 자동으로 지정된다.
     * 앞 경로는 src/main/resource/templates
     * 뒤 확장자는 .mustache
     * 이 컨트롤러는 index를 반환하므로
     * 최종적으로 src/main/resource/templates/index.mustache 를 반환하여 View Resolver가 처리한다.
     * 페이지에 관련된 컨트롤러는 전부 이곳에 작성한다.
     */

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

}
