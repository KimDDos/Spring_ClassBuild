package com.myWeb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myWeb.www.security.CustomAuthMemberService;
import com.myWeb.www.security.LoginFailurHandler;
import com.myWeb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

// WebSecurityConfigurerAdapter 상속 받아
// WebConfig에 getRootConfigClasses 메서드에 SecurityConfig.class 를 추가로 등록 해줘야함
// configure(AuthenticationManagerBuilder/HttpSecurity) 이 두개만 있어도 구동되나 입맛대로 못 만들어서 
// 커스텀으로 만드는게 좋음

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// 비밀번호 암호화 객체 : PasswordEncoder 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// SuccessHandler  => 사용자 커스텀 생성
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler();  // 아직 생성 안 함
	}
	
	// FailurHandler  =>  사용자 커스텀 생성
	@Bean
	public AuthenticationFailureHandler authFailurHandler() {
		return new LoginFailurHandler();
	}
	
	// UserDetail  =>  사용자 커스텀 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService(null);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증 되는 객체 설정
		auth.userDetailsService(customUserService())
		.passwordEncoder(bcPasswordEncoder());
		
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 화면에서 설정되는 권한에 따른 주소 맵핑 설정
		
		// csrf 보안 조치를 하는것인데 더 알아볼 필요가 있음
		// 사용하려고 하면, 더 자세히 공부해서 넣어보는것도 좋음!
		http.csrf().disable();
		
		// 승인 요청
		// antMatchers() : 접근을 허용하는 값
		// permitAll() : 누구나 접근 가능하게하는 경로
		// authenticated() : 인증된 사용자만 가능
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN")
		.antMatchers("/","/board/list","/board/detail","/comment/**","resources/**","/member/register", "/member/login").permitAll()
		.anyRequest().authenticated();
		// antMatchers("/member/list","/결제요청", "/상품요청") 이렇게 쓰면 됨
		// antMatchers("/comment/**").permitAll() 이렇게 쓰면 comment 경로로 들어가는건 누구나 다!
		// 일일히 하나하나 다 권한을 줘야함
		// .anyRequest().authenticated(); >> 나머지 Request 등은 인증된 사용자만 가능
		
		// 커스텀 로그인 페이지를 구성
		// Controller에 주소요청 맵핑이 같이 있어야 함. (필수)  >> 없으면 로그인 경로를 인지하지 못 함
		// 없으면 Security 가 제공하는 로그인 페이지로 넘어가버림
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/member/login")
		.successHandler(authSuccessHandler())
		.failureHandler(authFailurHandler());
		
		// 로그아웃 페이지 (Security에서는 반드시 get 매핑이 아닌 post 이여야만 함)
		// method = "post"
		http.logout()
		.logoutUrl("/member/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
	}
	
	
	
}
