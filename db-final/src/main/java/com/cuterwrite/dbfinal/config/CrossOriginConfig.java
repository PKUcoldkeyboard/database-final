package com.cuterwrite.dbfinal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**  
 * 跨域请求配置
 * @author Pang S.Z.
 * @create 2020-10-04 15:43:33 
 */
@Configuration
public class CrossOriginConfig {

	@Bean
	public CorsFilter corsFilter(){
		CorsConfiguration config=new CorsConfiguration();
		//允许所有域名进行跨域调用
		config.addAllowedOrigin("*");
		//允许跨域发送cookie
		config.setAllowCredentials(true);
		//允许所有头部信息
		config.addAllowedHeader("*");
		//允许所有请求
		config.addAllowedMethod("*");
		//设置缓存时间,减少Options请求次数
		config.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
