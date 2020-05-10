package com.mjmj.book.springboot.service.posts;

import com.mjmj.book.springboot.domain.posts.Posts;
import com.mjmj.book.springboot.domain.posts.PostsRepository;
import com.mjmj.book.springboot.web.dto.PostsResponseDTO;
import com.mjmj.book.springboot.web.dto.PostsSaveRequestDTO;
import com.mjmj.book.springboot.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO){
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다! id = "+ id));
        posts.update(requestDTO.getTitle(), requestDTO.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDTO findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다! id ="+id));
        return new PostsResponseDTO(entity);
    }
}
