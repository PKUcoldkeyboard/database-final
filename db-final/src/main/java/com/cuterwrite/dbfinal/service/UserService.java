package com.cuterwrite.dbfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.entity.User;

/**  
 * @author Pang S.Z.
 * @create 2020-09-27 17:44:37 
 */
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDao.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return user;
	}
	
}
