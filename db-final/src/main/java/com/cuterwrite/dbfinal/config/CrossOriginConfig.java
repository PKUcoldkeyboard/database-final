package com.cuterwrite.dbfinal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**  
 * 跨域请求配置
 * @author Pang S.Z.
 * @create 2020-10-04 15:43:33 
 */
@Configuration
public class CrossOriginConfig {
	@Value("${vue.uri}")
	private String allowedOrigins;
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			//重写跨域请求处理接口
			public void addCorsMappings(CorsRegistry registry) {
				//添加映射路径
				registry.addMapping("/**")
				.allowedOrigins(allowedOrigins) //请求地址
				.allowCredentials(true) //发送Cookie
				.allowedMethods("GET","POST","PUT","DELETE") //请求方式
				.allowedHeaders("*");
			}
		};
	}
}
