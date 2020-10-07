package com.cuterwrite.dbfinal.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cuterwrite.dbfinal.common.Const;
import com.cuterwrite.dbfinal.util.JwtTokenUtil;

/**
 * 请求过滤器，拦截没有jwt的请求
 * @author Pang S.Z.
 * @create 2020-09-27 17:10:20
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader(Const.HEADER_STRING);
		if(authHeader!=null&&authHeader.startsWith(Const.TOKEN_PREFIX)) {
			final String authToken=authHeader.substring(Const.TOKEN_PREFIX.length());
			String username=jwtTokenUtil.getUsernameFromToken(authToken);
			if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
				if(jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
