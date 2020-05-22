package com.mjmj.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param email
     * @return
     * 소셜 로그인으로 반환되는 값 중 email을 통해
     * 이미 생성된 사용자인지 처음가입하는 유저인지 판단하는 메소드
     */
    Optional<User> findByEmail(String email);
}
