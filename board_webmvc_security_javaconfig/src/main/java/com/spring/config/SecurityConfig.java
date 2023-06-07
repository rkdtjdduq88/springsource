package com.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.spring.handler.CustomLoginSuccessHandler;
import com.spring.service.CustomUserDetailService;

// 5.7.X 버전부터 security 환경 설정 작성 방법이 변경되었음(지금 작업하는 것은 이전버전 작업방식)

@Configuration //얘는 환경설정 클래스야
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean // 스프링이 관리해주세요
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomLoginSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailService();
	}
	
	@Override // <security:authentication-manager> 내용
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService())
			.passwordEncoder(passwordEncoder());
	}
	
	@Override // <security:http> 내용
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
		
		http.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/login")
			.failureUrl("/member/login-error")
			.successHandler(loginSuccessHandler());
		
		http.logout()
			.logoutSuccessUrl("/");
		
		http.rememberMe()
			.tokenRepository(perTokenRepository())
			.tokenValiditySeconds(604800);
	}
	
	@Bean // remember-me 를 설정하기 위한 메소드를 스프링이 관리해줌
	public PersistentTokenRepository perTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
}
