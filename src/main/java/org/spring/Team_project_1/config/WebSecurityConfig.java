package org.spring.Team_project_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> myOAuth2Service() {
        return new MyOAuth2UserService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();


        http.authorizeRequests()
                .antMatchers("member/login", "member/join", "/sell/***", "/board/***").permitAll()
                .antMatchers("/css/***", "/js/***", "/images/***").permitAll()
                .antMatchers("/member/detail/***", "shop/shopList").authenticated()
                .antMatchers("/shop/cartList", "/shop/cartBuyCon").hasAnyRole("ADMIN","SELLER","MEMBER")
                .antMatchers("/shop/detail/***").hasAnyRole("ADMIN", "SELLER")
                .antMatchers("/admin/***").hasRole("ADMIN")
                .anyRequest().permitAll();


        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("email")
                .passwordParameter("userPw")
                .loginProcessingUrl("/member/login")
                .successHandler(customAuthenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
//                .defaultSuccessUrl("/index")
//                .failureForwardUrl("/login")
                .and()
                .oauth2Login()
                .loginPage("/member/login")
                .userInfoEndpoint()
                .userService(myOAuth2Service());

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/index");

        return http.build();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailHandler();
    }


}
