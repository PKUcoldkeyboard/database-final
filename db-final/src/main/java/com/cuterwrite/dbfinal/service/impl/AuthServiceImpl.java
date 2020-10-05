package com.cuterwrite.dbfinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.common.Const;
import com.cuterwrite.dbfinal.common.ResultCode;
import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.exception.CMSException;
import com.cuterwrite.dbfinal.service.AuthService;
import com.cuterwrite.dbfinal.util.JwtTokenUtil;

/**  
 * @author Pang S.Z.
 * @create 2020-09-27 17:15:39 
 */
@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public User register(User userToAdd) {
		final String username=userToAdd.getUsername();
		if(userDao.findByUsername(username)!=null) {
			throw new CMSException(ResultCode.PARAM_ERROR.getCode(),"用户名已经被注册");
		}
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		final String rawPassword=userToAdd.getPassword();
		userToAdd.setPassword(encoder.encode(rawPassword));
		userDao.insert(userToAdd);
		userDao.insertUserRole(userToAdd);
		return userToAdd;
	}

	@Override
	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken=new UsernamePasswordAuthenticationToken(username, password);
		
		final Authentication authentication=authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(username);
		final String token=jwtTokenUtil.generateToken(userDetails);
		return token;
	}

	@Override
	public String refreshToken(String token) {
		token=token.substring(Const.TOKEN_PREFIX.length());
		return jwtTokenUtil.refreshToken(token);
	}

}
