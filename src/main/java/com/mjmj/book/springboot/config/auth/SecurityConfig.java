package com.mjmj.book.springboot.config.auth;

import com.mjmj.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable()  // h2-console 을 사용하기 위해 해당 옵션 비활성화
                .and()
                    // authorizeRequests : URL별 권한 관리를 설정하는 옵션의 시작점. authoriseRequest가 선언되어있어야 antMatchers를 사용가능하다.
                    .authorizeRequests()
                    // antMatchers : 권한 관리 대상을 지정하는 옵션. URL, HTTP 메소드별로 관리가 가능하다.
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    // 위의 지정된 URL들은 permitAll 옵션으로 전체열람권한을 주었다.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 위의 지정된 주소를 가진 API는 USER 권한을 가지고있는 사람만 열람할수있게 해놓았다.

                    // authenticated : 설정 값 외의 나머지 URL을 설정
                    .anyRequest().authenticated()
                // 위의 authenticated()를 추가하여 나머지 URL들은 모두 인증된 사용자들(로그인 한 사람들) 에게만 허용하게 한다.
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공시 URL 설정
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 설정
                        .userInfoEndpoint() // OAuth2 로그인 성공 시 가져올 설정들을 담당
                            .userService(customOAuth2UserService);
                            // 소셜 로그인 성공시 후속조치를 진행할 UserService 인터페이스 구현체를 등록한다.
                            // 리버스 서버(즉, 소셜서비스들)에게서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는걸 명시 할 수 있다.

    }
}
