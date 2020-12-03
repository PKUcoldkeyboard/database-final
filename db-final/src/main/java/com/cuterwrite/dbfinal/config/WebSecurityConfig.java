package com.cuterwrite.dbfinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * 
 * @author Pang S.Z.
 * @create 2020-09-27 17:08:38
 */
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cuterwrite.dbfinal.exception.RestAuthenticationEntryPoint;
import com.cuterwrite.dbfinal.exception.RestfulAccessDeniedHandler;
import com.cuterwrite.dbfinal.filter.JwtTokenFilter;
import com.cuterwrite.dbfinal.service.UserService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/**
 * Spring Security配置类
 * @author Pang S.Z.
 * @create 2020-10-04 14:20:42
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Bean
	public JwtTokenFilter authenticationTokenFilterBean() throws Exception{
		return new JwtTokenFilter();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity)throws Exception{
		//禁用csrf,取消session
		httpSecurity.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers(HttpMethod.POST,"/auth/**").permitAll()
			.antMatchers(HttpMethod.POST).authenticated()
			.antMatchers(HttpMethod.PUT).authenticated()
			.antMatchers(HttpMethod.DELETE).authenticated()
			.antMatchers(HttpMethod.GET).authenticated()
			.antMatchers("/test").hasAnyRole("USER","ADMIN");	
		//添加JWT filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		//禁用缓存
		httpSecurity.headers().cacheControl();
		//添加自定义未授权和未登录结果返回
		httpSecurity.exceptionHandling()
		.accessDeniedHandler(restfulAccessDeniedHandler)
		.authenticationEntryPoint(restAuthenticationEntryPoint);
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
